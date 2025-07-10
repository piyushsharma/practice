package com.dsandalgos.design;

import java.util.*;

/**
 * Created by Piyush Sharma
 */

/*
Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].

*/

public class NestedIterator implements Iterator<Integer> {

    private Stack<Iterator<NestedInteger>> iteratorStack;
    private Integer nextInt;

    public NestedIterator(List<NestedInteger> nestedList) {
        iteratorStack = new Stack<>();
        iteratorStack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextInt;
    }

    @Override
    public boolean hasNext() {

        // run through the stack elements until you find the next integer
        while(!iteratorStack.isEmpty()) {

            Iterator<NestedInteger> topIteratorInStack = iteratorStack.peek();
            if(topIteratorInStack.hasNext()) {

                NestedInteger nestedInteger = topIteratorInStack.next();
                if(nestedInteger.isInteger()) {
                    nextInt = nestedInteger.getInteger();
                    return true;
                }
                iteratorStack.push(nestedInteger.getList().iterator());

            } else {

                // the top element in stack does not have any more elements
                iteratorStack.pop();
            }
        }
        return false;
    }


    static class NestedList implements NestedInteger {

        Object item;

        public NestedList(Object o) {
            this.item = o;
        }

        @Override
        public boolean isInteger() {
            return item instanceof Integer;
        }

        @Override
        public Integer getInteger() {
            if(item instanceof Integer)
                return (Integer)item;

            return null;
        }

        @Override
        public List<NestedInteger> getList() {
            if(item instanceof Integer)
                return null;

            return (List<NestedInteger>) item;
        }
    }


    public static void main(String[] args) {

        NestedInteger l0 = new NestedList(1);
        NestedInteger l1 = new NestedList(2);
        NestedInteger l2 = new NestedList(Arrays.asList(l0, l1));
        NestedInteger l3 = new NestedList(Arrays.asList(l2, l1));

        List<NestedInteger> l = Arrays.asList(l0, l1, l2, l3);
        NestedIterator f = new NestedIterator(l);
        while(f.hasNext()) {
            System.out.println(f.next());
        }
    }


    // Another solution: Without iterators, but time limit exceeds on leetcode

//    private Stack<NestedInteger> stack;
//
//    public NestedIterator(List<NestedInteger> nestedList) {
//        stack = new Stack<>();
//
//        for(int i = nestedList.size() - 1; i >= 0; --i) {
//            stack.push(nestedList.get(i));
//        }
//    }
//
//    @Override
//    public Integer next() {
//        return stack.pop().getInteger();
//    }
//
//    @Override
//    public boolean hasNext() {
//
//        // run through the stack elements until you find the next integer
//        while(!stack.isEmpty()) {
//
//            NestedInteger nestedInteger = stack.peek();
//            if(nestedInteger.isInteger()) {
//                return true;
//            }
//
//            for(int i = nestedInteger.getList().size() - 1; i >= 0; --i) {
//                stack.push(nestedInteger.getList().get(i));
//            }
//            stack.pop();
//        }
//        return false;
//    }
}
