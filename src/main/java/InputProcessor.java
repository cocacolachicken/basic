package main.java;

public class InputProcessor {
    public static void takeInput (String s) {
        Guess g;
        try {
            System.out.println("aaa");
            g = new Guess(s);
            System.out.println("guess created");
            WordList.processGuess(g);
            System.out.println("Success!");


        } catch (Exception e) {
            // Something to handle erroneous output
            System.out.println("FAILURE!!!");
        }
    }
}
