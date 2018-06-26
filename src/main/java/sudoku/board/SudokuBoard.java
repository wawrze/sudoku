package sudoku.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SudokuBoard {

    private List<SudokuRow> rows;
    public static int counter;

    public SudokuBoard() {
        rows = new ArrayList<>();
        for(int i = 0;i < 9;i++)
            rows.add(new SudokuRow());
        counter = 0;
    }

    public SudokuBoard(SudokuBoard board) {
        this.rows = new ArrayList<>();
        for(int i = 0;i < 9;i++)
            this.rows.add(new SudokuRow(board.rows.get(i)));
        counter++;
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

    @Override
    public boolean equals(Object o) {
        for(int i = 1;i < 10;i++) {
            for(int j = 1;j < 10;j++) {
                if(((SudokuBoard) o).getValue(i, j) != this.getValue(i, j))
                    return false;
                for(int k = 1;k < 10;k++) {
                    if(((SudokuBoard) o).isPossibleValue(i, j, k) != this.isPossibleValue(i, j, k))
                        return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return counter;
    }

}