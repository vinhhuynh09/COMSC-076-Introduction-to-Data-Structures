/***********************************************************************************************************************
 * Name: Vinh Huynh
 * Assignment: Program #6 Sets and Maps
 * Course: COMSC 076 Section 201. Summer 2023
 * Instructor: Professor Henry Estrada
 * Date: July 6, 2023
 ***********************************************************************************************************************
 * Description:
 (Count the occurrences of words in a text file) Rewrite Listing 21.9 to read the text from a text file.
 The text file is passed as a command-line argument. Words are delimited by whitespace characters,
 punctuation marks (, ; . : ?), quotation marks (' "), and parentheses. Count the words in a case-sensitive
 fashion (e.g., consider Good and good to be the same word). The words must start with a letter.
 Display the output of words in alphabetical order, with each word preceded by the number of times it occurs.

 I ran my program solution with its own Java source code as the input file in order to generate the following sample output:
 C:\Users\......\Desktop>java CountOccurrenceOfWords CountOccurrenceOfWords.java
 Display words and their count in  ascending order of the words
 1       a
 1       alphabetical
 1       an
 2       and
 3       args
 2       as
 1       ascending
 1       catch
 1       class
 4       count
 2       countoccurrenceofwords
 ...
 ...
 ************************************************************************************************************************
 * Note:
 * On a Window OS, type "cmd" launch command prompt app.
 * From the Command Prompt app, navigate to the directory where "className.java" resides
 *
 * To compile: javac "path to java file name"
 * To execute: java "path file java file class" "path to textfile.txt"
 *
 **********************************************************************************************************************/

import java.util.*;
import java.io.*;

public class Program6 {
    public static void main(String[] args) throws FileNotFoundException {
        String[] wordsArray;  // word read in from file
        String key;           // word in map
        int countValue;       // number of occurrences for the word

        if (args.length > 0) {              // check if filename is passed in
            String fileName = args[0];      // get the filename and assign it to fileName
            Scanner input = new Scanner(new File(fileName));   // open the file

            Map<String, Integer> map = new TreeMap<>();  // declare map (String is for word, Integer is for occurrence for each word)

            while (input.hasNext()) {   // read until end of file
                // split the words based on the delimiter in the requirements
                // per instruction: delimited by whitespace characters, punctuation marks (, ; . : ?),
                // quotation marks (' "), and parentheses
                // I have added tab as a delimiter
                wordsArray = input.nextLine().split("[ ,;.:?'\"()\t]");
                for (int i = 0; i < wordsArray.length; i++) {
                    key = wordsArray[i].toLowerCase();
                    // only consider words that have length > 0 and start with a letter
                    if ((key.length() > 0) && (Character.isLetter(key.charAt(0)))) {
                        if (!map.containsKey(key)) {  // the word has not been ready yet, so add it to the map with a countValue of 1
                            map.put(key, 1);
                        }
                        else {   // word has already been read before, so just add the count value by 1
                            countValue = map.get(key);
                            countValue++;
                            map.put(key, countValue);
                        }  // end else
                    }   // end if (key.length() > 0 && Character.isLetter(key.charAt(0)))
                }   // end for loop
            }   // end while (input.hasNext())

            // print each word's number of occurrences and the word from the map
            System.out.println("Display words and their count in ascending order of the words");
            map.forEach((k, v) -> System.out.println(v + "\t" + k));
            input.close();
        }  // end if (args.length > 0)
        else {   // file not able to be found or read
            System.out.println("File not read. Make sure to pass the file name as a command-line argument.");
        }
    }  // end main
}   // end class