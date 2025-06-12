package com.tosort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.dsandalgos.util.GraphNode;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

public class BipartiteGraph {

    // Checks if the given graph is bipartite
    private static boolean isBipartite(GraphNode node) {
        if(node == null)
            return false;

        Queue<GraphNode> queue = new LinkedList<GraphNode>();
        node.setColor(GraphNode.Color.RED);
        queue.offer(node);
        while(!queue.isEmpty()) {
            GraphNode n = queue.remove();
            GraphNode.Color curColor = n.getColor();
            GraphNode.Color neighborColor = (curColor == GraphNode.Color.RED) ? GraphNode.Color.BLUE : GraphNode.Color.RED;
            List<GraphNode> list = n.getNeighbors();
            for(GraphNode neighbor : list) {
                if(neighbor.getColor() != null && neighbor.getColor().equals(curColor))
                    return false;
                else if(neighbor.getColor() == null) {
                    neighbor.setColor(neighborColor);
                    queue.offer(neighbor);
                }
            }
        }
        return true;
    }
}
