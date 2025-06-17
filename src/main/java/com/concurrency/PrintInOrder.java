package com.concurrency;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The same instance of PrintInOrder will be passed to three different threads.
 * Thread A will call first(), thread B will call second(), and thread C will call third().
 * Design a mechanism and modify the program to ensure that
 * second() is executed after first(), and third() is executed after second().
 *
 * Note:
 * We do not know how the threads will be scheduled in the operating system,
 * even though the numbers in the input seem to imply the ordering.
 * The input format you see is mainly to ensure our tests' comprehensiveness.
 */

public class PrintInOrder {

    /**
     * race conditions: the program ends with an undesired output,
     *      resulting from the sequence of execution among the processes.
     * deadlocks: the concurrent processes wait for some necessary resources from each other.
     *      As a result, none of them can make progress.
     * resource starvation: a process is perpetually denied necessary resources to progress its works.
     */

    private AtomicBoolean first = new AtomicBoolean(false);
    private AtomicBoolean second = new AtomicBoolean(false);

    public PrintInOrder() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        first.set(true);
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while(first.get() != true) {
            // busy wait
        }

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        second.set(true);
    }

    public void third(Runnable printThird) throws InterruptedException {
        while(second.get() != true) {
            // busy wait
        }

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
