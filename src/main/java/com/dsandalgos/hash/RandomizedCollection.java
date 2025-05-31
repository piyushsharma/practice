package com.dsandalgos.hash;

import java.util.*;

/**
 * Created by Piyush Sharma
 */

/*
Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements.

*The probability of each element being returned is linearly related to the number of same value the collection contains*

Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();

*/

public class RandomizedCollection {

    /*
        Design:
            - We need to be able to insert/remove in O(1), so definitely need a key value store (map)

            - We need to insert in O(1) and also store duplicates (since element being returned
              in getRandom shoud be linearly related to the number of times it was inserted)
              Arraylist can store duplicates, but to remove an element from the arraylist,
              we will need to know the index of that element, so we need to store all the indexes
              at which the same element is inserted in the arraylist
    */

    private Map<Integer, Set<Integer>> dataMap;
    private List<Integer> dataArray;
    private Random randomGenerator;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        this.dataMap = new HashMap<>();
        this.dataArray = new ArrayList<>();
        this.randomGenerator = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {

        boolean valueExists = dataMap.containsKey(val);

        if(!valueExists) {
            dataMap.put(val, new HashSet<>());
        }
        // save value in the data structures
        dataMap.get(val).add(dataArray.size());
        dataArray.add(val);

        return !valueExists;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {

        if (dataMap.get(val) == null) {
            return false;
        }

        // the sameValueIndexSet contains the indexes in the dataArray
        // even though we have the index in the dataArray we cannot just remove it
        // as that would cause the dataArray to resize and change the indexes of all other elemets

        // instead we will use the last element in the dataArray to do what we want
        int lastElementInArray = dataArray.get(dataArray.size() - 1);
        int deletionIndex = dataMap.get(val).iterator().next();

        // this if and else is very important to understand, if we do not do this,
        // we may be removing and adding the same element to the same hashset
        // that causes the hashcode of that element to change
        // so the set.remove() functions stops working as desired
        if(lastElementInArray != val) {
            dataMap.get(val).remove(deletionIndex);
            // we also need to update the dataMap to reflect this
            dataMap.get(lastElementInArray).remove(dataArray.size() - 1);
            dataMap.get(lastElementInArray).add(deletionIndex);
        } else {
            dataMap.get(val).remove(dataArray.size() - 1);
        }

        if (dataMap.get(val).isEmpty()) dataMap.remove(val);

        // this overwrites the element at arrayIndex
        dataArray.set(deletionIndex, lastElementInArray);
        dataArray.remove(dataArray.size() - 1);

        return true;
    }


    /** Get a random element from the collection. */
    public int getRandom() {
        int randomIndex = randomGenerator.nextInt(dataArray.size());
        return dataArray.get(randomIndex);
    }

    /** Getter and Setter **/
    public Map<Integer, Set<Integer>> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<Integer, Set<Integer>> dataMap) {
        this.dataMap = dataMap;
    }

    public List<Integer> getDataArray() {
        return dataArray;
    }

    public void setDataArray(List<Integer> dataArray) {
        this.dataArray = dataArray;
    }

}
