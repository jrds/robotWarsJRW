package domain.input;

import domain.Position;
import domain.commands.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RobotInput {
    private final Position startingPosition;
    private final List<Command> instructions;

    public RobotInput(Position startingPosition, List<Command> instructions) {
        this.startingPosition = startingPosition;
        this.instructions = new ArrayList<>(instructions);
    }

    public Position getStartingPosition() {
        return startingPosition;
    }

    public List<Command> getInstructions() {
        return Collections.unmodifiableList(instructions);
    }
}
