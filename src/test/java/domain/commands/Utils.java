package domain.commands;

import domain.Arena;
import domain.Direction;
import domain.Position;
import domain.Robot;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Utils {
    public static class Turn {
        private final Direction directionBeforeTurn;
        private final Direction directionAfterTurn;

        public Turn(Direction directionBeforeTurn, Direction directionAfterTurn) {
            this.directionBeforeTurn = directionBeforeTurn;
            this.directionAfterTurn = directionAfterTurn;
        }

        public static void validateTurns(List<Turn> turns, Command command, Arena arena) {
            for (Turn turn : turns) {
                Position positionBeforeTurn = new Position(0, 0, turn.directionBeforeTurn, arena);
                Robot robot = new Robot(positionBeforeTurn);
                //when
                Position positionAfterTurn = robot.executeCommand(command);
                //then
                assertEquals(turn.directionAfterTurn, positionAfterTurn.getDirection());
            }
        }
    }
}
