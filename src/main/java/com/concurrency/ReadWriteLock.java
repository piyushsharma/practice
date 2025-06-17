package com.concurrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Before we allow a reader to enter the critical section, we need to make sure that there's no writer in progress.
 * It is ok to have other readers in the critical section since they aren't making any modifications
 *
 * Before we allow a writer to enter the critical section,
 * we need to make sure that there's no reader or writer in the critical section.
 */

public class ReadWriteLock {

    boolean isWriteLocked = false;
    int readers = 0;

    public synchronized void acquireReadLock() throws InterruptedException {
        while(isWriteLocked) {
            wait();
        }
        readers++;
    }

    public synchronized void releaseReadLock() {
        readers--;
        notify();
    }

    public synchronized void acquireWriteLock() throws InterruptedException {
        while(readers != 0 || isWriteLocked) {
            wait();
        }
        isWriteLocked = true;
    }

    public synchronized void releaseWriteLock() {
        isWriteLocked = false;
        notify();
    }
}