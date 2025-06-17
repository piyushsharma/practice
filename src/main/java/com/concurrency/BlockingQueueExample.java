package com.concurrency;

public class BlockingQueueExample<T> {

    T[] array;
    Object lock = new Object();
    int size = 0;
    int capacity;
    int head = 0;
    int tail = 0;

    public BlockingQueueExample(int capacity) {
        array = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public void enqueue(T item) {
        synchronized(lock) {
            try {
                while (size == capacity) {
                    lock.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (tail == capacity) {
                tail = 0;
            }
            array[tail] = item;
            tail++;
            size++;
            lock.notifyAll();
        }
    }

    public synchronized T dequeue() {
        T item = null;
        synchronized (lock) {
            while (size == 0) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {}
            }

            if(head == capacity) {
                head = 0;
            }

            // store the reference to the object being dequeued
            // and overwrite with null
            item = array[head];
            array[head] = null;
            head++;
            size--;
            lock.notifyAll();
        }
        return item;
    }

}
