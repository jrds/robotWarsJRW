import domain.Arena;
import domain.Direction;
import domain.Position;
import domain.commands.Command;
import domain.commands.MoveForward;
import domain.commands.TurnLeft;
import domain.commands.TurnRight;
import domain.input.GameInput;
import domain.input.RobotInput;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputProcessorTest {

    InputProcessor inputProcessor = new InputProcessor();

    @Test
    void optionalGameInputIsEmptyIfInvalidRobotPositionProvided() {
        //given
        String inputWithInvalidLimits1 = "3 3\n" + "0 -1 N\n" + "LM\n";
        String inputWithInvalidLimits2 = "3 3\n" + "-2 0 N\n" + "LM\n";
        String inputWithInvalidLimits3 = "3 3\n" + "0 0 R\n" + "LM\n";
        //when
        List<String> invalidInputs = List.of(inputWithInvalidLimits1, inputWithInvalidLimits2, inputWithInvalidLimits3);
        invalidInputs.forEach(this::testInvalidInput);
    }

    @Test
    void optionalGameInputIsEmptyIfInvalidArenaLimitsProvided() {
        //given
        String inputWithInvalidLimits1 = "3 d\n" + "0 0 N\n" + "LM\n";
        String inputWithInvalidLimits2 = "33\n" + "0 0 N\n" + "LM\n";
        String inputWithInvalidLimits3 = "-3 3\n" + "0 0 N\n" + "LM\n";
        String inputWithInvalidLimits4 = "3 -3\n" + "0 0 N\n" + "LM\n";
        //when
        List<String> invalidInputs = List.of(inputWithInvalidLimits1, inputWithInvalidLimits2,
                inputWithInvalidLimits3, inputWithInvalidLimits4);
        invalidInputs.forEach(this::testInvalidInput);
    }

    @Test
    void optionalGameInputIsEmptyIsInputIsNullOrEmpty() {
        testInvalidInput(null);
        testInvalidInput("");
    }

    private void testInvalidInput(String invalidInput) {
        Optional<GameInput> result = inputProcessor.getValidGameInput(invalidInput);
        assertTrue(result.isEmpty());
    }


    @Test
    void optionalGameInputIsPresentIfValidInputProvided() {
        //given
        String validGameInput =
                "3 4\n" +
                        "1 2 N\n" +
                        "LM\n" +
                        "0 0 E\n" +
                        "MMR";
        Arena arena = new Arena(3, 4);
        Position startingPosition1 = new Position(1, 2, Direction.N, arena);
        Position startingPosition2 = new Position(0, 0, Direction.E, arena);

        TurnLeft turnLeft = new TurnLeft();
        TurnRight turnRight = new TurnRight();
        MoveForward moveForward = new MoveForward();

        List<RobotInput> expectedRobotInputList = List.of(
                new RobotInput(startingPosition1, List.of(turnLeft, moveForward)),
                new RobotInput(startingPosition2, List.of(moveForward, moveForward, turnRight)));
        //when
        Optional<GameInput> result = inputProcessor.getValidGameInput(validGameInput);
        //then
        assertTrue(result.isPresent());

        GameInput gameInput = result.get();
        assertEquals(arena, gameInput.getArena());

        for (int i = 0; i < expectedRobotInputList.size(); i++) {
            List<Command> expectedInstructions = expectedRobotInputList.get(i).getInstructions();
            for (int j = 0; j < expectedInstructions.size(); j++) {
                assertEquals(expectedInstructions.get(j).getClass(), gameInput.getRobotInputList().get(i).getInstructions().get(j).getClass());
            }
        }

    }
}

