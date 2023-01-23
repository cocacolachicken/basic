package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordList {
    public static void main (String[] args) {
        try {
            initialize();
        } catch (Exception e) {
            e.printStackTrace();

        }

        list = (ArrayList) Filter.letterAtLeastQuantity('a', 1, list);

        printWords();

        try {
            initialize();
        } catch (Exception e) {
            e.printStackTrace();

        }

        System.out.println("------------------");

        list = (ArrayList) Filter.letterInQuantity('a', 2, list);

        printWords();

    }

    private static ArrayList list = new ArrayList<String>();

    /** Sets ArrayList list to the list of words in valid-wordle-words.txt
     *
     * @throws FileNotFoundException valid-wordle-words.txt must not exist
     */
    public static void initialize () throws FileNotFoundException {
        list.clear();

        Scanner scanner = new Scanner(new File("src/valid-wordle-words.txt"));

        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
    }

    /** Sets the current list to another list
     * @param l
     */
    public static void setList (ArrayList l) {
        list = l;
    }

    /** prints every word in the list
     */
    public static void printWords () {
        for (int x = 0; x != list.size(); x++) {
            System.out.println(list.get(x));
        }
        if (list.size() == 0) {
            System.out.println("Nothing");
        }
    }

    public static List getList () {
        return list;
    }

    /** Processes the guess in this method
     *
     * @param g the guess to be processed
     * @return the list after being processed
     */
    public static List processGuess (Guess g) {
        list = (ArrayList) g.processGuess(list);
        return list;
    }

    /** Processes the guess in this method with a new list of words
     *
     * @param g the guess to be processed
     * @return the list after being processed
     */
    public static List processNewGuess (Guess g) {
        try {
            initialize();
        } catch(Exception ignore) {

        }
        list = (ArrayList) g.processGuess(list);
        return list;
    }
}
