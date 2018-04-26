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
	myMergeSort(arr, aux, 0, arr.length-1);

    }
    
    /**
     * My implementation of the mergesort Algorithm
     * @param arr
     * @param aux
     * @param lo
     * @param hi
     */
    private static void myMergeSort(CompareInt[] arr, CompareInt[] aux, int lo, int hi) {
	if(hi <= lo) return;
	int mid = (int)((lo+hi)*0.5);
	myMergeSort(arr, aux, lo, mid);
	myMergeSort(arr, aux, mid + 1, hi);
	merge(arr, aux, lo, mid, hi);
	Arrays.toString(arr);
    }
    
    //Helper methods
    
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
	//create aux array
	for(int i = lo; i <=hi; i++)
	    aux[i] = arr[i];
	
	
	int i=lo;
	int j = mid + 1;
	int k = lo;;
	//still elements in both arrays
	while(i <= mid && j <= hi) {
	    if(aux[i].compareTo(aux[j])<0) {
		arr[k++] = aux[i++];
	    }
		
	    else {
		arr[k++] = aux[j++];
	    }	
	}
	
	while(i <= mid) {
	    arr[k++] = aux[i++];
	}
	while(j <= hi) {
	    arr[k++] = aux[j++];
	}
	

	
    }
    

    /**
     * Implement the quickSelect
     * 
     * Again, you will probably want to use helper functions here (ex. partition(),
     * a helper quickselect function)
     */
    public static CompareInt quickSelect(int k, CompareInt[] arr) {
	// TODO
	return null;
    }

}
