package sudoku.solver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import sudoku.RefGridsFactory;
import sudoku.model.Grid;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SolverTest {

  Solver solver;

  public SolverTest(Solver impl) {
    this.solver = impl;
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(
        new Object[][]{
            {new BitBackedSolver()},
            {new CleverSolver()},
            {new BrutForceRecursiveSolver()},
            {new BrutForceFlatSolver()}
        });
  }

  @Test
  public void testSolve() throws Exception {
    System.out.println(solver.getClass().getName());
    Grid result = solver.solve(RefGridsFactory.get(RefGridsFactory.Name.AL_ESCARGOT));
    assertEquals("Al Escargot result is wrond",
        RefGridsFactory.getResult(RefGridsFactory.Name.AL_ESCARGOT).toString(),
        result.toString());
  }
}