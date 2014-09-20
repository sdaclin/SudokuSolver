package sudoku.solver.business;

import sudoku.solver.model.Grid;

/**
 * Some common tools to use over a Sudoku Grid
 */
public class GridTools {

  /**
   * Set a cell in an immutable way
   *
   * @param grid src
   * @param x    abscissa of the cell to set
   * @param y    ordinate of the celle to set
   * @param val  to set
   * @return a new grid with the new value
   */
  public static Grid setCell(Grid grid, int x, int y, int val) {
    if (!(grid.getContent()[y][x] == 0)) {
      throw new IllegalArgumentException("Can't set a cell that is already filled");
    }
    Grid newGrid = grid.clone();
    newGrid.getContent()[y][x] = val;
    return newGrid;
  }

  /**
   * Check if a col has no duplicates
   *
   * @param grid the grid to process
   * @param x    abscissa of the line to check
   * @return true if there is no duplicate
   */
  public static boolean checkCol(Grid grid, int x) {
    for (int i = 0; i < 9; i++) {
      // No need to test empty cell
      if (grid.getVal(x, i) == 0) {
        continue;
      }
      for (int j = 0; j < i; j++) {
        if (grid.getVal(x, i) == grid.getVal(x, j)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Check if a line has no duplicates
   *
   * @param grid the grid to process
   * @param y    y ordinate of the line to check
   * @return true if there is no duplicate
   */
  public static boolean checkLine(Grid grid, int y) {
    for (int i = 0; i < 9; i++) {
      // No need to test empty cell
      if (grid.getVal(i, y) == 0) {
        continue;
      }
      for (int j = 0; j < i; j++) {
        if (grid.getVal(i, y) == grid.getVal(j, y)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Check if a sector (one of the nine subsquares in a sudoku) has no duplicates
   *
   * @param grid the grid to process
   * @param x    x coord of a cell inside the sector to check
   * @param y    y coord of a cell inside the sector to check
   * @return true if there is no duplicate
   */
  public static boolean checkSector(Grid grid, int x, int y) {

    for (int i = (x / 3) * 3; i < ((x / 3) + 1) * 3; i++) {
      for (int j = (y / 3) * 3; j < ((y / 3) + 1) * 3; j++) {
        // No need to test empty cell
        if (grid.getVal(i, j) == 0) {
          continue;
        }
        TestVals:
        for (int k = (x / 3) * 3; k < ((x / 3) + 1) * 3; k++) {
          for (int l = (y / 3) * 3; l < ((y / 3) + 1) * 3; l++) {
            if (i == k && j == l) {
              continue TestVals;
            }
            if (grid.getVal(i, j) == grid.getVal(k, l)) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }
}