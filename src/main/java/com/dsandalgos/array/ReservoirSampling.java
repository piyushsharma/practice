package com.dsandalgos.array;

import java.util.Random;

/**
 * Created by Piyush Sharma
 */


/*
Reservoir sampling is a family of randomized algorithms randomly choosing k samples from a list of n items,
where n is either a very large or unknown number.

Typically n is large enough that the list doesn’t fit into main memory.
For example, a list of search queries in Google and Facebook.

So we are given a big array (or stream) of numbers (to simplify),
and we need to write an efficient function to randomly select k numbers where 1 <= k <= n.
Let the input array be stream[].

It can be solved in O(n) time. The solution also suits well for input in the form of stream.
Following are the steps:

1) Create an array reservoir[0..k-1] and copy first k items of stream[] to it.
2) Now one by one consider all items from (k+1)th item to nth item.
…a) Generate a random number from 0 to i where i is index of current item in stream[].
Let the generated random number is j.
…b) If j is in range 0 to k-1, replace reservoir[j] with arr[i]

 */

public class ReservoirSampling {

    // randomly select k items from n
    // O(n)
    public int[] selectKItems(int stream[], int n, int k) {
        Random r = new Random();

        int[] reservoir = new int[k];

        for(int i = 0; i < k; ++i) {
            reservoir[i] = stream[i];
        }

        for(int i = k; i < n; ++i) {
            // select random index between 0 to i
            int randomIndex = r.nextInt(i);

            if(randomIndex < k) {
                reservoir[randomIndex] = stream[i];
            }
        }

        // return selected k items
        return reservoir;
    }


/*

To prove that this solution works perfectly, we must prove that the probability that any item
stream[i] where 0 <= i < n will be in final reservoir[] is k/n.

Let us divide the proof in two cases as first k items are treated differently.

Case 1: For last n-k stream items, i.e., for stream[i] where k <= i < n
For every such stream item stream[i], we pick a random index from 0 to i and
if the picked index is one of the first k indexes, we replace the element at picked index with stream[i]

To simplify the proof, let us first consider the last item.
probability that the last item is in final reservoir =
probability that one of the first k indexes is picked for last item =
k/n (the probability of picking one of the k items from a list of size n)

Let us now consider the second last item.
probability that the second last item is in final reservoir[] =
[probability that one of the first k indexes is picked in iteration for stream[n-2]] X
[probability that the index picked in iteration for stream[n-1] is not same as index picked for stream[n-2] ] =
[k/(n-1)]*[(n-1)/n] = k/n

Similarly, we can consider other items for all stream items from stream[n-1] to stream[k] and generalize the proof.

Case 2: For first k stream items, i.e., for stream[i] where 0 <= i < k
The first k items are initially copied to reservoir[] and may be removed later in iterations
for stream[k] to stream[n].

The probability that an item from stream[0..k-1] is in final array =
Probability that the item is not picked when items stream[k], stream[k+1], …. stream[n-1] are considered =
[k/(k+1)] x [(k+1)/(k+2)] x [(k+2)/(k+3)] x … x [(n-1)/n] = k/n

*/




}
