package domain;

import java.util.Objects;

public class Arena {
    private final int yAxisLowerLimit = 0;
    private final int xAxisLowerLimit = 0;
    private final int xAxisUpperLimit;
    private final int yAxisUpperLimit;

    public Arena(int xAxisUpperLimit, int yAxisUpperLimit) {
        this.xAxisUpperLimit = xAxisUpperLimit;
        this.yAxisUpperLimit = yAxisUpperLimit;
    }

    public int getXAxisLowerLimit() {
        return xAxisLowerLimit;
    }

    public int getXAxisUpperLimit() {
        return xAxisUpperLimit;
    }

    public int getYAxisLowerLimit() {
        return yAxisLowerLimit;
    }

    public int getYAxisUpperLimit() {
        return yAxisUpperLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arena arena = (Arena) o;
        return xAxisUpperLimit == arena.xAxisUpperLimit && yAxisUpperLimit == arena.yAxisUpperLimit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(yAxisLowerLimit, xAxisLowerLimit, xAxisUpperLimit, yAxisUpperLimit);
    }
}
