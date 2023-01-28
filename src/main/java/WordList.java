package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordList {


    public WordList (boolean answers) {
        try {
            if (answers) initializeAnswers();
            else initialize();
        } catch (Exception ignored) {

        }
    }

    private List<String> list = new ArrayList<String>();

    /** Sets List list to the list of words in valid-wordle-words.txt
     *
     * @throws FileNotFoundException valid-wordle-words.txt must not exist
     */
    public void initialize () throws FileNotFoundException {
        list.clear();

        Scanner scanner = new Scanner(new File("src/valid-wordle-words.txt"));

        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
    }

    /** Sets List list to the list of words in wordle-answers-alphabetical.txt
     *
     * @throws FileNotFoundException wordle-answers-alphabetical.txt must not exist
     */
    public void initializeAnswers () throws FileNotFoundException {
        list.clear();

        Scanner scanner = new Scanner(new File("src/wordle-answers-alphabetical.txt"));

        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
    }

    /** Sets the current list to another list
     * @param l
     */
    public void setList (List<String> l) {
        list = l;
    }

    /** prints every word in the list
     */
    public void printWords () {
        for (int x = 0; x != list.size(); x++) {
            System.out.println(list.get(x));
        }
        if (list.size() == 0) {
            System.out.println("Nothing");
        }
    }

    public List<String> getList () {
        return list;
    }

    /** Processes the guess in this method
     *
     * @param g the guess to be processed
     * @return the list after being processed
     */
    public List<String> processGuess (Guess g) {
        list = g.processGuess(list);
        return list;
    }

    /** Processes the guess in this method with a new list of words
     *
     * @param g the guess to be processed
     * @return the list after being processed
     */
    public List<String> processNewGuess (Guess g) {
        try {
            initialize();
        } catch(Exception ignore) {

        }
        list = g.processGuess(list);
        return list;
    }
}
