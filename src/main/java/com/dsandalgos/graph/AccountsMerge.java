package com.dsandalgos.graph;

import java.util.*;


/**
 *
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name,
 * and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is
 * some email that is common to both accounts. Note that even if two accounts have the same name,
 * they may belong to different people as people could have the same name.
 * A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format:
 * the first element of each account is the name, and the rest of the elements are emails in sorted order.
 * The accounts themselves can be returned in any order.
 *
 * Example 1:
 * Input:
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 *
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 *
 * Note:
 * The length of accounts will be in the range [1, 1000].
 * The length of accounts[i] will be in the range [1, 10].
 * The length of accounts[i][j] will be in the range [1, 30].
 *
 */


public class AccountsMerge {


    class UnionFind {

        int parent[];
        int n;

        public UnionFind(int n) {
            parent = new int[n + 1];
            this.n = n;

            for(int i = 0; i <= n; ++i) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            if(parent[i] == i) {
                return i;
            }

            return find(parent[i]);
        }

        public void union(int n1, int n2) {

            int x1 = find(n1);
            int x2 = find(n2);

            if(x1 == x2) return;

            parent[x1] = x2;
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        Map<String, String> emailToName = new HashMap<>();
        Map<String, Integer> emailToId = new HashMap<>();
        int idCount = 0;

        for(List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); ++i) {

                if(!emailToId.containsKey(account.get(i))) {
                    emailToId.put(account.get(i), idCount);
                    idCount++;
                }

                emailToName.put(account.get(i), name);
            }
        }

        UnionFind unionFind = new UnionFind(idCount);

        for(List<String> account : accounts) {
            for (int i = 1; i < account.size(); ++i) {
                unionFind.union(emailToId.get(account.get(1)), emailToId.get(account.get(i)));
            }
        }

        Map<Integer, List<String>> result = new HashMap<>();
        for(String email : emailToName.keySet()) {
            int index = unionFind.find(emailToId.get(email));
            result.computeIfAbsent(index, x -> new ArrayList<>()).add(email);
        }

        for (List<String> component : result.values()) {
            Collections.sort(component);
            component.add(0, emailToName.get(component.get(0)));
        }
        return new ArrayList(result.values());
    }


}
