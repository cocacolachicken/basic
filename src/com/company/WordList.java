package com.company;

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

        list = (ArrayList) Filter.letterInQuantity('d', 1, list);

        printWords();
    }


    public static ArrayList list = new ArrayList<String>();

    public static void initialize () throws FileNotFoundException {


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
