package main.java;

import java.util.ArrayList;
import java.util.Scanner;

/** A console-based interface for the application.
 * @author tyler
 * @version 1.0
 */
public class Tester {

    // Class for the terminal output of the program
    public static void main (String args[]) throws Exception {
        WordList l = new WordList(true);
        l.initialize();
        l.processNewGuess(new Guess("a????"), true);
        l.printWords();
        l.processNewGuess(new Guess("CULTS"), true);
        l.printWords();

        Scanner sc = new Scanner(System.in);
        boolean wordNotFound = true;
        String guess = "";
    }


}
