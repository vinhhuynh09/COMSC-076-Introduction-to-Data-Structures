/***********************************************************************************************************************
 * Name: Vinh Huynh
 * Assignment: Program #8 Sorting
 * Course: COMSC 076 Section 201. Summer 2023
 * Instructor: Professor Henry Estrada
 * Date: July 11, 2023
 ***********************************************************************************************************************
 * Description:
 *(Execution time for sorting) Write a program that obtains the execution time of selection sort, merge sort,
 * quick sort, heap sort, and radix sort for input sizes of 50,000, 100,000, 150,000, 200,000, 250,000, and 300,000.
 * Your program should create data randomly and print a table that looks like this
 * (but which also includes execution times in milliseconds):
 *
 * Array size		Selection 		Merge		Quick		Heap		Radix
 * 50,000
 * 100,000
 * 150,000
 * 200,000
 * 250,000
 * 300,000
 *
 * You can use the following code template to obtain the execution time:
 *  long startTime = System.currentTimeMillis( );
 *  Code that performs the task
 *  long endTime=System.currentTimeMillis();
 *  long executionTime=endTime-startTime;
 **********************************************************************************************************************/
import java.util.ArrayList;
import java.util.Random;

public class Program8 {
    public static void main(String[] args) {
        System.out.println("\t\t\t\t\t\tTimes are in Milliseconds");
        System.out.println("Array size\t|\tSelection\t\tMerge\t\tQuick\t\tHeap\t\tRadix");
        System.out.println("---------------------------------------------------------------------------");

        // Per requirement, starting array size at 50,000 and increment by 50,000, until 300,000
        // In other words, array sizes are: 50,000  100,000 150,000 200,000 250,000 and 300,000
        // To prevent from redundant code (hundred lines of code), sort(size) method reduces duplicate codes.
        for (int size = 50000; size <= 300000; size+=50000) {
            System.out.println(sort(size));
        }
    }  // end main

    /*******************************************************************************************
     * public static String sort(int size)
     * Description: Store generate random number based on the size to selectionSortArray[].
     *  then clone selectionSortArray[] to all other different sort method.
     *         mergeSortArray = selectionSortArray.clone();
     *         quickSortArray = selectionSortArray.clone();
     *         heapSortArray = selectionSortArray.clone();
     *         radixSortArray = selectionSortArray.clone();
     *  This way all sort methods will be sorting with the same data.
     *
     * Input: int size  // size of array ranging from 50,000 to 300,000 and increment by 50,000
     * Return: String sortTimeStr. Contains time in milliseconds for all 5 different sort methods
     ******************************************************************************************/
    public static String sort(int size) {
        String sortTimeStr = new String();

        switch(size) {
            case 50000:
                sortTimeStr = "50,000\t\t|\t";
                break;
            case 100000:
                sortTimeStr = "100,000\t\t|\t";
                break;
            case 150000:
                sortTimeStr = "150,000\t\t|\t";
                break;
            case 200000:
                sortTimeStr = "200,000\t\t|\t";
                break;
            case 250000:
                sortTimeStr = "250,000\t\t|\t";
                break;
            case 300000:
                sortTimeStr = "300,000\t\t|\t";
                break;
            default:
                System.out.println("Invalid size!!!");
                break;
        }   // end switch

        int[] selectionSortArray = new int[size];
        int[] mergeSortArray;
        int[] quickSortArray;
        int[] heapSortArray;
        int[] radixSortArray;

        Random rd = new Random();
        for (int i = 0; i < selectionSortArray.length; i++) {
            selectionSortArray[i] = Math.abs(rd.nextInt(300000));  // generate random numbers between 0 and 300,000
        }

        // copy random integers created from selectionSortArray to array used for merge, quick, heap, and radix sort
        // They all have the same data before sorting.
        mergeSortArray = selectionSortArray.clone();
        quickSortArray = selectionSortArray.clone();
        heapSortArray = selectionSortArray.clone();
        radixSortArray = selectionSortArray.clone();

        sortTimeStr  = sortTimeStr  + SelectionSort.selectionSort(selectionSortArray)  + "\t\t\t";
        sortTimeStr  = sortTimeStr  + MergeSort.mergeSort(mergeSortArray) + "\t\t\t";
        sortTimeStr  = sortTimeStr  + QuickSort.quickSort(quickSortArray) + "\t\t\t";
        sortTimeStr  = sortTimeStr  + HeapSort.heapSort(heapSortArray) + "\t\t\t";
        sortTimeStr  = sortTimeStr  + RadixSort.radixSort(radixSortArray) + "\t\t";

        return sortTimeStr ;
    }   // end public static String sort(int size)
}  // end Main class

/***********************************************************************
 *  class SelectionSort
 *  The method for sorting the numbers
 ***********************************************************************/
class SelectionSort {
    /********************************************************************
     * public static long selectionSort(int[] list)
     * Input: list
     * Return: total time to sort the list.
     *********************************************************************/
    public static long selectionSort(int[] list) {
        long startTime, endTime, executionTime;

        startTime = System.currentTimeMillis();
        for (int i = 0; i < list.length - 1; i++) {
            // Find the minimum in the list[i..list.length−1]
            int currentMin = list[i];
            int currentMinIndex = i;

            for (int j = i + 1; j < list.length; j++) {
                if (currentMin > list[j]) {
                    currentMin = list[j];
                    currentMinIndex = j;
                }
            }

            // Swap list[i] with list[currentMinIndex] if necessary
            if (currentMinIndex != i) {
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        return executionTime;
    }
}   // end SelectionSort class

/***********************************************************************
 *  class MergeSort
 *  The method for sorting the numbers
 ***********************************************************************/
class MergeSort {
    /********************************************************************
     * public static long mergeSort(int[] list)
     * Input: list
     * Return: total time to sort the list.
     *********************************************************************/
    public static long mergeSort(int[] list) {
        long startTime, endTime, executionTime;

        startTime = System.currentTimeMillis();
        if (list.length > 1) {
            // Merge sort the first half
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);

            // Merge sort the second half
            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            // Merge firstHalf with secondHalf into list
            merge(firstHalf, secondHalf, list);
        }
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;

        return executionTime;
    }   // end public static void mergeSort(int[] list)

    /********************************************************************
     * public static void merge(int[] list1, int[] list2, int[] temp)
     * Description: Merge two sorted lists
     * Input: int[] list1, int[] list2, int[] temp
     *********************************************************************/
    public static void merge(int[] list1, int[] list2, int[] temp) {
        int current1 = 0; // Current index in list1
        int current2 = 0; // Current index in list2
        int current3 = 0; // Current index in temp

        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1] < list2[current2]) {
                temp[current3++] = list1[current1++];
            }
            else {
                temp[current3++] = list2[current2++];
            }
        }

        while (current1 < list1.length) {
            temp[current3++] = list1[current1++];
        }

        while (current2 < list2.length) {
            temp[current3++] = list2[current2++];
        }
    }   // end public static void merge(int[] list1, int[] list2, int[] temp)
}   // end MergeSort class

/***********************************************************************
 *  class QuickSort
 *  The method for sorting the numbers
 ***********************************************************************/
class QuickSort {
    /***********************************************************************
     *  public static long quickSort(int[] list)
     *  Description: The method for sorting the numbers using quicksort.
     *      Calls helper recursive method to do most of the work.
     *  Input: int[] list
     *  Return: total time to sort the list.
     ***********************************************************************/
    public static long quickSort(int[] list) {
        long startTime, endTime, executionTime;

        startTime = System.currentTimeMillis();
        quickSort(list, 0, list.length - 1);
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;

        return executionTime;
    }   // end public static long quickSort(int[] list)

    /***********************************************************************
     *  public static long quickSort(int[] list, int first, int last)
     *  Description: The method for sorting the numbers using quicksort.
     *      This is the helper recursive method to do most of the work.
     *  Input: int[] list, int first, int last
     ***********************************************************************/
    public static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }   // end public static void quickSort(int[] list, int first, int last)

    /************************************************************************
     *  public static int partition(int[] list, int first, int last)
     * Description: Partition the array list[first..last]
     **********************************************************************/
    public static int partition(int[] list, int first, int last) {
        int pivot = list[first];    // Choose the first element as the pivot
        int low = first + 1;        // Index for forward search
        int high = last;            // Index for backward search

        while (high > low) {
            while (low <= high && list[low] <= pivot) { // Search forward from left
                low++;
            }

            while (low <= high && list[high] > pivot) { // Search backward from right
                high--;
            }

            // Swap two elements in the list
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }   // end while (high > low)

        while (high > first && list[high] >= pivot) {
            high--;
        }

        // Swap pivot with list[high]
        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }
        else {
            return first;
        }
    }   // end public static int partition(int[] list, int first, int last)
}   // end QuickSort class

/***********************************************************************
 * class Heap<E extends Comparable<E>>
 ***********************************************************************/
class Heap<E extends Comparable<E>> {
    private ArrayList<Integer> list = new java.util.ArrayList<>();
    public Heap() {
    }

    /***********************************************************************
     * public Heap(E[] objects)
     * Create a heap from an array of objects
     ***********************************************************************/
    public Heap(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add((Integer) objects[i]);
    }   // end public Heap(E[] objects)

    /***********************************************************************
     * public void add(int newObject)
     * Add a new object into the heap
     ***********************************************************************/
    public void add(int newObject) {
        list.add(newObject); // Append to the heap
        int currentIndex = list.size() - 1; // The index of the last node

        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            // Swap if the current object is greater than its parent
            if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
                Integer temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            } else {
                break; // The tree is a heap now
            }
            currentIndex = parentIndex;
        }   // end while
    }   // end public void add(int newObject)

    /***********************************************************************
     * public Integer remove()
     * Remove the root from the heap
     ***********************************************************************/
    public Integer remove() {
        if (list.size() == 0) return null;

        Integer removedObject = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);

        int currentIndex = 0;
        while (currentIndex < list.size()) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;

            // Find the maximum between two children
            if (leftChildIndex >= list.size()) break; // The tree is a heap
            int maxIndex = leftChildIndex;
            if (rightChildIndex < list.size()) {
                if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0) {
                    maxIndex = rightChildIndex;
                }
            }

            // Swap if the current node is less than the maximum
            if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
                Integer temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            } else
                break; // The tree is a heap
        }   // end while

        return removedObject;
    }   // end public E remove()

    /***********************************************************************
     * public int getSize()
     * Get the number of nodes in the tree
     ***********************************************************************/
    public int getSize() {
        return list.size();
    }
}  // end Heap class

/***********************************************************************
 *  class HeapSort
 *  Heap sort method
 ***********************************************************************/
class HeapSort {
    /***********************************************************************
     *  public static <E extends Comparable<E>> long heapSort(int[] list)
     *  Input: int[] list
     *  Return: total execution time.
     ***********************************************************************/
    public static <E extends Comparable<E>> long heapSort(int[] list) {
        long startTime, endTime, executionTime;

        startTime = System.currentTimeMillis();
        Heap<E> heap = new Heap<>();    // Create a Heap of integers

        // Add elements to the heap
        for (int i = 0; i < list.length; i++) {
            heap.add(list[i]);
        }

        // Remove elements from the heap
        for (int i = list.length - 1; i >= 0; i--) {
            list[i] = (int) heap.remove();
        }
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;

        return executionTime;
    }   // end public static <E extends Comparable<E>> void heapSort(E[] list)
}   // end HeapSort class

/***********************************************************************
 *  class RadixSort
 *  RadixSort sort method
 ***********************************************************************/
class RadixSort {
    /***********************************************************************
     *  public static void countingSort(int array[], int size, int place)
     *  Using counting sort to sort the elements in the basis of significant places
     ***********************************************************************/
    public static void countingSort(int array[], int size, int place) {
        int[] output = new int[size + 1];
        int max = array[0];
        for (int i = 1; i < size; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int[] count = new int[max + 1];

        for (int i = 0; i < max; ++i) {
            count[i] = 0;
        }

        for (int i = 0; i < size; i++) {    // Calculate count of elements
            count[(array[i] / place) % 10]++;
        }

        for (int i = 1; i < 10; i++) {  // Calculate cumulative count
            count[i] += count[i - 1];
        }

        for (int i = size - 1; i >= 0; i--) {   // Place the elements in sorted order
            output[count[(array[i] / place) % 10] - 1] = array[i];
            count[(array[i] / place) % 10]--;
        }

        for (int i = 0; i < size; i++) {
            array[i] = output[i];
        }
    }  // end public static void countingSort(int array[], int size, int place)

    /***********************************************************************
     *  public static int getMax(int array[], int n)
     *  Description: Function to get the largest element from an array
     ***********************************************************************/
    public static int getMax(int array[], int n) {
        int max = array[0];
        for (int i = 1; i < n; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }   // end public static int getMax(int array[], int n)

    /***********************************************************************
     *  public static long radixSort(int array[])
     *  Description: Main function to implement radix sort
     *  Input: int array[]
     *  Output: total time to execute radix sort
     ***********************************************************************/
    public static long radixSort(int array[]) {
        long startTime, endTime, executionTime;

        startTime = System.currentTimeMillis();
        int size = array.length;

        int max = getMax(array, size);   // Get maximum element

        // Apply counting sort to sort elements based on place value.
        for (int place = 1; max / place > 0; place *= 10) {
            countingSort(array, size, place);
        }
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;

        return executionTime;
    }   // end public static long radixSort(int array[])
}  // end RadixSort class