package com.concurrency;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.*;

public class ExecutorFutureDemo {

    private static Callable<String> makeTask(int id) {
        return () -> {
            // Simulate work
            TimeUnit.MILLISECONDS.sleep(300 + id * 200);
            return "Task " + id + " ran on " + Thread.currentThread().getName();
        };
    }

    public static void main(String[] args) {
        // 1. Create an ExecutorService with exactly 3 worker threads
        ExecutorService pool = Executors.newFixedThreadPool(3);

        try {
            // 2. Submit three independent callables and keep their Futures
            List<Future<String>> futures = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                futures.add(pool.submit(makeTask(i)));
            }

            // 3. Retrieve the results (blocking here; could use isDone/polling instead)
            for (Future<String> f : futures) {
                try {
                    System.out.println(f.get());   // prints each task’s return value
                } catch (ExecutionException ee) {
                    System.err.println("Task failed: " + ee.getCause());
                }
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        } finally {
            // 4. Graceful shutdown
            pool.shutdown();
        }
    }
}
