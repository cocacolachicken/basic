package main.java;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordsByShannonEntropy {

    public static void main (String[] args) {
        WordList w = new WordList(true);
        w.setList(returnListSorted(w.getList()));
        w.printWords();
    }

    public static List<String> returnListSorted (List<String> words) {
        List<String> l = words.stream().sorted(Comparator.comparing(s -> calculateSEValue(s, words))).collect(Collectors.toList());
        return l;
    }

    public static double calculateSEValue (String w, List words) {
        System.out.println(w);
        double total = 0;
        String s = "";
        for (int x = 0; x != 3; x++) {
            int[] n = {x};
            total += autoLoop(n, w, words);
        }
        return total;
    }

    /**
     * A Sort of recursive loop which loops three times (one for if a letter is correct, is wrong, and is elsewhere)
     * Default case = calcInfo called
     * @param indices tracks the value of each iteration of the loop
     * @param w word that needs its value calculated
     * @param words list of words to run the calcs on
     * @return returns the value of this branch
     */
    public static double autoLoop (int[] indices, String w, List words) {
        double total = 0;
        String s = "";
        for (int x = 0; x != 3; x++) {
            if (indices.length == w.length()) {
                String taken = "";
                for (x = 0; x != indices.length; x++) {
                    if (indices[x] == 0) {
                        s += "n"; // NOT
                    } else if (indices[x] == 1) {
                        s += "c"; // CORRECT
                        taken += x + "";
                    } else if (indices[x] == 2) {
                        s += "e"; // ELSEWHERE
                    }
                }

                return calcInfo(s, taken, w, words);
            } else {
                int[] n = {x};
                total += autoLoop(IntStream.concat(Arrays.stream(indices), Arrays.stream(n)).toArray(), w, words);
            }
        }

        return total;
    }


    /**
     * Calculates info given according to the case given
     * @param pattern a
     * @param w a
     * @param words a
     * @return Mentioned earlier
     */
    public static double calcInfo (String pattern, String taken, String w, List words) {
        double initial = words.size();
        double info = 0;
        String processed = "";
        boolean[] ignore = new boolean[pattern.length()];

        for (int x = 0; x != pattern.length(); x++) {
            if (pattern.charAt(x) == 'n') { // Make sure to account for case where there's a green letter with this green letter
                boolean condition = true;
                for (int j = 0; j != pattern.length(); j++) {
                    if ((pattern.charAt(j) == 'c' || pattern.charAt(j) == 'e') && w.charAt(j) == w.charAt(x)) {
                        condition = false;
                        break;
                    }
                }
                if (condition)
                words = Filter.letterNotIn(w.charAt(x), words);
            } if (pattern.charAt(x) == 'c') {
                words = Filter.letterKnown(x, w.charAt(x), words);
            } if (pattern.charAt(x) == 'e') {
                if (!ignore[x]) {
                    int quantity = 0;
                    String notAt = "";
                    String isAt = "";

                    for (int j = 0; j != pattern.length(); j++) {
                        if (w.charAt(x) == w.charAt(j)) {
                            if (pattern.charAt(j) == 'e') {
                                quantity++;
                                notAt += "" + x;
                            } else if (pattern.charAt(j) == 'n') {
                                notAt += "" + x;
                            } else if (pattern.charAt(j) == 'c') {
                                quantity ++;
                                isAt += "" + x;
                            }
                        }
                    }

                    words = Filter.letterInQuantity(w.charAt(x), notAt, isAt, quantity, words);
                }
            }
        }

        info = words.size();
        if (info != 0) {
            return (info/initial) * (Math.log(1/(info/initial))/Math.log(2));
        } else return 0;
    }
}
