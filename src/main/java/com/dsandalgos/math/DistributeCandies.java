package com.dsandalgos.math;

public class DistributeCandies {


    public long distributeCandies(int n, int limit) {

        long ans = 0;

        // try to give every possible number of candies to first person upto the limit
        // i.e the range = 0 to min(n, limit);
        for(int i = 0; i <= Math.min(n, limit); ++i) {

            // first person gets i candies

            // we want to distribute (n - i) candies to the remaining 2
            // if (n - i) > limit * 2 ==> invalid scenario as all of them must have <= limit

            if((n - i) > (limit * 2)) {
                continue;
            }

            // (n - i) <= limit * 2 =>
            // second person must receive at least max(0, n - i - limit) candies
            // to ensure thrid child does not receive more than limit candies
            // so AT MOST, the second child can receive is min(limit, n - i)

            ans += Math.min(n-i, limit) - Math.max(0, n - i - limit) + 1;

        }


        return ans;
    }
}
