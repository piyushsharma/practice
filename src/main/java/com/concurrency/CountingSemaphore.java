package com.concurrency;

/**
 * Your task is to implement a semaphore which takes in its constructor the maximum number of permits
 * allowed and is also initialized with the same number of permits.
 *
 */

public class CountingSemaphore {

    int usedPermits = 0; // permits given out
    int maxCount;  // max permits to give out

    public CountingSemaphore(int count) {
        this.maxCount = count;
    }

    public synchronized void acquire() throws InterruptedException {
        while (usedPermits == maxCount) {
            wait();
        }
        usedPermits++;
        notify();
    }

    public synchronized void release() throws InterruptedException {
        while (usedPermits == 0) {
            wait();
        }
        usedPermits--;
        notify();
    }
}