package test.java;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import main.java.InputProcessor;
import main.java.WordList;
import org.testng.annotations.Test;

public class InputProcessorTest {

    @Test
    public void testTakeInput() {
        String s = "a";
        InputProcessor.takeInput(s);
        assertNotNull(InputProcessor.l);
    }

    @Test
    public void testGetList() {
        InputProcessor.l = new WordList(true);
        List expected = InputProcessor.l.getList();
        List actual = InputProcessor.getList();
        assertEquals(expected, actual);
    }

}