package com.dsandalgos.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piyush Sharma
 */

public class GraphNode {

    private int value;

    private List<GraphNode> neighbors;

    private boolean visited;

    public enum Color {
        RED,
        BLUE
    }

    private Color color;

    public GraphNode() { };

    public GraphNode(int val) {
        this.value = val;
        this.neighbors = new ArrayList<>();
        this.visited = false;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<GraphNode> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<GraphNode> neighbors) {
        this.neighbors = neighbors;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
