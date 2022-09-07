package domain.commands;

import domain.Arena;
import domain.Direction;
import domain.commands.Utils.Turn;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.commands.Utils.Turn.validateTurns;

public class TurnLeftTest {
    private final TurnLeft turnLeftCommand = new TurnLeft();

    @Test
    void robotCanTurnLeft() {
        //given
        Arena arena = new Arena(5, 5);
        Turn northToWest = new Turn(Direction.N, Direction.W);
        Turn eastToNorth = new Turn(Direction.E, Direction.N);
        Turn southToEast = new Turn(Direction.S, Direction.E);
        Turn westToSouth = new Turn(Direction.W, Direction.S);

        List<Turn> leftTurns = List.of(northToWest, eastToNorth, southToEast, westToSouth);
        //then
        validateTurns(leftTurns, turnLeftCommand, arena);
    }

}
