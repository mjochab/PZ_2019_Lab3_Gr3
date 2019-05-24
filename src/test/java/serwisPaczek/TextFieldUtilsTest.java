package serwisPaczek;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static serwisPaczek.utils.TextFieldUtils.isCorrect;

public class TextFieldUtilsTest {

    @Test
    void isCorrectCorrect_Test(){
        assertTrue(isCorrect("tyrtyrymtym"));
    }

    @Test
    void isCorrectNull_Test(){
        assertFalse(isCorrect(null));
    }

    @Test
    void isCorrectLengthZero_Test(){
        assertFalse(isCorrect(""));
    }

    @Test
    void isCorrectWhiteSpaces_Test(){
        assertFalse(isCorrect("co tam"));
    }


}
