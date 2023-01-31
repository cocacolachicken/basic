package main.java;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** A class to create and deal with int[26][5] arrays, that store the frequency of letters in specific positions in a list
 * @author tyler
 * @version 1.0
 */
public class FrequencyInfo {
    public static int alphaToInt (char c) {
        return Character.toLowerCase(c) - 97;
    }

    /** Returns a 2D array containing information of the number of times that a letter appears in according to position
     * First index is the letter in question, second index is the position in the word that it appears in
     *
     * @param l list of words examined. Assumes they're all 5 letter words
     * @return aforementioned 2d array
     */
    public static int[][] getFrequency (List<String> l) {
        int[][] letterFrequency = new int[26][5];
        String temp;

        for (int x = 0; x != l.size(); x ++) {
            temp = l.get(x);
            for (int z = 0; z != 5; z++) {
                letterFrequency[alphaToInt(temp.charAt(z))][z]++;
            }
        }

        return letterFrequency;
    }

    /** Computes a score : it is the values of freqData[][] added up according to the letters in word.
     * e.g. if my word is "apple", it'll add up the values of [0][0] [15][1], [15][2], [11][3], []4[4]
     *
     * @param freqData Frequency data on whatever word is from, assuming int[word.length()][26]
     * @param word Word to gather the frequency score on
     * @return score of the word according to freqdata
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

    /** Sorts a list of words ("l") by the frequency score of each word according to freq using computeValue(int[][], String)
     * The idea is that the more times a letter appears in the remaining answers, the better it could be used to rule out information.
     *
     * @param l
     * @param freq
     * @return list sorted by the score of word according to freq
     */
    public static List<String> sortByValue (List<String> l, int[][] freq) {
        return l.stream().sorted(Comparator.comparing(s -> (-computeValue(freq, s)))).collect(Collectors.toList());
    }
}
