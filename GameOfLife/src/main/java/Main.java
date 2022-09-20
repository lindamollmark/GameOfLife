import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final Map<Cell, Boolean> emptyCells = createCellSystem();

        Map<Cell, Boolean> cellSystem = mark5CellsAsStartCells(emptyCells);

        do {
            cellSystem = updateCells(cellSystem);
        }
        while (true);

    }

    public static Map<Cell, Boolean> updateCells(Map<Cell, Boolean> startCells) {
        final Map<Cell, Boolean> killedByUnderPopulation = startCells.entrySet().stream()
                .filter(Map.Entry::getValue)
                .filter(c -> checkIfCellIsKilledByUnderpopulation(startCells, c.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, c -> false));

        final Map<Cell, Boolean> killedByOverPopulation = startCells.entrySet().stream()
                .filter(Map.Entry::getValue)
                .filter(c -> checkIfCellIsKilledByOverpopulation(startCells, c.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, c -> false));

        final Map<Cell, Boolean> resurrectCells = startCells.entrySet().stream()
                .filter(cellBooleanEntry -> !cellBooleanEntry.getValue())
                .filter(c -> checkIfCellIsResurrect(startCells, c.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, c -> true));

        final Map<Cell, Boolean> stayingAlive = startCells.entrySet().stream()
                .filter(Map.Entry::getValue)
                .filter(c -> checkIfCellStaysAlive(startCells, c.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, c -> true));

        startCells.putAll(Stream.of(killedByUnderPopulation, killedByOverPopulation, resurrectCells, stayingAlive)
                .flatMap(c -> c.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        return startCells;
    }

    public static boolean checkIfCellIsKilledByUnderpopulation(Map<Cell, Boolean> cellSystem, Cell livingCell) {
        final long livingNeighbours = cellSystem.entrySet().stream()
                .filter(c -> !c.getKey().equals(livingCell))
                .filter(Map.Entry::getValue)
                .filter(c -> isXNeighbourCell(c.getKey(), livingCell.xCordinate()))
                .filter(c -> isYNeighbourCell(c.getKey(), livingCell.yCordinate()))
                .count();
        return livingNeighbours < 2;
    }

    public static boolean checkIfCellIsKilledByOverpopulation(Map<Cell, Boolean> cellSystem, Cell livingCell) {
        final long livingNeighbours = cellSystem.entrySet().stream()
                .filter(c -> !c.getKey().equals(livingCell))
                .filter(Map.Entry::getValue)
                .filter(c -> isXNeighbourCell(c.getKey(), livingCell.xCordinate()))
                .filter(c -> isYNeighbourCell(c.getKey(), livingCell.yCordinate()))
                .count();
        return livingNeighbours > 3;
    }

    public static boolean checkIfCellStaysAlive(Map<Cell, Boolean> cellSystem, Cell livingCell) {
        final long livingNeighbours = cellSystem.entrySet().stream()
                .filter(c -> !c.getKey().equals(livingCell))
                .filter(Map.Entry::getValue)
                .filter(c -> isXNeighbourCell(c.getKey(), livingCell.xCordinate()))
                .filter(c -> isYNeighbourCell(c.getKey(), livingCell.yCordinate()))
                .count();
        return livingNeighbours == 3 || livingNeighbours == 2;
    }

    public static boolean checkIfCellIsResurrect(Map<Cell, Boolean> cellSystem, Cell cellToCompare) {
        final long livingNeighbours = cellSystem.entrySet().stream()
                .filter(c -> !c.getKey().equals(cellToCompare))
                .filter(Map.Entry::getValue)
                .filter(c -> isXNeighbourCell(c.getKey(), cellToCompare.xCordinate()))
                .filter(c -> isYNeighbourCell(c.getKey(), cellToCompare.yCordinate()))
                .count();
        return livingNeighbours == 3;
    }


    private static boolean isXNeighbourCell(Cell neighbour, Integer x) {
        return neighbour.xCordinate().equals(x + 1)
                || neighbour.xCordinate().equals(x - 1)
                || neighbour.xCordinate().equals(x);
    }

    private static boolean isYNeighbourCell(Cell neighbour, Integer y) {
        return neighbour.yCordinate().equals(y + 1)
                || neighbour.yCordinate().equals(y - 1)
                || neighbour.yCordinate().equals(y);
    }


    private static Map<Cell, Boolean> mark5CellsAsStartCells(final Map<Cell, Boolean> startSystem) {
        Map<Cell, Boolean> cells = new HashMap<>();
        cells.putAll(startSystem);
        cells.replace(new Cell(0, 3), true);
        cells.replace(new Cell(0, 2), true);
        cells.replace(new Cell(1, 2), true);
        cells.replace(new Cell(1, 1), true);
        cells.replace(new Cell(2, 1), true);
        return cells;
    }


    private static Map<Cell, Boolean> createCellSystem() {
        Map<Cell, Boolean> cells = new HashMap<>();
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                cells.put(new Cell(x, y), false);
            }
        }
        return cells;
    }

}
