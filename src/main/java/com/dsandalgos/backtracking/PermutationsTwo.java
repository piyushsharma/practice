package com.dsandalgos.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Piyush Sharma
 */

/*

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]


                                [1,                          2,                           3]
                                /                            |                               \
                               /                             |                                \
          Swap 1 with 1       /                              |Swap 1 with 2                    \ Swap 1 with 3
                             /                               |                                  \
             1 is fixed [1, 2, 3]             2 is fixed [2, 1, 3]                  3 is fixed [3, 2, 1]
                         /    \                         /       \                              /        \
       Swap 2 with 2    /      \ Swap 2 with 3         /         \             Swap 2 with 2  /          \ Swap 2 with 1
                       /        \                     /           \                          /            \
                    [1,2,3]    [1,3,2]             [2,1,3]      [2,3,1]                  [3,2,1]       [3,1,2]
                (Two positions fixed, so the third slot filled with the remaining number, no more permutations)
*/


public class PermutationsTwo {

    // Follow the same principle as permutation without repetition
    // just do not call recursively if the swap is the same number
    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        permuteUnique(nums, 0, nums.length - 1, result);
        return result;
    }

    private void permuteUnique(int[] nums, int start, int end, List<List<Integer>> result) {

        if(start > end) return;

        if(start == end) {
            List<Integer> permutationFound = new ArrayList<>();
            for (int num : nums) permutationFound.add(num);
            result.add(permutationFound);
            return;
        }

        // Everything before start is fixed. Start swapping start with every element
        // in each new array formed, everything until start (including start) is fixed
        // then call recursively for the other elements

        for(int i = start; i <= end; ++i) {

            // to avoid duplicates, check if the element at i has already been consumed when processing
            // permutations from start to i-1, only call if it has not yet been consumed
            if(uniquePermutation(nums, start, i - 1, nums[i])) {

                // swap start with i (all the remaining elements)
                int temp = nums[start];
                nums[start] = nums[i];
                nums[i] = temp;

                // now start is considered fixed, call recursively to permute elements from start + 1 to end
                permuteUnique(nums, start + 1, end, result);

                // undo the swap so that the loop starts from the original array
                nums[i] = nums[start];
                nums[start] = temp;
            }
        }
    }

    private boolean uniquePermutation(int[] nums, int start, int end, int num) {
        for(int i = start; i <= end; ++i) {
            if(nums[i] == num) return false;
        }
        return true;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{2,2,1,1};

        List<List<Integer>> result = new PermutationsTwo().permuteUnique(nums);

        for(List<Integer> l : result) {
            for(Integer i : l) {
                System.out.printf("%d ", i);
            }
            System.out.println();
        }
    }




    // Method 1
    private static void kPermutations(char[] buf, int k, int n) {
        if(k == 0) {
            String s = "";
            for(int i = n; i < buf.length; ++i) {
                s += buf[i];
            }
            System.out.println(s);
            return;
        }
        for(int i = 0; i < n; ++i) {
            swap(buf, i, n-1);
            kPermutations(buf, k - 1, n - 1);
            swap(buf, i, n-1);
        }

    }

    // Method 1
    private static void permutations(String prefix, String str) {
        if(str.length() == 0) {
            System.out.println(prefix);
            return;
        }
        for(int i = 0; i < str.length(); ++i) {
            permutations(prefix + str.charAt(i), str.substring(0,i) + str.substring(i+1));
        }
    }


    /* Wrapper over _printParenthesis()*/
    public void printParenthesis(int n)
    {
        char[] str = new char[n*2 +1];
        if(n > 0)
            _printParenthesis(0, n, 0, 0, str);
        return;
    }

    public void _printParenthesis(int pos, int n, int open, int close, char[] str)
    {
        if(close == n)
        {
            System.out.printf("%s \n", new String(str));
            return;
        }
        else
        {
            if(open > close) {
                str[pos] = ')';
                _printParenthesis(pos+1, n, open, close+1, str);
            }
            if(open < n) {
                str[pos] = '(';
                _printParenthesis(pos+1, n, open+1, close, str);
            }
        }
    }



    public static List<Integer> grayCode(int n) {

        List<Integer> res = new ArrayList<Integer>();
        res.add(0);
        if(n==0) return res;
        HashSet<Integer> visited = new HashSet<Integer>();
        visited.add(0);
        while(true){
            int prev = res.get(res.size()-1);
            boolean isLast = true;
            for(int i = 0; i < n; ++i){
                int v = prev ^ (0x1 << i);
                if(!visited.contains(v)){
                    isLast = false;
                    res.add(v);
                    visited.add(v);
                    break;
                }
            }
            if(isLast) break;
        }

        return res;
    }



    public List<Integer> grayCode2(int n) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(0);
        for(int i = 0; i < n; i++){
            int inc = 1<<i;
            for(int j= arr.size()-1; j>=0; j--) {
                arr.add(arr.get(j) + inc);
            }
        }
        return arr;
    }




    /* The main function that recursively prints all repeated permutations of
    the given string. It uses data[] to store all permutations one by one */
    void allLexicographicRecur (char[] str, char[] data, int index, int last)
    {
        int i, len = str.length;

        // One by one fix all characters at the given index and recur for the
        // subsequent indexes
        for ( i=0; i<len; i++ )
        {
            // Fix the ith character at index and if this is not the last index
            // then recursively call for higher indexes
            data[index] = str[i] ;

            // If this is the last index then print the string stored in data[]
            if (index == last)
                System.out.printf("%s\n", new String(data));
            else // Recur for higher indexes
                allLexicographicRecur(str, data, index + 1, last);
        }
    }

    /* This function sorts input string, allocate memory for data (needed for
      allLexicographicRecur()) and calls allLexicographicRecur() for printing all
      permutations */
    void allLexicographic(char[] str)
    {
        int len = str.length;

        // Create a temp array that will be used by allLexicographicRecur()
        char[] data = new char[len + 1];

        // Sort the input string so that we get all output strings in
        // lexicographically sorted order
        Arrays.sort(str);

        // Now print all permutaions
        allLexicographicRecur(str, data, 0, len - 1);
    }

//    public static void main(String[] args) {
//        char[] str = new char[]{'A', 'B', 'C'};
//          new PermutationsTwo().allLexicographic(str);

//        List<Integer> t = new PermutationsTwo().grayCode2(3);
//        for(Integer i : t) {
//            System.out.printf("%d ", i);
//        }
//        System.out.printf("\n");

//        new PermutationsTwo().printParenthesis(3);

//        String str = "abc";
//        String prefix = "";
//        permutations(prefix, str);
//        System.out.println("++++++++++++++++");
//        char buf[] = str.toCharArray();
//        permutations(buf, 0, buf.length-1);
//        System.out.println("++++++++++++++++");
//        char buffer[] = str.toCharArray();
//        permutations(buf, buffer.length);
//        System.out.println("++++++++++++++++");
//
//        str = "abcd";
//        char kbuffer[] = str.toCharArray();
//        int k = 2;
//        kPermutations(kbuffer, k, str.length());

//    }

    // Method 3
    private static void permutations(char[] a, int n) {
        if (n == 1) {
            System.out.println(a);
            return;
        }
        for (int i = 0; i < n; i++) {
            swap(a, i, n-1);
            permutations(a, n - 1);
            swap(a, i, n-1);
        }
    }

    // Method 2
    // complexity = n * n!
    private static void permutations(char[] buf, int start, int end) {
        if(start == end) {
            System.out.println(new String(buf));
            return;
        }

        for(int i = start; i <= end; ++i) {
            swap(buf, start, i);
            permutations(buf, start+1, end);
            swap(buf, start, i);
        }
    }

    private static void swap(char[] buf, int start, int i) {
        char temp = buf[start];
        buf[start] = buf[i];
        buf[i] = temp;
    }

}
