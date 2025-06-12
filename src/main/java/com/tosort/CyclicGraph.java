package com.tosort;

import java.util.ArrayList;
import java.util.List;

class GraphNode {
    int value;
    List<GraphNode> neighbors;
    boolean visited;

	GraphNode(int value) {
		this.value = value;
		this.neighbors = new ArrayList<>();
		this.visited = false;
	}

	
}

public class CyclicGraph {

	/**
	 * Checks if the given undirected graph has a cycle
	 * @param node
	 * @param parent
	 * @return
	 */
	private static boolean isUndirectedGraphCyclic(GraphNode node, GraphNode parent)
	{
	    if(node.visited)
	        return true;

	    node.visited = true;
	    for(GraphNode neighbor : node.neighbors)
	    {
	        if(!neighbor.equals(parent))
	        {
	            if(isUndirectedGraphCyclic(neighbor, node))
	                return true;
	        }
	    }
	    return false;
	}

}
