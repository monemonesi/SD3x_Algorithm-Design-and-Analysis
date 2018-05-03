package HW1;
import java.util.Arrays;

public class Sorting {

    /**
     * Implement the mergesort function, which should sort the array of integers in
     * place
     * 
     * You will probably want to use helper functions here, as described in the
     * lecture recordings (ex. merge(), a helper mergesort function)
     * 
     * @param arr
     */
    public static void mergeSort(CompareInt[] arr) {
	int length = arr.length;
	mergeSort(arr, 0, length - 1);
    }

    /**
     * My implementation of the mergesort Algorithm
     * 
     * @param arr
     * @param low
     * @param high
     */
    private static void mergeSort(CompareInt[] arr, int low, int high) {
	if (high == low) {
	    return;
	}
	int mid = (low + high) / 2;
	mergeSort(arr, low, mid);
	mergeSort(arr, mid + 1, high);
	CompareInt[] mergedPart = merge(Arrays.copyOfRange(arr, low, mid + 1),
		Arrays.copyOfRange(arr, mid + 1, high + 1));
	int j = 0;
	for (int i = low; i <= high; i++) {
	    arr[i] = mergedPart[j++];
	}
    }


    // Helper methods

    /**
     * merge method:
     * 
     * @param arr1
     * @param arr2
     */
    private static CompareInt[] merge(CompareInt[] arr1, CompareInt[] arr2) {
	int length1 = arr1.length;
	int length2 = arr2.length;
	int lengthMerged = length1 + length2;
	CompareInt[] mergedArr = new CompareInt[length1 + length2];

	int i = 0, j = 0, k;
	for (k = 0; k <= lengthMerged - 1; k++) {
	    
	    if (j >= length2) {
		mergedArr[k] = arr1[i++];
	    } else if (i >= length1) {
		mergedArr[k] = arr2[j++];
	    } else {
		if (arr1[i].compareTo(arr2[j]) <= 0) {
		    mergedArr[k] = arr1[i++];
		} else {
		    mergedArr[k] = arr2[j++];
		}
	    }
	}
	return mergedArr;
    }
    

    /**
     * Implement the quickSelect
     * 
     * Again, you will probably want to use helper functions here (ex. partition(),
     * a helper quickselect function)
     * 
     * 
     */

    
    public static CompareInt quickSelect(int k, CompareInt[] arr) {
	int pivotIndex = partition(arr);
	if (pivotIndex == k - 1) {
	    return arr[pivotIndex];
	} else if (pivotIndex > k - 1) {
	    return quickSelect(k, Arrays.copyOfRange(arr, 0, pivotIndex));
	} else {
	    return quickSelect(k - pivotIndex - 1, Arrays.copyOfRange(arr, pivotIndex + 1, arr.length));
	}
    }




    //Helper methods
    
    /**
     * Partition method: chose a random element as pivot, partition the array and recursively
     * sort the left and right side
     * 
     * @param arr
     * @return
     */

    
    private static int partition(CompareInt[] arr)
	{
	  int length=arr.length;
	  int pivotIndex=(int)(Math.random()*length);
	  CompareInt temp;
	  
	  temp=arr[pivotIndex];
	  arr[pivotIndex]=arr[length-1];
	  arr[length-1]=temp;
	  
	  int i=0, j=length-1;
	  CompareInt[] tempArr=new CompareInt[length];
	  for(int k=0; k<=length-2; ++k)
	  {
	    if(arr[k].compareTo(temp)<=0)
	    {
	      tempArr[i++]=arr[k];
	    }
	    else
	    {
	      tempArr[j--]=arr[k];
	    }
	  }
	  tempArr[i]=temp;
	  
	  for(int k=0; k<=length-1; ++k)
	  {
	    arr[k]=tempArr[k];
	  }
	  
	  return i;
	}
    

}
