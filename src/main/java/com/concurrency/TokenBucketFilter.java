package com.concurrency;


/**
 * Imagine you have a bucket that gets filled with tokens at the rate of 1 token per second.
 *
 * The bucket can hold a maximum of N tokens.
 *
 * Implement a thread-safe class that lets threads get a token when one is available.
 * If no token is available, then the token-requesting threads should block.
 * The class should expose an API called getToken that various threads can call to get a token.
 */

public class TokenBucketFilter {

    private int MAX_TOKENS;

    // variable to note down the latest token request.
    private long lastRequestTime = System.currentTimeMillis();

    long possibleTokens = 0;
    private final int ONE_SECOND = 1000;

    public TokenBucketFilter(int maxTokens) {
        MAX_TOKENS = maxTokens;

        // for getTokenV2()
        // Never start a thread in a constructor; added here for demo purpose
        // use FactoryPattern or some other way to start the daemon thread
        Thread dt = new Thread(() -> {
            daemonThread();
        });
        dt.setDaemon(true);
        dt.start();
    }

    synchronized void getToken() throws InterruptedException {

        possibleTokens += (System.currentTimeMillis() - lastRequestTime) / ONE_SECOND;

        if(possibleTokens > MAX_TOKENS) {
            possibleTokens = MAX_TOKENS;
        }

        if(possibleTokens == 0) {
            Thread.sleep(ONE_SECOND);
        } else {
            possibleTokens--;
        }
        lastRequestTime = System.currentTimeMillis();

        System.out.println("Granting token to thread named: " + Thread.currentThread().getName());
    }

    /*
     * Another solution is to use threads to solve the token bucket filter problem.
     * We instantiate one thread to add a token to the bucket after every one second.
     * The user thread invokes the getToken() method and is granted one if available.
     */
    private void daemonThread() {
        while (true) {
            synchronized (this) {
                if (possibleTokens < MAX_TOKENS) {
                    possibleTokens++;
                }
                this.notify();
            }
            try {
                Thread.sleep(ONE_SECOND);
            } catch (InterruptedException ie) {
                // swallow exception
            }
        }
    }

    void getTokenV2() throws InterruptedException {

        synchronized (this) {
            while (possibleTokens == 0) {
                this.wait();
            }
            possibleTokens--;
        }

        System.out.println("Granting token to thread named: " + Thread.currentThread().getName());
    }

}
