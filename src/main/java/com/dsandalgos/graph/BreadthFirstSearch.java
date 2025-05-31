package com.dsandalgos.graph;

import com.dsandalgos.util.GraphNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Piyush Sharma
 */
public class BreadthFirstSearch {

    // O(V+E) where V is number of vertices in the graph and E is number of edges in the graph.

    public void bfs(GraphNode node) {
        if(node == null) return;

        Queue<GraphNode> graphQueue = new LinkedList<>();
        graphQueue.add(node);

        while(!graphQueue.isEmpty()) {

            GraphNode top = graphQueue.remove();
            top.setVisited(true);

            System.out.printf("%d ", top.getValue());

            for(GraphNode g : top.getNeighbors()) {
                if(!g.isVisited()) {
                    graphQueue.add(g);
                }
            }
        }
    }

    public static void main(String[] args) {
        BreadthFirstSearch b = new BreadthFirstSearch();

        GraphNode g2 = new GraphNode(2);
        GraphNode g0 = new GraphNode(0);
        GraphNode g1 = new GraphNode(1);
        GraphNode g3 = new GraphNode(3);

        g2.getNeighbors().add(g3);
        g2.getNeighbors().add(g0);

        g0.getNeighbors().add(g1);
        g0.getNeighbors().add(g2);

        g1.getNeighbors().add(g2);

        g3.getNeighbors().add(g3);

        b.bfs(g2);

    }
}
