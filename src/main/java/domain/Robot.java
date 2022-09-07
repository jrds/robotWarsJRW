package domain;

import domain.commands.Command;

import java.util.List;

public class Robot {
    private Position position;

    public Robot(Position position) {
        this.position = position;
    }

    public Position executeCommand(Command command) {
        position = command.execute(position);
        return position;
    }

    public Position executeCommands(List<Command> commands) {
        commands.forEach(this::executeCommand);
        return position;
    }

    public Position getPosition() {
        return position;
    }
}
