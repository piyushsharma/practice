package com.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;


public class LogAnalyzer {


    private static String[][] logs1 = {
        {"58523", "user_1", "resource_1"},
        {"62314", "user_2", "resource_2"},
        {"54001", "user_1", "resource_3"},
        {"200", "user_6", "resource_5"},
        {"215", "user_6", "resource_4"},
        {"54060", "user_2", "resource_3"},
        {"53760", "user_3", "resource_3"},
        {"58522", "user_22", "resource_1"},
        {"53651", "user_5", "resource_3"},
        {"2", "user_6", "resource_1"},
        {"100", "user_6", "resource_6"},
        {"400", "user_7", "resource_2"},
        {"100", "user_8", "resource_6"},
        {"54359", "user_1", "resource_3"},
    };

    private static String[][] logs2 = {
        {"300", "user_1", "resource_3"},
        {"599", "user_1", "resource_3"},
        {"900", "user_1", "resource_3"},
        {"1199", "user_1", "resource_3"},
        {"1200", "user_1", "resource_3"},
        {"1201", "user_1", "resource_3"},
        {"1202", "user_1", "resource_3"}
    };
  
    /**
     * Analyzes logs and returns the resource used most frequently within a rolling time window.
     * 
     * @param logs Array of log entries, each containing [timestamp, user, resource]
     * @param timeWindow Size of the rolling time window
     * @return The resource used most frequently within the window
     */
    private String highestUsageResourceUsage(String[][] logs, int timeWindow) {
        Map<String, Integer> resourceUsage = new HashMap<>();
        String mostUsedResource = null;
        int maxUsage = 0;

        // Sort logs by timestamp to process them in chronological order
        Arrays.sort(logs, (a, b) -> Integer.compare(Integer.parseInt(a[0]), Integer.parseInt(b[0])));

        // Use a sliding window approach
        int startIndex = 0;
        for (int i = 0; i < logs.length; i++) {
            String[] currentLog = logs[i];
            int currentTime = Integer.parseInt(currentLog[0]);
            String currentResource = currentLog[2];

            // Remove logs outside the current window
            while (startIndex < i && Integer.parseInt(logs[startIndex][0]) < currentTime - timeWindow) {
                String oldResource = logs[startIndex][2];
                resourceUsage.put(oldResource, resourceUsage.get(oldResource) - 1);
                if (resourceUsage.get(oldResource) == 0) {
                    resourceUsage.remove(oldResource);
                }
                startIndex++;
            }

            // Add current log to the window
            resourceUsage.put(currentResource, resourceUsage.getOrDefault(currentResource, 0) + 1);

            // Update most used resource if needed
            for (Map.Entry<String, Integer> entry : resourceUsage.entrySet()) {
                if (entry.getValue() > maxUsage) {
                    maxUsage = entry.getValue();
                    mostUsedResource = entry.getKey();
                }
            }
        }

        return mostUsedResource;
    }

    public static void main(String[] args) {
        LogAnalyzer analyzer = new LogAnalyzer();
        System.out.println("Most used resource in logs1: " + analyzer.highestUsageResourceUsage(logs1, 300));
        System.out.println("Most used resource in logs2: " + analyzer.highestUsageResourceUsage(logs2, 300));
    }


}
