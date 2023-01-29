package main.java;

import java.util.List;

/**
 * @author tyler
 * @version 1.0
 */
public class Guess {
    private final Letter[] guess = new Letter[5];

    /** Constructs a guess based off of string "str"
     *
     * @param str MUST only contain upper/lowercase characters or the special character '?'. else it will throw Exception
     * @throws Exception Thrown if the string doesn't match the constraints
     */
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

    /** Returns a Letter from this guess
     *
     * @param x the index of the guess needed
     * @return the letter requested based off of index x
     */
    public Letter getGuessLetter (int x) {
        return guess[x];
    }

    /** Processes the list of words, uses inputs based off of guess[] in Filter to process it
     *
     * If guess[x] is unknown, it does nothing.
     * If guess[x] is IN, then it trims down the list of words to a list of words such that all words have letter guess[x] at position x
     * If guess[x] is ELSEWHERE, then it trims down the list of words to a list of words such that the letter contains letter guess[x] other than words with that letter at position
     * at a letter where guess[num] is IN or the position of guess[x]
     *
     * Note: please use it like "list = guessName.processGuess(list);" because list will get depleted after he usage
     * @param words the list of words that will be processed according to the
     * @return the list of words after filtering words based off of this guess
     */
    public List<String> processGuess (List<String> words) {
        List<String> l = words;
        String elsewhere = "";
        for (int x = 0; x != 5; x++) {
            if (guess[x].getStatus() == LetterStatus.IN) {
                elsewhere += Integer.toString(x);
            }
        }

        for (int x = 0; x != 5; x++) {
            if (guess[x].getStatus() == LetterStatus.IN) {
                l = Filter.letterKnown(x, guess[x].getLetter(), l);
            } else if (guess[x].getStatus() == LetterStatus.ELSEWHERE) {
                l = Filter.letterHas(elsewhere + x, guess[x].getLetter(), l);
            }
        }
        return l;
    }
}
