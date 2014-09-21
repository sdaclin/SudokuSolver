package sudoku.solver.business;

import sudoku.solver.exception.IllegalGridException;
import sudoku.solver.model.Grid;

/**
 * An implementation of a Solver that makes recursive brut force tries from every empty cell found from coord(0,0) to coord(8,8)
 * <p/>
 * Tries are made from 1 to 9 regardless of adjacent cells.
 * Checks are made afterward to verify viability.
 */
public class BrutForceRecursiveSolver implements Solver {

  @Override
  public Grid solve(Grid grid) {
    try {
      return solveRecursive(grid, 0);
    } catch (IllegalGridException ige) {
      return grid;
    }
  }

  public Grid solveRecursive(Grid grid, int idx) throws IllegalGridException {
    //System.out.println(grid);
    if (idx == 81) {
      return grid;
    }
    int x = idx % 9;
    int y = idx / 9;
    if (grid.getVal(x, y) != 0) {
      return solveRecursive(grid, idx + 1);
    }
    for (int i = 1; i < 10; i++) {
      Grid nextGrid = GridTools.setCell(grid, x, y, i);
      if (!(GridTools.checkCol(nextGrid, x) && GridTools.checkLine(nextGrid, y) && GridTools.checkSector(nextGrid, x, y))) {
        continue;
      }
      try {
        return solveRecursive(nextGrid, idx + 1);
      } catch (IllegalGridException ige) {
        // fausse piste
        continue;
      }
    }
    throw new IllegalGridException();
  }
}
