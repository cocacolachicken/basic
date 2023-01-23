package main.java;

public class Letter {
    private char letter;
    private LetterStatus status;

    /** Constructs an unknown letter
     *
     */
    public Letter () {
        this.letter = '?';
        this.status = LetterStatus.UNKNOWN;
    }

    /** Constructs Letter, and takes the status based off of the capitalization of the letter
     * ELSEWHERE is used if it's lowercase, otherwise it's considered to be IN
     * @param l
     */
    public Letter (char l) {
        letter = Character.toLowerCase(l);
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
