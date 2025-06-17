package com.crawler;

import java.util.*;
import java.util.concurrent.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class WebCrawler {
    private final Set<String> visitedUrls;
    private final Queue<String> urlQueue;
    private final int maxPages;
    private final int politenessDelay; // in milliseconds
    private final RobotsTxtParser robotsTxtParser;
    private final ExecutorService executorService;

    public WebCrawler(int maxPages, int politenessDelay) {
        this.visitedUrls = Collections.synchronizedSet(new HashSet<>());
        this.urlQueue = new ConcurrentLinkedQueue<>();
        this.maxPages = maxPages;
        this.politenessDelay = politenessDelay;
        this.robotsTxtParser = new RobotsTxtParser();
        this.executorService = Executors.newFixedThreadPool(5); // Using 5 threads
    }

    public void crawl(List<String> seedUrls) {
        // Add seed URLs to the queue
        urlQueue.addAll(seedUrls);

        while (!urlQueue.isEmpty() && visitedUrls.size() < maxPages) {
            String currentUrl = urlQueue.poll();
            
            if (currentUrl == null || visitedUrls.contains(currentUrl)) {
                continue;
            }

            try {
                // Check robots.txt before crawling
                if (!robotsTxtParser.isAllowed(currentUrl)) {
                    Logger.warn("Skipping " + currentUrl + " (disallowed by robots.txt)");
                    continue;
                }

                // Respect politeness delay
                Thread.sleep(politenessDelay);

                // Process the URL
                processUrl(currentUrl);
                
                // Mark as visited
                visitedUrls.add(currentUrl);
                
                Logger.info("Crawled: " + currentUrl + " (Total: " + visitedUrls.size() + ")");

            } catch (Exception e) {
                Logger.error("Error crawling " + currentUrl, e);
            }
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logger.error("Crawler interrupted while waiting for termination", e);
        }
    }

    private void processUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "JavaWebCrawler/1.0");

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
                if (!visitedUrls.contains(newUrl)) {
                    urlQueue.add(newUrl);
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            Logger.error("Please provide at least one seed URL");
            return;
        }

        List<String> seedUrls = Arrays.asList(args);
        WebCrawler crawler = new WebCrawler(100, 1000); // Crawl 100 pages with 1 second delay
        crawler.crawl(seedUrls);
    }
} 