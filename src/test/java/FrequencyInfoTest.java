package test.java;

import static org.junit.jupiter.api.Assertions.*;

import main.java.FrequencyInfo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

// create test harness class
public class FrequencyInfoTest {
    // create unit test for alphaToInt()
    @Test
    public void testAlphaToInt() {
        char c = 'c';
        int expected = 2;
        int result = FrequencyInfo.alphaToInt(c);
        assertEquals(expected, result);
    }

    // create unit test for getFrequency()
    @Test
    public void testGetFrequency() {
        List<String> s = Arrays.asList("ABCDE");
        String expected = "[[I@7ce6a65d";
        String result = (FrequencyInfo.getFrequency(s)).toString();

        assertEquals(expected, result);

        System.out.println(FrequencyInfo.getFrequency(s));
    }
}