package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Filter {
    /** Combs through a list of words ("words") and returns a sublist of "words" such that the elements retained in the sublist
     * are words that have letter "letter" at index "position"
     *
     * @param position position that the letter is in
     * @param letter the letter that wants to be found
     * @param words list of words that needs to be filtered
     * @return filtered list of words based off of the constraints given
     */
    public static List letterKnown (int position, char letter, ArrayList<String> words) {
        return words.stream().filter((String s) -> (s.charAt(position) == letter)).collect(Collectors.toList());
    }

    /** Combs through a list of words ("words") and returns a sublist of "words" such that the elements retained in the sublist
     * are words that contain letter "letter" but not at any of the indexes in "taken"
     * @param taken a string that contains all the positions of the indexes that are excluded from the sublist (e.g. "012" would exclude the first three letters, "3" would exclude the fourth)
     * @param letter the letter that is being found
     * @param words list of words that needs to be filtered
     * @return filtered list of words based off of the constraints given
     */
    public static List letterHas (String taken, char letter, ArrayList<String> words) {
        List<String> l = words;

        l = words.stream().filter((String s) -> (s.contains(letter + ""))).collect(Collectors.toList());

        for (int x = 0; x != taken.length(); x++) {
            int finalX = x;
            l = l.stream().filter((String s) -> (letter != s.charAt(Integer.parseInt(taken.charAt(finalX) + "")))).collect(Collectors.toList());
        }

        return l;
    }

    /** Combs through a list of words ("words") and returns a sublist of "words" such that the elements retained in the sublist
     * are words that do not contain letter "letter"
     * @param letter letter that is being excluded from the list
     * @param words list of words that needs to be filtered
     * @return filtered list of words based off of the constraints given
     */
    public static List letterNotIn (char letter, ArrayList<String> words) {
        return words.stream().filter((String s) -> (!s.contains(letter + ""))).collect(Collectors.toList());
    }

    /** Combs through a list of words ("words") and returns a sublist of "words" such that the elements retained in the sublist
     *
     * Used when it is known how much of a certain letter is in
     * e.g. "apple" is entered and only one of the p's is yellow means that there's only one p in the word
     *
     * @param letter
     * @param quantity
     * @param words list of words that needs to be filtered
     * @return filtered list of words based off of the constraints given
     */
    public static List letterInQuantity (char letter, int quantity, ArrayList<String> words) {
        Pattern pattern = Pattern.compile("[" + letter + "]");
        return words.stream().filter((String s) -> {
            Matcher m = pattern.matcher(s);
            int count = 0;
            while (m.find()) {
                count++;
            }
            return count == quantity;
        }).collect(Collectors.toList());
    }

    /** Combs through a list of words ("words") and returns a sublist of "words" such that the elements retained in the sublist
     *
     *
     * Used when it's known that there's more than one of a letter
     * e.g. "guava" is entered as a guess and one a being yellow and the other green tells us there are at least two a's in the word
     *
     * @param letter
     * @param quantity least amount of times that a letter appears in a word
     * @param words list of words that needs to be filtered
     * @return filtered list of words based off of the constraints given
     */
    public static List letterAtLeastQuantity (char letter, int quantity, ArrayList<String> words) {
        Pattern pattern = Pattern.compile("[" + letter + "]");
        return words.stream().filter((String s) -> {
            Matcher m = pattern.matcher(s);
            int count = 0;
            while (m.find()) {
                count++;
            }
            return count > quantity;
        }).collect(Collectors.toList());
    }
}
