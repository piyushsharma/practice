package com.tosort;

import java.util.Random;
import java.util.*;

class Point {
    int x;
    int y;
    int dist;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int dist() {
        return x*x + y*y;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }
}

public class KClosestPoints 
{

	public int[][] kClosest(int[][] points, int k) {

		PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> (b[2] - a[2]));

		for(int[] d : points) {
			int dist = (d[0] * d[0]) + (d[1] * d[1]);

			if(q.size() < k) {
				q.add(new int[]{d[0], d[1], dist});
				// System.out.println("1 = " + d[0] + " " + d[1] + " " + dist);
			} else if(dist < q.peek()[2]) {

				q.remove();
				q.add(new int[]{d[0], d[1], dist});

				// System.out.println("2 = " + d[0] + " " + d[1] + " " + dist);
			}
		}

		int[][] result = new int[k][2];
		for(int i = 0; i < k; ++i) {
			int[] item = q.remove();
			result[i] = new int[]{item[0], item[1]};
		}

		return result;

	}


	public List<Integer> findClosestElements(int[] arr, int k, int x) {

		PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> (a-x) - (b-x));

		for(int elem : arr) {

			if(q.size() < k) {
				q.add(elem);
			} else if(Math.abs(q.peek() - x) > Math.abs(elem - x)) {
				q.remove();
				q.add(elem);
			}
		}

		List<Integer> result = new ArrayList<>();
		for(int i = 0; i < k; ++i) {
			result.add(q.remove());
			// result[i] = new int[]{item[0], item[1]};
		}

		return result;
	}


	private static void getKClosestPoints(Point[] points, int k)
	{
		Random r = new Random();
		int lo = 0;
		int hi = points.length-1;

		while(true)
		{
			int randIdx = r.nextInt(hi - lo) + lo;
			int partitionIdx = partition(points, lo, hi, randIdx);
			if(partitionIdx ==  k-1)
			{
				for(int i = 0; i <= k-1; i++)
					System.out.println(points[i].dist());
				return;
			}
			else if(partitionIdx > k-1)
				hi = partitionIdx-1;
			else
				lo = partitionIdx+1;
		}
	}

	private static int partition( Point[] points, int lo, int hi, int pivotIdx)
	{
		int smallIdx = 0;
		 Point pivot = points[pivotIdx];
		swap(points, hi, pivotIdx);

		for(int i = lo; i < hi; i++)
		{
			if(points[i].dist() < pivot.dist())
			{
				swap(points, smallIdx, i);
				smallIdx++;
			}
		}
		return smallIdx;
	}

	private static void swap( Point[] points, int idx1, int idx2)
	{
		Point temp = points[idx1];
		points[idx1] = points[idx2];
		points[idx2] = temp;
	}
	
	public static void main(String[] args)
	{
		 Point[] points = new  Point[10];
		for(int i = 9, j = 0; i >= 0; i--, j++)
		{
			points[j] = new Point(0,0);
			points[j].setDist(i);
		}
		getKClosestPoints(points, 4);
	}
}
