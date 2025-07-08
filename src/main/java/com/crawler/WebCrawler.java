package com.crawler;

import java.util.*;
import java.util.concurrent.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class WebCrawler {
    private final Set<String> visitedUrls;
    private final Queue<String> urlQueue;
    private final int maxPages;
    private final int rateLimitPerSecond;
    private final RobotsTxtParser robotsTxtParser;
    private final ExecutorService executorService;
    private final RateLimiter rateLimiter;
    private final AtomicInteger activeTasks;
    private volatile boolean shutdownRequested;

    public WebCrawler(int maxPages, int rateLimitPerSecond) {
        this.visitedUrls = Collections.synchronizedSet(new HashSet<>());
        this.urlQueue = new ConcurrentLinkedQueue<>();
        this.maxPages = maxPages;
        this.rateLimitPerSecond = rateLimitPerSecond;
        this.robotsTxtParser = new RobotsTxtParser();
        this.executorService = Executors.newCachedThreadPool();
        this.rateLimiter = new RateLimiter(rateLimitPerSecond);
        this.activeTasks = new AtomicInteger(0);
        this.shutdownRequested = false;
    }

    public void crawl(List<String> seedUrls) {
        // Add seed URLs to the queue
        urlQueue.addAll(seedUrls);

        // Start the URL processing loop in a separate thread
        Thread urlProcessor = new Thread(this::processUrlQueue);
        urlProcessor.start();

        // Wait for completion or shutdown
        try {
            urlProcessor.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logger.error("Crawler interrupted", e);
        }

        // Shutdown executor service
        shutdownExecutorService();
    }

    private void processUrlQueue() {
        while (!shutdownRequested && !urlQueue.isEmpty() && visitedUrls.size() < maxPages) {
            String currentUrl = urlQueue.poll();
            
            if (currentUrl == null) {
                continue;
            }

            // Check if already visited (thread-safe check)
            if (visitedUrls.contains(currentUrl)) {
                continue;
            }

            // Check robots.txt before crawling
            if (!robotsTxtParser.isAllowed(currentUrl)) {
                Logger.warn("Skipping " + currentUrl + " (disallowed by robots.txt)");
                continue;
            }

            // Wait for rate limiter permission
            rateLimiter.acquire();

            // Submit task to executor service
            executorService.submit(() -> processUrlTask(currentUrl));
        }

        // Wait for all active tasks to complete
        waitForActiveTasks();
    }

    private void processUrlTask(String urlString) {
        activeTasks.incrementAndGet();
        
        try {
            // Double-check visited status after acquiring task
            if (visitedUrls.contains(urlString)) {
                return;
            }

            // Mark as visited early to prevent duplicate processing
            visitedUrls.add(urlString);

            // Process the URL
            processUrl(urlString);
            
            Logger.info("Crawled: " + urlString + " (Total: " + visitedUrls.size() + ")");

        } catch (Exception e) {
            Logger.error("Error crawling " + urlString, e);
        } finally {
            activeTasks.decrementAndGet();
        }
    }

    private void processUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "JavaWebCrawler/1.0");
        connection.setConnectTimeout(10000); // 10 seconds
        connection.setReadTimeout(10000);    // 10 seconds

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }

            // Extract and add new URLs to the queue
            List<String> newUrls = UrlExtractor.extractUrls(content.toString(), urlString);
            for (String newUrl : newUrls) {
                if (!visitedUrls.contains(newUrl) && visitedUrls.size() < maxPages) {
                    urlQueue.add(newUrl);
                }
            }
        }
    }

    private void waitForActiveTasks() {
        while (activeTasks.get() > 0) {
            try {
                Thread.sleep(100); // Wait 100ms before checking again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void shutdownExecutorService() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                    Logger.error("Executor service did not terminate");
                }
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
            Logger.error("Crawler interrupted while waiting for termination", e);
        }
    }

    public void shutdown() {
        shutdownRequested = true;
        rateLimiter.shutdown();
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            Logger.error("Please provide at least one seed URL");
            return;
        }

        List<String> seedUrls = Arrays.asList(args);
        // Crawl 100 pages with rate limit of 5 requests per second
        WebCrawler crawler = new WebCrawler(100, 5);
        crawler.crawl(seedUrls);
    }
} 