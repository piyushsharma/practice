package com.tosort;

public class DivideNumbers
{

    public static double pow(double x, int n) {
        if (n == 0) return 1.0;
        double half = pow(x, n/2);
        if (n%2 == 0)
        {
            return half*half;
        }
        else if (n>0)
        {
            return half*half*x;
        }
        else
        {
            return half/x*half;
        }
    }


    public int divideB(int dividend, int divisor) {
        //handle special cases
        if(divisor==0) return Integer.MAX_VALUE;
        if(divisor==-1 && dividend == Integer.MIN_VALUE)
            return Integer.MAX_VALUE;

        //get positive values
        long pDividend = Math.abs((long)dividend);
        long pDivisor = Math.abs((long)divisor);

        int result = 0;
        while(pDividend>=pDivisor){
            //calculate number of left shifts
            int numShift = 0;
            while(pDividend>=(pDivisor<<numShift)){
                numShift++;
            }

            //dividend minus the largest shifted divisor
            result += 1<<(numShift-1);
            pDividend -= (pDivisor<<(numShift-1));
        }

        if((dividend>0 && divisor>0) || (dividend<0 && divisor<0)){
            return result;
        }else{
            return -result;
        }
    }

    public int divide(int dividend, int divisor){

        if(divisor==0) return Integer.MAX_VALUE;
        int res = 0;
        if(dividend==Integer.MIN_VALUE){
            res = 1;
            dividend += Math.abs(divisor);
        }
        if(dividend==Integer.MIN_VALUE){
            return res;
        }

        boolean isNeg = ((dividend^divisor)>>>31==1);
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        int digit = 0;
        while(divisor<= (dividend>>1)){
            divisor <<= 1;
            ++digit;
        }

        while(digit>=0){
            if(dividend>=divisor){
                dividend -= divisor;
                res +=  1<<digit;
            }
            divisor >>= 1;
            digit --;
        }
        return isNeg?-res:res;

    }
}
