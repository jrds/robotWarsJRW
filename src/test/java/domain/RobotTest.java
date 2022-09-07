package domain;

import domain.commands.MoveForward;
import domain.commands.TurnLeft;
import domain.commands.TurnRight;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RobotTest {
    Arena arena = new Arena(10, 5);

    @Test
    void robotCanExecuteOneCommandSuccessfully() {
        //given
        Position positionBeforeCommand = new Position(1, 2, Direction.N, arena);
        Position expectedPosition = new Position(1, 2, Direction.W, arena);
        Robot robot = new Robot(positionBeforeCommand);
        TurnLeft turnLeft = new TurnLeft();
        //when
        robot.executeCommands(Collections.singletonList(turnLeft));
        //then
        assertEquals(expectedPosition, robot.getPosition());
    }

    @Test
    void robotCanExecuteMultipleTurnCommandSuccessfully() {
        //given
        Position positionBeforeCommand = new Position(1, 2, Direction.N, arena);
        Position expectedPosition = new Position(1, 2, Direction.S, arena);
        Robot robot = new Robot(positionBeforeCommand);
        TurnLeft turnLeft = new TurnLeft();
        TurnRight turnRight = new TurnRight();
        //when
        robot.executeCommands(List.of(turnLeft, turnRight, turnLeft, turnLeft));
        //then
        assertEquals(expectedPosition, robot.getPosition());
    }

    @Test
    void robotCanExecuteMoveAndTurnCommandSuccessfully() {
        //given
        Arena arena = new Arena(5, 5);
        Position positionBeforeCommand = new Position(3, 3, Direction.E, arena);
        Position expectedPosition = new Position(5, 1, Direction.E, arena);
        Robot robot = new Robot(positionBeforeCommand);
        TurnRight turnRight = new TurnRight();
        MoveForward moveForward = new MoveForward();
        //when
        robot.executeCommands(List.of(moveForward, moveForward, turnRight, moveForward, moveForward, turnRight, moveForward, turnRight, turnRight, moveForward));
        //then
        assertEquals(expectedPosition, robot.getPosition());
    }

}

