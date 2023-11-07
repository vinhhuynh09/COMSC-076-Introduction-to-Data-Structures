/***********************************************************************************************************************
 * Name: Vinh Huynh
 * Assignment: Test Program #2
 * Course: COMSC 076 Section 201. Summer 2023
 * Instructor: Professor Henry Estrada
 * Date: July 12, 2023
 ***********************************************************************************************************************
 * Test #1 - Program
 Write the following two generic methods using merge sort. The first sorts the elements using the Comparable interface,
 and the second uses the Comparator interface. Below, you will find the source code you need to complete this program.

 public static <E extends Comparable<E>>void mergeSort(E[ ] list)
 Include your code here

 public static <E> void mergeSort(E[ ] list, Comparator<? super E> comparator)
 // Code goes here

 Your program should all fit into one file and look like the following:
 public class FirstName_LastName_Test2 {
 public static void main(String[ ] args) {
 Integer[ ] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
 mergeSort(list);
 for (int i = 0; i < list.length; i++) {
 System.out.print(list[i] + " ");
 }

 System.out.println();
 Circle[ ] list1 = {new Circle(2), new Circle(3), new Circle(2),
 new Circle(5), new Circle(6), new Circle(1), new Circle(2),
 new Circle(3), new Circle(14), new Circle(12)};
 mergeSort( You need to include the relevant parameter list here );
 for (int i = 0; i < list1.length; i++) {
 System.out.print(list1[i] + " ");
 }
 }
 Your other methods will be included here
 }

 Your other classes all included in the same file
 *********************************************************************************************************************/

import java.util.Comparator;

public class Vinh_Huynh_Test2 {
    public static void main(String[] args) {
        Integer[] intList = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        mergeSort(intList);
        System.out.println("Sorted integer list:");
        for (int i = 0; i < intList.length; i++) {
            System.out.print(intList[i] + " ");
        }

        System.out.println();
        Circle[] circleList = {new Circle(2), new Circle(3), new Circle(2), new Circle(5), new Circle(6),
                new Circle(1), new Circle(2), new Circle(3), new Circle(14), new Circle(12)};
        mergeSort(circleList, new CircleComparator());
        System.out.println("\nSorted circle list:");
        for (int i = 0; i < circleList.length; i++) {
            System.out.print(circleList[i] + " ");
        }
    }   // end main()

    /**********************************************************************************
     * public static <E extends Comparable<E>> void mergeSort(E[] list)
     * Description: Sorting elements using the Comparable interface
     * Input: E[] list
     **********************************************************************************/
    public static <E extends Comparable<E>> void mergeSort(E[] list) {
        if (list == null) {  // list is empty
            return;
        }

        if (list.length > 1) {  // there are elements in the list
            int mid = (list.length / 2);

            // split left part and fill in left array
            E[] left = (E[]) new Comparable[mid];
            for (int i = 0; i < mid; i++) {
                left[i] = list[i];
            }

            // split right part and fill in right array
            E[] right = (E[]) new Comparable[list.length - mid];
            for (int i = mid; i < list.length; i++) {
                right[i - mid] = list[i];
            }
            // call merge sort recursively
            mergeSort(left);
            mergeSort(right);

            int i = 0, j = 0, k = 0;

            // merge left and right arrays
            while ((i < left.length) && (j < right.length)) {
                if (left[i].compareTo(right[j]) <= 0) {  // element in left[i] < element in right[j]
                    list[k] = left[i];
                    i++;
                }
                else {  // element in left[i] > element in right[j]
                    list[k] = right[j];
                    j++;
                }
                k++;
            }  // end while loop

            // collect remaining elements for left and right arrays
            while (i < left.length) {
                list[k] = left[i];
                i++;
                k++;
            }

            while (j < right.length) {
                list[k] = right[j];
                j++;
                k++;
            }
        }  // end if (list.length > 1)
    }  // end public static <E extends Comparable<E>> void mergeSort(E[] list)

    /**********************************************************************************
     * public static <E> void mergeSort(E[] list, Comparator<? super E> comparator)
     * Description: Sorting elements using the COMPARATOR interface
     * Input: E[] list, Comparator<? super E> comparator
     **********************************************************************************/
    public static <E> void mergeSort(E[] list, Comparator<? super E> comparator) {
        if (list == null) {
            return;
        }

        if (list.length > 1) {
            int mid = (list.length / 2);

            // split left part and fill in left array
            E[] left = (E[]) new Object[mid];
            for (int i = 0; i < mid; i++) {
                left[i] = list[i];
            }

            // split right part and fill in right array
            E[] right = (E[]) new Object[list.length - mid];
            for (int i = mid; i < list.length; i++) {
                right[i - mid] = list[i];
            }
            mergeSort(left, comparator);
            mergeSort(right, comparator);

            int i = 0, j = 0, k = 0;

            //merge left and right arrays
            while (i < left.length && j < right.length) {
                if (comparator.compare(left[i], right[j]) <= 0) {
                    list[k] = left[i];
                    i++;
                }
                else {
                    list[k] = right[j];
                    j++;
                }
                k++;
            }

            // collect remaining elements for left and right arrays
            while (i < left.length) {
                list[k] = left[i];
                i++;
                k++;
            }
            while (j < right.length) {
                list[k] = right[j];
                j++;
                k++;
            }
        }  // end if (list.length > 1)
    }  // end public static <E> void mergeSort(E[] list, Comparator<? super E> comparator)
}  // end Main class

/**********************************************************************************
 * class CircleComparator implements Comparator<Circle>
 **********************************************************************************/
class CircleComparator implements Comparator<Circle> {
    /**********************************************************************************
     * public int compare(Circle c1, Circle c2)
     * Description: Compare two objects of type Circle
     * Input: Circle c1, Circle c2
     **********************************************************************************/
    public int compare(Circle c1, Circle c2) {
        if (c1.getData() < c2.getData()) {
            return -1;
        }
        else if (c1.getData() > c2.getData()) {
            return 1;
        }
        else {
            return 0;
        }
    }  // end public int compare(Circle c1, Circle c2)
}  // end CircleComparator class

/**********************************************************************************
 * class Circle
 **********************************************************************************/
class Circle {
    int data;   // This variable could represent many things that describes a circle.
                // it could be circumference, radius, diameter, area, perimeter.
    Circle(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public String toString() {
        return "" + data;
    }
}  // end Circle class