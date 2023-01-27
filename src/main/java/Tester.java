package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Tester {

    public static void main (String args[]) throws Exception {
        WordList l = new WordList(true);
        l.initialize();
        l.processNewGuess(new Guess("a????"));
        l.printWords();
        l.processNewGuess(new Guess("CULTS"));
        l.printWords();

        Scanner sc = new Scanner(System.in);
        boolean wordNotFound = true;
        String guess = "";


        while (wordNotFound) {

        }
    }


}
