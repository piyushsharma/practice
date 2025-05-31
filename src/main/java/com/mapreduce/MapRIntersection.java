package com.mapreduce;

import org.json.simple.JSONObject;

import java.util.*;

/**
 * Created by Piyush Sharma
 */

/*
Given two sets of integers, R and S select and display the interesection.
The relative ordering of integers in the intersecting set should be same as that in set R.

Input Format

The first line contains two space separated integers Nr and Ns, which are the number of elements in the set R and S respectively. This is followed by Nr integers, the elements of set R, each on a new line, such that -100 <= X <= 100. This is followed by Ns integers, the elements of set S, each on a new line, such that -100 <= X <= 100. Also, 10 <= Nr,*Ns* <= 100

For instance, if R = [10,20,40,20,60];you may treat it as [10,20,40,60]

Output Format

Output each of the integers in the intersection of the sets R and S.
The relative ordering of integers in the intersecting set should be same as that in set R.

*/

public class MapRIntersection {

    private LinkedHashMap intermediate;
    private JSONObject finalResult = new JSONObject();
    private int resultCount;

    MapRIntersection() {
        resultCount=0;
        finalResult = new JSONObject();
        intermediate=new LinkedHashMap();
    }

    JSONObject execute(JSONObject inputdata) {

        for(int i=0;i<inputdata.size();i++)  {
            LinkedHashMap record=(LinkedHashMap)inputdata.get(i);
            mapper(record);
        }

        Iterator it = intermediate.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            reducer((int)pair.getKey(), (HashSet)pair.getValue());
            it.remove();
        }
        return finalResult;
    }


    private void emit(LinkedHashMap obj) {
        finalResult.put(resultCount++,obj);
    }


    private void reducer(int key, HashSet value) {
        // Fill up the question mark in the reducer function!
        if(value.size() == 2) {
            LinkedHashMap obj=new LinkedHashMap();
            obj.put("key",key);
            emit(obj);
        }
    }

    private void mapper(LinkedHashMap record)  {
        String value=(String)record.get("value");
        int key=(int)record.get("key");
        emitIntermediate(key,value);
    }

    private void emitIntermediate(int key, String value) {
        if(!intermediate.containsKey(key))
            intermediate.put(key,new HashSet());

        HashSet temp=(HashSet)intermediate.get(key);
        temp.add(value);
        intermediate.put(key,temp);
    }


    public static void main(String []argh) {
        JSONObject inputdata= new JSONObject();

        int Nr = 10;
        int Ns = 10;

        int []R = new int[]{-51,-43,74,-96,24,-14,11,77,-45,-90};
        int []S = new int[]{45,8,29,0,-43,-13,-72,71,-96,-26};
        int c = 0;

        for(int i = 0; i < Nr; i++) {
            Map obj = new LinkedHashMap();
            obj.put("value", "R");
            obj.put("key", R[i]);
            inputdata.put(c,obj);
            c++;
        }

        for(int i = 0; i < Ns; i++) {
            Map obj = new LinkedHashMap();
            obj.put("value", "S");
            obj.put("key", S[i]);
            inputdata.put(c,obj);
            c++;
        }

        MapRIntersection mapred = new MapRIntersection();
        JSONObject result = mapred.execute(inputdata);

        for(int i = 0; i < result.size(); i++) {
            LinkedHashMap record = (LinkedHashMap)result.get(i);
            System.out.println(record.get("key"));
        }
    }

}
