/***********************************************************************************************************************
 * Group: Group 1
 * Names: Vinh Huynh, Mohsen Alavian Ghavanini, Janelle Correa, Manreev Cheema, Xunwei Shi
 * Assignment: Group Project Part 2
 * Course: COMSC 076. Summer 2023
 * Date: July 25, 2023
 ***********************************************************************************************************************
 * Description:
 Part II: Write a second program, Decompress_a_File.java, to decompress a previously compressed file so
 that it replicates the original source file in Part I above. You would do this at the command line with a
 command that looks like the following:

 C:\Users\...> java Decompress_a_File compressedFile.txt decompressedFile.txt

 Part 1 for this assignment is in a separate file, Compress_a_File.java.
 **********************************************************************************************************************/

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Decompress_a_File {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        // Check for valid args first before moving forward
        if (args.length != 2) { //args.length must be exactly 2
            System.out.println("Usage: java Main sourceFile targetFile");
            System.exit(1);
        }

        File sourceFile = new File(args[0]);    // args[0] is the source file
        if (!sourceFile.exists()) {
            System.out.println("File " + args[0] + " does not exist");
            System.exit(2);
        }
        // source file is good. Move on.
        int intValue = 0;
        StringBuilder textStr = new StringBuilder("");
        StringBuilder targetFileOutput = new StringBuilder();
        FileInputStream fileInput = new FileInputStream(args[0]);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        String[] sourceFileCodes = (String[])(objectInput.readObject());
        int dataSize = objectInput.readInt();

        while ((intValue = fileInput.read()) != -1) {
            textStr.append(readBits(intValue));
        }   // by now, text should only contain binary numbers (0 and 1)
        fileInput.close();  // close input source file
        textStr.delete(dataSize, textStr.length());

        while(textStr.length()!= 0) {
            boolean isValid = false;
            for (int i = 0; i < sourceFileCodes.length; i++) {
                if((sourceFileCodes[i] != null) && (textStr.indexOf(sourceFileCodes[i]) == 0)) {
                    targetFileOutput.append((char)i);
                    textStr.delete(0, sourceFileCodes[i].length());
                    isValid = true;
                    break;
                }
            }
            if(!isValid) {
                System.out.println("Bad source file format");
                System.exit(2);
            }
        }

        // Get ready to write to target file
        DataOutputStream targetFile = new DataOutputStream(new FileOutputStream(args[1]));
        targetFile.write(targetFileOutput.toString().getBytes());
        targetFile.close();
    }   // end main

    /****************************************************************************
     *     public static String readBits(int value)
     * Input: int
     * Return: String 8 bits at a time
     *****************************************************************************/
    public static String readBits(int value) {
        String binaryStr = "";
        int bit = 0;
        int shiftValueToRight;
        value = value % 256;
        shiftValueToRight = value >> bit;
        for (int i = 0; i < 8; i++) {   // up to 8 bits
            binaryStr = (shiftValueToRight & 1) + binaryStr;
            bit++;
            shiftValueToRight = value >> bit;
        }
        return binaryStr;
    }   // end public static String readBits(int value)
}   // end class
