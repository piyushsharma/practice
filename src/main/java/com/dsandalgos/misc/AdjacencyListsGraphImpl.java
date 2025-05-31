package com.dsandalgos.misc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyListsGraphImpl<T> {


	// dictionary of adjacency lists
	private LinkedHashMap<GraphNode<T>, List<Edge<T>>> nodes;

	private int numNodes;
	private int numEdges;

	private Map<Integer, GraphNode<String>> idToNameMap;

	public AdjacencyListsGraphImpl() {
		this.nodes = new LinkedHashMap<>();
		this.numEdges = 0;
		this.numNodes = 0;
	}

	public LinkedHashMap<GraphNode<T>, List<Edge<T>>> getAllNodes() {
		return this.nodes;
	}

	public void addNode(GraphNode<T> node) {

		List<Edge<T>> edges = new ArrayList<>();
		nodes.put(node, edges);

		node.setVertexId(++numNodes);
	}


	public void addEdge(GraphNode<T> src, GraphNode<T> dest, float cost) {
		List<Edge<T>> srcChildren = nodes.get(src);
		Edge<T> newEdge = new Edge<T>(src, dest, cost);
		srcChildren.add(newEdge);
		++numEdges;
	}


	private class Edge<T> {
		GraphNode<T> source;
		GraphNode<T> dest;
		private float weight;

		public Edge(GraphNode<T> src, GraphNode<T> dest, float cost) {
			this.source = src;
			this.dest = dest;
			this.weight = cost;
		}

		public GraphNode<T> getSource() {
			return source;
		}

		public void setSource(GraphNode<T> source) {
			this.source = source;
		}

		public GraphNode<T> getDest() {
			return dest;
		}

		public void setDest(GraphNode<T> dest) {
			this.dest = dest;
		}

		public float getWeight() {
			return weight;
		}

		public void setWeight(float weight) {
			this.weight = weight;
		}
	}


	private class GraphNode<T> {
		String vertexName;
		Integer vertexId;
		T element;

		public String getVertexName() {
			return vertexName;
		}

		public void setVertexName(String vertexName) {
			this.vertexName = vertexName;
		}

		public Integer getVertexId() {
			return vertexId;
		}

		public void setVertexId(Integer vertexId) {
			this.vertexId = vertexId;
		}

		public T getElement() {
			return element;
		}

		public void setElement(T element) {
			this.element = element;
		}
	}
}
