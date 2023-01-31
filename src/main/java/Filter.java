package main.java;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/** A class that contains methods to filter lists of words based off of constraints given
 *
 * @author tyler
 * @version 1.0
 */
public class Filter {
    /** Combs through a list of words ("words") and returns a sublist of "words" such that the elements retained in the sublist
     * are words that have letter "letter" at index "position"
     *
     * @param position position that the letter is in
     * @param letter the letter that wants to be found
     * @param words list of words that needs to be filtered
     * @return filtered list of words based off of the constraints given
     */
    public static List<String> letterKnown (int position, char letter, List<String> words) {
        return words.stream().filter((String s) -> (s.charAt(position) == letter)).collect(Collectors.toList());
    }

    /** Combs through a list of words ("words") and returns a sublist of "words" such that the elements retained in the sublist
     * are words that contain letter "letter" but not at any of the indices in "taken"
     * @param taken a string that contains all the positions of the indices that are excluded from the sublist (e.g. "012" would exclude the first three letters, "3" would exclude the fourth)
     * @param letter the letter that is being found
     * @param words list of words that needs to be filtered
     * @return filtered list of words based off of the constraints given
     */
    public static List<String> letterHas (String taken, char letter, List<String> words) {
        List<String> l;

        // Filters words that don't contain letter
        l = words.stream().filter((String s) -> (s.contains(letter + ""))).collect(Collectors.toList());

        // Filters words that have letter at any of the positions in "taken"
        for (int x = 0; x != taken.length(); x++) {
            int finalX = x;
            l = l.stream().filter((String s) -> (letter != s.charAt(Integer.parseInt(taken.charAt(finalX) + "")))).collect(Collectors.toList());
        }

        return l;
    }

    /** Combs through a list of words ("words") and returns a sublist of "words" such that the elements retained in the sublist
     * are words that do not contain letter "letter"
     *
     * @param letter letter that is being excluded from the list
     * @param words list of words that needs to be filtered
     * @return filtered list of words based off of the constraints given
     */
    public static List<String> letterNotIn (char letter, List<String> words) {
        return words.stream().filter((String s) -> (!s.contains(letter + ""))).collect(Collectors.toList());
    }

    /** Combs through a list of words "words" and returns a sublist of "words" such that the elements retained in the sublist
     * are words that do not contain letter "letter" at index x
     * This DOES not make it so that words that contain letter letter are out: e.g. "hello" would not be removed if
     * this method was called with the parameters as letter = 'e', x = 3, and the list contained "hello"
     *
     * @param x index that the letter cannot be in for the word to pass the filter
     * @param letter letter to be found
     * @param words words to be processed
     */
    public static List<String> letterNotAt (int x, char letter, List<String> words) {
        return words.stream().filter((String s) -> (s.charAt(x) != letter)).collect(Collectors.toList());
    }

    /** Combs through a list of words ("words") and returns a sublist of "words" such that the elements retained in the sublist
     *
     * Used when it is known how much of a certain letter is in
     * e.g. "apple" is entered and only one of the p's is yellow means that there's only one p in the word
     *
     * @param letter letter to be found
     * @param notAt indices of a word that a letter must NOT be in
     * @param isAt indices of a word that a letter MUST be in
     * @param quantity the quantity that a letter must appear in
     * @param words list of words that needs to be filtered
     * @return filtered list of words based off of the constraints given
     */
    public static List<String> letterInQuantity (char letter, String notAt, String isAt, int quantity, List<String> words) {
        List<String> l = words;
        Pattern pattern = Pattern.compile("[" + letter + "]");

        // Filters words that don't contain the letter quantity times
        l = l.stream().filter((String s) -> {
            Matcher m = pattern.matcher(s);
            int count = 0;
            while (m.find()) {
                count++; // Counts the amount of times that there's a match of letter in the word
            }
            return count == quantity;
        }).collect(Collectors.toList());

        // Filters out words that don't contain letter at the indices given in isAt
        for (int x = 0; x != isAt.length(); x++) {
            l = letterKnown(Integer.parseInt(isAt.charAt(x) + ""), letter, l);
        }

        // Filters out words that contain letter at the indices given in notAt
        for (int x = 0; x != notAt.length(); x++) {
            l = letterNotAt(Integer.parseInt(notAt.charAt(x) + ""), letter, l);
        }

        return l;
    }

    /** Combs through a list of words ("words") and returns a sublist of "words" such that the elements retained in the sublist
     *
     * Used when it's known that there's more than one of a letter
     * e.g. "guava" is entered as a guess and one a being yellow and the other green tells us there are at least two a's in the word
     *
     * @param letter the letter to be found
     * @param quantity least amount of times that a letter must appear in a word for that word to be retained
     * @param words list of words that needs to be filtered
     * @return filtered list of words based off of the constraints given
     */
    public static List<String> letterAtLeastQuantity (char letter, int quantity, List<String> words) {
        // Filters words that don't contain the letter quantity times
        Pattern pattern = Pattern.compile("[" + letter + "]");
        return words.stream().filter((String s) -> {
            Matcher m = pattern.matcher(s);
            int count = 0;
            while (m.find()) {
                count++; // Counts the amount of times that there's a match of letter in the word
            }
            return count > quantity;
        }).collect(Collectors.toList());
    }
}
