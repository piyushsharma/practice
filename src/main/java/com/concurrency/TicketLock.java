package com.concurrency;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * TicketLock ensures FIFO order; avoids starvation at the cost of busy-wait.
 * Suitable when critical sections are very short and thread count low.
 */
public class TicketLock {
    private final AtomicInteger nextTicket = new AtomicInteger(0);
    private final AtomicInteger serving = new AtomicInteger(0);

    // two atomic = simple; cache friendly; fairness = FIFO
    // contrast with simple TAS spin-lock (compareAndSet (false, true)) which can starve
    // can add tryLock and exponential backoff / park after N spins
    // Under high contention - switch to ReentrantLock with kernel parking to save CPU

    public void lock() {
        int myTicket = nextTicket.getAndIncrement();
        while (serving.get() != myTicket) {
            // Spin – optionally call Thread.onSpinWait() in JDK 9+
            Thread.onSpinWait();
        }
    }

    public void unlock() {
        serving.incrementAndGet();
    }
}

