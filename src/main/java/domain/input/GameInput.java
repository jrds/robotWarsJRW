package domain.input;

import domain.Arena;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameInput {
    private final Arena arena;
    private final List<RobotInput> robotInputList;

    public GameInput(Arena arena, List<RobotInput> robotInputList) {
        this.arena = arena;
        this.robotInputList = new ArrayList<>(robotInputList);
    }

    public Arena getArena() {
        return arena;
    }

    public List<RobotInput> getRobotInputList() {
        return Collections.unmodifiableList(robotInputList);
    }
}
