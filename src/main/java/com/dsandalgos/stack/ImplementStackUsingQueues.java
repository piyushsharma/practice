package com.dsandalgos.stack;

/**
 * Created by Piyush Sharma
 */

/*
Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Notes:
You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size,
and is empty operations are valid.

Depending on your language, queue may not be supported natively. You may simulate a queue by using a
list or deque (double-ended queue), as long as you use only standard operations of a queue.

You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).

 */

public class ImplementStackUsingQueues {

    class DoublyListNode {
        int val;
        DoublyListNode next;
        DoublyListNode prev;

        DoublyListNode(int x) {
            this.val = x;
        }
    }

    DoublyListNode head = new DoublyListNode(-1);
    DoublyListNode tail = head;

    // Push element x onto stack.
    public void push(int x) {
        DoublyListNode newNode = new DoublyListNode(x);
        newNode.prev = tail;
        tail.next = newNode;
        tail = tail.next;
    }

    // Removes the element on top of the stack.
    public void pop() {
        tail = tail.prev;
        tail.next = null;
    }

    // Get the top element.
    public int top() {
        return tail.val;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return head.next == null;
    }



//    Another Solution:

//    Queue<Integer> inQ = new LinkedList<Integer>();
//
//    // Push element x onto stack.
//    public void push(int x) {
//        Queue<Integer> outQ = new LinkedList<Integer>();
//        outQ.add(x);
//        for(int val : inQ) {
//            outQ.add(val);
//        }
//        inQ = outQ;
//    }
//
//    // Removes the element on top of the stack.
//    public void pop() {
//        inQ.poll();
//    }
//
//    // Get the top element.
//    public int top() {
//        return inQ.peek();
//    }
//
//    // Return whether the stack is empty.
//    public boolean empty() {
//        return inQ.isEmpty();
//    }

}
