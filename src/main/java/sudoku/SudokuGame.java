package sudoku;

import sudoku.board.SudokuBoard;
import sudoku.board.SudokuElement;
import sudoku.exceptions.IncorrectValueException;
import sudoku.user_interface.ConsoleInterface;

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

    public boolean resolveSudoku() {
        while(!resolveSudoku) {
            int minPossibilities = 9;
            resolveSudoku = true;
            boolean nothingDone = true;
            for(int i = 1;i < 10;i++)
                for(int j = 1;j < 10;j++) {
                    if(board.getValue(i, j) != SudokuElement.EMPTY)
                        continue;
                    resolveSudoku = false;
                    int[] pv = board.getPossibleValues(i, j);
                    if(pv.length == 0) {
                        return false;
                    }
                    else if(pv.length == 1)
                        try {
                            setValue(i, j, pv[0]);
                            nothingDone = false;
                        }
                        catch (IncorrectValueException e) {
                            return false;
                        }
                    else if(pv.length < minPossibilities)
                        minPossibilities = pv.length;
                }
            if(!resolveSudoku && nothingDone) {
                int row = 0;
                int col = 0;
                for(int i = 1;i < 10;i++)
                    for(int j = 1;j < 10;j++)
                        if(board.getPossibleValues(i, j).length == minPossibilities) {
                            row = i;
                            col = j;
                        }
                if(row == 0 || col == 0)
                    return false;
                int value = board.getPossibleValues(row, col)[0];
                boolean result = guess(row, col, value);
                if(!result)
                    board.removePossibleValue(row, col, value);
            }
        }
        return true;
    }

    private boolean guess(int row, int col, int value) {
        SudokuBoard backTrack = new SudokuBoard(board);
        try {
            setValue(row, col ,value);
        }
        catch(IncorrectValueException e) {
            return false;
        }
        if(resolveSudoku())
            return true;
        else {
            board = backTrack;
            return false;
        }
    }

}