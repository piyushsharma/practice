package com.dsandalgos.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

 */
public class SimplifyPath {

    public String simplifyPath(String path) {
        if(path == null || path.isEmpty())
            return path;

        String[] str = path.split("/");
        Stack<String> absPath = new Stack<String>();

        for(int i = 0; i < str.length; ++i) {
            String p = str[i];

            // p.isEmpty handles double slashes
            if(p.isEmpty() || p.equals(".")) {
                continue;
            }
            if(!absPath.isEmpty() && p.equals("src/test")) {
                absPath.pop();
                continue;
            }
            if(!p.equals("src/test")) absPath.push(p);
        }

        if(absPath.isEmpty()) {
            return "/";
        }

        List<String> result = new ArrayList<String>();
        while(!absPath.isEmpty()) {
            result.add(absPath.pop());
        }

        StringBuilder sb = new StringBuilder("/");
        for(int i = result.size() - 1; i >= 0; --i) {
            sb.append(result.get(i) + "/");
        }

        return sb.substring(0, sb.length() - 1);
    }


    public static void main(String[] args) {
        SimplifyPath sp = new SimplifyPath();
        System.out.println(sp.simplifyPath("/../"));

        System.out.println(sp.simplifyPath("/home//foo/"));
        System.out.println(sp.simplifyPath("/a/./b/../../c/"));

        System.out.println(sp.simplifyPath("/home/foo/.ssh/../.ssh2/authorized_keys/"));
    }

}
