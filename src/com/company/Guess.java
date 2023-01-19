package com.company;
import java.util.List;

public class Guess {
    public static void main (String args[]) {
        try {
            new Guess("as");
        } catch (Exception e) {
            System.out.println("Unsuccessful 1");
        }
        try {
            new Guess("mna*m");
        } catch (Exception e) {
            System.out.println("Unsuccessful 2");
        }
        try {
            new Guess("Board");
        } catch (Exception e) {
            System.out.println("Unsuccessful 3");
        }
        try {
            new Guess("bOArD");
        } catch (Exception e) {
            System.out.println("Unsuccessful 4");
        }
    }

    private Letter[] guess = new Letter[5];

    // Code for "stat": g = green, y = yellow, r = red
    public Guess (String str) throws Exception {
        if (str.length() != 5) {
            throw new Exception ("Please enter in a 5 letter word");
        } else if (!str.matches("^[a-zA-Z?]*$")) {
            throw new Exception ("Please enter in only upper or lowercase letters");
        }


        for (int x = 0; x != 5; x++) {

            if (str.charAt(x) == '?') {
                guess[x] = new Letter();
            } else {
                guess[x] = new Letter(str.charAt(x));
            }
        }
    }

    public Letter getGuessLetter (int x) {
        return guess[x];
    }

    public void processGuess (List words) {

    }
}
