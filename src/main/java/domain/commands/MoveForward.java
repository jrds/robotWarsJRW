package domain.commands;

import domain.Arena;
import domain.Position;

public class MoveForward implements Command {

    @Override
    public Position execute(Position position) {
        // numberOfSpaces can be added to MoveForward's state & constructor in the future.
        // The offset method below handles the arena limits and the number of potential spaces.
        int numberOfSpaces = 1;

        switch (position.getDirection()) {
            case N:
                return offset(0, numberOfSpaces, position);
            case E:
                return offset(numberOfSpaces, 0, position);
            case S:
                return offset(0, -numberOfSpaces, position);
            case W:
                return offset(-numberOfSpaces, 0, position);
        }
        throw new IllegalArgumentException("Unknown direction");
    }

    private Position offset(int xOffset, int yOffset, Position position) {
        Arena arena = position.getArena();
        return new Position(
                Math.max(arena.getXAxisLowerLimit(), Math.min(arena.getXAxisUpperLimit(), position.getXCoordinate() + xOffset)),
                Math.max(arena.getYAxisLowerLimit(), Math.min(arena.getYAxisUpperLimit(), position.getYCoordinate() + yOffset)),
                position.getDirection(), arena);
    }
}

