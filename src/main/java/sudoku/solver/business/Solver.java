package sudoku.solver.business;

import sudoku.solver.exception.IllegalGridException;
import sudoku.solver.model.Grid;

/**
 * Interface for a Sudoku Solver
 */
public interface Solver {
  Grid solve(Grid grid) throws IllegalGridException;
}
