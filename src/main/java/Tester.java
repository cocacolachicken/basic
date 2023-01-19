package main.java;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {

    public static void main (String args[]) throws Exception {
        WordList.initialize();
        WordList.processNewGuess(new Guess("a????"));
        WordList.printWords();
        WordList.processNewGuess(new Guess("CULTS"));
        WordList.printWords();

        Scanner sc = new Scanner(System.in);
        boolean wordNotFound = true;
        String guess = "";
        ArrayList l = null;


        while (wordNotFound) {
            System.out.println("Enter in a guess (5 letters): ");
            guess = sc.nextLine();
            while (guess.length() != 5 || !guess.matches("^[a-zA-Z?]*$")) {
                System.out.println("Not valid, enter in another: ");
                guess = sc.nextLine();
            }
            System.out.println("Processing " + guess);

            Guess g = new Guess(guess);
            for (int x = 0; x != 5; x++) {
                System.out.println(g.getGuessLetter(x).getStatus());
            }

            l = ((ArrayList) g.processGuess(WordList.getList()));

            for (int x = 0; x != l.size(); x++) {
                System.out.println(l.get(x));
            }
        }
    }


}
