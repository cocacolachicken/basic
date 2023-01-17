package com.company;

public class Letter {
    private char letter;
    private LetterStatus status;

    public Letter (char l, char s) {
        letter = l;
        if (s == 'g') {
            status = LetterStatus.IN;
        } else if (s == 'y') {
            status = LetterStatus.ELSEWHERE;
        } else if (s == 'r') {
            status = LetterStatus.NOT;
        }
    }

    public LetterStatus getStatus () {
        return status;
    }

    public char getLetter() {
        return letter;
    }
}
