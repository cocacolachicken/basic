package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * @author tyler
 * @version 1.0
 */
public class WordsByShannonEntropy {

    public static void main (String[] args) {
        WordList w = new WordList(true);
        w.setList(returnListSorted(w.getList()));

        w.printWords();
    }

    /** Returns a list sorted such that the words with the lower calculated uncertainty (based off of "Shannon Entropy")
     * are after words with a higher calculated uncertainty
     *
     * Note: words with duplicate letters are considered with 1/3 of the calculated value. This is because words with
     * duplicate letters tend to have a lot of duplicate cases which will result in
     * a higher value for calculated entropy, especially for words with duplicate "e's."
     *
     * **VERY BAD TIME AND SPACE COMPLEXITY!!!**
     * It works but like it'll take ages.
     *
     * @param words list of words inputed
     * @return aforementioned
     */
    public static List<String> returnListSorted (List<String> words) {
        List<Word> w = new ArrayList<>();

        int size = words.size();

        for (int x = 0; x != size; x++) {
            w.add(new Word(words.get(x), words));
        }

        w = w.stream().sorted(Comparator.comparing(Word::getVal).reversed()).collect(Collectors.toList());

        return w.stream().map(Word::getWord).collect(Collectors.toList());
    }

    /**
     *
     *
     * ** EVEN WORSE SPACE AND TIME COMPLEXITY!!!! Do not use. **
     * @param wordsLeft
     * @return
     */
    public static List<String> returnInformationList (List<String> wordsLeft) {
        List<Word> w = new ArrayList<>();
        WordList wl = new WordList(false);
        int size = wl.getList().size();
        for(int x = 0; x != size; x++) {
            w.add(new Word(wl.getList().get(x), wordsLeft));
        }

        w = w.stream().sorted(Comparator.comparing(Word::getVal).reversed()).collect(Collectors.toList());

        return w.stream().map(Word::getWord).collect(Collectors.toList());
    }

    /** Calculates the average uncertainty for a given word (String w) to the possible outcomes
     *
     *
     * Higher value = more uncertain, meaning it's likely more information will be given following that specific guess.
     * @param w
     * @param words
     * @return
     */
    public static double calculateSEValue (String w, List<String> words) {
        double total = 0;
        String s = "";
        for (int x = 0; x != 3; x++) {
            int[] n = {x};
            total += autoLoop(n, w, words);
        }

        if (check(w)) { // For duplicate letters
            // 1.5 is an arbitrary number; this is a bit of a band-aid solution to the fact that the way that
            // the info given is calculated will have a lot of overlapping values
            total /= 1.5;
        }

        return total;
    }

    /**
     * A recursive loop which nests 4 loops (one for if a letter is correct, is wrong, and is elsewhere)
     * Default case = calcInfo called
     * @param indices tracks the value of each iteration of the loop
     * @param w word that needs its value calculated
     * @param words list of words to run the calcs on
     * @return returns the value of this branch
     */
    public static double autoLoop (int[] indices, String w, List<String> words) {
        double total = 0;
        StringBuilder s = new StringBuilder();
        for (int x = 0; x != 3; x++) {
            if (indices.length == w.length()) {
                StringBuilder taken = new StringBuilder();
                for (x = 0; x != indices.length; x++) {
                    if (indices[x] == 0) {
                        s.append("n"); // NOT
                    } else if (indices[x] == 1) {
                        s.append("c"); // CORRECT
                        taken.append(x);
                    } else if (indices[x] == 2) {
                        s.append("e"); // ELSEWHERE
                    }
                }

                return calcInfo(s.toString(), taken.toString(), w, words);
            } else {
                int[] n = {x};
                total += autoLoop(IntStream.concat(Arrays.stream(indices), Arrays.stream(n)).toArray(), w, words);
            }
        }

        return total;
    }


    /**
     * Calculates information that will be given based off of if a guess ("w") matches a certain "case"
     *
     * @param pattern a
     * @param w a
     * @param words a
     * @return Mentioned earlier
     */
    public static double calcInfo (String pattern, String taken, String w, List<String> words) {
        double initial = words.size();
        boolean[] ignore = new boolean[pattern.length()];
        int patternSize = pattern.length();

        for (int x = 0; x != patternSize; x++) {
            if (pattern.charAt(x) == 'n') { // Make sure to account for case where there's a green letter with this green letter
                boolean condition = true;
                for (int j = 0; j != patternSize; j++) {
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
                    StringBuilder notAt = new StringBuilder();
                    StringBuilder isAt = new StringBuilder();
                    boolean alreadyDone = false;

                    for (int j = 0; j != patternSize; j++) {
                        if (w.charAt(x) == w.charAt(j)) {
                            if (pattern.charAt(j) == 'e') {
                                quantity++;
                                notAt.append(x);
                                ignore[x] = true;
                                if (alreadyDone) return 0;
                            } else if (pattern.charAt(j) == 'n') {
                                notAt.append(x);
                                ignore[x] = true;
                                alreadyDone = true;
                            } else if (pattern.charAt(j) == 'c') {
                                quantity ++;
                                isAt.append(x);
                                ignore[x] = true;
                            }
                        }
                    }

                    words = Filter.letterInQuantity(w.charAt(x), notAt + taken, isAt.toString(), quantity, words);
                }
            }
        }

        double info = words.size();

        if (info != 0) {
            return (info / initial) * (Math.log(1 / (info / initial)) / Math.log(2));
        } else {
            return 0;
        }
    }


    /**
     * A class used to store information about a word and its entropy value
     */
    private static class Word {
        private String w;
        private double v;

        public Word (String word, List<String> l) {
            w = word;
            v = calculateSEValue(word, l);
        }

        public Double getVal () {
            return v;
        }

        public String getWord () {
            return w;
        }
    }

    /** Checks if a string (s) contains duplicate letters
     * @param s string to be examined
     * @return true if contains, false if doesn't
     */
    public static boolean check (String s) {
        for (int x = 0; x != s.length(); x++) {
            if (s.indexOf(s.charAt(x), x+1) != -1) {
                return true;
            }
        }
        return false;
    }
}
