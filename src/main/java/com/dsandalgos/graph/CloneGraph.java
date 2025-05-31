package com.dsandalgos.graph;

import com.dsandalgos.util.UndirectedGraphNode;

import java.util.HashMap;

/**
 * Created by Piyush Sharma
 */

/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/

*/

public class CloneGraph {

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {

        if (node == null) return node;

        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        return dfs(node, map);
    }


    public UndirectedGraphNode dfs(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> map) {
        if(map.containsKey(node)) {
            return map.get(node);
        }

        UndirectedGraphNode result = new UndirectedGraphNode(node.label);
        map.put(node, result);

        for(UndirectedGraphNode n : node.neighbors) {
            result.neighbors.add(dfs(n, map));
        }

        return result;
    }

    public static void main(String[] args) {

        UndirectedGraphNode n0 = new UndirectedGraphNode(0);
        UndirectedGraphNode n1 = new UndirectedGraphNode(1);
        UndirectedGraphNode n2 = new UndirectedGraphNode(2);

        n0.neighbors.add(n1);
        n0.neighbors.add(n2);

        n1.neighbors.add(n2);

        n2.neighbors.add(n2);

        UndirectedGraphNode result = new CloneGraph().cloneGraph(n0);
        System.out.println("Done");
    }

}
