package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {
    public static List letterKnown (int position, char letter, ArrayList<String> words) {
        return words.stream().filter((String s) -> (s.charAt(position) == letter)).collect(Collectors.toList());
    }

    public static List letterHas (String taken, char letter, ArrayList<String> words) {
        List<String> l = words;

        l = words.stream().filter((String s) -> (s.contains(letter + ""))).collect(Collectors.toList());

        for (int x = 0; x != taken.length(); x++) {
            int finalX = x;
            l = l.stream().filter((String s) -> (letter != s.charAt(Integer.parseInt(taken.charAt(finalX) + "")))).collect(Collectors.toList());
        }

        return l;
    }

    public static List letterNotIn (char letter, ArrayList<String> words) {
        return words.stream().filter((String s) -> (!s.contains(letter + ""))).collect(Collectors.toList());
    }
}
