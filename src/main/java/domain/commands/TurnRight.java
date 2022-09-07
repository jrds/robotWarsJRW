package domain.commands;

import domain.Direction;
import domain.Position;

public class TurnRight implements Command {
    @Override
    public Position execute(Position position) {
        int currentOrdinal = position.getDirection().ordinal();
        Direction newDirection = currentOrdinal == Direction.values().length - 1
                ? Direction.values()[0]
                : Direction.values()[currentOrdinal + 1];
        return new Position(position.getXCoordinate(), position.getYCoordinate(), newDirection, position.getArena());
    }


}
