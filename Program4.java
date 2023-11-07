/***********************************************************************************************************************
 * Name: Vinh Huynh
 * Assignment: Program #4 Generics
 * Course: COMSC 076. Summer 2023
 * Date: June 28, 2023
 ***********************************************************************************************************************
 * Description:
 (Sort ArrayList) Write the following method that sorts an ArrayList:
 public static <E extends Comparable<E>> void sort(ArrayList<E> list)

 Write a program to test the method with three different arrays:
 Integers: 2, 4, and 3;
 Doubles: 3.4, 1.2, and -12.3;
 Strings: "Bob", "Alice", "Ted", and "Carol"

 This program is similar to the Case Study in the book: Sorting an Array of Objects

 The following illustrates how the output should appear:
 Sorted Integer Objects: 2 3 4
 Sorted Double Objects: -12.3 1.2 3.4
 Sorted String Objects: Alice Bob Carol Ted
 **********************************************************************************************************************/

import java.util.ArrayList;
import java.util.Arrays;

public class Program4 {
    /*****************************************
     * 	main
     * ***************************************/
    public static void main(String[] args) {
        // instantiate 3 array lists with their necessary data types
        ArrayList<Integer> integerList = new ArrayList<>(Arrays.asList(2, 4, 3));
        ArrayList<Double> doubleList = new ArrayList<>(Arrays.asList(3.4, 1.2, -12.3));

        // ArrayList<String> stringList = new ArrayList<>(Arrays.asList("Bob", "Alice", "Ted", "Carol"));
        // Note: I can instantiate stringList object with the code above, which I purposely commented out.
        // Here is another way to add each string one by one.
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("Bob");
        stringList.add("Alice");
        stringList.add("Ted");
        stringList.add("Carol");

        // sort each array list in ascending order
        sort(integerList);
        sort(doubleList);
        sort(stringList);

        // print the sorted array lists
        System.out.print("Sorted Integer Objects: ");
        print(integerList);

        System.out.print("Sorted Double Objects: ");
        print(doubleList);

        System.out.print("Sorted String Objects: ");
        print(stringList);
    }   // end main

    /************************************************
     * public static <E extends Comparable<E>> void sort(ArrayList<E> list)
     * Sorts array list in ascending order
     * Input: ArrayList<E> list
     * Output: none
     ************************************************/
    public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                // if > than 0, then list.get(j) > list.get(j + 1)
                // (check if content at j index is greater than content at j + 1 index)
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    // swap content at the 2 indexes
                    E temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }  // end inner for loop
        }   // end outer for loop
    }   // end public static <E extends Comparable<E>> void sort(ArrayList<E> list)

    /************************************************
     * public static <E> void print(ArrayList<E> list)
     * Prints array list
     * Input: ArrayList<E> list
     * Output: none
     ************************************************/
    public static <E> void print(ArrayList<E> list) {
        // print each value in the array list
        for (E e : list) {
            System.out.print(e + " ");
        }
        System.out.println();
    }   // end public static <E> void print(ArrayList<E> list)
}
