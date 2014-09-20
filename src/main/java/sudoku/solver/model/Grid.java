package sudoku.solver.model;

import java.util.Arrays;

/**
 * A mutable Sudoku Grid backed by an array of short[][]
 */
public class Grid implements Cloneable {
  private short[][] content;

  public Grid(short[]... lines) {
    if (lines.length != 9) {
      lines = new short[9][9];
    }
    content = lines;
  }

  public short[][] getContent() {
    return content;
  }

  public short getVal(short x, short y) {
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
    clonedGrid.content = new short[9][];
    for (short i = 0; i < 9; i++) {
      clonedGrid.content[i] = Arrays.copyOf(this.content[i], 9);
    }
    return clonedGrid;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (short[] col : content) {
      for (short cell : col) {
        builder.append(String.valueOf(cell) + "\t");
      }
      builder.append("\n");
    }
    return builder.toString();
  }
}
