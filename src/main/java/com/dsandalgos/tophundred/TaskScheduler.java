package com.dsandalgos.tophundred;

import java.util.*;

public class TaskScheduler {

    class Task {
        int count;
        char id;

        Task(char id) {
            this.id = id;
        }
    }
    
    public int leastInterval(char[] tasks, int n) {

        Map<Character, Task> taskMap = new HashMap<>();

        for(char c : tasks) {
            taskMap.put(c, taskMap.getOrDefault(c, new Task(c)));
            taskMap.get(c).count += 1;
        }

        PriorityQueue<Task> pqCount = new PriorityQueue<>((a1, a2) -> (a2.count - a1.count));
        for(Task t : taskMap.values()) {
            pqCount.add(t);
        }

        int curTime = 0;
        while(!pqCount.isEmpty()) {
            List<Task> temp = new ArrayList<>();
            int i = 0;

            // we do this - because we want to execute the task with most instances as soon as it is possible
            // loop and do other tasks that can be executed during the cooldown
            while(i <= n) {

                if(!pqCount.isEmpty()) {
                    Task t = pqCount.poll();
                    t.count -= 1;

                    if(t.count > 0) temp.add(t);
                }
                ++curTime;

                if(pqCount.isEmpty() && temp.isEmpty()) {
                    break;
                }
                ++i;
            }

            for(Task t : temp) {
                pqCount.add(t);
            }
        }

        return curTime;
    }

   

    public static void main(String[] args) {
//        int x = new TaskScheduler().leastInterval(new char[]{'A','A','A','B','B','B'}, 2);

        char[] c = new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'};
        int y = new TaskScheduler().leastInterval(c, 2);
//        ['A','A','A','A','A','A','B','C','D','E','F','G']
        System.out.println(y);
    }
}
