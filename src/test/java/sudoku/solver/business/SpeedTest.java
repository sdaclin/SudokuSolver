package sudoku.solver.business;

import com.google.common.base.Stopwatch;
import org.junit.Test;
import sudoku.solver.model.Grid;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SpeedTest {

  public Solver solver = new SolverBrutForce();

  @Test
  public void speedTest() {
    // Iteration number to make an average measure
    int iter = 50;

    // Warm up : perform 100 resolutions to warm-up
    for (int i=0; i<25; i++) {
      solver.solve(RefGrids.AL_ESCARGOT);
    }

    // Performing speed test
    Stopwatch stopwatch = Stopwatch.createStarted();
    for (int i=0; i<iter; i++) {
      solver.solve(RefGrids.AL_ESCARGOT);
    }
    stopwatch.stop();
    System.out.println("Average time spend [" + stopwatch.elapsed(TimeUnit.MILLISECONDS)/iter + "] ms. Average calculated for ["+iter+"].");
  }
}