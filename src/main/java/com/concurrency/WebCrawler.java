package com.concurrency;

import java.util.*;
import java.util.concurrent.*;

/**
 *
 * Given a URL startUrl and an interface HtmlParser, implement a Multi-threaded web crawler
 * to crawl all links that are under the same hostname as startUrl.
 *
 * Return all URLs obtained by your web crawler in any order.
 *
 * Your crawler should:
 *
 * Start from the page: startUrl
 * Call HtmlParser.getUrls(url) to get all URLs from a webpage of a given URL.
 * Do not crawl the same link twice.
 * Explore only the links that are under the same hostname as startUrl.
 *
 * As shown in the example URL above, the hostname is example.org. For simplicity's sake,
 * you may assume all URLs use HTTP protocol without any port specified.
 * For example, the URLs http://leetcode.com/problems and http://leetcode.com/contest are under the same hostname,
 * while URLs http://example.org/test and http://example.com/abc are not under the same hostname.
 *
 * The HtmlParser interface is defined as such:
 *
 * interface HtmlParser {
 *   // Return a list of all urls from a webpage of given url.
 *   // This is a blocking call, that means it will do HTTP request and return when this request is finished.
 *   public List<String> getUrls(String url);
 * }
 * Note that getUrls(String url) simulates performing an HTTP request.
 * You can treat it as a blocking function call that waits for an HTTP request to finish.
 * It is guaranteed that getUrls(String url) will return the URLs within 15ms.
 * Single-threaded solutions will exceed the time limit so, can your multi-threaded web crawler do better?
 *
 * Below are two examples explaining the functionality of the problem. For custom testing purposes,
 * you'll have three variables urls, edges and startUrl.
 * Notice that you will only have access to startUrl in your code,
 * while urls and edges are not directly accessible to you in code.
 *
 *
 *
 * Example 1:
 * Input:
 * urls = [
 *   "http://news.yahoo.com",
 *   "http://news.yahoo.com/news",
 *   "http://news.yahoo.com/news/topics/",
 *   "http://news.google.com",
 *   "http://news.yahoo.com/us"
 * ]
 * edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
 * startUrl = "http://news.yahoo.com/news/topics/"
 * Output: [
 *   "http://news.yahoo.com",
 *   "http://news.yahoo.com/news",
 *   "http://news.yahoo.com/news/topics/",
 *   "http://news.yahoo.com/us"
 * ]
 */

public class WebCrawler {

    interface HtmlParser {
        // Return a list of all urls from a webpage of given url.
        // This is a blocking call, that means it will do HTTP request and return when this request is finished.
        public List<String> getUrls(String url);
    }

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {

        String hostName = getHostName(startUrl);

        List<String> res = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        Deque<Future> tasks = new ArrayDeque<>();

        queue.offer(startUrl);

        // Create a thread pool of 4 threads to perform I/O operations.
        ExecutorService executor = Executors.newFixedThreadPool(4, r -> {
            Thread t = new Thread(r);
            // Leetcode doesn't allow executor.shutdown().
            // Use daemon threads so the program can exit.
            t.setDaemon(true);
            return t;
        });

        while (true) {
            String url = queue.poll();
            if (url != null) {
                if (getHostName(url).equals(hostName) && !visited.contains(url)) {
                    res.add(url);
                    visited.add(url);
                    // Use a thread in thread pool to fetch new URLs and put them into the queue.
                    tasks.add(executor.submit(() -> {
                        List<String> newUrls = htmlParser.getUrls(url);
                        for (String newUrl : newUrls) {
                            queue.offer(newUrl);
                        }
                    }));
                }
            } else {
                if (!tasks.isEmpty()) {
                    // Wait for the next task to complete, which may supply new URLs into the queue.
                    Future nextTask = tasks.poll();
                    try {
                        nextTask.get();
                    } catch (InterruptedException | ExecutionException e) {}
                } else {
                    // Exit when all tasks are completed.
                    break;
                }
            }
        }
        return res;
    }

    private String getHostName(String url) {
        url = url.substring(7);
        String[] parts = url.split("/");
        return parts[0];
    }
}
