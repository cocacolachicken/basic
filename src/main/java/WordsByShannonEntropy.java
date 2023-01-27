package main.java;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordsByShannonEntropy {

    public static void returnListSorted (List words) {

    }

    public static int calculateSEValue (String w, List words) {
        int[] n = {0};
        return autoLoop(n, w, words);
    }

    public static int autoLoop (int[] indices, String w, List words) {
        int total = 0;
        String s = "";
        for (int x = 0; x != 3; x++) {
            if (indices.length == w.length()) {
                for (x = 0; x != 5; x++) {
                    if (indices[x] == 0) {
                        s += "n"; // NOT
                    } else if (indices[x] == 1) {
                        s += "c"; // CORRECT
                    } else if (indices[x] == 2) {
                        s += "e"; // ELSEWHERE
                    }
                }

                return calcInfo(s, w, words);
            } else {
                int[] n = {x};
                total += autoLoop(IntStream.concat(Arrays.stream(indices), Arrays.stream(n)).toArray(), w, words);
            }
        }

        return total;
    }

    public static int calcInfo (String pattern, String w, List words) {
        int info = 0;

        for (int x = 0; x != pattern.length(); x++) {

        }

        return info;
    }
}
