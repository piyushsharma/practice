package com.dsandalgos.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Comprehensive Regex Examples for common use cases
 * Reference this class whenever you need regex patterns for various scenarios
 */
public class RegexExamples {
    
    public static void main(String[] args) {
        RegexExamples examples = new RegexExamples();
        
        System.out.println("=== EMAIL VALIDATION ===");
        examples.demonstrateEmailValidation();
        
        System.out.println("\n=== LOG PARSING ===");
        examples.demonstrateLogParsing();
        
        System.out.println("\n=== NUMBER EXTRACTION ===");
        examples.demonstrateNumberExtraction();
        
        System.out.println("\n=== TIME/DATE PARSING ===");
        examples.demonstrateTimeDateParsing();
        
        System.out.println("\n=== URL PARSING ===");
        examples.demonstrateUrlParsing();
        
        System.out.println("\n=== PHONE NUMBER VALIDATION ===");
        examples.demonstratePhoneValidation();
        
        System.out.println("\n=== STRING MANIPULATION ===");
        examples.demonstrateStringManipulation();
        
        System.out.println("\n=== VALIDATION PATTERNS ===");
        examples.demonstrateValidationPatterns();
    }
    
    // ==================== EMAIL VALIDATION ====================
    
    public void demonstrateEmailValidation() {
        String[] emails = {
            "user@example.com",
            "user.name@domain.co.uk",
            "user+tag@example.org",
            "invalid.email",
            "user@.com",
            "@example.com"
        };
        
        // Basic email pattern
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        
        for (String email : emails) {
            boolean isValid = emailPattern.matcher(email).matches();
            System.out.println(email + " -> " + isValid);
        }
    }
    
    // ==================== LOG PARSING ====================
    
    public void demonstrateLogParsing() {
        String logLine = "2024-01-15 14:30:25 [INFO] User login successful - userId=12345, ip=192.168.1.100";
        
        // Parse timestamp, level, message, and extract key-value pairs
        Pattern logPattern = Pattern.compile(
            "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}) \\[(\\w+)\\] (.+?)(?: - (.+))?"
        );
        
        Matcher matcher = logPattern.matcher(logLine);
        if (matcher.find()) {
            System.out.println("Timestamp: " + matcher.group(1));
            System.out.println("Level: " + matcher.group(2));
            System.out.println("Message: " + matcher.group(3));
            if (matcher.group(4) != null) {
                System.out.println("Details: " + matcher.group(4));
                parseKeyValuePairs(matcher.group(4));
            }
        }
    }
    
    private void parseKeyValuePairs(String details) {
        Pattern kvPattern = Pattern.compile("(\\w+)=([^,]+)");
        Matcher matcher = kvPattern.matcher(details);
        
        Map<String, String> pairs = new HashMap<>();
        while (matcher.find()) {
            pairs.put(matcher.group(1), matcher.group(2));
        }
        System.out.println("Key-Value pairs: " + pairs);
    }
    
    // ==================== NUMBER EXTRACTION ====================
    
    public void demonstrateNumberExtraction() {
        String text = "The price is $123.45, quantity: 42, temperature: -5.2°C, phone: +1-555-123-4567";
        
        // Extract integers
        Pattern intPattern = Pattern.compile("\\b\\d+\\b");
        extractMatches(text, intPattern, "Integers");
        
        // Extract decimals
        Pattern decimalPattern = Pattern.compile("\\b\\d+\\.\\d+\\b");
        extractMatches(text, decimalPattern, "Decimals");
        
        // Extract currency amounts
        Pattern currencyPattern = Pattern.compile("\\$\\d+(?:\\.\\d{2})?");
        extractMatches(text, currencyPattern, "Currency");
        
        // Extract phone numbers
        Pattern phonePattern = Pattern.compile("\\+?\\d{1,3}[-.]?\\d{3}[-.]?\\d{3}[-.]?\\d{4}");
        extractMatches(text, phonePattern, "Phone numbers");
        
        // Extract negative numbers
        Pattern negativePattern = Pattern.compile("-\\d+(?:\\.\\d+)?");
        extractMatches(text, negativePattern, "Negative numbers");
    }
    
    private void extractMatches(String text, Pattern pattern, String description) {
        Matcher matcher = pattern.matcher(text);
        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        System.out.println(description + ": " + matches);
    }
    
    // ==================== TIME/DATE PARSING ====================
    
    public void demonstrateTimeDateParsing() {
        String[] timeStrings = {
            "14:30:25",
            "2:30 PM",
            "2024-01-15",
            "15/01/2024",
            "Jan 15, 2024",
            "2024-01-15T14:30:25Z"
        };
        
        // 24-hour time format
        Pattern time24Pattern = Pattern.compile("(\\d{1,2}):(\\d{2}):(\\d{2})");
        
        // 12-hour time format
        Pattern time12Pattern = Pattern.compile("(\\d{1,2}):(\\d{2})\\s*(AM|PM)", Pattern.CASE_INSENSITIVE);
        
        // ISO date format
        Pattern isoDatePattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
        
        // European date format
        Pattern euDatePattern = Pattern.compile("(\\d{1,2})/(\\d{1,2})/(\\d{4})");
        
        for (String timeStr : timeStrings) {
            System.out.println("Parsing: " + timeStr);
            
            Matcher time24Matcher = time24Pattern.matcher(timeStr);
            if (time24Matcher.find()) {
                System.out.println("  24h time: " + time24Matcher.group(1) + "h " + 
                                 time24Matcher.group(2) + "m " + time24Matcher.group(3) + "s");
            }
            
            Matcher time12Matcher = time12Pattern.matcher(timeStr);
            if (time12Matcher.find()) {
                System.out.println("  12h time: " + time12Matcher.group(1) + ":" + 
                                 time12Matcher.group(2) + " " + time12Matcher.group(3));
            }
            
            Matcher isoMatcher = isoDatePattern.matcher(timeStr);
            if (isoMatcher.find()) {
                System.out.println("  ISO date: Year=" + isoMatcher.group(1) + 
                                 ", Month=" + isoMatcher.group(2) + 
                                 ", Day=" + isoMatcher.group(3));
            }
            
            Matcher euMatcher = euDatePattern.matcher(timeStr);
            if (euMatcher.find()) {
                System.out.println("  EU date: Day=" + euMatcher.group(1) + 
                                 ", Month=" + euMatcher.group(2) + 
                                 ", Year=" + euMatcher.group(3));
            }
        }
    }
    
    // ==================== URL PARSING ====================
    
    public void demonstrateUrlParsing() {
        String url = "https://www.example.com:8080/path/to/resource?param1=value1&param2=value2#fragment";
        
        Pattern urlPattern = Pattern.compile(
            "^(https?)://([^:/]+)(?::(\\d+))?([^?#]*)(?:\\?([^#]*))?(?:#(.*))?$"
        );
        
        Matcher matcher = urlPattern.matcher(url);
        if (matcher.find()) {
            System.out.println("Protocol: " + matcher.group(1));
            System.out.println("Host: " + matcher.group(2));
            System.out.println("Port: " + (matcher.group(3) != null ? matcher.group(3) : "default"));
            System.out.println("Path: " + matcher.group(4));
            System.out.println("Query: " + (matcher.group(5) != null ? matcher.group(5) : "none"));
            System.out.println("Fragment: " + (matcher.group(6) != null ? matcher.group(6) : "none"));
        }
    }
    
    // ==================== PHONE NUMBER VALIDATION ====================
    
    public void demonstratePhoneValidation() {
        String[] phones = {
            "+1-555-123-4567",
            "555-123-4567",
            "(555) 123-4567",
            "555.123.4567",
            "5551234567",
            "123-456-7890",
            "invalid-phone"
        };
        
        // US phone number pattern (various formats)
        Pattern phonePattern = Pattern.compile(
            "^(\\+?1[-.]?)?\\(?([0-9]{3})\\)?[-.]?([0-9]{3})[-.]?([0-9]{4})$"
        );
        
        for (String phone : phones) {
            Matcher matcher = phonePattern.matcher(phone);
            if (matcher.find()) {
                System.out.println(phone + " -> Valid: " + 
                                 matcher.group(2) + "-" + matcher.group(3) + "-" + matcher.group(4));
            } else {
                System.out.println(phone + " -> Invalid");
            }
        }
    }
    
    // ==================== STRING MANIPULATION ====================
    
    public void demonstrateStringManipulation() {
        String text = "Hello   World   with   multiple   spaces";
        
        // Remove extra whitespace
        String cleaned = text.replaceAll("\\s+", " ");
        System.out.println("Remove extra spaces: '" + text + "' -> '" + cleaned + "'");
        
        // Convert to camelCase
        String camelCase = text.replaceAll("\\s+(.)", "$1").toLowerCase();
        camelCase = camelCase.substring(0, 1).toUpperCase() + camelCase.substring(1);
        System.out.println("CamelCase: '" + text + "' -> '" + camelCase + "'");
        
        // Extract words
        Pattern wordPattern = Pattern.compile("\\b\\w+\\b");
        Matcher wordMatcher = wordPattern.matcher(text);
        List<String> words = new ArrayList<>();
        while (wordMatcher.find()) {
            words.add(wordMatcher.group());
        }
        System.out.println("Words: " + words);
        
        // Split by multiple delimiters
        String mixedText = "apple,banana;orange grape";
        String[] fruits = mixedText.split("[,;\\s]+");
        System.out.println("Split by multiple delimiters: " + java.util.Arrays.toString(fruits));
    }
    
    // ==================== VALIDATION PATTERNS ====================
    
    public void demonstrateValidationPatterns() {
        // Password validation (8+ chars, at least one uppercase, lowercase, digit)
        Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
        
        // Username validation (3-20 chars, alphanumeric and underscore only)
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9_]{3,20}$");
        
        // Credit card validation (basic pattern)
        Pattern creditCardPattern = Pattern.compile("^\\d{4}[-\\s]?\\d{4}[-\\s]?\\d{4}[-\\s]?\\d{4}$");
        
        // IPv4 address validation
        Pattern ipv4Pattern = Pattern.compile(
            "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"
        );
        
        // Test cases
        String[] passwords = {"Password123", "weak", "nouppercase123", "NOLOWERCASE123"};
        String[] usernames = {"user123", "u", "user@name", "valid_username"};
        String[] creditCards = {"1234-5678-9012-3456", "1234567890123456", "1234 5678 9012 3456"};
        String[] ipAddresses = {"192.168.1.1", "256.1.2.3", "192.168.001.001"};
        
        System.out.println("Password validation:");
        for (String pwd : passwords) {
            System.out.println("  " + pwd + " -> " + passwordPattern.matcher(pwd).matches());
        }
        
        System.out.println("Username validation:");
        for (String user : usernames) {
            System.out.println("  " + user + " -> " + usernamePattern.matcher(user).matches());
        }
        
        System.out.println("Credit card validation:");
        for (String card : creditCards) {
            System.out.println("  " + card + " -> " + creditCardPattern.matcher(card).matches());
        }
        
        System.out.println("IPv4 validation:");
        for (String ip : ipAddresses) {
            System.out.println("  " + ip + " -> " + ipv4Pattern.matcher(ip).matches());
        }
    }
    
    // ==================== UTILITY METHODS ====================
    
    /**
     * Extract all matches from text using a pattern
     */
    public static List<String> extractAll(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }
    
    /**
     * Check if text matches a pattern
     */
    public static boolean matches(String text, String regex) {
        return Pattern.matches(regex, text);
    }
    
    /**
     * Replace all occurrences using regex
     */
    public static String replaceAll(String text, String regex, String replacement) {
        return text.replaceAll(regex, replacement);
    }
    
    /**
     * Split string using regex
     */
    public static String[] split(String text, String regex) {
        return text.split(regex);
    }
} 