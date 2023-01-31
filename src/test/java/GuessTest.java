package test.java;//JUnit Testing Harness
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import main.java.Guess;
import org.junit.jupiter.api.Test;

class GuessTest {

    @Test
    public void testConstructor() throws Exception {
        //Test if constructor is able to correctly create a Guess object with no exception thrown
        Guess guess1 = new Guess("hello");

        assertNotNull(guess1);
    }

    @Test
    public void testProcessGuess() throws Exception {
        //Test that processGuess() method correctly filters list of strings based on the given guess
        Guess guess2 = new Guess("hello");
        List<String> list;  //some list of strings
    }

    @Test
    public void testExceptionThrown() {
        //Test that an exception is thrown if string is not of length 5
        String str = "abde";

        assertThrows(Exception.class, () -> new Guess(str));
    }
}