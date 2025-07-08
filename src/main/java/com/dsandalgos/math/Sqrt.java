package com.dsandalgos.math;

public class Sqrt {

	private int sqrt(int num) {
		if(num < 2) {
			return num;
		}

		int lo = 2;
		int hi = num/2;
		int mid;
		long x;
		while(lo <= hi) {
			mid = lo + (hi - lo) / 2;
			x = (long)mid * mid;
			if(x > num)
				hi = mid - 1;
			else if(x < num)
				lo = mid + 1;
			else
				return mid;
		}
		return hi;
	}

	private double sqrtD(double num) {
	    double lo = 0;
	    double hi = num;
	    double diff = 0.0001;
	    double mid = num/2;
	    
	    while( Math.abs(num - (mid * mid)) > diff )
	    {
	       mid = lo + (hi - lo) / 2;
	       if(mid*mid > num)
	           hi = mid;
	       else
	           lo = mid;
	    }
	    return mid;
	}
	
	public static void main(String[] args) {
		System.out.print(new Sqrt().sqrtD(84));
	}
}
