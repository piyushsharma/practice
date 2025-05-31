package com.dsandalgos.util;
import java.util.ArrayList;
import java.util.List;


public class GraphNodeV<T>
{
	private T value_;
	private List<GraphNodeV<T>> neighbors_ = new ArrayList<GraphNodeV<T>>();
	private boolean visited_;
	public enum color 
	{
		UNCOLORED,
		RED,
		BLUE
	}
	private color c_ = color.UNCOLORED;
	
	public GraphNodeV()
	{
		
	}
	
	public GraphNodeV(T val)
	{
		value_ = val;
	}
	
	public boolean isColored()
	{
		return c_ != color.UNCOLORED;
	}
	
	public T getValue()
	{
		return value_;
	}
	
	public List<GraphNodeV<T>> neighbors()
	{
		return neighbors_;
	}
	
	public void addNeighbor(GraphNodeV<T> node)
	{
		neighbors_.add(node);
	}
	
	public void setColor(color c)
	{
		c_ = c;
	}
	
	public color color()
	{
		return c_;
	}
	
	public void visit()
	{
		visited_ = true;
	}
	
	public boolean visited()
	{
		return visited_;
	}
	
	public String toString()
	{
		return value_ + ":" + visited_;
	}
}
