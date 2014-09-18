package sudoku.solver.business;

import sudoku.solver.model.Grid;

/**
 * Created by Sylvain on 18/09/2014.
 */
public class SolverStd implements Solver {
  @Override
  public Grid setCell(Grid grid, short x, short y, short val) {
    if (!(grid.getContent()[x][y] == 0)) {
      throw new IllegalArgumentException("Can't set a cell that is already filled");
    }
    Grid newGrid = grid.clone();
    newGrid.getContent()[y][x] = val;
    return newGrid;
  }

  @Override
  public boolean checkCol(Grid grid, short x) {
    return false;
  }

  @Override
  public boolean checkLine(Grid grid, short y) {
    return false;
  }

  @Override
  public boolean checkSector(Grid grid, short id) {
    return false;
  }

  @Override
  public Grid solve(Grid grid) {
    return null;
  }
}
