package domain.commands;

import domain.Arena;
import domain.Direction;
import domain.Position;
import domain.Robot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveForwardTest {

    private final Arena arena = new Arena(5, 5);
    private final MoveForward moveForwardCommand = new MoveForward();

    private void validateMove(Position startingPosition, Position finishingPosition) {
        //when
        Robot robot = new Robot(startingPosition);
        robot.executeCommand(moveForwardCommand);
        //then
        assertEquals(finishingPosition, robot.getPosition());
    }

    @Test
    void robotCanMoveNorthOne() {
        validateMove(new Position(0, 0, Direction.N, arena), new Position(0, 1, Direction.N, arena));
    }

    @Test
    void robotCanMoveEastOne() {
        validateMove(new Position(0, 0, Direction.E, arena), new Position(1, 0, Direction.E, arena));
    }

    @Test
    void robotCanMoveSouthOne() {
        validateMove(new Position(1, 1, Direction.S, arena), new Position(1, 0, Direction.S, arena));
    }

    @Test
    void robotCanMoveWestOne() {
        validateMove(new Position(1, 1, Direction.W, arena), new Position(0, 1, Direction.W, arena));
    }

    @Test
    void robotDoesNotExceedArenaLimitsWhenMovingNorth() {
        validateMove(new Position(0, arena.getYAxisUpperLimit(), Direction.N, arena), new Position(0, arena.getYAxisUpperLimit(), Direction.N, arena));
    }

    @Test
    void robotDoesNotExceedArenaLimitsWhenMovingEast() {
        validateMove(new Position(arena.getXAxisUpperLimit(), 0, Direction.E, arena), new Position(arena.getXAxisUpperLimit(), 0, Direction.E, arena));
    }

    @Test
    void robotDoesNotExceedArenaLimitsWhenMovingSouth() {
        validateMove(new Position(0, arena.getYAxisLowerLimit(), Direction.S, arena), new Position(0, arena.getYAxisLowerLimit(), Direction.S, arena));
    }

    @Test
    void robotDoesNotExceedArenaLimitsWhenMovingWest() {
        validateMove(new Position(arena.getXAxisLowerLimit(), 0, Direction.W, arena), new Position(arena.getXAxisLowerLimit(), 0, Direction.W, arena));
    }

}
