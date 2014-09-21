SudokuSolver
============
This project contains 
 *  a `Grid` object baked by a two dimensional array of int `int[9][9]`.
 *  a `Solver` interface with a `solve` method that returns a solved `Grid`
 *  Some implementations of `Solver` with differents strategies like _brut force_ or _human style_ ones. 
 
The `Grid` object is mutable by the `getContent()` method. Immutability behavior can be achieved with the `clone()` method. Feel free to implement your own method to clone the `Grid`. 
 
Since this project is for experiment purpose, there are some test cases to valid various implementations and a `SpeedTest` to check performances. 

SpeedTests results
------------------
__Results computed on an Asus c200 4Go / 32 Go chromebook with Ubuntu Trusty__
 *  `BrutForceRecursiveSolver.java` [avg 200ms] 
    This solver use brut force strategy over an immutable grid that is cloned over recursive iteration. Each non ending trees rolls back in a recursive way by throwing Exceptions. 
 *  `CleverSolver.java` [avg 300ms] 
    This solver behaves has human regular strategy trying to first identify cells with less possibilities.
 *  `BrutForceFlatSolver.java` [avg *0ms*]
 *  This solver use brut force strategy with an history of Moves in order to rollback unsuccessful trees. This version is no more recursive and don't clone each Grid.

Next step : use `Calpier` to perform speed tests [Caliper](https://code.google.com/p/caliper/).
