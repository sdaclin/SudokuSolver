package sudoku.tools;

import org.junit.Before;
import org.junit.Test;
import sudoku.RefGridsFactory;
import sudoku.model.Grid;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class GridToolsTest {
  private Grid grid;

  @Before
  public void init() {
    grid = RefGridsFactory.get(RefGridsFactory.Name.AL_ESCARGOT);
  }

  @Test
  public void testGridConstructor() {
    String alEscargotStr = grid.toString();
    assertEquals("1\t0\t0\t0\t0\t7\t0\t9\t0", alEscargotStr.substring(0, "1\t0\t0\t0\t0\t7\t0\t9\t0".length()));
  }

  @Test
  public void testSetLine() {
    Grid newGrid = GridTools.setCell(grid, 1, 0, 1);
    assertEquals(newGrid.getVal(1, 0), 1);
    assertNotSame(grid, newGrid);
    assertNotSame(grid.getContent()[0], newGrid.getContent()[0]);
  }

  @Test
  public void testCheckCol() {
    Grid gridOK = GridTools.setCell(grid, 0, 1, 2);
    assertTrue("Check Colonne OK", GridTools.checkCol(gridOK, 0));
    Grid gridKO = GridTools.setCell(grid, 0, 1, 1);
    assertFalse("Check Colonne NOK", GridTools.checkCol(gridKO, 0));
  }

  @Test
  public void testCheckLine() {
    Grid gridOK = GridTools.setCell(grid, 1, 0, 2);
    assertTrue(GridTools.checkLine(gridOK, 0));
    Grid gridKO = GridTools.setCell(grid, 1, 0, 1);
    assertFalse(GridTools.checkLine(gridKO, 0));
  }

  @Test
  public void testCheckSector() {
    Grid gridOK = GridTools.setCell(grid, 1, 0, 2);
    assertTrue(GridTools.checkSector(gridOK, 0, 0));
    Grid gridKO = GridTools.setCell(grid, 1, 0, 9);
    assertFalse(GridTools.checkSector(gridKO, 0, 0));

    Grid gridTest;
    gridTest = GridTools.setCell(grid, 1, 6, 5);
    gridTest = GridTools.setCell(gridTest, 2, 6, 8);
    gridTest = GridTools.setCell(gridTest, 0, 7, 9);
    gridTest = GridTools.setCell(gridTest, 2, 7, 1);
    gridTest = GridTools.setCell(gridTest, 0, 8, 8);
    gridTest = GridTools.setCell(gridTest, 1, 8, 9);
    assertFalse(GridTools.checkSector(gridTest, 0, 6));
  }

  @Test
  public void testGetSectorId(){
    assertEquals(0,GridTools.getSector(0,0));
    assertEquals(1,GridTools.getSector(4,1));
    assertEquals(2,GridTools.getSector(8,2));
    assertEquals(8,GridTools.getSector(6,7));
  }
}