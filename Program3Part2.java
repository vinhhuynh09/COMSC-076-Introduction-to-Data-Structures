/***********************************************************************************************************************
 * Name: Vinh Huynh
 * Assignment: Program #3 Part 2 Recursion
 * Course: COMSC 076. Summer 2023
 * Date: June 27, 2023
 ***********************************************************************************************************************
 * Description:
 Write a recursive method to print all permutations of a string. Then a write a program to test the method, by once
 again, prompting the user to enter a string of characters. For example, the permutations for the string "abc" are the
 following:
 abc
 acb
 bac
 bca
 cab
 cba
 **********************************************************************************************************************/

import java.util.Scanner;

public class Program3Part2 {
    // I added this variable, permutationsCount to help testing with longer string.
    // For example "abcde", five characters has total of 120 permutations
    // Printing this helps to confirm my code runs correctly.
    static int permutationsCount = 1;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str;

        System.out.print("Enter a string: ");
        str = input.nextLine(); // nextLine() reads entire string until Enter key is pressed

        // Number of permutations is the factorial of str.length()
        System.out.println("There are total of " + factorial(str.length()) + " possible permutations for this string: " + str);

        System.out.println("\nThe permutations for " + str + " are: ");
        displayPermutation(str);  // finding all permutations of str
    }	// end main

    /************************************************
     * public static void displayPermutation(String str)
     * Calls helper functions, displayPermutation(" ", str)
     * Input: String
     * Output: none
     ************************************************/
    public static void displayPermutation(String str) {
        displayPermutation("", str);  // calls helper function
    }	// end displayPermutation(String str)

    /************************************************
     * public static void reverseString(String str1, String str2)
     * Recursive helper function. If str2 is empty, print str1.
     * Input: str1 and str2
     ************************************************/
    public static void displayPermutation(String str1, String str2) {
        // base case (str2 is empty and str1 is printed to console)
        if (str2.isEmpty()) {
            System.out.println(permutationsCount + ". " + str1);
            permutationsCount++;
        }
        // recursive case (moves characters one by one from str2 to str1 and
        // calls displayPermutation(String str1, String str2) recursively with
        // new str1 and str2 strings each time
        else {
            for (int i = 0; i < str2.length(); i++) {
                // 1 character is added to str1 and erased from str2 each time this recursive call occurs
                displayPermutation(str1 + str2.charAt(i), str2.substring(0, i) + str2.substring(i + 1));
            }
        }
    }  // end displayPermutation(String str1, String str2)

    /************************************************
     * public static long factorial(int n)
     * Returns the factorial of the length of the string
     * Input: int n
     * Output: the factorial of given n
     ************************************************/
    public static long factorial(int n) {
        if (n == 0) {        // Base case
            return 1;
        }
        else {
            return n * factorial(n - 1); // Recursive call
        }
    }	// end factorial
}	// end Program3Part2 class
