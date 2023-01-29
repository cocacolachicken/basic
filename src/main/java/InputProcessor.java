package main.java;
import java.util.List;
import java.util.Random;

/**
 * @author tyler
 * @version 1.0
 */
public class InputProcessor {
    private static WordList l = new WordList(true);
    private static int[][] freq;

    /** Takes in a String "s" and filters WordList based off of the guess
     * If the String "s" is an invalid guess based off of the constructor for the Guess class, then
     * it returns that the input was erroneous
     *
     * @param s The string that is about to be examined
     */
    public static void takeInput (String s) {
        Guess g;
        try {
            g = new Guess(s);
            System.out.println("guess created");
            freq = FrequencyInfo.getFrequency(l.getList());
            l.processNewGuess(g, true);
            l.setList(FrequencyInfo.sortByValue(l.getList(), freq));
            System.out.println("Success!");
            l.printWords();

        } catch (Exception e) {
            // Something to handle erroneous output
            System.out.println("FAILURE!!!");
        }
    }

    public static String[] getRandomWords (int x, List<String> l) {
        String[] s = new String[x];
        Random r = new Random();

        for (int i = 0; i != x; i ++) {
            System.out.println("s");
            s[i] = l.get(r.nextInt(l.size()));
        }

        return s;
    }

    public static String getSuggestions () {
        return ("Try ADIEU, AUDIO, RAISE, SOARE, STARE, TREAD.");
    }


    public static List<String> getList () {
        return l.getList();
    }
}
