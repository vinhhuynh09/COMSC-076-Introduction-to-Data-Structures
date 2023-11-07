/***********************************************************************************************************************
 * Name: Vinh Huynh
 * Assignment: Program #3 Part 1 Recursion
 * Course: COMSC 076. Summer 2023
 * Instructor: Professor Henry Estrada
 * Date: June 27, 2023
 ***********************************************************************************************************************
 * Description:
 Write a recursive method that displays a string in reverse order on the console using the following header:
 public static void reverseDisplay(String value)

 To make sure this program makes efficient use of memory, have the method call a helper method that includes the
 string's high index:
 public static void reverseDisplay(String value, int high)

 Write a program to test your recursive method by prompting the user to enter a string and then displays it in reverse
 order. This program is the same as Exercise 18.12 of the textbook, page 744.

 Sample Output:
 Enter your string: Able was I, I saw Elba (user input appears in bold adjacent the prompt)

 ablE was I ,I saw elbA (this is the program output)
 **********************************************************************************************************************/
import java.util.Scanner;
import java.lang.String;

public class Program3Part1 {
    /*****************************************
     * 	main
     * ***************************************/
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your string: ");
        String string1 = input.nextLine();  // nextLine() reads entire string until Enter key is pressed
        reverseString(string1);
        System.out.println();

        // Another way using just for loop
        // reverseDisplayUsingForLoop(string1);
    }	// end main

    /************************************************
     * public static void reverseDisplayUsingForLoop(String str)
     * Another way to solve this program, using for loop.
     * Input: String
     * Output: none
     ************************************************/
    public static void reverseDisplayUsingForLoop(String str) {
        System.out.println("\nPrint reverse string using for loop.");
        int length = str.length();

        for (int i = length-1; i >= 0; i--) {
            System.out.print(str.charAt(i));
        }
    }	// end reverseDisplayUsingForLoop

    /************************************************
     * public static void reverseString(String str)
     * Calls helper functions, reverseString(str, high)
     * Input: String
     * Output: none
     ************************************************/
    public static void reverseString(String str) {
        int highIndex = str.length()-1;  // highIndex is the last character in the string
        reverseString(str, highIndex);   // calling recursive helper function
    } // end reverseString(String)

    /************************************************
     * public static void reverseString(String str, int high)
     * Recursive helper function. Prints last character and decrement high each time until high < 0
     * Input: string and int high which is the last character in the string
     ************************************************/
    public static void reverseString(String str, int high) {
        if (high < 0) {     // base case
            return;
        }
        System.out.print(str.charAt(high)); // print high character in the string
        high--;                             // decrement high so eventually will hit the base case
        reverseString(str, high);           // calling recursive helper function
    } // end reverseString(String)
}	// end class
