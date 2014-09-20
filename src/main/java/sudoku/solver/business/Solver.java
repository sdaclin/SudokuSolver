package sudoku.solver.business;

import sudoku.solver.model.Grid;

/**
 * Created by Sylvain on 18/09/2014.
 */
public interface Solver {
  Grid setCell(Grid grid, int x, int y, int val);

  boolean checkCol(Grid grid, int x);

  boolean checkLine(Grid grid, int y);

  boolean checkSector(Grid grid, int x, int y);

  Grid solve(Grid grid);
}
