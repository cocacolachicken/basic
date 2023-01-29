package main.java;
import java.util.List;
import java.util.Random;

/** A class to handle user input from the GUI
 *
 * @author tyler
 * @version 1.0
 */
public class InputProcessor {
    private static WordList l = new WordList(true);
    private static int[][] freq;
    private static Guess g;

    /** Takes in a String "s" and filters WordList based off of the guess
     * If the String "s" is an invalid guess based off of the constructor for the Guess class, then
     * it returns that the input was erroneous
     *
     * @param s The string that is about to be examined
     */
    public static void takeInput (String s) {
        try {
            // Creates a guess
            g = new Guess(s);
            System.out.println("guess created");

            // Processes the guess to get the list of words still allowed
            l.processNewGuess(g, true);

            // Gets frequency info (int[][]) on the list
            freq = FrequencyInfo.getFrequency(l.getList());
            l.setList(FrequencyInfo.sortByValue(l.getList(), freq));
            System.out.println("Success!");

            // Prints words to console
            l.printWords();

        } catch (Exception e) {
            // Something to handle erroneous output
            System.out.println("FAILURE!!!");
        }
    }

    // Gets suggestions: first two are vowel rich, the latter two are common letters
    public static String getSuggestions () {
        return ("Try ADIEU, AUDIO, RAISE, SOARE.");
    }

    public static List<String> getList () {
        return l.getList();
    }
}
