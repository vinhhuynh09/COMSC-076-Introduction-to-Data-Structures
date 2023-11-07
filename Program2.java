/***********************************************************************************************************************
 * Name: Vinh Huynh
 * Assignment: Program #2 File I/O
 * Course: COMSC 076. Summer 2023
 * Date: June 24, 2023
 ***********************************************************************************************************************
 * Description:
 Implement a class named BitOutputStream for writing a stream of bits to a file as follows:

 +BitOutputStream(file: File)           // Creates a BitOutputStream to write bits to the file.
 +writeBit(char bit): void              // Writes a bit '0' or '1' to the output stream
 +writeBit(String bitString): void      // Writes a string of bits to the output stream
 +close(): void                         // This method must be invoked to close the stream

 The writeBit(char bit) method stores the bit in a byte variable. When you create a BitOutputStream, the byte is empty.
 After invoking writeBit('1') the byte becomes 00000001. After invoking writeBit("0101"), the byte becomes
 00010101. The first three bits are not filled yet. When a byte is full, it is sent to the output stream. Now the byte
 is reset to empty. You must close the stream by invoking the close() method. If a byte is neither empty nor full, the
 close() method first fills in zeros to make a full 8 bits in the byte, and then closes the stream. Hint: It might help
 to look at Exercise 5.44, on page 202, as well as Appendix G on Bitwise operations on page 1169 of the text. Write a
 program that sends the bits 010000100100001001101 to a file called testOutput.dat.
 **********************************************************************************************************************
 * Note:
 * 010000100100001001101 has length of 21. Each byte contains 8 bits, the original string is short of 3 bits to make it
 * a total of 24 bits which is three bytes.
 * Final string written to file: 010000100100001001101 000 // " 000" extra 3 bits to fill last byte
 * 01000010-01000010-01101000
 * 01000010 = 66 decimal = ascii 'B'
 * 01000010 = 66 decimal = ascii 'B'
 * 01101000 = 104 decimal = ascii 'h'
 * So BBh is written to file.
 **/

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;

public class Program2 {
    public static void main(String[] args) throws Exception {
        // instantiate BitOutputStream object
        BitOutputStream output = new BitOutputStream(new File("testOutput.dat"));
        String bitString;
        bitString = "010000100100001001101";              // Length is 21 "BBh"
        // Some other strings I have tested
        //   bitString =   "01101000010000100110100001101";    // "hBhh"
        //   bitString =   "010000010100001001000011";         // "ABC"
        //   bitString =   "01000001010000100100001";          // "ABB"
        System.out.println("\nWriting " + bitString + " to file");
        output.writeBit(bitString); // write the entire string to file
        output.close();

        System.out.println("Done writing");

        // By now program is done. But I want to practice and review some more, so the codes below will read the file
        // and display in binary and ascii.
        // read file to confirm characters written to file are correct
        System.out.println("\nConfirming write, reading the file");
        Scanner input = new Scanner(new File("testOutput.dat"));

        // read file
        while (input.hasNextLine()) {
            String line = input.nextLine();
            for (int i = 0; i < line.length(); i++) {
                // prints each bit to console in binary
                System.out.print(byteBinaryString((byte) line.charAt(i)));
                System.out.print(" ");  // to separate one byte
            }
            System.out.println();

            for (int i = 0; i < line.length(); i++) {
                // prints one byte to console as ascii characters
                System.out.print(line.charAt(i));
            }
            System.out.println();
        }
        System.out.println("Done reading");
    }  // end main


    /************************************************************
     *       public static class BitOutputStream
     ************************************************************/
    public static class BitOutputStream {
        private FileOutputStream output;
        private int currentByteValue;   // keeps track of the current byte
        private int bitsPosition;       // number of bits written

        /** CONSTRUCTOR THAT TAKES IN FILE OBJECT **/
        public BitOutputStream(File file) throws IOException {
            output = new FileOutputStream(file);
            bitsPosition = 0;
            currentByteValue = 0;
        }

        /************************************************************
         *       public void writeBit(String bitString)
         *  Takes in bit string and takes care of each character bit at a time
         ************************************************************/
        public void writeBit(String bitString) throws IOException {
            // takes each character bit and calls writeBit(char bit)
            for (int i = 0; i < bitString.length(); i++) {
                writeBit(bitString.charAt(i));
            }
        }   // end writeBit(String bitString)

        /************************************************************
         *       public void writeBit(char bit)
         *  Overload writeBit(). This function takes in char.
         *  Calculates 8 bit value and prints its ascii equivalent character to file
         ************************************************************/
        public void writeBit(char bit) throws IOException {
            if ((bit != '0') && (bit != '1')) {  // check for invalid input
                throw new IllegalArgumentException("Bit can only be 1 or 0");
            }
            // by now we have a valid bit (0 or 1)
            currentByteValue = (currentByteValue << 1);  // shift bit to left by 1 (multiple currentByte by 2)
            // if bit is 0, add 0 to current byte
            // if bit is 1, add 1 to current byte
            currentByteValue += (bit == '0' ? 0 : 1);

            if (++bitsPosition == 8) {
                output.write(currentByteValue); // write to file
                currentByteValue = 0;
                bitsPosition = 0;
            }
        }   // end writeBit(char bit)

        /************************************************************
         *       public void close()
         *  If bitsPosition is > 0 fill in the remainder bit to make it a byte.
         ************************************************************/
        public void close() throws IOException {
            if (bitsPosition > 0) {
                // fill the rest with 0
                output.write((currentByteValue << (8 - bitsPosition))); // fill in remainder empty bit(s) so it's a byte
            }
            output.close();  // close FileOutputStream object
        }   // end close()
    }  // end BitOutputStream class

    /************************************************************
     *       public static String byteBinaryString(Byte b)
     *  Converts ascii character value to binary
     ************************************************************/
    public static String byteBinaryString(Byte b) {
        String binaryString = Integer.toBinaryString(b);
        return String.format("%8s", binaryString).replace(' ', '0');
    }   // end byteBinaryString(Byte b)
}   // end Program2 class
