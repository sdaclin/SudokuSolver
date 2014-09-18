package sudoku.solver.business;

import sudoku.solver.exception.IllegalGridException;
import sudoku.solver.model.Grid;

/**
 * Created by Sylvain on 18/09/2014.
 */
public class SolverBrutForce implements Solver {
  private int[] existingVals = new int[9];

  @Override
  public Grid setCell(Grid grid, short x, short y, short val) {
    if (!(grid.getContent()[y][x] == 0)) {
      throw new IllegalArgumentException("Can't set a cell that is already filled");
    }
    Grid newGrid = grid.clone();
    newGrid.getContent()[y][x] = val;
    return newGrid;
  }

  @Override
  public boolean checkCol(Grid grid, short x) {
    for (short i = 0; i < 9; i++) {
      // No need to test empty cell
      if (grid.getVal(x, i) == 0) {
        continue;
      }
      for (short j = 0; j < i; j++) {
        if (grid.getVal(x, i) == grid.getVal(x, j)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public boolean checkLine(Grid grid, short y) {
    for (short i = 0; i < 9; i++) {
      // No need to test empty cell
      if (grid.getVal(i, y) == 0) {
        continue;
      }
      for (short j = 0; j < i; j++) {
        if (grid.getVal(i, y) == grid.getVal(j, y)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public boolean checkSector(Grid grid, short x, short y) {

    for (int i = (x / 3) * 3; i < ((x / 3) + 1) * 3; i++) {
      for (int j = (y / 3) * 3; j < ((y / 3) + 1) * 3; j++) {
        // No need to test empty cell
        if (grid.getVal((short) i, (short) j) == 0) {
          continue;
        }
        TestVals:
        for (int k = (x / 3) * 3; k < ((x / 3) + 1) * 3; k++) {
          for (int l = (y / 3) * 3 ; l < ((y / 3) + 1) * 3; l++) {
      if (i == k && j == l) {
        continue TestVals;
      }
      if (grid.getVal((short) i, (short) j) == grid.getVal((short) k, (short) l)) {
        return false;
      }
    }
    }
  }
}
return true;
    }

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
    if (grid.getVal((short) x, (short) y) != 0) {
      return solveRecursive(grid, idx + 1);
    }
    for (int i = 1; i < 10; i++) {
      Grid nextGrid = setCell(grid, (short) x, (short) y, (short) i);
      if (!(checkCol(nextGrid, (short) x) && checkLine(nextGrid, (short) y) && checkSector(nextGrid, (short) x, (short) y))) {
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
