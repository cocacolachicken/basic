package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import main.java.Filter;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class FilterTest {
    @Test
    public void letterKnownTest() {
        ArrayList<String> words = new ArrayList<String>();
        words.add("apple");
        words.add("banana");
        words.add("orange");
        words.add("pears");

        List<String> filteredList = Filter.letterKnown(2, 'p', words);

        // checks that the returned list is the same size as the list of words that contain the desired letter at the desired position
        assertEquals(1, filteredList.size());

        // checks that the returned list only contains words that have the desired letter at the desired position
        assertTrue(filteredList.contains("apple"));
        assertFalse(filteredList.contains("banana"));
        assertFalse(filteredList.contains("orange"));
        assertFalse(filteredList.contains("pears"));
    }

    @Test
    public void letterHasTest() {
        ArrayList<String> words = new ArrayList<String>();
        words.add("apple");
        words.add("pears");

        List<String> filteredList = Filter.letterHas("2", 'p', words);

        // checks that the returned list is the same size as the list of words that contain the desired letter at the desired position
        assertEquals(1, filteredList.size());

        // checks that the returned list only contains words that have the desired letter at the desired position
        assertFalse(filteredList.contains("apple"));
        assertTrue(filteredList.contains("pears"));
    }

    @Test
    public void letterNotInTest() {
        ArrayList<String> words = new ArrayList<String>();
        words.add("apple");
        words.add("banana");
        words.add("orange");

        List<String> filteredList = Filter.letterNotIn('p', words);

        // checks that the returned list is the same size as the list of words that contain the desired letter at the desired position
        assertEquals(1, filteredList.size());

        // checks that the returned list only contains words that have the desired letter at the desired position
        assertFalse(filteredList.contains("apple"));
        assertTrue(filteredList.contains("banana"));
        assertTrue(filteredList.contains("orange"));
    }

    @Test
    public void letterInQuantityTest() {
        ArrayList<String> words = new ArrayList<String>();
        words.add("apple");
        words.add("banana");
        words.add("orange");

        List<String> filteredList = Filter.letterInQuantity('p', "", "",  2, words);

        // checks that the returned list is the same size as the list of words that contain the desired letter at the desired position
        assertEquals(1, filteredList.size());

        // checks that the returned list only contains words that have the desired letter at the desired position
        assertTrue(filteredList.contains("apple"));
        assertFalse(filteredList.contains("banana"));
        assertFalse(filteredList.contains("orange"));
    }

    @Test
    public void letterAtLeastQuantityTest() {
        ArrayList<String> words = new ArrayList<String>();
        words.add("apple");
        words.add("banana");
        words.add("orange");

        List<String> filteredList = Filter.letterAtLeastQuantity('a', 2, words);

        // checks that the returned list is the same size as the list of words that contain the desired letter at the desired position
        assertEquals(1, filteredList.size());

        // checks that the returned list only contains words that have the desired letter at the desired position
        assertFalse(filteredList.contains("apple"));
        assertTrue(filteredList.contains("banana"));
    }
}