package com.concurrency;

import java.util.concurrent.*;

/**
 * Created by Piyush Sharma
 */

public class ExecutorExample {

    private void executorExample() {

        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(() -> {

            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);

        });

        try {
            es.shutdown();
            es.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            System.err.println("tasks interrupted");
        } finally {
            if (!es.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            es.shutdownNow();
            System.out.println("shutdown finished");
        }

        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        es = Executors.newFixedThreadPool(1);
        Future<Integer> future = es.submit(task);

        System.out.println("future done? " + future.isDone());

        Integer result = null;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("future done? " + future.isDone());
        System.out.print("result: " + result);

    }

    public static void main(String[] args) {
        new ExecutorExample().executorExample();
    }

}
