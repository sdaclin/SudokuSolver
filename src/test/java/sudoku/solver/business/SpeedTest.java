package sudoku.solver.business;

import com.google.common.base.Stopwatch;
import org.junit.Test;
import sudoku.solver.exception.IllegalGridException;
import sudoku.solver.model.Grid;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SpeedTest {

  public Solver solver = new SolverBrutForce();

  @Test
  public void speedTest() throws IllegalGridException {
    // Iteration number to make an average measure
    int iter = 50;

    // Warm up : perform 10 resolutions of Al_ESCARGOT
    for (int i = 0; i < 10; i++) {
      solver.solve(RefGrids.AL_ESCARGOT);
    }

    // Performing speed test
    Grid result = null;
    Stopwatch stopwatch = Stopwatch.createStarted();
    for (int i = 0; i < iter; i++) {
      result = solver.solve(RefGrids.EASY_GRID);
    }
    stopwatch.stop();
    if (result != null) {
      System.out.println(result);
      System.out.println("Average time spend [" + stopwatch.elapsed(TimeUnit.MILLISECONDS) / iter + "] ms. Average calculated for [" + iter + "].");
    }
  }
}