package sudoku.solver.business;

import sudoku.solver.model.Grid;

import java.util.LinkedList;

/**
 * An implementation of a Solver that makes brut force tries from every empty cell found from coord(0,0) to coord(8,8)
 *
 * This implementation differ from the recursive one by the use of an array of Move that register every tentative move in order to undo if the tree goes nowhere
 * <p/>
 * Tries are made from 1 to 9 regardless of adjacent cells.
 * Checks are made afterward to verify viability.
 */
public class BrutForceFlatSolver implements Solver {

  public class Move {
    private int idx;
    private int x;
    private int y;
    private int val;

    public Move(int idx, int val) {
      this.idx = idx;
      this.val = val;
    }

    public int getIdx() {
      return idx;
    }

    public int getVal() {
      return val;
    }
  }

  @Override
  public Grid solve(Grid grid) {
    LinkedList<Move> moves = new LinkedList<Move>();

    int idx = 0;
    int cellStartingValue=1;
    boolean skipVerifyCell=false;
    cellLoop :
    while (idx < 81) {
      //System.out.println(grid);
      int x = idx % 9;
      int y = idx / 9;
      // Is it an empty cell ?
      if (!skipVerifyCell && grid.getVal(x, y) != 0) {
        // Cell is not empty => continue
        idx++;
        continue;
      }else{
        skipVerifyCell=false;
      }
      for (int i = cellStartingValue; i < 10; i++) {
        if (!(GridTools.checkNewValInCol(grid, x, i) && GridTools.checkNewValInLine(grid, y, i) && GridTools.checkNewValInSector(grid, x, y, i))) {
          continue;
        }
        grid.getContent()[y][x]=i;
        //Memorize move
        Move move = new Move(idx,i);
        moves.add(move);
        // Continue to next idx
        idx++;
        cellStartingValue=1;
        continue cellLoop;
      }
      // No value suitable we must go back
      grid.getContent()[y][x]=0;
      Move moveToUndo = moves.pollLast();
      idx=moveToUndo.getIdx();
      skipVerifyCell=true;
      cellStartingValue=moveToUndo.getVal()+1;
    }
    return grid;
  }
}
