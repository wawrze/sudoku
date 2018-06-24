package sudoku;

import sudoku.exceptions.IncorrectValueException;

public class SudokuGame {

    private boolean resolveSudoku;
    private SudokuBoard board;

    public SudokuGame() {
        resolveSudoku = false;
        board = new SudokuBoard();
    }

    public void setValue(int row, int col, int value) throws IncorrectValueException{
        if(!board.isPossibleValue(row, col, value))
            throw new IncorrectValueException();
        board.setValue(row, col, value);
        updatePossibleValues(row, col);
    }

    private void updatePossibleValues(int row, int col) {
        int value = board.getValue(row, col);
        int subgrid = whichSubgrid(row, col);
        for(int i = 1;i < 10;i++) {
            board.removePossibleValue(row, i, value);
            board.removePossibleValue(i, col, value);
            for(int j = 1;j < 10;j++)
                if(whichSubgrid(i, j) == subgrid)
                    board.removePossibleValue(i, j, value);
        }
    }

    private int whichSubgrid(int row, int col) {
        int subgrid = 0;
        if(row < 4 && col < 4)
            subgrid = 1;
        if(row < 4 && col > 3 && col < 7)
            subgrid =  2;
        if(row < 4 && col > 6)
            subgrid =  3;
        if(row > 3 && row < 7 && col < 4)
            subgrid =  4;
        if(row > 3 && row < 7 && col > 3 && col < 7)
            subgrid =  5;
        if(row > 3 && row < 7 && col > 6)
            subgrid =  6;
        if(row > 6 && col < 4)
            subgrid =  7;
        if(row > 6 && col > 3 && col < 7)
            subgrid =  8;
        if(row > 6 && col > 6)
            subgrid =  9;
        return subgrid;
    }

    public SudokuBoard getBoard() {
        return board;
    }
}