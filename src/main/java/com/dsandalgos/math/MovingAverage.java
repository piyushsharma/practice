package com.dsandalgos.math;

import java.util.LinkedList;
import java.util.Queue;

public class MovingAverage {

    Queue<Integer> queue;
    double sum;
    int size;
    int count;

    public MovingAverage(int size) {
        queue = new LinkedList<>();
        count = 0;
        sum = 0.0;
        this.size = size;
    }

    public double next(int val) {

        queue.add(val);
        sum += val;

        if(count < size) {
            count += 1;
            return sum / count;
        } else {
            sum -= queue.remove();
            return sum / size;
        }
    }

    public double nextV2(int val) {

        if(count < size) {
            queue.add(val);
            sum += val;
            count += 1;
            return sum / count;
        } else {
            sum -= queue.remove();
            sum += val;
            queue.add(val);
            return sum / size;
        }
    }
}
