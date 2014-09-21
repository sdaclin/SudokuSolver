package sudoku.solver.business;

import sudoku.solver.exception.IllegalGridException;
import sudoku.solver.model.Grid;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tfoucault on 21/09/14.
 */
public class CellByCellRecursiveSolver implements Solver{

    private Grid setCell(Grid grid, short x, short y, short val) {
        if (!(grid.getContent()[y][x] == 0)) {
            throw new IllegalArgumentException("Can't set a cell that is already filled");
        }
        Grid newGrid = grid.clone();
        newGrid.getContent()[y][x] = val;
        return newGrid;
    }

    private Integer[] getColumn(Grid grid, short index){

        Integer[] column = new Integer[9];
        int[][] content = grid.getContent();

        for(int i=0; i<9; i++){
            column[i] = content[i][index];
        }

        return column;
    }

    private Integer[] getLine(Grid grid, short index){

        int[] line = grid.getContent()[index];
        Integer[] list  = new Integer[9];

        for(int i=0; i<line.length; ++i){
            list[i] = line[i];
        }

        return list;
    }

    private Integer[] getSector(Grid grid, short x, short y){

        Integer[] sector = new Integer[9];

        //On determine
        // la ligne
        int line = y/9;
        //et la colonne
        int column = x%9;

        //Index ligne de départ
        int lineStart = (line/3)*3;
        //Index colonne de départ
        int columnStart = (column/3)*3;

        int[][] content = grid.getContent();
        int increment = 0;

        //On stocke toutes les valeurs du
        //bloc dans un tableau de short
        for(int i=lineStart; i<lineStart+3; ++i){
            for(int j=columnStart; j<columnStart+3; ++j){
                sector[increment] = content[i][j];
                ++increment;
            }
        }

        return sector;
    }

    private boolean checkCol(Grid grid, short index) {

        //On recupere la colonne à l'index donné
        Integer[] column = getColumn(grid, index);
        List<Integer> list = Arrays.asList(column);

        for(Integer val : list){
            if(val!=0){
                if(list.indexOf(val) != list.lastIndexOf(val)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkLine(Grid grid, short index) {

        //On recupere la ligne à l'index donné
        Integer[] line = getLine(grid, index);
        List<Integer> list  = Arrays.asList(line);

        for(Integer val : list){
            if(val!=0){
                if(list.indexOf(val) != list.lastIndexOf(val)){
                    return false;
                }
            }
        }

        return true;
    }

    private boolean checkSector(Grid grid, short x, short y) {

        Integer[] sector = getSector(grid, x, y);
        List<Integer> list = Arrays.asList(sector);

        for(Integer val : list){
            if(val!=0){
                if(list.indexOf(val) != list.lastIndexOf(val)){
                    return false;
                }
            }
        }

        return true;
    }

    private Grid solve(Grid grid, int position) throws IllegalGridException {

        if (position == 81)
            return grid;

        //calcul numero ligne
        int line = position / 9;
        //calcul numero colonne
        int column = position % 9;

        if (grid.getContent()[line][column] != 0) {
            return solve(grid, position + 1);
        }

        for (int value = 1; value <= 9; ++value) {

            //On met la valeur parcourue dans la cellule d'une noubelle grille
            //Grid newGrid = setCell(grid, (short) line, (short) col, (short) value);
            Grid newGrid = setCell(grid, (short)column, (short)line, (short)value);

            //On teste la nouvelle grille
            if (checkCol(newGrid, (short) column)
                    && checkLine(newGrid, (short) column)
                    && checkSector(grid, (short) line, (short) column)) {

                return solve(newGrid, position + 1);
            }
        }

        throw new IllegalGridException();
    }

    @Override
    public Grid solve(Grid grid) {

        try{
            return solve(grid, 0);

        }catch (IllegalGridException ige){

            return grid;
        }
    }
}
