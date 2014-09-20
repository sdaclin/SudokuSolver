package sudoku.solver.business;

import org.junit.Test;
import sudoku.solver.exception.IllegalGridException;
import sudoku.solver.model.Grid;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SolverTest {
  private Solver solver = new SolverBrutForce();
  private Grid grid = RefGrids.EASY_GRID;

  @Test
  public void testGridConstructor() {
    String alEscargotStr = RefGrids.AL_ESCARGOT.toString();
    assertEquals("1\t0\t0\t0\t0\t7\t0\t9\t0", alEscargotStr.substring(0, "1\t0\t0\t0\t0\t7\t0\t9\t0".length()));
  }

  @Test
  public void testSetLine() {
    Grid newGrid = GridTools.setCell(RefGrids.AL_ESCARGOT, 1, 0, 1);
    assertEquals(newGrid.getVal(1, 0), 1);
    assertNotSame(RefGrids.AL_ESCARGOT, newGrid);
    assertNotSame(RefGrids.AL_ESCARGOT.getContent()[0], newGrid.getContent()[0]);
  }

  @Test
  public void testCheckCol() {
    Grid gridOK = GridTools.setCell(RefGrids.AL_ESCARGOT, 0, 1, 2);
    assertTrue("Check Colonne OK", GridTools.checkCol(gridOK, 0));
    Grid gridKO = GridTools.setCell(RefGrids.AL_ESCARGOT, 0, 1, 1);
    assertFalse("Check Colonne NOK", GridTools.checkCol(gridKO, 0));
  }

  @Test
  public void testCheckLine() {
    Grid gridOK = GridTools.setCell(RefGrids.AL_ESCARGOT, 1, 0, 2);
    assertTrue(GridTools.checkLine(gridOK, 0));
    Grid gridKO = GridTools.setCell(RefGrids.AL_ESCARGOT, 1, 0, 1);
    assertFalse(GridTools.checkLine(gridKO, 0));
  }

  @Test
  public void testCheckSector() {
    Grid gridOK = GridTools.setCell(RefGrids.AL_ESCARGOT, 1, 0, 2);
    assertTrue(GridTools.checkSector(gridOK, 0, 0));
    Grid gridKO = GridTools.setCell(RefGrids.AL_ESCARGOT, 1, 0, 9);
    assertFalse(GridTools.checkSector(gridKO, 0, 0));

    Grid gridTest;
    gridTest = GridTools.setCell(RefGrids.AL_ESCARGOT, 1, 6, 5);
    gridTest = GridTools.setCell(gridTest, 2, 6, 8);
    gridTest = GridTools.setCell(gridTest, 0, 7, 9);
    gridTest = GridTools.setCell(gridTest, 2, 7, 1);
    gridTest = GridTools.setCell(gridTest, 0, 8, 8);
    gridTest = GridTools.setCell(gridTest, 1, 8, 9);
    assertFalse(GridTools.checkSector(gridTest, 0, 6));
  }

  @Test
  public void testSolve() {
    Grid result;
    try {
      result = solver.solve(RefGrids.AL_ESCARGOT);
    } catch (IllegalGridException ige) {
      fail("Even if Al escargot is tough, it definitely has a solution :)");
      return;
    }
    for (short i = 0; i < 9; i++) {
      assertTrue(GridTools.checkCol(result, i));
      assertTrue(GridTools.checkLine(result, i));
      for (short j = 0; j < 9; j++) {
        assertFalse(result.getVal(i, j) == 0);
        assertTrue(GridTools.checkSector(result, i, j));

      }
    }
    System.out.println(result);
  }
}