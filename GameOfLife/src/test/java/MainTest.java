import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void shouldUpdateBoard() {
        Map<Cell, Boolean> cells = createCellSystem(false);
        cells.replace(new Cell(0, 3), true);
        cells.replace(new Cell(0, 2), true);
        cells.replace(new Cell(1, 2), true);
        cells.replace(new Cell(1, 1), true);
        cells.replace(new Cell(2, 1), true);

        final Map<Cell, Boolean> expected = createCellSystem(false);
        expected.replace(new Cell(0, 1), true);
        expected.replace(new Cell(0, 2), true);
        expected.replace(new Cell(0, 3), true);
        expected.replace(new Cell(1, 1), true);
        expected.replace(new Cell(1, 3), true);
        expected.replace(new Cell(2, 1), true);
        expected.replace(new Cell(2, 2), true);
        final Map<Cell, Boolean> updated = Main.updateCells(cells);
        assertThat(updated, equalTo(expected));
    }

    @Test
    void shouldUpdateBoardTwoRounds() {
        Map<Cell, Boolean> cells = createCellSystem(false);
        cells.replace(new Cell(0, 3), true);
        cells.replace(new Cell(0, 2), true);
        cells.replace(new Cell(1, 2), true);
        cells.replace(new Cell(1, 1), true);
        cells.replace(new Cell(2, 1), true);

        final Map<Cell, Boolean> updated1 = Main.updateCells(cells);
        final Map<Cell, Boolean> updated2 = Main.updateCells(updated1);
        final Map<Cell, Boolean> expected = createCellSystem(false);
        expected.replace(new Cell(0, 1), true);
        expected.replace(new Cell(0, 3), true);
        expected.replace(new Cell(1, 0), true);
        expected.replace(new Cell(1, 3), true);
        expected.replace(new Cell(2, 1), true);
        expected.replace(new Cell(2, 2), true);
        assertThat(updated2, equalTo(expected));
    }

    @Test
    void shouldKillCellWithLessThanTwoLivingNeighbours() {
        final Map<Cell, Boolean> cellSystem = createCellSystem(false);
        final Cell cellToCompare = new Cell(1, 2);
        final Cell livingCell1 = new Cell(3, 2);
        final Cell livingCell2 = new Cell(4, 4);
        cellSystem.put(cellToCompare, true);
        cellSystem.put(livingCell1, true);
        cellSystem.put(livingCell2, true);
        assertTrue(Main.checkIfCellIsKilledByUnderpopulation(cellSystem, cellToCompare));
    }

    @Test
    void shouldNotKillCellWithTwoLivingNeighbours() {
        final Map<Cell, Boolean> cellSystem = createCellSystem(false);
        final Cell cellToCompare = new Cell(1, 2);
        final Cell livingCell1 = new Cell(2, 2);
        final Cell livingCell2 = new Cell(0, 1);
        cellSystem.put(cellToCompare, true);
        cellSystem.put(livingCell1, true);
        cellSystem.put(livingCell2, true);

        assertFalse(Main.checkIfCellIsKilledByUnderpopulation(cellSystem, cellToCompare));
    }

    @Test
    void shouldKillCellWithMoreThanThreeLivingNeighbours() {
        final Map<Cell, Boolean> cellSystem = createCellSystem(false);
        final Cell cellToCompare = new Cell(1, 2);
        cellSystem.replace(new Cell(1, 2), true);
        cellSystem.replace(new Cell(1, 1), true);
        cellSystem.replace(new Cell(2, 1), true);
        cellSystem.replace(new Cell(2, 3), true);
        cellSystem.replace(new Cell(0, 3), true);


        assertTrue(Main.checkIfCellIsKilledByOverpopulation(cellSystem, cellToCompare));
    }

    @Test
    void shouldResurrectCellWithExactThreeLivingNeighbours() {
        final Map<Cell, Boolean> cellSystem = createCellSystem(false);
        final Cell cellToCompare = new Cell(1, 2);
        final Cell livingCell1 = new Cell(2, 2);
        final Cell livingCell2 = new Cell(0, 1);
        final Cell livingCell3 = new Cell(1, 3);
        cellSystem.put(livingCell1, true);
        cellSystem.put(livingCell2, true);
        cellSystem.put(livingCell3, true);

        assertTrue(Main.checkIfCellIsResurrect(cellSystem, cellToCompare));
    }

    private Map<Cell, Boolean> createCellSystem(boolean livingCell) {
        Map<Cell, Boolean> cells = new HashMap<>();
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                cells.put(new Cell(x, y), livingCell);
            }
        }
        return cells;
    }

}