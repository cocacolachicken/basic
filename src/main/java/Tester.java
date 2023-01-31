package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** A console-based interface for the application.
 * @author tyler
 * @version 1.0
 */
public class Tester {

    // Class for the terminal output of the program
    public static void main (String args[]) throws Exception {
        System.out.println("--WORDLE WORD FINDER--");
        System.out.println("This wordle game assistant helps users find words.");
        System.out.println("Words will filtered by constraints given and then be ranked by letter diversity and how often a letter appears in a word.");

        WordList l = new WordList(true);

        Scanner sc = new Scanner(System.in);
        boolean wordNotFound = true;
        String input;
        int[][] freqData;
        Guess g;

        while (wordNotFound) {
            System.out.println();
            System.out.println("Enter in a command (type in help for help for commands): ");
            l.initializeAnswers();
            input = sc.nextLine();
            if (input.equals("help")) {
                System.out.println();
                System.out.println("guess: used as \"guess ?????\", with the question marks representing lower or uppercase letters or the special character \"?\".");
                System.out.println("Green letters are represented by uppercase, yellow letters by lowercase, and gray letters are ignored and instead replaced with question marks.");
                System.out.println();
                System.out.println("suggest: used as \"suggest\", gives suggestions on first guesses.");
                System.out.println();
                System.out.println("found: used as \"found\", exits the program.");
            } else if (input.contains("guess")) {
                if (input.length() == 11) {
                    System.out.println();
                    try {
                        g = new Guess(input.substring(6));
                        l.setList(g.processGuess(l.getList()));
                        freqData = FrequencyInfo.getFrequency(l.getList());
                        l.setList(FrequencyInfo.sortByValue(l.getList(), freqData));
                        if (l.getList().size() != 0) {
                            paginate(l.getList());
                            System.out.println("If you found your word, please type in found as your next command.");
                        } else System.out.println("No answers found, please try again");
                    } catch (Exception e) {
                        System.out.println("Please try again");
                    }
                } else {
                    System.out.println("Please try again");
                }
            } else if (input.equals("suggest")) {
                System.out.println();
                System.out.println("For a vowel-rich answer, try adieu or audio.");
                System.out.println("For an answer with common letters, try soare or raise.");
            } else if (input.equals("found")) {
                wordNotFound = false;
            } else System.out.println("Please try again");
        }

        System.out.println("Have a nice day!");

    }

    /** Outputs a list onto the console in a paginated format, with NUMBER_ON_PAGE at a time.
     * commands: > to go up a page, < to go down a page, exit to exit the dialog
     *
     * @param l list to be outputted
     */
    public static void paginate (List<String> l) {
        System.out.println();
        boolean exit = false;
        String answer;
        final int NUMBER_ON_PAGE = 4;
        int pageNumber = 0;
        Scanner sc = new Scanner(System.in);

        while (!exit) {
            System.out.println("On page " + (pageNumber + 1) + ".");

            for (int x = 0; x != NUMBER_ON_PAGE; x++) {
                try {
                    System.out.println("[" + (pageNumber * NUMBER_ON_PAGE + x + 1) + "] " + l.get(pageNumber * NUMBER_ON_PAGE + x));
                } catch (Exception ignored) {

                }
            }

            System.out.println("Type in \">\" to move right and \"<\" to move left. Type in exit to stop browsing: ");
            answer = sc.nextLine();

            if (answer.equals(">")) {
                 if ((pageNumber+1) * NUMBER_ON_PAGE < l.size() - 1) {
                     pageNumber++;
                 } else System.out.println("No more on the next page");
            } else if (answer.equals("<")) {
                if (pageNumber != 0) {
                    pageNumber --;
                } else System.out.println("No more on the previous page");
            } else if (answer.equals("exit")) {
                System.out.println("Exiting...");
                exit = true;
            }
        }
    }


}
