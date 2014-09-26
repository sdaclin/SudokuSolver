package sudoku.solver;

import sudoku.exception.IllegalGridException;
import sudoku.model.Grid;

/**
 * Interface for a Sudoku Solver
 */
public interface Solver {
  Grid solve(Grid grid) throws IllegalGridException;
}
