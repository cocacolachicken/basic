package main.java;

import main.java.Filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

    public static ArrayList list = new ArrayList<String>();

    public static void initialize () throws FileNotFoundException {
        list.clear();

        Scanner scanner = new Scanner(new File("src/valid-wordle-words.txt"));

        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
    }

    public static void printWords () {
        for (int x = 0; x != list.size(); x++) {
            System.out.println(list.get(x));
        }
    }
}
