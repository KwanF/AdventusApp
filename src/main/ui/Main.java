package ui;

import java.io.FileNotFoundException;

// Starts the console application
public class Main {
    public static void main(String[] args) {
        try {
            new AdventusApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}