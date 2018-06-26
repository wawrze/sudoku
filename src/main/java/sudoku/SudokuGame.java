package sudoku;

import sudoku.board.SudokuBoard;
import sudoku.board.SudokuElement;
import sudoku.exceptions.IncorrectValueException;

public class SudokuGame {

    private boolean resolveSudoku;
    private SudokuBoard board;

    public SudokuGame() {
        resolveSudoku = false;
        board = new SudokuBoard();
    }

    public void setValue(int row, int col, int value) throws IncorrectValueException {
        if(!board.isPossibleValue(row, col, value) || board.getValue(row, col) != SudokuElement.EMPTY)
            throw new IncorrectValueException();
        board.setValue(row, col, value);
        for(int i = 1;i < 10;i++)
            board.removePossibleValue(row, col, i);
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
        return (int) (Math.floor((row - 1) / 3) * 3 + Math.floor((col - 1) / 3));
    }

    public SudokuBoard getBoard() {
        return board;
    }

    public boolean resolveSudoku() throws IncorrectValueException {
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
                    else if(pv.length == 1) {
                        setValue(i, j, pv[0]);
                        nothingDone = false;
                    }
                    else if(pv.length < minPossibilities)
                        minPossibilities = pv.length;
                }
            if(!resolveSudoku && nothingDone) {
                int row = 0;
                int col = 0;
                for(int i = 1;i < 10;i++) {
                    for (int j = 1; j < 10; j++)
                        if (board.getPossibleValues(i, j).length == minPossibilities) {
                            row = i;
                            col = j;
                        }
                }
                int value = board.getPossibleValues(row, col)[0];
                boolean result = guess(row, col, value);
                if(!result)
                    board.removePossibleValue(row, col, value);
            }
        }
        return true;
    }

    private boolean guess(int row, int col, int value) throws IncorrectValueException {
        SudokuBoard backTrack = new SudokuBoard(board);
        setValue(row, col ,value);
        if(resolveSudoku())
            return true;
        else {
            board = backTrack;
            return false;
        }
    }

}