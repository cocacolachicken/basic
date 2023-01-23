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

    public static void initialize () throws FileNotFoundException {
        list.clear();

        Scanner scanner = new Scanner(new File("src/valid-wordle-words.txt"));

        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
    }

    public static void setList (ArrayList l) {
        list = l;
    }

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

    public static List processGuess (Guess g) {
        list = (ArrayList) g.processGuess(list);
        return list;
    }

    public static List processNewGuess (Guess g) {
        try {
            initialize();
        } catch(Exception ignore) {

        }
        list = (ArrayList) g.processGuess(list);
        return list;
    }
}
