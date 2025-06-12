package com.tosort;


public class FirstMissingPositive 
{

	private static int firstMissingPositive(int[] array)
	{
	    int i = 0;
	    while(i < array.length)
	    {
	        if(array[i] <= array.length && array[i] > 0 && array[array[i]-1] != array[i])
	            swap(array, i, array[i]-1);
	        else
	            i++;
	    }

	    i = 0;
	    while(i < array.length)
	    {
	        if(array[i] != i+1)
	            return i+1;
	        
	        i++;
	    }
	    return -1;
	}


	public static void swap(int[] array, int i, int j)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}


	public static void main(String[] args)
	{
		int[] array = {3, 4, -1, 1};
		System.out.println(firstMissingPositive(array));
	}
}
