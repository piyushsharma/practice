package com.dsandalgos.string;

import java.util.regex.Pattern;

/**
 * Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.
 *
 * IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers,
 * each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
 *
 * Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.
 *
 * IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits.
 * The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334
 * is a valid one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters
 * in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334
 * is also a valid IPv6 address(Omit leading zeros and using upper cases).
 *
 * However, we don't replace a consecutive group of zero value with a single empty group
 * using two consecutive colons (::) to pursue simplicity.
 * For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.
 *
 * Besides, extra leading zeros in the IPv6 is also invalid.
 * For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.
 *
 * Note: You may assume there is no extra space or special characters in the input string.
 *
 * Example 1:
 * Input: "172.16.254.1"
 *
 * Output: "IPv4"
 *
 * Explanation: This is a valid IPv4 address, return "IPv4".
 * Example 2:
 * Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
 *
 * Output: "IPv6"
 *
 * Explanation: This is a valid IPv6 address, return "IPv6".
 * Example 3:
 * Input: "256.256.256.256"
 *
 * Output: "Neither"
 *
 * Explanation: This is neither a IPv4 address nor a IPv6 address.
 */


public class ValidateIPAddress {

    public String validIPAddress(String IP) {

        int v4 = IP.indexOf('.');
        if(v4 != -1) {
            return validateIPV4(IP);
        } else {
            return validateIPV6(IP);
        }
    }

    private String validateIPV6(String ip) {
        String[] ipv6 = ip.split(Pattern.quote(":"));

        if(ipv6.length != 8 || ip.charAt(ip.length() - 1) == ':') {
            return "Neither";
        }

        for(String item : ipv6) {
            if(item.length() == 0 || item.length() > 4) {
                return "Neither";
            }
            for(char c : item.toCharArray()) {
                if(!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) {
                    return "Neither";
                }
            }
        }
        return "IPv6";
    }

    private String validateIPV4(String ip) {
        String[] ipv4 = ip.split(Pattern.quote("."));

        if(ipv4.length != 4 || ip.charAt(ip.length() - 1) == '.') {
            return "Neither";
        }

        for(String item : ipv4) {
            if(item.length() == 0 || item.length() > 3) {
                return "Neither";
            }
            try {
                int x = Integer.parseInt(item);
                if (x > 255) {
                    return "Neither";
                }
                if(!String.valueOf(x).equals(item)) {
                    return "Neither";
                }
            } catch (Exception e) {
                return "Neither";
            }
        }
        return "IPv4";
    }

    public static void main(String[] args) {
        ValidateIPAddress v = new ValidateIPAddress();

        System.out.println(v.validIPAddress("172.16.254.1"));
        System.out.println(v.validIPAddress("172.16.254.1."));
        System.out.println(v.validIPAddress("172.16.254.01"));
        System.out.println(v.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
        System.out.println(v.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));
        System.out.println(v.validIPAddress("256.256.256.256"));
        System.out.println(v.validIPAddress("02001:0db8:85a3:0000:0000:8a2e:0370:7334"));
        System.out.println(v.validIPAddress("2001:0db8:85a3::8A2E:0370:7334"));
    }
}
