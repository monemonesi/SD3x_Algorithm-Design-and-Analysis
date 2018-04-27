
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
	    Sorting.swap(heap, k, k / 2);
	    k = k / 2;
	}

    }

    /**
     * Extracts the smallest element from the heap
     */
    public CompareInt extractMin() {
	// reminder: here we are starting from the index 1
	CompareInt min = heap[1];
	// Bring the latest element to the root
	heap[1] = heap[size];
	size--;

	// rebuild the heap
	sink(1);
	return min;

    }
    
    // rebuild the heap
    private void sink(int k) {
	while (2 * k <= size) {
	    int min = 2 * k;
	    if (heap[2 * k + 1].compareTo(heap[2 * k]) < 0)
		min = 2 * k;
	    if (heap[k].compareTo(heap[min]) < 0)
		break;
	    Sorting.swap(heap, k, min);
	    k = min;

	}

    }

}
