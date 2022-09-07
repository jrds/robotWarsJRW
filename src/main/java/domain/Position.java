package domain;

import java.util.Objects;

public class Position {
    private final int xCoordinate;
    private final int yCoordinate;
    private final Direction direction;
    private final Arena arena;

    public Position(int xCoordinate, int yCoordinate, Direction direction, Arena arena) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.direction = direction;
        this.arena = arena;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public Direction getDirection() {
        return direction;
    }

    public Arena getArena() {
        return arena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return xCoordinate == position.xCoordinate && yCoordinate == position.yCoordinate && direction == position.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate, direction);
    }

    @Override
    public String toString() {
        return xCoordinate + " " + yCoordinate + " " + direction;
    }
}