package com.dsandalgos.tophundred;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class EvaluateDivision {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        // build graph
        Map<String, Map<String, Double>> graph  = buildGraph(equations, values);

        double[] result = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            result[i] = getPathWeight(queries.get(i).get(0), queries.get(i).get(1), new HashSet<String>(), graph);
        }

        return result;
    }

    private double getPathWeight(String start, String end, HashSet<String> visited,
                                 Map<String, Map<String, Double>> graph) {

        if(!graph.containsKey(start)) {
            return -1.0;
        }

        if(start.equals(end)) {
            return 1.0;
        }

        visited.add(start);

        Map<String, Double> neighbors = graph.get(start);
        for(String n : neighbors.keySet()) {

            if(!visited.contains(n)) {
                if (n.equals(end)) {
                    return neighbors.get(n);

                } else {

                    double weight = getPathWeight(n, end, visited, graph);
                    if(weight != -1.0) {
                        return weight * neighbors.get(n);
                    }
                }

            }

        }

        return -1.0;
    }

    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {

        Map<String, Map<String, Double>> graph = new HashMap<>();

        for(int i = 0; i < equations.size(); ++i) {
            List<String> eq = equations.get(i);
            Double value = values[i];

            String num = eq.get(0);
            String den = eq.get(1);

            graph.putIfAbsent(num, new HashMap<>());
            graph.putIfAbsent(den, new HashMap<>());

            graph.get(num).put(den, value);
            graph.get(den).put(num, 1/value);
        }

        return graph;
    }


}
