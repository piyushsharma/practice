package com.concurrency;

public class DemoNonRenentrant {

    class NonReentrantLock {

        boolean isLocked;

        public NonReentrantLock() {
            isLocked = false;
        }

        public synchronized void lock() throws InterruptedException {

            while (isLocked) {
                wait();
            }
            isLocked = true;
        }

        public synchronized void unlock() {
            isLocked = false;
            notify();
        }
    }

    public static void main (String[] args) throws Exception {
        NonReentrantLock nreLock = new DemoNonRenentrant().new NonReentrantLock();

        // First locking would be successful
        nreLock.lock();
        System.out.println("Acquired first lock");

        // Second locking results in a self deadlock -- it is not Reentrant
        System.out.println("Trying to acquire second lock");
        nreLock.lock();
        System.out.println("Acquired second lock");
    }


}
