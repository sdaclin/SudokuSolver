package sudoku.solver.model;

import java.util.Arrays;

/**
 * A mutable Sudoku Grid backed by an array of int[][]
 */
public class Grid implements Cloneable {
  private int[][] content;

  public Grid(int[]... lines) {
    if (lines.length != 9) {
      lines = new int[9][9];
    }
    content = lines;
  }

  public int[][] getContent() {
    return content;
  }

  public int getVal(int x, int y) {
    return content[y][x];
  }

  @Override
  public Grid clone() {
    Grid clonedGrid;
    try {
      clonedGrid = (Grid) super.clone();
    } catch (CloneNotSupportedException cnse) {
      throw new RuntimeException(cnse);
    }
    // Deep copy of arrays
    clonedGrid.content = new int[9][];
    for (int i = 0; i < 9; i++) {
      clonedGrid.content[i] = Arrays.copyOf(this.content[i], 9);
    }
    return clonedGrid;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int[] col : content) {
      for (int cell : col) {
        builder.append(String.valueOf(cell) + "\t");
      }
      builder.append("\n");
    }
    return builder.toString();
  }
}
