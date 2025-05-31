package com.brainteaser;


public class Lazy {

    // deadlock,
    // main is waiting for thread to finish
    // background thread is waiting for the class to initialize

    private static boolean initialized = false;
    static {
        Thread t = new Thread(new Runnable() {
        public void run() {
            initialized = true;
        }
        });

        t.start();
        try {
            t.join();
        } catch(InterruptedException e) {
            throw new AssertionError(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(initialized);
    }
}