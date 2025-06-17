package com.concurrency;

public class MultiThreadSingleton {


    private MultiThreadSingleton() {
    }

    private static class Holder {
        private static final MultiThreadSingleton superman = new MultiThreadSingleton();
    }

    // lazy loading - singleton loaded when getInstance is called
    public static MultiThreadSingleton getInstance() {
        return Holder.superman;
    }

    public void fly() {
        System.out.println("I am flyyyyinggggg ...");
    }


    // Example of Double Checked Locking (DCL) below:
    // private constructor with volatile

//    class Superman {
//
//        private static volatile Superman superman;
//
//        private Superman() {
//
//        }
//
//        public static Superman getInstance() {
//
//            if (superman == null) {
//                synchronized (Superman.class) {
//
//                    if (superman == null) {
//                        superman = new Superman();
//                    }
//                }
//            }
//
//            return superman;
//        }
//
//        public void fly() {
//            System.out.println("I am Superman & I can fly !");
//        }
//    }
}
