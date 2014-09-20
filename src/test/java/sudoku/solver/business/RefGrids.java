package sudoku.solver.business;

import sudoku.solver.model.Grid;

/**
 * Created by sylvain on 9/20/14.
 */
public class RefGrids {
  // From http://zonkedyak.blogspot.fr/2006/11/worlds-hardest-sudoku-puzzle-al.html
  public static final Grid AL_ESCARGOT = new Grid(
      new int[]{1, 0, 0, 0, 0, 7, 0, 9, 0},
      new int[]{0, 3, 0, 0, 2, 0, 0, 0, 8},
      new int[]{0, 0, 9, 6, 0, 0, 5, 0, 0},
      new int[]{0, 0, 5, 3, 0, 0, 9, 0, 0},
      new int[]{0, 1, 0, 0, 8, 0, 0, 0, 2},
      new int[]{6, 0, 0, 0, 0, 4, 0, 0, 0},
      new int[]{3, 0, 0, 0, 0, 0, 0, 1, 0},
      new int[]{0, 4, 0, 0, 0, 0, 0, 0, 7},
      new int[]{0, 0, 7, 0, 0, 0, 3, 0, 0});

  // From http://money.cnn.com/popups/2005/consumer/sudoku/content.1.html
  public static final Grid EASY_GRID = new Grid(
      new int[]{0, 7, 2, 0, 3, 6, 4, 0, 0},
      new int[]{0, 9, 0, 7, 0, 0, 0, 3, 5},
      new int[]{0, 0, 0, 1, 8, 0, 0, 0, 2},
      new int[]{2, 0, 6, 0, 0, 0, 0, 9, 0},
      new int[]{3, 0, 5, 0, 9, 0, 6, 0, 8},
      new int[]{0, 4, 0, 0, 0, 0, 5, 0, 1},
      new int[]{7, 0, 0, 0, 2, 3, 0, 0, 0},
      new int[]{1, 5, 0, 0, 0, 4, 0, 8, 0},
      new int[]{0, 0, 8, 6, 1, 0, 9, 7, 0});

  public static final Grid MEDIUM_GRID = new Grid(
      new int[]{0, 0, 0, 9, 0, 7, 0, 0, 0},
      new int[]{9, 0, 0, 0, 0, 0, 0, 0, 8},
      new int[]{0, 3, 0, 4, 0, 5, 0, 2, 0},
      new int[]{3, 0, 7, 0, 4, 0, 2, 0, 6},
      new int[]{0, 0, 0, 5, 0, 9, 0, 0, 0},
      new int[]{8, 0, 9, 0, 2, 0, 1, 0, 3},
      new int[]{0, 7, 0, 6, 0, 4, 0, 3, 0},
      new int[]{2, 0, 0, 0, 0, 0, 0, 0, 9},
      new int[]{0, 0, 0, 1, 0, 2, 0, 0, 0});
}
