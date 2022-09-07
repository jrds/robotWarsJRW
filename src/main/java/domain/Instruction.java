package domain;

import domain.commands.Command;
import domain.commands.MoveForward;
import domain.commands.TurnLeft;
import domain.commands.TurnRight;

public enum Instruction {
    L {
        public Command getCommand() {
            return new TurnLeft();
        }
    },
    R {
        public Command getCommand() {
            return new TurnRight();
        }
    },
    M {
        public Command getCommand() {
            return new MoveForward();
        }
    };

    public abstract Command getCommand();
}
