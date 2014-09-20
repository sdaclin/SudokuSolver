package sudoku.solver.business;

import org.junit.Test;
import sudoku.solver.model.Grid;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SolverTest {
  public Solver solver = new SolverBrutForce();

  @Test
  public void testGridConstructor() {
    String alEscargotStr = RefGrids.AL_ESCARGOT.toString();
    assertEquals("1\t0\t0\t0\t0\t7\t0\t9\t0", alEscargotStr.substring(0,"1\t0\t0\t0\t0\t7\t0\t9\t0".length()));
  }

  @Test
  public void testSetLine() {
    Grid newGrid = solver.setCell(RefGrids.AL_ESCARGOT, 1, 0, 1);
    assertEquals(newGrid.getVal(1, 0), 1);
    assertNotSame(RefGrids.AL_ESCARGOT,newGrid);
    assertNotSame(RefGrids.AL_ESCARGOT.getContent()[0], newGrid.getContent()[0]);
  }

  @Test
  public void testCheckCol() {
    Grid gridOK = solver.setCell(RefGrids.AL_ESCARGOT, 0, 1, 2);
    assertTrue("Check Colonne OK",solver.checkCol(gridOK, 0));
    Grid gridKO = solver.setCell(RefGrids.AL_ESCARGOT, 0, 1, 1);
    assertFalse("Check Colonne NOK",solver.checkCol(gridKO, 0));
  }

  @Test
  public void testCheckLine() {
    Grid gridOK = solver.setCell(RefGrids.AL_ESCARGOT, 1, 0, 2);
    assertTrue(solver.checkLine(gridOK, 0));
    Grid gridKO = solver.setCell(RefGrids.AL_ESCARGOT, 1, 0, 1);
    assertFalse(solver.checkLine(gridKO, 0));
  }

  @Test
  public void testCheckSector() {
    Grid gridOK = solver.setCell(RefGrids.AL_ESCARGOT, 1, 0, 2);
    assertTrue(solver.checkSector(gridOK, 0, 0));
    Grid gridKO = solver.setCell(RefGrids.AL_ESCARGOT, 1, 0, 9);
    assertFalse(solver.checkSector(gridKO, 0, 0));

    Grid gridTest;
    gridTest = solver.setCell(RefGrids.AL_ESCARGOT, 1, 6, 5);
    gridTest = solver.setCell(gridTest, 2, 6, 8);
    gridTest = solver.setCell(gridTest, 0, 7, 9);
    gridTest = solver.setCell(gridTest, 2, 7, 1);
    gridTest = solver.setCell(gridTest, 0, 8, 8);
    gridTest = solver.setCell(gridTest, 1, 8, 9);
    assertFalse(solver.checkSector(gridTest, 0, 6));
  }

  @Test
  public void testSolve() {
    Grid result = solver.solve(RefGrids.AL_ESCARGOT);
    for (short i=0; i<9;i++){
      assertTrue(solver.checkCol(result, i));
      assertTrue(solver.checkLine(result, i));
      for (short j=0; j<9; j++) {
        assertFalse(result.getVal(i,j) == 0);
        assertTrue(solver.checkSector(result, i, j));

      }
    }
    System.out.println(result);
  }
}