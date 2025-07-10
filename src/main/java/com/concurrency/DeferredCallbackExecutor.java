package com.concurrency;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DeferredCallbackExecutor {
    /**
     * Represents the class which holds the callback. For simplicity instead of
     * executing a method, we print a message.
     */
    static class CallBack {

        long executeAt;
        String message;

        public CallBack(long executeAfter, String message) {
            this.executeAt = System.currentTimeMillis() + executeAfter * 1000;
            this.message = message;
        }
    }

    PriorityQueue<CallBack> q = new PriorityQueue<CallBack>(new Comparator<CallBack>() {
        public int compare(CallBack o1, CallBack o2) {
            return (int) (o1.executeAt - o2.executeAt);
        }
    });

    ReentrantLock lock = new ReentrantLock();
    Condition callBackArrivedCondition = lock.newCondition();

    // Run by the Executor Thread
    public void start() throws InterruptedException {
        long sleepFor = 0;

        while (true) {
            lock.lock();

            while(q.size() == 0) {
                callBackArrivedCondition.await();
            }

            while(q.size() != 0){
                sleepFor = findSleepDuration();

                if(sleepFor <= 0){
                    break;
                }
                callBackArrivedCondition.await(sleepFor, TimeUnit.MILLISECONDS);
            }

            CallBack callBack = q.poll();
            System.out.println("Executed callback message " + callBack.message);

            lock.unlock();
        }
    }

    private long findSleepDuration() {
        long currentTime = System.currentTimeMillis();
        return q.peek().executeAt - currentTime;
    }

    // Called by Consumer Threads to register callback
    public void registerCallback(CallBack callBack) {
        lock.lock();
        q.add(callBack);
        callBackArrivedCondition.signal();
        lock.unlock();
    }
}
