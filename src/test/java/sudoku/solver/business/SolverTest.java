package sudoku.solver.business;

import org.junit.Test;
import sudoku.solver.model.Grid;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SolverTest {
  public static final Grid alEscaargot = new Grid(
      new short[]{1, 0, 0, 0, 0, 7, 0, 9, 0},
      new short[]{0, 3, 0, 0, 2, 0, 0, 0, 8},
      new short[]{0, 0, 9, 6, 0, 0, 5, 0, 0},
      new short[]{0, 0, 5, 3, 0, 0, 9, 0, 0},
      new short[]{0, 1, 0, 0, 8, 0, 0, 0, 2},
      new short[]{6, 0, 0, 0, 0, 4, 0, 0, 0},
      new short[]{3, 0, 0, 0, 0, 0, 0, 1, 0},
      new short[]{0, 4, 0, 0, 0, 0, 0, 0, 7},
      new short[]{0, 0, 7, 0, 0, 0, 3, 0, 0});

  public Solver solver = new SolverBrutForce();

  @Test
  public void testGridConstructor() {
    String alEscargotStr = alEscaargot.toString();
    assertEquals("1\t0\t0\t0\t0\t7\t0\t9\t0", alEscargotStr.substring(0,"1\t0\t0\t0\t0\t7\t0\t9\t0".length()));
  }

  @Test
  public void testSetLine() {
    Grid newGrid = solver.setCell(alEscaargot, (short) 1, (short) 0, (short) 1);
    assertEquals(newGrid.getVal((short) 1, (short) 0), 1);
    assertNotSame(alEscaargot,newGrid);
    assertNotSame(alEscaargot.getContent()[0], newGrid.getContent()[0]);
  }

  @Test
  public void testCheckCol() {
    Grid gridOK = solver.setCell(alEscaargot, (short) 0, (short) 1, (short) 2);
    assertTrue("Check Colonne OK",solver.checkCol(gridOK, (short) 0));
    Grid gridKO = solver.setCell(alEscaargot, (short) 0, (short) 1, (short) 1);
    assertFalse("Check Colonne NOK",solver.checkCol(gridKO, (short) 0));
  }

  @Test
  public void testCheckLine() {
    Grid gridOK = solver.setCell(alEscaargot, (short) 1, (short) 0, (short) 2);
    assertTrue(solver.checkLine(gridOK, (short) 0));
    Grid gridKO = solver.setCell(alEscaargot, (short) 1, (short) 0, (short) 1);
    assertFalse(solver.checkLine(gridKO, (short) 0));
  }

  @Test
  public void testCheckSector() {
    Grid gridOK = solver.setCell(alEscaargot, (short) 1, (short) 0, (short) 2);
    assertTrue(solver.checkSector(gridOK, (short) 0, (short) 0));
    Grid gridKO = solver.setCell(alEscaargot, (short) 1, (short) 0, (short) 9);
    assertFalse(solver.checkSector(gridKO, (short) 0, (short) 0));

    Grid gridTest;
    gridTest = solver.setCell(alEscaargot, (short) 1, (short) 6, (short) 5);
    gridTest = solver.setCell(gridTest, (short) 2, (short) 6, (short) 8);
    gridTest = solver.setCell(gridTest, (short) 0, (short) 7, (short) 9);
    gridTest = solver.setCell(gridTest, (short) 2, (short) 7, (short) 1);
    gridTest = solver.setCell(gridTest, (short) 0, (short) 8, (short) 8);
    gridTest = solver.setCell(gridTest, (short) 1, (short) 8, (short) 9);
    assertFalse(solver.checkSector(gridTest, (short) 0, (short) 6));
  }

  @Test
  public void testSolve() {
    Grid result = solver.solve(alEscaargot);
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