package com.crawler;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlExtractor {
    private static final Pattern HREF_PATTERN = Pattern.compile(
        "href=[\"']([^\"']+)[\"']",
        Pattern.CASE_INSENSITIVE
    );

    public static List<String> extractUrls(String htmlContent, String baseUrl) {
        List<String> urls = new ArrayList<>();
        Matcher matcher = HREF_PATTERN.matcher(htmlContent);

        while (matcher.find()) {
            String url = matcher.group(1);
            try {
                String absoluteUrl = convertToAbsoluteUrl(url, baseUrl);
                if (isValidUrl(absoluteUrl)) {
                    urls.add(absoluteUrl);
                }
            } catch (MalformedURLException e) {
                // Skip invalid URLs
            }
        }

        return urls;
    }

    private static String convertToAbsoluteUrl(String relativeUrl, String baseUrl) throws MalformedURLException {
        // Handle protocol-relative URLs
        if (relativeUrl.startsWith("//")) {
            return "https:" + relativeUrl;
        }

        // Handle relative URLs
        if (!relativeUrl.startsWith("http://") && !relativeUrl.startsWith("https://")) {
            URL base = new URL(baseUrl);
            return new URL(base, relativeUrl).toString();
        }

        return relativeUrl;
    }

    private static boolean isValidUrl(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
} 