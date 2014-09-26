SudokuSolver
============
This project contains 
 *  a `Grid` object baked by a two dimensional array of int `int[9][9]`.
 *  a `Solver` interface with a `solve` method that returns a solved `Grid`
 *  Some implementations of `Solver` with differents strategies like _brut force_ or _human style_ ones. 
 
The `Grid` object is mutable by the `getContent()` method. Immutability behavior can be achieved with the `clone()` method. Feel free to implement your own method to clone the `Grid`. 
 
Since this project is for experiment purpose, there are some test cases to valid various implementations and a `SpeedTest` to check performances. 

Solver implementations by performances
--------------------------------------
 
 1.  `BitBackedSolver.java` [avg 1.5 ms]  
    This solver behaves has human regular strategy trying to first identify cells with less possibilities.
 1.  `BrutForceFlatSolver.java` [avg 5 ms]  
    This solver use brut force strategy with an history of Moves in order to rollback unsuccessful trees. This version is no more recursive and don't clone each Grid.
 1.  `BrutForceRecursiveSolver.java` [avg 83 ms]  
    This solver use brut force strategy over an immutable grid that is cloned over recursive iteration. Each non ending trees rolls back in a recursive way by throwing Exceptions. 
 1.  `CleverSolver.java` [avg 447 ms]  
    This solver behaves has human regular strategy trying to first identify cells with less possibilities.

OpenJDK JMH Microbenchmark FWK SpeedTests results
----------------------
__Results computed on HP DV6 Core i5 4Go with Win7__
```
Benchmark                                            (gridsToTest)  Mode  Samples    Score  Score error  Units
s.j.SolverBenchmarks.bitBackedSolverBenchmark          AL_ESCARGOT  avgt        5    1,725        0,108  ms/op
s.j.SolverBenchmarks.bitBackedSolverBenchmark          MEDIUM_GRID  avgt        5    1,042        0,035  ms/op
s.j.SolverBenchmarks.bitBackedSolverBenchmark            EASY_GRID  avgt        5    0,034        0,001  ms/op
s.j.SolverBenchmarks.brutForceFlatSolverBenchmark      AL_ESCARGOT  avgt        5    4,905        1,258  ms/op
s.j.SolverBenchmarks.brutForceFlatSolverBenchmark      MEDIUM_GRID  avgt        5    3,224        0,571  ms/op
s.j.SolverBenchmarks.brutForceFlatSolverBenchmark        EASY_GRID  avgt        5    0,035        0,008  ms/op
s.j.SolverBenchmarks.brutForceRecursiveBenchmark       AL_ESCARGOT  avgt        5   83,840       46,523  ms/op
s.j.SolverBenchmarks.brutForceRecursiveBenchmark       MEDIUM_GRID  avgt        5   55,528        4,822  ms/op
s.j.SolverBenchmarks.brutForceRecursiveBenchmark         EASY_GRID  avgt        5    0,482        0,167  ms/op
s.j.SolverBenchmarks.cleverSolverBenchmark             AL_ESCARGOT  avgt        5  447,568       87,966  ms/op
s.j.SolverBenchmarks.cleverSolverBenchmark             MEDIUM_GRID  avgt        5   33,032        8,530  ms/op
s.j.SolverBenchmarks.cleverSolverBenchmark               EASY_GRID  avgt        5    0,437        0,121  ms/op
```
