package com.dsandalgos.string;

import java.util.*;

public class ReorganizeString {
    


    public String reorganizeString(String s) {
        int[] counts = new int[26];
        Arrays.fill(counts, 0);

        for(int i = 0; i < s.length(); ++i) {
            counts[s.charAt(i) - 'a'] += 1;
        }
        
    
        int maxCount = 0;
        int maxLetter = 0;
        for(int i = 0; i < counts.length; ++i) {
            if(counts[i] > maxCount) {
                maxCount = counts[i];
                maxLetter = i;
            }
        }

        if(maxCount > (s.length() + 1)/2) {
            return "";
        }

        char[] answer = new char[s.length()];
        int index = 0;

        while(counts[maxLetter] > 0) {            
            answer[index] = (char) ('a' + maxLetter);
            index += 2;
            counts[maxLetter] -= 1;
        }

        for(int i = 0; i < counts.length; ++i) {

            while(counts[i] > 0) {

                if(index >= s.length()) {
                    index = 1;
                }
                answer[index] = (char) ('a' + i);
                index += 2;
                counts[i] -= 1;
            }
        }

        return answer.toString();
    }




    class Node {
        int count;
        char id;
        Node(char c) {
            id = c;
            count = 0;
        }
    }

    public String reorganizeStringV2(String s) {
        Map<Character, Node> map = new HashMap<>();

        for(int i = 0; i < s.length(); ++i) {            
            Node n = map.getOrDefault(s.charAt(i), new Node(s.charAt(i)));
            n.count++;
            map.put(s.charAt(i), n);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.count - a.count);
        for(Character c : map.keySet()) {
            pq.add(map.get(c));
        }
        
        StringBuilder sb = new StringBuilder();        
        while(!pq.isEmpty()) {

            Node first = pq.remove();
            
            if(sb.length() == 0 || sb.charAt(sb.length() - 1) != first.id) {
                
                sb.append(first.id);
                
                first.count -= 1;
                if(first.count > 0) {
                    pq.add(first);
                }
            } else {
                // what we removed on first is the same as last character of current builder, 
                // so try second 

                // if no second, fail 
                if(pq.isEmpty()) {
                    return "";
                }

                Node second = pq.remove();
                sb.append(second.id);
                second.count -= 1;

                if(second.count > 0) {
                    pq.add(second);
                }

                // have to add back first after we were able to use the second 
                pq.add(first);
            }
        }

        return sb.toString();
    }
}
