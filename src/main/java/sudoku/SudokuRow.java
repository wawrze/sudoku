package sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuRow {

    private List<SudokuElement> elements;

    public SudokuRow() {
        elements = new ArrayList<>();
        for(int i = 0;i < 9; i++)
            elements.add(new SudokuElement());
    }

    public SudokuRow(SudokuRow row) {
        this.elements = new ArrayList<>();
        for(int i = 0;i < 9;i++)
            this.elements.add(new SudokuElement(row.elements.get(i)));
    }

    public int getValue(int element) {
        return elements.get(element - 1).getValue();
    }

    public void setValue(int element, int value) {
        elements.get(element - 1).setValue(value);
    }

    public int[] getPossibleValues(int element) {
        return elements.get(element - 1).getPossibleValues();
    }

    public void removePossibleValue(int element, int value) {
        elements.get(element - 1).removePossibleValue(value);
    }

    public boolean isPossibleValue(int element, int value) {
        return elements.get(element - 1).isPossibleValue(value);
    }

}