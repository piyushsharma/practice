package com.dsandalgos.math;

public class Power {

	public double myPow(double x, int n) {
		long y = (long) n;
		if(y >= 0)
			return pow(x,  y);
		else
			return 1.0/pow(x, -y);
	}

	private double pow(double x, long y) {
		if(y == 0)
			return 1.0;

		double result = pow(x, y/2);

		if(y % 2 == 1)
			return x * result * result;
		else
			return result * result;
	}

	double binExp(double x, long n) {
		if(n == 0) {
			return 1.0;
		}

		if(n < 0) {
			n = -1 * n;
			x = 1.0 / x;
		}

		double result = 1;
		while(n != 0) {
			if(n % 2 == 1) {

				result = result * x;
				n -= 1;
			}

			x = x * x;
			n = n / 2;
		}
		return result;
	}
}
