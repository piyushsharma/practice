package com.mapreduce;

/**
 * Created by Piyush Sharma
 */
import org.json.simple.JSONObject;

import java.util.*;

public class MapRSelection
{
    private LinkedHashMap intermediate;
    private JSONObject finalResult = new JSONObject();
    private int resultCount;

    MapRSelection()
    {
        resultCount=0;
        finalResult = new JSONObject();
        intermediate=new LinkedHashMap();
    }

    JSONObject execute(JSONObject inputdata)
    {
        for(int i=0;i<inputdata.size();i++)
        {
            JSONObject record=(JSONObject)inputdata.get(i);
            mapper(record);
        }

        Iterator it = intermediate.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            reducer((int)pair.getKey(), (ArrayList)pair.getValue());
            it.remove();
        }
        return finalResult;
    }


    private void emit(LinkedHashMap obj)
    {
        finalResult.put(resultCount++,obj);
    }

    private <T> void reducer(T key, ArrayList value)
    {
        LinkedHashMap obj=new LinkedHashMap();
        obj.put("key",key);
        obj.put("value",key);
        emit(obj);
    }


    private void mapper(JSONObject record)
    {
        int value=(int)record.get("value");
        if((value % 2) != 0 && value > 10)
            emitIntermediate(value,1);
    }


    private  <T1,T2>  void emitIntermediate(T1 key, T2 value)
    {
        if(!intermediate.containsKey(key))
            intermediate.put(key,new ArrayList());

        ArrayList temp=(ArrayList)intermediate.get(key);
        temp.add(value);
        intermediate.put(key,temp);
    }


    public static void main(String []argh)
    {
        JSONObject inputdata= new JSONObject();

        int[] R = new int[]{99,43,-27,21,99,-100,35,-91,-45,59};
        int t = R.length;

        int linecount=0;
        for(int i=0;i<t;i++)
        {
            int value = R[i];
            Map obj=new JSONObject();
            obj.put("key",linecount);
            obj.put("value",value);

            inputdata.put(linecount++,obj);
        }
        MapRSelection mapred= new MapRSelection();
        JSONObject result=mapred.execute(inputdata);

        for(int i=0;i<result.size();i++)
        {
            LinkedHashMap record=(LinkedHashMap)result.get(i);
            int value=(int)record.get("value");

            System.out.println (value);

        }

    }

}

