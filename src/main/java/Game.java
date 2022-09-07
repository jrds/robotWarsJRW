import domain.Position;
import domain.Robot;
import domain.input.GameInput;
import domain.input.RobotInput;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Optional;

public class Game {
    public static void main(String[] args) {
        try {
            new Game(System.out).start("inputProvided");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private final PrintStream out;

    private final InputProcessor inputProcessor = new InputProcessor();

    public Game(PrintStream out) {
        this.out = out;
    }

    void start(String input) {
        Optional<GameInput> optionalValidGameInput = inputProcessor.getValidGameInput(input);
        if (optionalValidGameInput.isEmpty()) {
            throw new IllegalArgumentException("Invalid input - can not execute game");
        }
        GameInput gameInput = optionalValidGameInput.get();

        int counter = 0;
        for (RobotInput robotInput : gameInput.getRobotInputList()) {
            Robot robot = new Robot(robotInput.getStartingPosition());
            Position finishingPosition = robot.executeCommands(robotInput.getInstructions());
            counter++;
            try {
                out.write(finishingPosition.toString().getBytes());
                if (gameInput.getRobotInputList().size() != counter) {
                    out.write("\n".getBytes());
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
