package main.java;

import java.io.FileNotFoundException;

public class FrequencyInfo {
    public static int alphaToChar (char c) {
        return Character.toLowerCase(c) - 97;
    }

    // This was just written to get info quickly rather than doing this every time it starts up
    public static void main (String args[]) throws FileNotFoundException {
        WordList.initializeAnswers();
        int[] letters = new int[26];

        for (int x = 0; x != WordList.getList().size(); x++) {
            for (int z = 0; z != 5; z++) {
                letters[alphaToChar(Character.toLowerCase(((String)WordList.getList().get(x)).charAt(z)))] ++;
            }
        }

        for (int x = 0; x != 26; x++) {
            System.out.println( (char) (x+65) + ": " + letters[x]);
        }
    }
}
