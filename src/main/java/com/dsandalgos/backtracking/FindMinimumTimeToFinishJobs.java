package com.dsandalgos.backtracking;

import java.util.Arrays;

public class FindMinimumTimeToFinishJobs {

    public int minimumTimeRequired(int[] jobs, int k) {
        int left = 0;
        int right = 0;
        for (int job : jobs) {
            left = Math.max(left, job);
            right += job;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isPossible(jobs, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    private boolean isPossible(int[] jobs, int k, int maxTime) {
        int[] workers = new int[k];
        return backtrack(jobs, workers, 0, maxTime);
    }

    private boolean backtrack(int[] jobs, int[] workers, int index, int maxTime) {
        if (index == jobs.length) {
            return true;
        }

        for (int i = 0; i < workers.length; i++) {
            if (workers[i] + jobs[index] <= maxTime) {
                workers[i] += jobs[index];
                if (backtrack(jobs, workers, index + 1, maxTime)) {
                    return true;
                }
                workers[i] -= jobs[index];
            }

//            if (workers[i] == 0 || workers[i] + jobs[index] == maxTime) {
             if (workers[i] + jobs[index] == maxTime) {
                break;
            }
        }

        return false;
    }

    int result = Integer.MAX_VALUE;
    public int minimumTimeRequiredV2(int[] jobs, int k) {
        int length = jobs.length;
        Arrays.sort(jobs);
        backtrack(jobs, length - 1, new int [k]);
        return result;
    }

    public void backtrack(int [] jobs, int current, int [] workers) {
        int curMax = workers[0];
        for(int i = 1; i < workers.length; i++){
            curMax = Math.max(workers[i], curMax);
        }

        if (current < 0) {
            result = Math.min(result, curMax);
            return;
        }

        if (curMax >= result)
            return;

        for (int i = 0; i < workers.length; i++) {
            if (i > 0 && workers[i] == workers[i-1])
                continue;

            // make choice
            workers[i] += jobs[current];
            // backtrack
            backtrack(jobs, current-1, workers);
            // undo the choice
            workers[i] -= jobs[current];
        }
    }

}
