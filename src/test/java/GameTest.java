import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    void givenTestInputExpectedOutputIsReceived() {
        //given
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        Game testGame = new Game(new PrintStream(bytes));
        String input = "5 5\n" +
                "1 2 N\n" +
                "LMLMLMLMM\n" +
                "3 3 E\n" +
                "MMRMMRMRRM";
        String expectedOutput =
                "1 3 N\n" +
                        "5 1 E";
        //when
        testGame.start(input);
        //then
        assertEquals(expectedOutput, bytes.toString());
    }

    @Test
    void givenInvalidInputNoPositionsAreReceived() {
        //given
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        Game testGame = new Game(new PrintStream(bytes));
        String input1 = "5 5\n" +
                "1 2 N\n" +
                "LMLMKMLMM\n" +
                "3 3 N\n" +
                "MMRMMRMRRM";
        String input2 = "5 5\n" +
                "1 2 N\n" +
                "LMLMMLMM\n" +
                "3 3 f\n" +
                "MMRMMRMRRM";

        try {
            testGame.start(input1);
        } catch (IllegalArgumentException e) {
            assertEquals("Unknown robot instruction: K", e.getMessage());
        }
        try {
            testGame.start(input2);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid input - can not execute game", e.getMessage());
        }
    }

}
