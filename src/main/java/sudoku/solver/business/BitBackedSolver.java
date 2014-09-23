package sudoku.solver.business;

import sudoku.solver.exception.IllegalGridException;
import sudoku.solver.model.Grid;

public class BitBackedSolver implements Solver {
	
	@SuppressWarnings("serial")
	private static final class GridSolveException extends Exception {}

	static int   SIZE = 9;
	static int   SIZ2 = SIZE * 2;
	static int   TYPE = 3;
	static int[] NUMBER_MASK = new int[10];
	static int[][] SUB = new int[SIZE][SIZE];
	static {
		NUMBER_MASK[0] = 0;
		for (int n = 1; n <= SIZE; n++) {
			NUMBER_MASK[n] = 1 << (n - 1);
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int m = 0; m < 3; m++) {
					for (int n = 0; n < 3; n++) {
						SUB[3 * i + m][3 * j + n] = i + 3 * j;
					}
				}
			}
		}
	}

	private static int getRowIndex(int row) {return row;}
	private static int getColIndex(int col) {return SIZE + col;}
	private static int getSubIndex(int row, int col) {return SIZ2 + SUB[row][col];}
	
	private static void xorGridMask(int[] mask, int row, int col, int number) {
		if (number == 0) {
			return;
		}
		mask[getRowIndex(row)] ^= NUMBER_MASK[number];
		mask[getColIndex(col)] ^= NUMBER_MASK[number];
		mask[getSubIndex(row, col)] ^= NUMBER_MASK[number];
	}

	@Override
	public Grid solve(Grid grid) throws IllegalGridException {
		int[] mask = new int[TYPE * SIZE];
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				xorGridMask(mask, row, col, grid.getContent()[row][col]);
			}
		}
		
		try {
			solveRecursive(grid, mask, 0, 0);
		} catch (GridSolveException e) {}
		
		return grid;
	}
	
	private final void solveRecursive(Grid grid, int[] mask, int row, int col) throws GridSolveException {
		while (row < SIZE && grid.getContent()[row][col] != 0) {
			col++;
			if (col >= SIZE) {
				col = 0;
				row++;
			}
		}
		// done!
		if (row == SIZE) {
			throw new GridSolveException();
		}
		// (i,j) to figure out!
		int m = mask[getRowIndex(row)] | mask[getColIndex(col)] | mask[getSubIndex(row, col)];
		if (m == 0b111111111) {
			// impossible!
			return;
		} else {
			m = ~m;
			for (int n = 1; n <= SIZE; n++) {
				if ((m & NUMBER_MASK[n]) != 0) {
					// try it...
					grid.getContent()[row][col] = n;
					xorGridMask(mask, row, col, n);
					solveRecursive(grid, mask, row, col);
					// if we fail... try next number
					xorGridMask(mask, row, col, n);
					grid.getContent()[row][col] = 0;
				}
			}
		}
		
	}

}
