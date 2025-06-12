package com.dsandalgos.array;

import java.util.*;

/**
 * 
 * Implement a SnapshotArray that supports the following interface:

SnapshotArray(int length) initializes an array-like data structure with the given length. Initially, each element equals 0.
void set(index, val) sets the element at the given index to be equal to val.
int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 

Example 1:

Input: ["SnapshotArray","set","snap","set","get"]
[[3],[0,5],[],[0,6],[0,0]]
Output: [null,null,0,null,5]
Explanation: 
SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
snapshotArr.set(0,5);  // Set array[0] = 5
snapshotArr.snap();  // Take a snapshot, return snap_id = 0
snapshotArr.set(0,6);
snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 

Constraints:

1 <= length <= 5 * 104
0 <= index < length
0 <= val <= 109
0 <= snap_id < (the total number of times we call snap())
At most 5 * 104 calls will be made to set, snap, and get.
 * 
 * 
 */


public class SnapshotArray {



    int snapId = 0;
    TreeMap<Integer, Integer>[] records;
    

    // treemap dictionary; for each index i; value = list of (snapId, nums[i])

    public SnapshotArray(int length) {
        records = new TreeMap[length];
        for(int i = 0; i < length; i++) {
            records[i] = new TreeMap<Integer, Integer>();
            records[i].put(0, 0);
        }
    }
    
    public void set(int index, int val) {
        records[index].put(snapId, val);
    }
    
    public int snap() {        
        snapId += 1;
        return snapId - 1;
    }
    
    public int get(int index, int snap_id) {
        // note snap_id
        return records[index].floorEntry(snap_id).getValue();
        
    }

    // brute force => 


    // private List<int[]> snaplist = new ArrayList<>();
    // int[] cur;
    // int snapId = 0;

    // public SnapshotArray(int length) {
    //     cur = new int[length];
    //     for(int i = 0; i < length; ++i) {
    //         cur[i] = 0;
    //     }
    // }
    
    // public void set(int index, int val) {
    //     cur[index] = val;
    // }
    
    // public int snap() {

    //     int[] snapData = new int[cur.length];
    //     for(int i = 0; i < cur.length; ++i) {
    //         snapData[i] = cur[i];
    //     }

    //     snaplist.add(snapId, snapData);
    //     snaplist.set(snapId, snapData);
    //     ++snapId;

    //     return snapId-1;
    // }
    
    // public int get(int index, int snap_id) {
    //     int[] snapData = snaplist.get(snap_id);
    //     return snapData[index];
    // }
    
}
