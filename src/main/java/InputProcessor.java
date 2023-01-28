package main.java;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;

public class InputProcessor {
    public static WordList l = new WordList(true);

    /** Takes in a String "s" and filters WordList based off of the guess
     * If the String "s" is an invalid guess based off of the constructor for the Guess class, then
     * it returns that the input was erroneous
     *
     * @param s The string that is about to be examined
     */
    public static void takeInput (String s) {
        Guess g;
        try {
            System.out.println("aaa");
            g = new Guess(s);
            System.out.println("guess created");
            l.processGuess(g);
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
            s[i] = (String) l.get(r.nextInt(l.size()));
        }

        return s;
    }

    public static String[] getSuggestions () {
        return new String[]{"ADIEU", "AUDIO", "RAISE", "SOARE", "STARE", "TREAD"};
    }

    public static List getList () {
        return l.getList();
    }
}
