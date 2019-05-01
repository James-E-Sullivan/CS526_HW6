import java.util.Arrays;
import java.util.Random;

public class SortingComparison {

    /**
     * Insertion-sort of an array of integers into non-decreasing order
     * @param data: int[] of integers to be sorted
     */
    public static void insertionSort(int[] data){
        int n = data.length;
        for (int k=1; k<n; k++){
            int current = data[k];
            int j = k;
            while (j > 0 && data[j-1] > current){
                data[j] = data[j-1];
                j--;
            }
            data[j] = current;
        }
    }

    /**
     * Merge contents of arrays S1 and S2 into properly sized array S
     * @param S1: 1st sub-array
     * @param S2: 2nd sub-array
     * @param S: Array of size S1 + S2
     */
    public static void merge(int[] S1, int[] S2, int[] S){
        int i = 0, j = 0;
        while (i+j < S.length){
            if(j == S2.length || (i < S1.length && S1[i] < S2[j]))
                S[i+j] = S1[i++];       // copy ith element of S1 and increment i
            else
                S[i+j] = S2[j++];       // copy jth element of S2 and increment j
        }
    }

    /**
     * Merge-sort contents of array S
     * @param S: Array to be sorted
     */
    public static void mergeSort(int[] S){
        int n = S.length;
        if(n < 2) return;       // array is trivially sorted
        // divide
        int mid = n/2;
        int[] S1 = Arrays.copyOfRange(S, 0, mid);     // copy of first half
        int[] S2 = Arrays.copyOfRange(S, mid, n);           // copy of second half
        // conquer (with recursion)
        mergeSort(S1);                                      // sort copy of first half
        mergeSort(S2);                                      // sort copy of second half
        // merge results
        merge(S1, S2, S);                   // merge sorted halves back into original
    }


    // copied from https://www.geeksforgeeks.org/iterative-quick-sort/
    public static int partition(int arr[], int low, int high){
        int pivot = arr[high];

        // index of smaller element
        int i = (low-1);
        for (int j = low; j <= high-1; j++){
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    // Copied from https://www.geeksforgeeks.org/iterative-quick-sort/
    public static void quickSortIterative(int arr[], int l, int h){
        // Create an auxiliary stack
        int[] stack = new int[h-l+1];

        // initialize top of stack
        int top = -1;

        // push initial values of l and h to stack
        stack[++top] = l;
        stack[++top] = h;

        // Keep popping from stack while is not empty
        while (top >= 0){
            // Pop h and l
            h = stack[top--];
            l = stack[top--];

            // Set pivot element at its correct position
            // in sorted array
            int p = partition(arr, l, h);

            // If there are elements on left side of pivot,
            // then push left side to stack
            if (p-1 > l){
                stack[++top] = l;
                stack[++top] = p - 1;
            }

            // If there are elements on right side of pivot,
            // then push right side to stack
            if (p+1 < h){
                stack[++top] = p + 1;
                stack[++top] = h;
            }
        }
    }

    /**
     * Heapify array S
     * @param S: int[] to be heapified
     * @param heapSize: size of heap S
     * @param root; root node of S
     */
    public static void heapify(int[] S, int heapSize, int root){
        int largest = root;        // initialize largest as root
        int leftChild = 2 * root + 1;  // left = 2*root+1
        int rightChild = 2 * root + 2; // right = 2*root+2

        // if left child is larger than root
        if (leftChild < heapSize && S[leftChild] > S[largest])
            largest = leftChild;

        // if right child is larger than largest so far
        if (rightChild < heapSize && S[rightChild] > S[largest])
            largest = rightChild;

        // if largest is not root
        if (largest != root){
            int swap = S[root];
            S[root] = S[largest];
            S[largest] = swap;

            // recursive call to heapify the sub-tree
            heapify(S, heapSize, largest);
        }
    }

    /**
     * Copied from "Heap Sort - Java Implementation"
     * https://algorithms.tutorialhorizon.com/heap-sort-java-implementation/
     * Sort int[] S using heapify
     * @param S: int[] to be sorted
     */
    public static void heapSort(int[] S){
        int size = S.length;

        // build heap
        for (int i = (size / 2) - 1; i >= 0; i--)
            heapify(S, size, i);

        // one-by-one extract (Max) an element from heap and
        // replace it with the last element in the array
        for (int i = size-1; i>=0; i--){
            //S[0] is a root of the heap and is the max element in heap
            int x = S[0];
            S[0] = S[i];
            S[i] = x;

            // call max heapify on the reduced heap
            heapify(S, i, 0);
        }
    }

    public static void main(String[] args) {

        long startTime;
        long endTime;

        for (int n = 10000; n <= 100000; n += 10000){

            System.out.println("\nn: " + n);

            // create array of n random ints between 1 and 1,000,000
            int[] keys = new int[n];
            Random r = new Random(System.currentTimeMillis());
            for (int j=0; j<n; j++)
                keys[j] = r.nextInt(1000000) + 1;

            // get elapsed time for insertion sort
            startTime = System.currentTimeMillis();
            insertionSort(keys.clone());
            endTime = System.currentTimeMillis();
            Long insertionSortElapsedTime = endTime - startTime;
            System.out.println("Insertion Sort: " + insertionSortElapsedTime);

            // get elapsed time for merge sort
            startTime = System.currentTimeMillis();
            mergeSort(keys.clone());
            endTime = System.currentTimeMillis();
            Long mergeSortElapsedTime = endTime - startTime;
            System.out.println("Merge Sort: " + mergeSortElapsedTime);

            // get elapsed time for quick sort
            int[] quickSortKeys = keys.clone();
            startTime = System.currentTimeMillis();
            quickSortIterative(quickSortKeys, 0, quickSortKeys.length-1);
            endTime = System.currentTimeMillis();
            Long quickSortElapsedTime = endTime - startTime;
            System.out.println("Quick Sort: " + quickSortElapsedTime);

            // get elapsed time for heap sort
            startTime = System.currentTimeMillis();
            heapSort(keys.clone());
            endTime = System.currentTimeMillis();
            Long heapSortElapsedTime = endTime - startTime;
            System.out.println("Heap Sort: " + heapSortElapsedTime);
        }
    }

}
