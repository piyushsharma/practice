package com.crawler;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * A rate limiter that allows a specified number of operations per second.
 * Uses a token bucket algorithm with semaphore for thread safety.
 */
public class RateLimiter {
    private final Semaphore semaphore;
    private final int permitsPerSecond;
    private final Thread refillThread;
    private volatile boolean running;

    public RateLimiter(int permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
        this.semaphore = new Semaphore(permitsPerSecond);
        this.running = true;
        
        // Start the refill thread
        this.refillThread = new Thread(this::refillTokens);
        this.refillThread.setDaemon(true);
        this.refillThread.start();
    }

    /**
     * Acquires a permit, blocking if necessary until one becomes available.
     */
    public void acquire() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Rate limiter interrupted", e);
        }
    }

    /**
     * Acquires a permit with timeout.
     * @param timeout the maximum time to wait
     * @param unit the time unit of the timeout argument
     * @return true if permit was acquired, false if timeout occurred
     */
    public boolean tryAcquire(long timeout, TimeUnit unit) {
        try {
            return semaphore.tryAcquire(timeout, unit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    /**
     * Refills tokens at the specified rate.
     */
    private void refillTokens() {
        long intervalMs = 1000L / permitsPerSecond; // Time between each token
        
        while (running) {
            try {
                Thread.sleep(intervalMs);
                semaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * Shuts down the rate limiter.
     */
    public void shutdown() {
        running = false;
        refillThread.interrupt();
    }

    /**
     * Gets the current number of available permits.
     */
    public int availablePermits() {
        return semaphore.availablePermits();
    }
} 