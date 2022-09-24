import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ManagedBean(name = "helloWorld")
@SessionScoped
public class Main implements Serializable {

    private static final long serialVersionUID = -6913972022251814607L;
    private static Set<Cell> cells;
    private static Set<Cell> cellValues;
    private static String s1;

    static {
        Main.cells = createCellSystem();
        Main.s1 = "Starta spelet";

    }

    public static void play() {
        System.out.println("Knapp använd");
        Main.cellValues = mark5CellsAsStartCells(Main.cells);


        cells = updateCells(cellValues);

    }

    public static Set<Cell> getCellValues() {
        return cellValues;
    }

    public static Set<Cell> updateCells(Set<Cell> startCells) {
        final Set<Cell> killedByUnderPopulation = startCells.stream()
                .filter(Cell::isAlive)
                .filter(c -> checkIfCellIsKilledByUnderpopulation(startCells, c))
                .map(c -> new Cell(c.xCordinate(), c.yCordinate(), false))
                .collect(Collectors.toSet());

        final Set<Cell> killedByOverPopulation = startCells.stream()
                .filter(Cell::isAlive)
                .filter(c -> checkIfCellIsKilledByOverpopulation(startCells, c))
                .map(c -> new Cell(c.xCordinate(), c.yCordinate(), false))
                .collect(Collectors.toSet());

        final Set<Cell> resurrectCells = startCells.stream()
                .filter(cellBooleanEntry -> !cellBooleanEntry.isAlive())
                .filter(c -> checkIfCellIsResurrect(startCells, c))
                .map(c -> new Cell(c.xCordinate(), c.yCordinate(), true))
                .collect(Collectors.toSet());

        final Set<Cell> stayingAlive = startCells.stream()
                .filter(Cell::isAlive)
                .filter(c -> checkIfCellStaysAlive(startCells, c))
                .map(c -> new Cell(c.xCordinate(), c.yCordinate(), true))
                .collect(Collectors.toSet());

        startCells.addAll(Stream.of(killedByUnderPopulation, killedByOverPopulation, resurrectCells, stayingAlive)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet()));
        return startCells;
    }

    public static boolean checkIfCellIsKilledByUnderpopulation(Set<Cell> cellSystem, Cell livingCell) {
        final long livingNeighbours = cellSystem.stream()
                .filter(c -> !c.equals(livingCell))
                .filter(Cell::isAlive)
                .filter(c -> isXNeighbourCell(c, livingCell.xCordinate()))
                .filter(c -> isYNeighbourCell(c, livingCell.yCordinate()))
                .count();
        return livingNeighbours < 2;
    }

    public static boolean checkIfCellIsKilledByOverpopulation(Set<Cell> cellSystem, Cell livingCell) {
        final long livingNeighbours = cellSystem.stream()
                .filter(c -> !c.equals(livingCell))
                .filter(Cell::isAlive)
                .filter(c -> isXNeighbourCell(c, livingCell.xCordinate()))
                .filter(c -> isYNeighbourCell(c, livingCell.yCordinate()))
                .count();
        return livingNeighbours > 3;
    }

    //    public static void main(String[] args) {
//        final Map<Cell, Boolean> emptyCells = createCellSystem();
//
//        Map<Cell, Boolean> cellSystem = mark5CellsAsStartCells(emptyCells);
//
//        do {
//            cellSystem = updateCells(cellSystem);
//        }
//        while (true);
//
//    }

    public static boolean checkIfCellStaysAlive(Set<Cell> cellSystem, Cell livingCell) {
        final long livingNeighbours = cellSystem.stream()
                .filter(c -> !c.equals(livingCell))
                .filter(Cell::isAlive)
                .filter(c -> isXNeighbourCell(c, livingCell.xCordinate()))
                .filter(c -> isYNeighbourCell(c, livingCell.yCordinate()))
                .count();
        return livingNeighbours == 3 || livingNeighbours == 2;
    }

    public static boolean checkIfCellIsResurrect(Set<Cell> cellSystem, Cell cellToCompare) {
        final long livingNeighbours = cellSystem.stream()
                .filter(c -> !c.equals(cellToCompare))
                .filter(Cell::isAlive)
                .filter(c -> isXNeighbourCell(c, cellToCompare.xCordinate()))
                .filter(c -> isYNeighbourCell(c, cellToCompare.yCordinate()))
                .count();
        return livingNeighbours == 3;
    }

    private static Set<Cell> mark5CellsAsStartCells(final Set<Cell> startSystem) {
        Set<Cell> cells = new HashSet<>();
        cells.addAll(startSystem);
        cells.add(new Cell(0, 3, true));
        cells.add(new Cell(0, 2, true));
        cells.add(new Cell(1, 2, true));
        cells.add(new Cell(1, 1, true));
        cells.add(new Cell(2, 1, true));
        return cells;
    }

    private static Set<Cell> createCellSystem() {
        Set<Cell> cells = new HashSet<>();
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                cells.add(new Cell(x, y, false));
            }
        }
        return cells;
    }

    public String getS1() {
        return s1;
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

    public String cell(final int x, final int y) {
        return cells.stream().filter(c -> c.xCordinate() == x && c.yCordinate() == y)
                .findAny().map(c -> c.isAlive() ? "Lever" : "Död").orElse("SAKNAS");
    }

    public Set<Cell> getCells() {
        return cells;
    }

}
