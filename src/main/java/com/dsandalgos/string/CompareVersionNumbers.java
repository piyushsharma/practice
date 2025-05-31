package com.dsandalgos.string;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three",
it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
*/

public class CompareVersionNumbers {

    public int compareVersion(String version1, String version2) {

        String[] versionOne = version1.split("\\.");
        String[] versionTwo = version2.split("\\.");

        int i = 0;
        while(i < versionOne.length || i < versionTwo.length) {
            int v1 = 0;

            // check for empty because if the string = .1 (the first element of array will be empty)
            if(i < versionOne.length && !versionOne[i].isEmpty()) {
                v1 = Integer.parseInt(versionOne[i]);
            }

            int v2 = 0;
            if(i < versionTwo.length && !versionTwo[i].isEmpty()) {
                v2 = Integer.parseInt(versionTwo[i]);
            }

            if(v1 > v2) {
                return 1;
            } else if (v1 < v2) {
                return -1;
            }
            ++i;
        }
        return 0;
    }


    public int compareVersionV2(String version1, String version2) {

        // this is so that when we split, it is easy to c
        if(version1.charAt(0) == '.')
            version1 = "0" + version1;

        if(version2.charAt(0) == '.')
            version2 = "0" + version2;

        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int i = 0;
        while(i < v1.length || i < v2.length) {
            if(i < v1.length && i < v2.length) {
                int vi1 = Integer.parseInt(v1[i]);
                int vi2 = Integer.parseInt(v2[i]);

                if(vi1 < vi2) return -1;
                else if (vi1 > vi2) return 1;
            } else if (i < v1.length) {
                int vi1 = Integer.parseInt(v1[i]);
                if(vi1 != 0) return 1;
            } else if (i < v2.length) {
                int vi2 = Integer.parseInt(v2[i]);
                if(vi2 != 0) return -1;
            }
            ++i;
        }
        return 0;
    }

    public static void main(String[] args) {
        CompareVersionNumbers c = new CompareVersionNumbers();

        System.out.println(c.compareVersion("0.1", "1.1"));
        System.out.println(c.compareVersion("1.1", "1.1"));
        System.out.println(c.compareVersion("1.2", "13.1"));
        System.out.println(c.compareVersion("12.2.1", "12.2.2"));
        System.out.println(c.compareVersion(".2", ".1"));
        System.out.println(c.compareVersion("1", "1.1"));
        System.out.println(c.compareVersion("2", "1.1"));
        System.out.println(c.compareVersion("1.1", "1"));
    }
}
