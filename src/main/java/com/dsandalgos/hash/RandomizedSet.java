package com.dsandalgos.hash;

import java.util.*;

/**
 * Created by Piyush Sharma
 */

/*
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 1 is the only number in the set, getRandom always return 1.
randomSet.getRandom();

*/

public class RandomizedSet {

    /*
        Design:
            - We need to be able to insert/remove in O(1), hashset can do that
              But each element must have the same probability of being returned
              Since hashset is not ordered, we cannot really get a specific element without iterating and
              counting the elements in the hashset, so we can also store it in an arraylist
              We can use the last position of the arraylist to remove in O(1)
    */

    private Map<Integer, Integer> dataMap;
    private List<Integer> dataArray;
    private Random randomGenerator;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        this.dataMap = new HashMap<>();
        this.dataArray = new ArrayList<>();
        this.randomGenerator = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if(dataMap.containsKey(val)) {
            return false;
        }

        dataArray.add(val);
        dataMap.put(val, dataArray.size() - 1);
        return true;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {

        if(dataMap.containsKey(val)) {
            int valIndex = dataMap.get(val);

            int valAtLastIndex = dataArray.get(dataArray.size() - 1);
            // int lastIndex = dataArray.size() - 1;

            // we must update the map before we remove it from the dataArray
            dataMap.put(valAtLastIndex, valIndex);
            dataArray.set(valIndex, valAtLastIndex);

            dataArray.remove(dataArray.size() - 1);
            dataMap.remove(val);

            return true;
        }
        return false;
    }


    /** Get a random element from the collection. */
    public int getRandom() {
        int randomIndex = randomGenerator.nextInt(dataArray.size());
        return dataArray.get(randomIndex);
    }


    public Map<Integer, Integer> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<Integer, Integer> dataMap) {
        this.dataMap = dataMap;
    }

    public List<Integer> getDataArray() {
        return dataArray;
    }

    public void setDataArray(List<Integer> dataArray) {
        this.dataArray = dataArray;
    }
}
