package sudoku.solver.business;

import com.google.common.base.Stopwatch;
import org.junit.Test;
import sudoku.solver.exception.IllegalGridException;
import sudoku.solver.model.Grid;

import java.util.concurrent.TimeUnit;

public class SpeedTest {

  @Test
  public void speedTestBrutForceSolver() throws IllegalGridException {
    // Iteration number to make an average measure
    int iter = 50;

    testOverRefGrids(new BrutForceRecursiveSolver(), iter);
  }

  @Test
  public void speedTestBrutForceFlatSolver() throws IllegalGridException {
    // Iteration number to make an average measure
    int iter = 50;

    testOverRefGrids(new BrutForceFlatSolver(), iter);
  }

  @Test
  public void speedTestCleverSolver() throws IllegalGridException {
    // Iteration number to make an average measure
    int iter = 50;

    testOverRefGrids(new CleverSolver(), iter);
  }

  public void speedTestCellByCellsolver() throws IllegalGridException {
    // Iteration number to make an average measure
    int iter = 50;

    testOverRefGrids(new CellByCellRecursiveSolver(), iter);
  }

  private void testOverRefGrids(Solver solver, int iter) throws IllegalGridException {
    System.out.println("["+solver.getClass().getName()+"]");

    // Warm up : perform 5 resolutions of Al_ESCARGOT
    for (int i = 0; i < 5; i++) {
      solver.solve(RefGrids.AL_ESCARGOT.clone());
    }

    // Performing speed test
    long resultAlEscargot, resultMediumGrid, resultEasyGrid;
    Grid result = null;
    Stopwatch stopwatch = Stopwatch.createUnstarted();

    // Testing Al Escargot
    stopwatch.start();
    for (int i = 0; i < iter; i++) {
      result = solver.solve(RefGrids.AL_ESCARGOT.clone());
    }
    stopwatch.stop();
    System.out.println(result);
    resultAlEscargot = stopwatch.elapsed(TimeUnit.MICROSECONDS) / iter;

    // Testing Medium grid
    stopwatch.reset().start();
    for (int i = 0; i < iter; i++) {
      result = solver.solve(RefGrids.MEDIUM_GRID.clone());
    }
    stopwatch.stop();
    System.out.println(result);
    resultMediumGrid = stopwatch.elapsed(TimeUnit.MICROSECONDS) / iter;

    // Testing Easy grid
    stopwatch.reset().start();
    for (int i = 0; i < iter; i++) {
      result = solver.solve(RefGrids.EASY_GRID.clone());
    }
    stopwatch.stop();
    System.out.println(result);
    resultEasyGrid = stopwatch.elapsed(TimeUnit.MICROSECONDS) / iter;

    System.out.println("Average time spend [" + (resultAlEscargot+resultMediumGrid+resultEasyGrid)/3 + "] µs. Average calculated for [" + iter + "].");
    System.out.println("Per force results :\n" +
        "Al Escargot\t[" + resultAlEscargot + "] µs.\n" +
        "Medium grid\t[" + resultMediumGrid + "] µs.\n" +
        "Easy grid\t[" + resultEasyGrid + "] µs.\n");
  }
}