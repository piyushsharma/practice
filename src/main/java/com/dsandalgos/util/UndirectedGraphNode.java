package com.dsandalgos.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piyush Sharma
 */
public class UndirectedGraphNode {

    public int label;
    public  List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }

}
