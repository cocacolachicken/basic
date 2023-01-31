package test.java;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import main.java.WordList;
import org.junit.Test;

public class WordListTest {

    // Test for the constructor of WordList
    @Test
    public void testConstructor() {
        WordList wl = new WordList(false);
        assertNotNull(wl);
    }

    // Test for the initialize() method
    @Test
    public void testInitialize() throws FileNotFoundException {
        WordList wl = new WordList(false);
        List<String> expected = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/valid-wordle-words.txt"));

        while (scanner.hasNextLine()) {
            expected.add(scanner.nextLine());
        }
        wl.initialize();
        List<String> actual = wl.getList();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    // Test for the initializeAnswers() method
    @Test
    public void testInitializeAnswers() throws FileNotFoundException {
        WordList wl = new WordList(true);
        List<String> expected = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/wordle-answers-alphabetical.txt"));

        while (scanner.hasNextLine()) {
            expected.add(scanner.nextLine());
        }
        wl.initializeAnswers();
        List<String> actual = wl.getList();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    // Test for the setList() method
    @Test
    public void testSetList() {
        WordList wl = new WordList(false);
        List<String> expected = Arrays.asList("test", "list", "data");
        wl.setList(expected);
        List<String> actual = wl.getList();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    // Test for the getList() method
    @Test
    public void testGetList() throws FileNotFoundException {
        WordList wl = new WordList(false);
        List<String> expected = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/valid-wordle-words.txt"));

        while (scanner.hasNextLine()) {
            expected.add(scanner.nextLine());
        }
        wl.initialize();
        List<String> actual = wl.getList();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}