package sudoku.jmh;

import org.openjdk.jmh.annotations.*;
import sudoku.solver.*;
import sudoku.RefGridsFactory;
import sudoku.model.Grid;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sylvain on 25/09/2014.
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class SolverBenchmarks {
  private Solver brutForceRecursiveSolver = new BrutForceRecursiveSolver();
  private Solver brutForceFlatSolver = new BrutForceFlatSolver();
  private Solver bitBackedSolver = new BitBackedSolver();
  private Solver cleverSolver = new CleverSolver();

  @Param({"AL_ESCARGOT", "MEDIUM_GRID", "EASY_GRID"})
  public String gridsToTest;

  @Benchmark
  public Grid brutForceRecursiveBenchmark() throws Exception {
    return brutForceRecursiveSolver.solve(RefGridsFactory.get(RefGridsFactory.Name.valueOf(gridsToTest)));
  }

  @Benchmark
  public Grid brutForceFlatSolverBenchmark() throws Exception {
    return brutForceFlatSolver.solve(RefGridsFactory.get(RefGridsFactory.Name.valueOf(gridsToTest)));
  }

  @Benchmark
  public Grid bitBackedSolverBenchmark() throws Exception {
    return bitBackedSolver.solve(RefGridsFactory.get(RefGridsFactory.Name.valueOf(gridsToTest)));
  }

  @Benchmark
  public Grid cleverSolverBenchmark() throws Exception {
    return cleverSolver.solve(RefGridsFactory.get(RefGridsFactory.Name.valueOf(gridsToTest)));
  }
}
