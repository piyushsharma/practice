package com.concurrency;

import java.util.concurrent.atomic.AtomicStampedReference;


/** Classic Treiber stack, but version-stamped to defeat ABA. */
public class LockFreeState<E> {

    private static class Node<E> {
        E val;
        Node<E> next;
        Node(E v) {
            val = v;
        }
    }

    /**
     * AtomicStampedReference adds a 32-bit version to each CAS → prevents old node resurrection (ABA).
     * [ABA :: a thread came in between and did A -> B and B -> A; the current thread still reads A and
     * CAS will work -- this may or may not be ideal for programs] - solve with version stamp;
     * contention - use exponential back off;  Lock free cuts latency under low contention; but can starve threads;
     * always benchmark both paths
     *
     * Lock-free ⇒ no kernel park/unpark → great under light contention; still linearizable.
     * Under heavy contention, consider elimination back-off stack or a plain lock-based stack.
     */
    private final AtomicStampedReference<Node<E>> head = new AtomicStampedReference<>(null, 0);

    public void push(E item) {
        Node<E> node = new Node<>(item);
        int[] stampHolder = new int[1];
        Node<E> oldHead;
        do {
            // On return of the .get below, stampHolder[0] will hold the value of the stamp
            // and the current value of the reference is returned
            oldHead = head.get(stampHolder);
            node.next = oldHead;
        } while (!head.compareAndSet(oldHead, node, stampHolder[0], stampHolder[0] + 1));
    }

    public E pop() {
        int[] stampHolder = new int[1];
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = head.get(stampHolder);
            if (oldHead == null) return null;
            newHead = oldHead.next;
        } while (!head.compareAndSet(oldHead, newHead, stampHolder[0], stampHolder[0] + 1));

        return oldHead.val;
    }

}
