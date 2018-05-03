package HW1;


/**
 * A Heap implementation class
 * 
 * @param heap
 *            the array that holds the heap data
 * @param size
 *            the number of elements currently stored in the heap
 */
public class MinHeap {

    CompareInt[] heap;
    int size;

    /**
     * Constructs a new heap with maximum capacity n Remember to index your heap at
     * 1 instead of 0!
     * 
     * @param n
     *            the maximum number of elements allowed in the heap
     */
    public MinHeap(int n) {
	heap = new CompareInt[n + 1];
	size = 0;
    }

    /**
     * Adds an element to the heap
     * 
     * @param val
     *            the value to be added to the heap
     */
    public void add(CompareInt val) {
	if (size == heap.length - 1)
	    throw new IllegalArgumentException();
	// We add the new element in the first free leave
	size++;
	heap[size] = val;
	swim(size);

	return;
    }

    /**
     * Fix the order property for the heap bringing the added element in the
     * correct location
     * 
     * @param k
     *            index for insert (starting from 1)
     */

    private void swim(int k) {
	while (k > 1 && heap[k].compareTo(heap[k / 2]) < 0) {
	    swap(heap, k, k / 2);
	    k = k / 2;
	}

    }

    /**
     * Extracts the smallest element from the heap
     */
    public CompareInt extractMin() {
	if (size < 1)
	    return null;
	else {
	    // reminder: here we are starting from the index 1
	    CompareInt min = heap[1];
	    // Bring the latest element to the root
	    heap[1] = heap[size];
	    size--;

	    // rebuild the heap
	    sink(1);
	    return min;
	}

    }
    
    
    /**
     * Sink function for rebuild the heap
     * @param k
     */
    private void sink(int k) {
	int leftChild = k*2;
	int rightChild = k*2+1;
	
	while (leftChild <= size) {
	    if(leftChild == size) {
		if(heap[leftChild].compareTo(heap[k])<0) {
		    swap(heap, k, leftChild);
		}else {
		    break;
		}
	    } else {
		if(heap[leftChild].compareTo(heap[rightChild])<0) {
		    if(heap[k].compareTo(heap[leftChild])<0) {
			break;			
		    } else {
			swap(heap, k, leftChild);
			k = leftChild;
			leftChild = k*2;
			rightChild = k*2+1;
		    }
		} else {
		    if (heap[k].compareTo(heap[rightChild]) < 0) {
			break;
		    } else {
			swap(heap, k, rightChild);
			k = rightChild;
			leftChild = k * 2;
			rightChild = k * 2 + 1;
		    }
		    
		}
	    }


	}// end while loop

    }
    
    
    /**
     * swap method 
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
