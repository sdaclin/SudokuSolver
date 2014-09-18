package sudoku.solver.business;

import sudoku.solver.model.Grid;

/**
 * Created by Sylvain on 18/09/2014.
 */
public interface Solver {
  Grid setCell(Grid grid, short x, short y, short val);

  boolean checkCol(Grid grid, short x);

  boolean checkLine(Grid grid, short y);

  boolean checkSector(Grid grid, short x, short y);

  Grid solve(Grid grid);
}
