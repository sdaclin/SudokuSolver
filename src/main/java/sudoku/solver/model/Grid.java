package sudoku.solver.model;

import java.util.Arrays;

/**
 * Created by Sylvain on 18/09/2014.
 */
public class Grid {
  private short[][] content;

  public Grid(short[]... lines){
    if (lines.length != 9){
      lines = new short[9][9];
    }
    content = lines;
  }

  public short[][] getContent() {
    return content;
  }

  public short getVal(short x, short y){
    return content[y][x];
  }

  @Override
  public Grid clone() {
    Grid newGrille = new Grid();
    for (short i=0; i<9;i++){
      newGrille.content[i] = Arrays.copyOf(this.content[i],9);
    }
    return newGrille;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (short[] col: content){
      for (short cell : col){
        builder.append(String.valueOf(cell) + "\t");
      }
      builder.append("\n");
    }
    return builder.toString();
  }
}
