package com.dsandalgos.design;

import java.util.*;

/**
 * Created by Piyush Sharma
 */

/*
Given a nested list of integers, flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],
The resulting flattened list should be: [1,1,2,1,1].

Given the list [[2,3],2,[5,4]],
The resulting flattened list should be: [2,3,2,5,4].

Example 2:
Given the list [1,[4,[6]]],
The resulting flattened list should be: [1,4,6].

*/

public class FlattenNestedListObject {

    public static List<Object> flattenedList(List<Object> nestedList) {

        Stack<Object> stack = new Stack<>();
        stack.push(nestedList);

        List<Object> result = new ArrayList<>();

        while(!stack.isEmpty()) {
            Object o = stack.pop();
            if(o instanceof List<?>) {
                for(Object item : (List<?>) o)
                    stack.push(item);
            } else {
                result.add(o);
            }
        }

//        LinkedList<Object> queue = new LinkedList<>();
//        queue.add(nestedList);
//
//        while(!queue.isEmpty()) {
//
//            Object o = queue.remove();
//
//            // adding to 0 preserves the order of elements in the nested list
//            if(o instanceof List<?>) {
//                queue.addAll(0, (List<?>) o);
//            } else {
//                result.add(o);
//            }
//        }

        return result;
    }

    public static List<Object> flattenedListB(List<Object> nestedList) { // [1,[4,[6]]]

        List<Object> list = new ArrayList();

        for  (int i =0 ; i < nestedList.size() ; i++){
            Object li = nestedList.get(i);
            if(li instanceof List<?>){
                list.addAll(flattenedListB((List)li));   //[4,[6]]
            } else{
                list.add(li); //1
            }
        }
        return list;
    }


    public static List<Object> list(Object... args) {
        return Arrays.asList(args);
    }

    public static void main(String[] args) {

        List<Object> nestedList = list(1, 2, list(3,4), 5, list(9, 10, list(-1), list(-100)), 15);

        List<Object> result = flattenedList(nestedList);
        for(Object o : result) {
            System.out.printf("%d ", o);
        }
    }

}
