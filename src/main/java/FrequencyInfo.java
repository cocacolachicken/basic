package main.java;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tyler
 * @version 1.0
 */
public class FrequencyInfo {
    public static int alphaToInt (char c) {
        return Character.toLowerCase(c) - 97;
    }

    /**
     *
     * @param l list of words examined
     * @return aforementioned 2d array
     */
    public static int[][] getFrequency (List<String> l) {
        int[][] letterFrequency = new int[26][5];
        String temp = "";

        for (int x = 0; x != l.size(); x ++) {
            temp = l.get(x);
            for (int z = 0; z != 5; z++) {
                letterFrequency[alphaToInt(temp.charAt(z))][z]++;
            }
        }

        return letterFrequency;
    }

    /**
     *
     * @param freqData
     * @param word
     * @return
     */
    public static int computeValue (int[][] freqData, String word) {
        int value = 0;
        int penalty = 0;

        for (int x = 0; x != word.length(); x++) {
            if (word.indexOf(word.toLowerCase().charAt(x), x+1) != -1) {
                penalty = 1;
            }
            value += freqData[alphaToInt(word.charAt(x))][x];
        }

        value /= Math.pow(2, penalty);

        return value;
    }

    /**
     *
     * @param l
     * @param freq
     * @return
     */
    public static List<String> sortByValue (List<String> l, int[][] freq) {
        return l.stream().sorted(Comparator.comparing(s -> (-computeValue(freq, s)))).collect(Collectors.toList());
    }
}
