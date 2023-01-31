package test.java;//JUnit testing harness
import static org.junit.jupiter.api.Assertions.*;

import main.java.Letter;
import main.java.LetterStatus;
import org.junit.jupiter.api.Test;

class LetterTest {

    //Test default constructor
    @Test
    void testLetterDefault() {
        Letter testLetter = new Letter();
        assertEquals('?', testLetter.getLetter());
        assertEquals(LetterStatus.UNKNOWN, testLetter.getStatus());
    }

    //Test letter constructor
    @Test
    void testLetter() {
        Letter testLetter = new Letter('A');
        assertEquals('a', testLetter.getLetter());
        assertEquals(LetterStatus.IN, testLetter.getStatus());
    }
}