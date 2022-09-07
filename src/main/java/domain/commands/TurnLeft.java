package domain.commands;

import domain.Direction;
import domain.Position;

public class TurnLeft implements Command {
    @Override
    public Position execute(Position position) {
        int currentOrdinal = position.getDirection().ordinal();
        Direction newDirection = currentOrdinal == 0
                ? Direction.values()[(Direction.values().length - 1)]
                : Direction.values()[currentOrdinal - 1];
        position = new Position(position.getXCoordinate(), position.getYCoordinate(), newDirection, position.getArena());
        return position;
    }
}
