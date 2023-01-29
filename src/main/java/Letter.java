package main.java;

/** A class to represent a letter in a Wordle guess
 * @author tyler
 * @version 1.0
 */
public class Letter {
    private char letter;
    private LetterStatus status;

    /** Constructs an unknown letter
     */
    public Letter () {
        this.letter = '?';
        this.status = LetterStatus.UNKNOWN;
    }

    /** Constructs Letter, and takes the status based off of the capitalization of the letter
     * ELSEWHERE is used if it's lowercase, otherwise it's considered to be IN
     *
     * @param l the letter to be made
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
