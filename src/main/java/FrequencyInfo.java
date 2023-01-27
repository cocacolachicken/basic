package main.java;

import java.util.List;

public class FrequencyInfo {
    public static int alphaToInt (char c) {
        return Character.toLowerCase(c) - 97;
    }

    public static int[][] getFrequency (List s) {
        int[][] letterFrequency = new int[26][5];
        String temp = "";

        for (int x = 0; x != s.size(); x ++) {
            for (int z = 0; z != 5; z++) {
                char letterToExamine = Character.toLowerCase(((String) s.get(x)).charAt(z));


            }

            temp = "";
        }

        return letterFrequency;
    }
}
