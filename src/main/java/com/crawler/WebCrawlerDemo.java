package com.crawler;

import java.util.Arrays;
import java.util.List;

/**
 * Demo class to demonstrate the parallel web crawler with rate limiting.
 */
public class WebCrawlerDemo {
    
    public static void main(String[] args) {
        // Example seed URLs (replace with actual URLs for testing)
        List<String> seedUrls = Arrays.asList(
            "https://example.com",
            "https://httpbin.org/delay/1",
            "https://httpbin.org/delay/2"
        );
        
        // Create crawler with rate limit of 3 requests per second
        // This means it will process at most 3 URLs simultaneously
        WebCrawler crawler = new WebCrawler(10, 3);
        
        System.out.println("Starting parallel web crawler...");
        System.out.println("Rate limit: 3 requests per second");
        System.out.println("Max pages: 10");
        System.out.println("Seed URLs: " + seedUrls);
        System.out.println("----------------------------------------");
        
        long startTime = System.currentTimeMillis();
        
        try {
            crawler.crawl(seedUrls);
        } catch (Exception e) {
            Logger.error("Crawler failed", e);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("----------------------------------------");
        System.out.println("Crawling completed in " + duration + "ms");
        System.out.println("Average rate: " + String.format("%.2f", 1000.0 / duration * 10) + " requests per second");
    }
} 