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
	CompareInt[] aux = new CompareInt[arr.length];
	myMergeSort(arr, aux, 0, arr.length - 1);

    }

    /**
     * My implementation of the mergesort Algorithm
     * 
     * @param arr
     * @param aux
     * @param lo
     * @param hi
     */
    private static void myMergeSort(CompareInt[] arr, CompareInt[] aux, int lo, int hi) {
	if (hi <= lo)
	    return;
	int mid = (int) ((lo + hi) * 0.5);
	myMergeSort(arr, aux, lo, mid);
	myMergeSort(arr, aux, mid + 1, hi);
	merge(arr, aux, lo, mid, hi);
	Arrays.toString(arr);
    }

    // Helper methods

    /**
     * merge method:
     * 
     * @param arr
     * @param aux
     * @param lo
     * @param mid
     * @param hi
     */

    private static void merge(CompareInt[] arr, CompareInt[] aux, int lo, int mid, int hi) {
	// create aux array
	for (int i = lo; i <= hi; i++)
	    aux[i] = arr[i];

	int i = lo;
	int j = mid + 1;
	int k = lo;
	;
	// still elements in both arrays
	while (i <= mid && j <= hi) {
	    if (aux[i].compareTo(aux[j]) < 0) {
		arr[k++] = aux[i++];
	    }

	    else {
		arr[k++] = aux[j++];
	    }
	}

	while (i <= mid) {
	    arr[k++] = aux[i++];
	}
	while (j <= hi) {
	    arr[k++] = aux[j++];
	}

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

	return myQuickSelect(arr, 0, arr.length - 1, k - 1);
    }

    /**
     * My version of the quickSelect Algorithm
     * 
     * @param arr
     * @param lo
     * @param hi
     * @param k
     * @return
     */

    public static CompareInt myQuickSelect(CompareInt[] arr, int lo, int hi, int k) {
	if (hi == lo)
	    return arr[lo];

	int pivotLoc = partition(arr, lo, hi);
	if (pivotLoc == k)
	    return arr[k];
	else if (pivotLoc > k)
	    return myQuickSelect(arr, lo, pivotLoc - 1, k);
	else
	    return myQuickSelect(arr, pivotLoc + 1, hi, k);

    }

    //Helper methods
    
    /**
     * Partition method: chose a random element as pivot, partition the array and recursively
     * sort the left and right side
     * 
     * @param arr
     * @param lo
     * @param hi
     * @return
     */
    public static int partition(CompareInt[] arr, int lo, int hi) {
	int pivotIndex = (int)(Math.random()*(hi-lo));
	swap(arr, pivotIndex, hi);
	CompareInt pivot = arr[hi];
	int i  = lo;
	int j = hi;
	CompareInt[] aux = new CompareInt[arr.length];
	for(int k = lo; k < hi; k++) {
	    if(arr[k].compareTo(pivot)<0)
		aux[i++]=arr[k];
	    else
		aux[j--]=arr[k];
	    
	}
	aux[i] = arr[hi];
	
	for(int k = lo; k < hi; k++) {
	    arr[k] = aux[k];
	    
	}
	
	return i;
}
    

    /**
     * Swam two elements in an array
     * 
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(CompareInt[] arr, int i, int j) {
	CompareInt tmp = arr[i];
	arr[i] = arr[j];
	arr[j] = tmp;
    }

}
