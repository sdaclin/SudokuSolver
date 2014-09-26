package sudoku;

import com.google.common.base.Stopwatch;
import org.junit.Test;
import sudoku.RefGridsFactory;
import sudoku.exception.IllegalGridException;
import sudoku.model.Grid;
import sudoku.solver.*;

import java.util.concurrent.TimeUnit;

/**
 * Hand made speed tests.
 *
 * For advanced micro benchmarking see @sudoku.jmh.Main
 */
public class SpeedTest {

  @Test
  public void speedTestBrutForceSolver() throws IllegalGridException {
    // Iteration number to make an average measure
    int iter = 10;

    testOverRefGrids(new BrutForceRecursiveSolver(), iter);
  }

  @Test
  public void speedTestBrutForceFlatSolver() throws IllegalGridException {
    // Iteration number to make an average measure
    int iter = 10;

    testOverRefGrids(new BrutForceFlatSolver(), iter);
  }

  @Test
  public void speedTestCleverSolver() throws IllegalGridException {
    // Iteration number to make an average measure
    int iter = 10;

    testOverRefGrids(new CleverSolver(), iter);
  }

  @Test
  public void speedTestBitBackedSolver() throws IllegalGridException {
    // Iteration number to make an average measure
    int iter = 10;

    testOverRefGrids(new BitBackedSolver(), iter);
  }

  private void testOverRefGrids(Solver solver, int iter) throws IllegalGridException {
    System.out.println("["+solver.getClass().getName()+"]");

    // Warm up : perform 5 resolutions of Al_ESCARGOT
    for (int i = 0; i < 5; i++) {
      solver.solve(RefGridsFactory.get(RefGridsFactory.Name.AL_ESCARGOT));
    }

    // Performing speed test
    double resultAlEscargot, resultMediumGrid, resultEasyGrid;
    long result = 0L;
    Stopwatch stopwatch = Stopwatch.createUnstarted();

    // Testing Al Escargot
    stopwatch.start();
    for (int i = 0; i < iter; i++) {
      result |= solver.solve(RefGridsFactory.get(RefGridsFactory.Name.AL_ESCARGOT)).hashCode();
    }
    stopwatch.stop();
    resultAlEscargot = stopwatch.elapsed(TimeUnit.MICROSECONDS) / iter;

    // Testing Medium grid
    stopwatch.reset().start();
    for (int i = 0; i < iter; i++) {
      result |= solver.solve(RefGridsFactory.get(RefGridsFactory.Name.MEDIUM_GRID)).hashCode();
    }
    stopwatch.stop();
    resultMediumGrid = stopwatch.elapsed(TimeUnit.MICROSECONDS) / iter;

    // Testing Easy grid
    stopwatch.reset().start();
    for (int i = 0; i < iter; i++) {
      result |= solver.solve(RefGridsFactory.get(RefGridsFactory.Name.EASY_GRID)).hashCode();
    }
    stopwatch.stop();
    resultEasyGrid = stopwatch.elapsed(TimeUnit.MICROSECONDS) / iter;

    System.out.println("Average time spend [" + (resultAlEscargot+resultMediumGrid+resultEasyGrid)/3 + "] µs. Average calculated for [" + iter + "].");
    System.out.println("Per force results :\n" +
        "Al Escargot\t[" + resultAlEscargot + "] µs.\n" +
        "Medium grid\t[" + resultMediumGrid + "] µs.\n" +
        "Easy grid\t[" + resultEasyGrid + "] µs.\n");
  }
}