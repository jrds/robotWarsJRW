package domain.commands;

import domain.Arena;
import domain.Direction;
import domain.commands.Utils.Turn;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.commands.Utils.Turn.validateTurns;

public class TurnRightTest {

    private final TurnRight turnRightCommand = new TurnRight();

    @Test
    void robotCanTurnRight() {
        //given
        Arena arena = new Arena(5, 5);
        Turn northToEast = new Turn(Direction.N, Direction.E);
        Turn eastToSouth = new Turn(Direction.E, Direction.S);
        Turn southToWest = new Turn(Direction.S, Direction.W);
        Turn westToNorth = new Turn(Direction.W, Direction.N);

        List<Turn> rightTurns = List.of(northToEast, eastToSouth, southToWest, westToNorth);
        //then
        validateTurns(rightTurns, turnRightCommand, arena);
    }
}
