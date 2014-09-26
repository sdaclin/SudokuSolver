package sudoku;

import sudoku.model.Grid;

import java.util.Arrays;

/**
 * Created by sylvain on 9/20/14.
 */
public class RefGridsFactory {

  public enum Name {
    // From http://zonkedyak.blogspot.fr/2006/11/worlds-hardest-sudoku-puzzle-al.html
    AL_ESCARGOT (
        new int[]{1, 0, 0, 0, 0, 7, 0, 9, 0},
        new int[]{0, 3, 0, 0, 2, 0, 0, 0, 8},
        new int[]{0, 0, 9, 6, 0, 0, 5, 0, 0},
        new int[]{0, 0, 5, 3, 0, 0, 9, 0, 0},
        new int[]{0, 1, 0, 0, 8, 0, 0, 0, 2},
        new int[]{6, 0, 0, 0, 0, 4, 0, 0, 0},
        new int[]{3, 0, 0, 0, 0, 0, 0, 1, 0},
        new int[]{0, 4, 0, 0, 0, 0, 0, 0, 7},
        new int[]{0, 0, 7, 0, 0, 0, 3, 0, 0},

        new int[]{1, 6, 2, 8, 5, 7, 4, 9, 3},
        new int[]{5, 3, 4, 1, 2, 9, 6, 7, 8},
        new int[]{7, 8, 9, 6, 4, 3, 5, 2, 1},
        new int[]{4, 7, 5, 3, 1, 2, 9, 8, 6},
        new int[]{9, 1, 3, 5, 8, 6, 7, 4, 2},
        new int[]{6, 2, 8, 7, 9, 4, 1, 3, 5},
        new int[]{3, 5, 6, 4, 7, 8, 2, 1, 9},
        new int[]{2, 4, 1, 9, 3, 5, 8, 6, 7},
        new int[]{8, 9, 7, 2, 6, 1, 3, 5, 4}),

    // From http://money.cnn.com/popups/2005/consumer/sudoku/content.1.html
    EASY_GRID (
        new int[]{0, 7, 2, 0, 3, 6, 4, 0, 0},
        new int[]{0, 9, 0, 7, 0, 0, 0, 3, 5},
        new int[]{0, 0, 0, 1, 8, 0, 0, 0, 2},
        new int[]{2, 0, 6, 0, 0, 0, 0, 9, 0},
        new int[]{3, 0, 5, 0, 9, 0, 6, 0, 8},
        new int[]{0, 4, 0, 0, 0, 0, 5, 0, 1},
        new int[]{7, 0, 0, 0, 2, 3, 0, 0, 0},
        new int[]{1, 5, 0, 0, 0, 4, 0, 8, 0},
        new int[]{0, 0, 8, 6, 1, 0, 9, 7, 0},

        new int[]{8, 7, 2, 5, 3, 6, 4, 1, 9},
        new int[]{6, 9, 1, 7, 4, 2, 8, 3, 5},
        new int[]{5, 3, 4, 1, 8, 9, 7, 6, 2},
        new int[]{2, 8, 6, 4, 5, 1, 3, 9, 7},
        new int[]{3, 1, 5, 2, 9, 7, 6, 4, 8},
        new int[]{9, 4, 7, 3, 6, 8, 5, 2, 1},
        new int[]{7, 6, 9, 8, 2, 3, 1, 5, 4},
        new int[]{1, 5, 3, 9, 7, 4, 2, 8, 6},
        new int[]{4, 2, 8, 6, 1, 5, 9, 7, 3}),

    MEDIUM_GRID (
        new int[]{0, 0, 0, 9, 0, 7, 0, 0, 0},
        new int[]{9, 0, 0, 0, 0, 0, 0, 0, 8},
        new int[]{0, 3, 0, 4, 0, 5, 0, 2, 0},
        new int[]{3, 0, 7, 0, 4, 0, 2, 0, 6},
        new int[]{0, 0, 0, 5, 0, 9, 0, 0, 0},
        new int[]{8, 0, 9, 0, 2, 0, 1, 0, 3},
        new int[]{0, 7, 0, 6, 0, 4, 0, 3, 0},
        new int[]{2, 0, 0, 0, 0, 0, 0, 0, 9},
        new int[]{0, 0, 0, 1, 0, 2, 0, 0, 0},

        new int[]{4, 8, 2, 9, 1, 7, 3, 6, 5},
        new int[]{9, 1, 5, 2, 6, 3, 4, 7, 8},
        new int[]{7, 3, 6, 4, 8, 5, 9, 2, 1},
        new int[]{3, 5, 7, 8, 4, 1, 2, 9, 6},
        new int[]{6, 2, 1, 5, 3, 9, 8, 4, 7},
        new int[]{8, 4, 9, 7, 2, 6, 1, 5, 3},
        new int[]{1, 7, 8, 6, 9, 4, 5, 3, 2},
        new int[]{2, 6, 4, 3, 5, 8, 7, 1, 9},
        new int[]{5, 9, 3, 1, 7, 2, 6, 8, 4});

    private Grid grid;
    private Grid result;

    Name(int[]... content){
      grid = new Grid(Arrays.copyOfRange(content,0,9));
      result = new Grid(Arrays.copyOfRange(content,9,18));
    }
  }

  /**
   * Return a new clone of the desired grid
   * @param refGrid
   * @return
   */
  public static Grid get(Name refGrid){
    return refGrid.grid.clone();
  }

  public static Grid getResult(Name name){
    return name.result.clone();
  }
}
