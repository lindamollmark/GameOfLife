import java.util.Objects;

public class Cell {
    private final Integer xCordinate;
    private final Integer yCordinate;
    private boolean isAlive;

    public Cell(Integer xCordinate, Integer yCordinate, boolean isAlive) {
        this.xCordinate = xCordinate;
        this.yCordinate = yCordinate;
        this.isAlive = isAlive;
    }

    public Integer xCordinate() {
        return xCordinate;
    }

    public Integer yCordinate() {
        return yCordinate;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(final boolean alive) {
        isAlive = alive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Cell cell = (Cell) o;
        return Objects.equals(xCordinate, cell.xCordinate) && Objects.equals(yCordinate, cell.yCordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCordinate, yCordinate);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "xCordinate=" + xCordinate +
                ", yCordinate=" + yCordinate +
                '}';
    }
}
