package com.company;

import java.util.HashMap;

public class Guess {
    private Letter[] guess = new Letter[5];

    // Code for stat: g = green, y = yellow, r = red
    public Guess (String str, String stat) {
        for (int x = 0; x != 5; x++) {
            guess[x] = new Letter(str.toLowerCase().charAt(x), stat.toLowerCase().charAt(x));
        }
    }

    public Letter getGuessLetter (int x) {
        return guess[x];
    }
}
