/*
File: CreateFile.java
Author: Ethan Nephew
Assignment: Midterm Project
Date due: October 18, 2020
Course: COP3300C
Description: Create and write to file class with exception handling
*/

package MidtermProjectEthanNephew;

import java.io.*;

public class CreateFile {
    private static boolean error = false; // default
    public void writeToFile(String string) {
        // It seems to be necessary to create this object for every instance of file writing.
        File file = null;
        file = new File("MidtermEthanNephew.txt"); // creates file in this location
        FileWriter fw = null;
            //file = new File("MidtermProjectVersion2//MidtermEthanNephew.txt"); // creates file in this location
            if (!error) { // if an exception is throw, then it will not continue trying to write to a file.
                try {
                    fw = new FileWriter(file, true);
                } catch (FileNotFoundException fnfe) {
                    error = true;
                    System.out.println("There was a file not found exception. Please verify file pathway.");
                    //fnfe.printStackTrace(); // print stack
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    if (!error) { // make sure a file not found exception or ioexception have not occurred.
                        try {
                            PrintWriter pw = new PrintWriter(fw);
                            pw.println(string);
                            pw.close();
                        } catch (NullPointerException npe){
                            error = true;
                            System.out.println("There was a null pointer exception. Please verify file pathway.");
                            npe.printStackTrace();
                        }
                    }
            }
    }
}



