package com.company;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester {

    public static void main (String args[]) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        WordList.initialize();
        boolean wordNotFound = true;
        String guess = "";


        while (wordNotFound) {
            System.out.println("Enter in a guess (5 letters): ");
            guess = sc.nextLine();
            while (guess.length() != 5 || !guess.matches("^[a-zA-Z]*$")) {
                System.out.println("Not valid, enter in another: ");
                guess = sc.nextLine();
            }
            System.out.println("Processing " + guess);
        }
    }


}
