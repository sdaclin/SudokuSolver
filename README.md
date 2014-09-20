SudokuSolver
============
This project contains 
 *  a `Grid` object baked by a two dimensional array of int `int[9][9]`.
 *  a `Solver` interface with a `solve` method that returns a solved `Grid`
 *  Some implementations of `Solver` with differents strategies like _brut force_ or _smarter_ ones. 
 
The `Grid` object is mutable by the `getContent()` method. Immutability behavior can be achieved with the `clone()` method. Feel free to implement your own method to clone the `Grid`. 
 
Since this project is for experiment purpose, there are some test cases to valid various implementations and a `SpeedTest` to check performances. 
