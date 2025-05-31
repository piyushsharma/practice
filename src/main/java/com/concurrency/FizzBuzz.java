package com.concurrency;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * Write a program that outputs the string representation of numbers from 1 to n, however:
 *
 * If the number is divisible by 3, output "fizz".
 * If the number is divisible by 5, output "buzz".
 * If the number is divisible by both 3 and 5, output "fizzbuzz".
 * For example, for n = 15, we output: 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz.
 *
 * Suppose you are given the following code:
 *
 * class FizzBuzz {
 *   public FizzBuzz(int n) { ... }               // constructor
 *   public void fizz(printFizz) { ... }          // only output "fizz"
 *   public void buzz(printBuzz) { ... }          // only output "buzz"
 *   public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
 *   public void number(printNumber) { ... }      // only output the numbers
 * }
 * Implement a multithreaded version of FizzBuzz with four threads.
 * The same instance of FizzBuzz will be passed to four different threads:
 *
 * Thread A will call fizz() to check for divisibility of 3 and outputs fizz.
 * Thread B will call buzz() to check for divisibility of 5 and outputs buzz.
 * Thread C will call fizzbuzz() to check for divisibility of 3 and 5 and outputs fizzbuzz.
 * Thread D will call number() which should only output the numbers.
 */

public class FizzBuzz {

    private int n;
    private final AtomicInteger curN;


    public FizzBuzz(int n) {
        this.n = n;
        curN = new AtomicInteger(1);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        int x;
        while((x = curN.get()) <= n) {
            if (x % 3 == 0 && x % 5 != 0) {
                printFizz.run();
                add(x);
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        int x;
        while((x = curN.get()) <= n) {
            if (x % 5 == 0 && x % 3 != 0) {
                printBuzz.run();
                add(x);
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        int x;
        while((x = curN.get()) <= n) {
            if (x % 5 == 0 && x % 3 == 0) {
                printFizzBuzz.run();
                add(x);
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        int x;
        while((x = curN.get()) <= n) {
            if (x % 5 != 0 && x % 3 != 0) {
                printNumber.accept(x);
                add(x);
            }
        }
    }

    public void add(int x) {
        if(!curN.compareAndSet(x, x + 1)) {
            throw new ConcurrentModificationException("x");
        }
    }

    public static void main(String[] args) throws Exception {

        FizzBuzz fb = new FizzBuzz(30);

        new Thread(() -> {
            try {
                fb.fizz(() -> System.out.print(" Fizz "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                fb.buzz(() -> System.out.print(" Buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                fb.fizzbuzz(() -> System.out.print(" FizzBuzz "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                fb.number((x) -> System.out.print(" " + x + " "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }
}
