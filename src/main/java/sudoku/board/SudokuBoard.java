package sudoku.board;

import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {

    private List<SudokuRow> rows;

    public SudokuBoard() {
        rows = new ArrayList<>();
        for(int i = 0;i < 9;i++)
            rows.add(new SudokuRow());
    }

    public SudokuBoard(SudokuBoard board) {
        this.rows = new ArrayList<>();
        for(int i = 0;i < 9;i++)
            this.rows.add(new SudokuRow(board.rows.get(i)));
    }

    public int getValue(int row, int element) {
        return rows.get(row - 1).getValue(element);
    }

    public void setValue(int row, int element, int value) {
        rows.get(row - 1).setValue(element, value);
    }

    public int[] getPossibleValues(int row, int element) {
        return rows.get(row - 1).getPossibleValues(element);
    }

    public void removePossibleValue(int row, int element, int value) {
        rows.get(row - 1).removePossibleValue(element, value);
    }

    public boolean isPossibleValue(int row, int element, int value) {
        return rows.get(row - 1).isPossibleValue(element, value);
    }

}