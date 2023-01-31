package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Heavily inspired by https://www.youtube.com/watch?v=v68zYyaEmEA, I would not have gotten the idea if it wasn't for that video
 * also used https://www.youtube.com/watch?v=bkLHszLlH34 to help me understand the concept better
 *
 * @author tyler
 * @version 1.0
 * @deprecated not optimized
 */
public class WordsByShannonEntropy {

    public static void main (String[] args) {
        WordList w = new WordList(true);
        w.setList(returnListSorted(w.getList()));

        w.printWords();
    }

    /** Returns a list sorted such that the words with the lower calculated uncertainty (based off of "Shannon Entropy")
     * are after words with a higher calculated uncertainty.
     *
     * Uncertainty is the average value of information given based off of all the probable guesses
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

    /** The same as the previous class, except it accounts for EVERY POSSIBLE WORDLE WORD
     *
     *
     * ** EVEN WORSE SPACE AND TIME COMPLEXITY!!!! Do not use. **
     * @param wordsLeft List of words that are still able to be chosen
     * @return a list of every possible word ranked based off of how much information it'll give
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
     * Essentially, it adds up all the "information given" values of each possible branches
     *
     * Higher value = more uncertain, meaning it's likely more information will be given following that specific guess.
     * @param w word to be calculated
     * @param words list of words to calculate it based off of
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
     * A recursive loop which nests a loop (that does so three times) for each letter in "w" (one for if a letter
     * is correct, one for if a letter is wrong, and one for if a letter is elsewhere).
     *
     *
     * Default case = calcInfo called
     * @param indices tracks the value of each iteration of the loop
     * @param w word that needs its value calculated
     * @param words list of words to run the calcs on
     * @return returns the value of the branch
     */
    public static double autoLoop (int[] indices, String w, List<String> words) {
        double total = 0;
        StringBuilder s = new StringBuilder();
        for (int x = 0; x != 3; x++) {
            if (indices.length == w.length()) { // Base case
                // Compiles a pattern based off of int[] indices;
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
                // Recursively calls itself to layer another loop, adding its own index to the array.
                int[] n = {x};
                total += autoLoop(IntStream.concat(Arrays.stream(indices), Arrays.stream(n)).toArray(), w, words);
            }
        }

        return total;
    }


    /**
     * Calculates the value of information given according to the following equation:
     * let x = number of words which are still able to be found after a pattern is shown
     * (x/number of words) * log2(number of words / x)
     *
     * @param pattern the pattern in question. every character corresponds to a letter in string "w" – n for not in, c
     *                for correct, and e for elsewhere
     * @param w a
     * @param words a
     * @return Mentioned earlier
     */
    public static double calcInfo (String pattern, String taken, String w, List<String> words) {
        double initial = words.size();
        boolean[] ignore = new boolean[pattern.length()];
        int patternSize = pattern.length();

        for (int x = 0; x != patternSize; x++) {
            if (pattern.charAt(x) == 'n') {
                boolean condition = true;
                for (int j = 0; j != patternSize; j++) {
                    if ((pattern.charAt(j) == 'c' || pattern.charAt(j) == 'e') && w.charAt(j) == w.charAt(x)) {
                        condition = false;
                        break;
                    } // If there's a letter that is the same letter as the one being examined and its matching pattern
                    // letter isn't 'n', then it ignores taking away all words that contains the letter n.
                    // This is so that if you have a case where you have a duplicate letter though one is green
                    // and the other gray it doesn't take away all words that have that duplicate letter
                }
                if (condition) {
                    words = Filter.letterNotIn(w.charAt(x), words);
                }
            } if (pattern.charAt(x) == 'c') {
                // Cuts out words that don't contain the letter at index x
                words = Filter.letterKnown(x, w.charAt(x), words);
            } if (pattern.charAt(x) == 'e') {
                if (!ignore[x]) { // Makes it so that it computes a yellow in a certain letter just once; e.g. two yellow "p"'s in apple will only have it be processed in the first p
                    int quantity = 0; // counts amount of p's in the word
                    StringBuilder notAt = new StringBuilder();
                    StringBuilder isAt = new StringBuilder();
                    boolean alreadyDone = false; // Condition to disqualify certain invalid cases;

                    for (int j = 0; j != patternSize; j++) { // Loops through the pattern to check for duplicate letters, because the yellow is super dependent on this
                        if (w.charAt(x) == w.charAt(j)) {
                            if (pattern.charAt(j) == 'e') {
                                quantity++;
                                notAt.append(x); // Adds a space where the character isn't at
                                ignore[x] = true;
                                if (alreadyDone) return 0; // Invalidates; explained below
                            } else if (pattern.charAt(j) == 'n') {
                                notAt.append(x); // Adds a space where the character isn't at
                                ignore[x] = true;
                                alreadyDone = true; // Invalidates the case of a yellow duplicate coming after a letter that is gray
                                // E.G. all cases where the second p of the word apple is yellow but the first gray are invalid because a yellow space is awarded to the first match
                            } else if (pattern.charAt(j) == 'c') { //
                                quantity ++;
                                isAt.append(x); // Adds a space where the character must be at
                                ignore[x] = true;
                            }
                        }
                    }

                    // Calls the method to actually filter
                    words = Filter.letterInQuantity(w.charAt(x), notAt + taken, isAt.toString(), quantity, words);
                }
            }
        }

        double info = words.size();

        if (info != 0) {
            // Returns the information given by a guess;
            return (info / initial) * (Math.log(initial/info) / Math.log(2));
        } else {
            return 0;
        }
    }


    /**
     * class used to store information about a word and its entropy value
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