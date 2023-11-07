/***********************************************************************************************************************
 * Name: Vinh Huynh
 * Assignment: Test #1 Program
 * Course: COMSC 076. Summer 2023
 * Date: June 29, 2023
 ***********************************************************************************************************************
 * Test #1 - Program
 * Write a method that uses recursion to count the number of times a specific
 * character occurs in an array of characters: for example,
 * char[ ] test = {‘T’, ‘h’, ‘i’, ‘s’, ‘ ‘, ‘i’, ‘s’, ‘ ‘, ‘t’, ‘h’, ‘e’, ‘ ‘, ‘s’, ‘t’, ‘r’, ‘i’, ’n’, ‘g’}.
 * The charCount method counts the number of times a character appears in the portion of the
 * array starting at a specified subscript. Remember that in Java (case sensitive) that ‘T’ is different from ‘t’.
 *
 * The method header is:  public static int charCount(char[] array, int start, char ch).
 *
 * Demonstrate the method with a driver program called CharacterCounter, that prompts the user to enter a character,
 * and then displays the number of occurrences of the character in the array.
 * ********************************************************************************************************************/

import java.util.Scanner;

// public class CharacterCounter {
public class CharacterCounter {
    public static void main(String[] args) {
        String str;         // Input from user
        char searchChar;    // Input from user
        char[] charArray;   // Convert input str from user to char[]
        int occursTimes;    // Number of times searchChar was found in the str

        Scanner input = new Scanner(System.in);

        System.out.print("Enter a string: ");               // Ask user to enter a string
        str = input.nextLine();

        System.out.print("Enter a search character: ");     // ask user to enter a search character
        searchChar = input.next().charAt(0);
        // Note: User must NOT enter a space ' '. This program does not search for space.
        // In order for this program to search for space, searchChar must be type String and convert to char.
        // String searchChar = input.nextLine();
        // char c = searchChar.charAt(0);
        // and instead of calling
        // occursTimes = charCount(charArray, charArray.length - 1, searchChar);
        // call this
        // occursTimes = charCount(charArray, charArray.length - 1, c);

        charArray = new char[str.length()];     // Convert str to charArray
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = str.charAt(i);
        }

        occursTimes = charCount(charArray, charArray.length - 1, searchChar);
        System.out.println("'" + searchChar + "'" + " occurs " + occursTimes +
                " time(s) in the string " + "\"" + str + "\"");
    }   // end main

    /******************************************************************************
     * Recursive public static int charCount(char[] array, int start, char ch)
     * Recursive method until reaches base case, start < 0. Start is the length of the array-1.
     * Each recursive calls start is decrement by 1.
     * Input: char[] array, int start, char ch
     * Output: Returns an int which is the number of time(s) char ch was found in the array
     ******************************************************************************/
    public static int charCount(char[] array, int start, char ch) {
        if (start < 0) {        // Base case
            return 0;
        }
        else if (array[start] == ch) {  // found matching search char in the string
            return (1 + charCount(array, start - 1, ch));   // Recursive call until reaches base case
        }
        else {
            return charCount(array, start - 1, ch);         // Recursive call until reaches base case
        }
    }   // end charCount(char[] array, int start, char ch)
}   // end class
