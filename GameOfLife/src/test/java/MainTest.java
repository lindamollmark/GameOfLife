import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void shouldUpdateBoard() {
        Set<Cell> cells = createCellSystem(false);
        cells.remove(new Cell(0, 3, true));
        cells.remove(new Cell(0, 2, true));
        cells.remove(new Cell(1, 2, true));
        cells.remove(new Cell(1, 1, true));
        cells.remove(new Cell(2, 1, true));
        cells.add(new Cell(0, 3, true));
        cells.add(new Cell(0, 2, true));
        cells.add(new Cell(1, 2, true));
        cells.add(new Cell(1, 1, true));
        cells.add(new Cell(2, 1, true));

        final Set<Cell> expected = createCellSystem(false);
        expected.remove(new Cell(0, 1, true));
        expected.remove(new Cell(0, 2, true));
        expected.remove(new Cell(0, 3, true));
        expected.remove(new Cell(1, 1, true));
        expected.remove(new Cell(1, 3, true));
        expected.remove(new Cell(2, 1, true));
        expected.remove(new Cell(2, 2, true));
        expected.add(new Cell(0, 1, true));
        expected.add(new Cell(0, 2, true));
        expected.add(new Cell(0, 3, true));
        expected.add(new Cell(1, 1, true));
        expected.add(new Cell(1, 3, true));
        expected.add(new Cell(2, 1, true));
        expected.add(new Cell(2, 2, true));
        final Set<Cell> updated = Main.updateCells(cells);
        assertThat(updated, equalTo(expected));
    }

    @Test
    void shouldUpdateBoardTwoRounds() {
        Set<Cell> cells = createCellSystem(false);
        cells.remove(new Cell(0, 3, true));
        cells.remove(new Cell(0, 2, true));
        cells.remove(new Cell(1, 2, true));
        cells.remove(new Cell(1, 1, true));
        cells.remove(new Cell(2, 1, true));
        cells.add(new Cell(0, 3, true));
        cells.add(new Cell(0, 2, true));
        cells.add(new Cell(1, 2, true));
        cells.add(new Cell(1, 1, true));
        cells.add(new Cell(2, 1, true));

        final Set<Cell> updated1 = Main.updateCells(cells);
        final Set<Cell> updated2 = Main.updateCells(updated1);
        final Set<Cell> expected = createCellSystem(false);
        expected.remove(new Cell(0, 1, true));
        expected.remove(new Cell(0, 3, true));
        expected.remove(new Cell(1, 0, true));
        expected.remove(new Cell(1, 3, true));
        expected.remove(new Cell(2, 1, true));
        expected.remove(new Cell(2, 2, true));
        expected.add(new Cell(0, 1, true));
        expected.add(new Cell(0, 3, true));
        expected.add(new Cell(1, 0, true));
        expected.add(new Cell(1, 3, true));
        expected.add(new Cell(2, 1, true));
        expected.add(new Cell(2, 2, true));
        assertThat(updated2, equalTo(expected));
    }

    @Test
    void shouldKillCellWithLessThanTwoLivingNeighbours() {
        final Set<Cell> cellSystem = createCellSystem(false);
        final Cell cellToCompare = new Cell(1, 2, true);
        final Cell livingCell1 = new Cell(3, 2, true);
        final Cell livingCell2 = new Cell(4, 4, true);
        cellSystem.remove(cellToCompare);
        cellSystem.remove(livingCell1);
        cellSystem.remove(livingCell2);
        cellSystem.add(cellToCompare);
        cellSystem.add(livingCell1);
        cellSystem.add(livingCell2);
        assertTrue(Main.checkIfCellIsKilledByUnderpopulation(cellSystem, cellToCompare));
    }

    @Test
    void shouldNotKillCellWithTwoLivingNeighbours() {
        final Set<Cell> cellSystem = createCellSystem(false);
        final Cell cellToCompare = new Cell(1, 2, true);
        final Cell livingCell1 = new Cell(2, 2, true);
        final Cell livingCell2 = new Cell(0, 1, true);
        cellSystem.remove(cellToCompare);
        cellSystem.remove(livingCell1);
        cellSystem.remove(livingCell2);
        cellSystem.add(cellToCompare);
        cellSystem.add(livingCell1);
        cellSystem.add(livingCell2);

        assertFalse(Main.checkIfCellIsKilledByUnderpopulation(cellSystem, cellToCompare));
    }

    @Test
    void shouldKillCellWithMoreThanThreeLivingNeighbours() {
        final Set<Cell> cellSystem = createCellSystem(false);
        final Cell cellToCompare = new Cell(1, 2, true);
        cellSystem.remove(new Cell(1, 2, true));
        cellSystem.remove(new Cell(1, 1, true));
        cellSystem.remove(new Cell(2, 1, true));
        cellSystem.remove(new Cell(2, 3, true));
        cellSystem.remove(new Cell(0, 3, true));

        cellSystem.add(new Cell(1, 2, true));
        cellSystem.add(new Cell(1, 1, true));
        cellSystem.add(new Cell(2, 1, true));
        cellSystem.add(new Cell(2, 3, true));
        cellSystem.add(new Cell(0, 3, true));


        assertTrue(Main.checkIfCellIsKilledByOverpopulation(cellSystem, cellToCompare));
    }

    @Test
    void shouldResurrectCellWithExactThreeLivingNeighbours() {
        final Set<Cell> cellSystem = createCellSystem(false);
        final Cell cellToCompare = new Cell(1, 2, false);
        final Cell livingCell1 = new Cell(2, 2, true);
        final Cell livingCell2 = new Cell(0, 1, true);
        final Cell livingCell3 = new Cell(1, 3, true);
        cellSystem.remove(livingCell1);
        cellSystem.remove(livingCell2);
        cellSystem.remove(livingCell3);

        cellSystem.add(livingCell1);
        cellSystem.add(livingCell2);
        cellSystem.add(livingCell3);

        assertTrue(Main.checkIfCellIsResurrect(cellSystem, cellToCompare));
    }

    private Set<Cell> createCellSystem(boolean livingCell) {
        Set<Cell> cells = new HashSet<>();
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                cells.add(new Cell(x, y, livingCell));
            }
        }
        return cells;
    }

}