package domain.commands;

import domain.Position;

public interface Command {
    Position execute(Position startingPosition);
}
