package com.tosort;

import java.util.ArrayList;
import java.util.List;



class Interval {
    int left;
    int right;
    Interval(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int left() {
        return left;
    }

    public int right() {
        return right;
    }
}

public class InsertInterval 
{

	private static List<Interval> insertInterval(List<Interval> intervals, Interval newInterval)
	{
	    List<Interval> result = new ArrayList<Interval>();
	    for(Interval interval : intervals)
	    {
	        if(interval.right() < newInterval.left())
	        {
	            result.add(interval);
	        }
	        else if(interval.left() > newInterval.right())
	        {
	            result.add(newInterval);
	            newInterval = interval;
	        }
	        else
	        {
	            newInterval = new Interval(Math.min(interval.left(), newInterval.left()), Math.max(interval.right(), newInterval.right()));
	        }
	    }
	    result.add(newInterval);
	    return result;
	}
	
	public static void main(String[] args)
	{
		Interval a = new Interval(1,2);
		Interval b = new Interval(3,5);
		Interval c = new Interval(6,7);
		Interval d = new Interval(8,10);
		Interval e = new Interval(12,16);
		Interval newInterval = new Interval(4,9);
		
		List<Interval> list = new ArrayList<Interval>();
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		list.add(e);
		System.out.println(insertInterval(list, newInterval));
	}
}
