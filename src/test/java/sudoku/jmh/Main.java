package sudoku.jmh;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Created by Sylvain on 25/09/2014.
 */
public class Main {
  public static void main(String[] args) throws RunnerException {
    Options opt =  new OptionsBuilder()
        .include(".*"+SolverBenchmarks.class.getSimpleName()+".*")
        .warmupIterations(5)
        .measurementIterations(5)
        .forks(1)
        .build();

    new Runner(opt).run();
  }
}