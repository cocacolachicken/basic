package com.company;
import java.util.List;

public class Guess {
    private Letter[] guess = new Letter[5];

    // Code for "stat": g = green, y = yellow, r = red
    public Guess (String str) {



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
