package com.crawler;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class RobotsTxtParser {
    private final Map<String, Set<String>> disallowedPaths;
    private final Map<String, Integer> crawlDelays;
    private static final String USER_AGENT = "JavaWebCrawler/1.0";

    public RobotsTxtParser() {
        this.disallowedPaths = new HashMap<>();
        this.crawlDelays = new HashMap<>();
    }

    public boolean isAllowed(String urlString) {
        try {
            URL url = new URL(urlString);
            String host = url.getHost();
            String path = url.getPath();

            // Check if we need to fetch robots.txt
            if (!disallowedPaths.containsKey(host)) {
                fetchRobotsTxt(host);
            }

            // Check if the path is disallowed
            Set<String> disallowed = disallowedPaths.get(host);
            if (disallowed != null) {
                for (String disallowedPath : disallowed) {
                    if (path.startsWith(disallowedPath)) {
                        return false;
                    }
                }
            }

            return true;
        } catch (Exception e) {
            Logger.error("Error checking robots.txt for " + urlString, e);
            return true; // Default to allowing if there's an error
        }
    }

    private void fetchRobotsTxt(String host) {
        try {
            URL robotsUrl = new URL("https://" + host + "/robots.txt");
            HttpURLConnection connection = (HttpURLConnection) robotsUrl.openConnection();
            connection.setRequestProperty("User-Agent", USER_AGENT);

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                
                Set<String> disallowed = new HashSet<>();
                int crawlDelay = 0;
                boolean isRelevantSection = false;

                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.toLowerCase().trim();
                    
                    if (line.startsWith("user-agent:")) {
                        String userAgent = line.substring(11).trim();
                        isRelevantSection = userAgent.equals("*") || userAgent.equals(USER_AGENT.toLowerCase());
                    } else if (isRelevantSection) {
                        if (line.startsWith("disallow:")) {
                            String path = line.substring(9).trim();
                            if (!path.isEmpty()) {
                                disallowed.add(path);
                            }
                        } else if (line.startsWith("crawl-delay:")) {
                            try {
                                crawlDelay = Integer.parseInt(line.substring(12).trim());
                            } catch (NumberFormatException e) {
                                // Ignore invalid crawl delay
                                Logger.warn("Invalid crawl delay value in robots.txt for " + host);
                            }
                        }
                    }
                }

                disallowedPaths.put(host, disallowed);
                if (crawlDelay > 0) {
                    crawlDelays.put(host, crawlDelay);
                }
            }
        } catch (IOException e) {
            Logger.error("Error fetching robots.txt for " + host, e);
        }
    }

    public int getCrawlDelay(String host) {
        return crawlDelays.getOrDefault(host, 0);
    }
} 