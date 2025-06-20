package com.dsandalgos.stack;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.

Notes:
    You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is
    empty operations are valid.
    Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque
    (double-ended queue), as long as you use only standard operations of a stack.

    *You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty
    queue).*
*/

public class ImplementQueueUsingStacks {

    private Stack<Integer> in = new Stack<Integer>();
    private Stack<Integer> out = new Stack<Integer>();

    // Push element x to the back of queue.
    public void push(int x) {
        in.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if(out.isEmpty()) {
            while(!in.empty()) out.push(in.pop());
        }
        out.pop();
    }

    // Get the front element.
    public int peek() {
        if(out.isEmpty()) {
            while(!in.empty()) out.push(in.pop());
        }
        return out.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }

    public static void main(String[] args) {
        ImplementQueueUsingStacks m = new ImplementQueueUsingStacks();
        m.push(1);
        m.pop();
        System.out.println(m.empty());
    }

}
