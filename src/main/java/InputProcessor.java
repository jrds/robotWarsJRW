import domain.Arena;
import domain.Direction;
import domain.Instruction;
import domain.Position;
import domain.commands.Command;
import domain.input.GameInput;
import domain.input.RobotInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InputProcessor {
    public Optional<GameInput> getValidGameInput(String input) {
        if (input == null || input.length() == 0) {
            return Optional.empty();
        }

        String[] arenaInputAndRobotInputs = input.split(System.lineSeparator(), 2);

        Optional<Arena> optionalArena = getValidArena(arenaInputAndRobotInputs[0]);
        if (optionalArena.isEmpty() || arenaInputAndRobotInputs[1].lines().count() % 2 == 1) {
            return Optional.empty();
        }

        String[] robotInputStrings = arenaInputAndRobotInputs[1].split(System.lineSeparator());
        List<RobotInput> robotInputList = new ArrayList<>();
        Arena arena = optionalArena.get();

        for (int i = 0; i < robotInputStrings.length; i += 2) {
            Optional<Position> optionalPosition = getValidInitialPosition(robotInputStrings[i], arena);
            if (optionalPosition.isEmpty()) {
                return Optional.empty();
            }
            Optional<List<Command>> optionalCommands = getValidCommandList(robotInputStrings[i + 1]);
            if (optionalCommands.isEmpty()) {
                return Optional.empty();
            }
            RobotInput robotInput = new RobotInput(optionalPosition.get(), optionalCommands.get());
            robotInputList.add(robotInput);
        }
        return Optional.of(new GameInput(arena, robotInputList));
    }

    private Optional<List<Command>> getValidCommandList(String robotInputString) {
        List<Command> commandList = new ArrayList<>();

        for (char c : robotInputString.toCharArray()) {
            try {
                commandList.add(Instruction.valueOf(String.valueOf(c)).getCommand());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unknown robot instruction: " + c);
            }
        }
        return Optional.of(commandList);
    }

    private Optional<Position> getValidInitialPosition(String positionInputs, Arena arena) {
        int xCoord;
        int yCoord;
        String direction;

        try {
            String[] positionDetails = positionInputs.split(" ");
            if (positionDetails.length != 3) {
                return Optional.empty();
            }
            xCoord = Integer.parseInt(positionDetails[0]);
            yCoord = Integer.parseInt(positionDetails[1]);
            direction = positionDetails[2];
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("Unrecognised position input");
            return Optional.empty();
        }
        Direction dir;
        try {
            dir = Direction.valueOf(direction.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Unrecognised Direction");
            return Optional.empty();
        }

        return xCoord < 0 || yCoord < 0
                ? Optional.empty()
                : Optional.of(new Position(xCoord, yCoord, dir, arena));

    }

    private Optional<Arena> getValidArena(String arenaLimitsInput) {
        int yAxisLimit;
        int xAxisLimit;
        try {
            String[] x = arenaLimitsInput.split(" ");
            xAxisLimit = Integer.parseInt(x[0]);
            yAxisLimit = Integer.parseInt(x[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Invalid input for the arena boundary coordinate(s)");
            return Optional.empty();
        }
        return xAxisLimit < 0 || yAxisLimit < 0
                ? Optional.empty()
                : Optional.of(new Arena(xAxisLimit, yAxisLimit));
    }

}
