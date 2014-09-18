package sudoku.solver.business;

import sudoku.solver.model.Grid;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sylvain on 18/09/2014.
 */
public class SolverStd implements Solver {
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
  public boolean checkCol(Grid grille, short Y) {
    // Get grille contents
    short[][] content = grille.getContent();

    // New temp list
    Set<Short> tempNum = new HashSet<Short>();

    // Loop on Col ( 0 => 8)
    for (short x = 0; x < 9; x++) {
      // Value => x moving / Y constant
      // Dismiss 0...
      if(content[x][Y] != 0) {
        // If exists break and false
        if (tempNum.contains(content[x][Y])) return false;
        tempNum.add(content[x][Y]);
      }
    }
    return true;
  }
  @Override
  public boolean checkLine(Grid grille, short X) {
    // Get grille contents
    short[][] content = grille.getContent();

    // New temp list
    Set<Short> tempNum = new HashSet<Short>();

    // Loop on Col ( 0 => 8)
    for (int y = 0; y < 9; y++) {
      // Value => X constant / y moving
      // Dismiss 0...
      if(content[X][y] != 0){
        // If exists break and false
        if(tempNum.contains(content[X][y])) return false;
        tempNum.add(content[X][y]);
      }
    }
    return true;
  }
  @Override
  public boolean checkSector(Grid grille, short x, short y) {
    // Get grille contents
    short[][] content = grille.getContent();
    // New temp list
    Set<Short> tempNum = new HashSet<Short>();

    // Determine offset in order to find the right sector coordinates
    short xOffset = (short)((x/3) * 3);
    short yOffset = (short)((y/3) * 3);


    // Double loop on row and columns
    for ( x = xOffset; x < 3 + xOffset; x++) {
      for ( y = yOffset; y < 3 + yOffset; y++) {
        // Value => X moving / y moving
        // Dismiss 0...
        if(content[x][y] != 0){
          if(tempNum.contains(content[x][y])) return false;
          tempNum.add(content[x][y]);
        }
      }
    }
    return true;
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
