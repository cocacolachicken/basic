package main.java;

public class Letter {
    private char letter;
    private LetterStatus status;

    public Letter () {
        this.letter = '?';
        this.status = LetterStatus.UNKNOWN;
    }

    public Letter (char l) {
        letter = l;
        if (Character.isLowerCase(l)) {
            this.status = LetterStatus.ELSEWHERE;
        } else {
            this.status = LetterStatus.IN;
        }
    }

    public LetterStatus getStatus () {
        return status;
    }

    public char getLetter() {
        return letter;
    }
}
