package sudoku.solver.business;

import sudoku.solver.exception.IllegalGridException;
import sudoku.solver.model.Grid;

/**
 * An implementation of a Solver that memorize lines, cell and sectors with fewer possibilities
 */
public class CleverSolver implements Solver {
  @Override
  public Grid solve(Grid grid) {
    try {
      return solveRecursive(0, grid, new int[9], new int[9], new int[9], true);
    } catch (IllegalGridException ige) {
      return grid;
    }
  }

  public Grid solveRecursive(long depth, Grid grid, int[] cols, int[] lines, int sectors[], boolean init) throws IllegalGridException {
    //System.out.println(depth);
    // Initializing filling arrays
    if (init) {
      for (int x = 0; x < 9; x++) {
        for (int y = 0; y < 9; y++) {
          if (grid.getVal(x, y) != 0) {
            incrementCounters(cols, lines, sectors, x,y, GridTools.getSector(x, y), 1);
          }
        }
      }
    }
    if (!hasHole(cols)) {
      // Everything is done
      return grid;
    }
    // Search for the best coord to fill
    int bestScore=0;
    int bestX=0;
    int bestY=0;
    for (int x = 0; x < 9; x++) {
      for (int y = 0; y < 9; y++) {
        if (grid.getVal(x, y) == 0) {
          int currentScore = cols[y] + lines[x] + sectors[GridTools.getSector(x, y)];
          if (currentScore > bestScore) {
            bestScore = currentScore;
            bestX = x;
            bestY = y;
          }
          // TODO check this optimisation
          //if (currentScore==24){
          //  break;
          //}
        }
      }
    }

    // Make an assumption
    for (int i=1;i<10;i++){
      Grid nextGrid = GridTools.setCell(grid, bestX, bestY, i);
      if (!(GridTools.checkCol(nextGrid, bestX) && GridTools.checkLine(nextGrid, bestY) && GridTools.checkSector(nextGrid, bestX, bestY))) {
        continue;
      }
      int bestSector = GridTools.getSector(bestX, bestY);
      incrementCounters(cols, lines, sectors, bestX,bestY, bestSector, 1);
      try {
        return solveRecursive(depth++, nextGrid, cols, lines, sectors, false);
      } catch (IllegalGridException ige) {
        // fausse piste
        incrementCounters(cols, lines, sectors, bestX,bestY, bestSector, -1);
        continue;
      }
    }

    throw new IllegalGridException();
  }

  private void incrementCounters(int[] cols, int[] lines, int[] sectors, int x, int y, int sector, int nb) {
    cols[x] = cols[x] + nb;
    lines[y] = lines[y] + nb;
    sectors[sector] = sectors[sector] + nb;
  }

  private boolean hasHole(int[] array) {
    for (int val : array) {
      if (val != 9) {
        return true;
      }
    }
    return false;
  }
}
