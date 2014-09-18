package sudoku.solver.business;

import sudoku.solver.model.Grid;

import java.util.*;

/**
 * Created by Sylvain on 18/09/2014.
 */
public class SolverAdrien implements Solver {

  private class Probabilites implements Comparable{
    public int x;
    public int y;
    public Set<Short> values;

    public Probabilites(short x , short y, Set<Short> values){
      this.x = x;
      this.y = y;
      this.values = values;
    }

    @Override
    public int compareTo(Object o) {
      final int BEFORE = -1;
      final int EQUAL = 0;
      final int AFTER = 1;

      Probabilites p = (Probabilites) o ;

      if(this.values.size() < p.values.size()) return BEFORE;
      if(this.values.size() > p.values.size()) return AFTER;
      if(this.values.size() == p.values.size()) return EQUAL;

      return EQUAL;
    }
  }

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

  public Set<Short> getPossibilities(Grid grille, short x, short y) {
    Set<Short> values = new HashSet<Short>();

    for ( short i = 1; i < 9 ; i++) {
      Grid gridtest = setCell(grille, x, y, i);
      if(checkCol(gridtest,y) && checkLine(gridtest,x) && checkSector(gridtest,x,y)){
        values.add(i);
      }
    }

    return values;
  }

  public boolean getBestPossibilities(Grid grille) {
    short[][] content = grille.getContent();

    List<Probabilites> probabilites = new ArrayList<Probabilites>();

    for ( short x = 0; x < 9 ; x++) {
      for ( short y = 0; y < 9 ; y++) {
        if(content[x][y] == 0){
          Probabilites proba = new Probabilites(x , y , getPossibilities(grille, x, y) );
          probabilites.add(proba);
        }
      }
    }
    Collections.sort(probabilites);

    return true;
  }

  @Override
  public Grid solve(Grid grid) {

    solveRecursive(grid, 0);

    return null;
  }

  public Grid solveRecursive(Grid grid, int idx) {

    getBestPossibilities(grid);



    return grid;
  }
}