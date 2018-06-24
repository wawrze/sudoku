package sudoku.board;

import java.util.ArrayList;
import java.util.List;

public class SudokuElement {

    public static int EMPTY = -1;

    private int value;
    private List<Boolean> possibleValues;

    public SudokuElement() {
        value = EMPTY;
        possibleValues = new ArrayList<>();
        for(int i = 0;i < 9;i++)
            possibleValues.add(true);
    }

    public SudokuElement(SudokuElement element) {
        this.value = element.getValue();
        this.possibleValues = new ArrayList<>();
        for(int i = 0;i < 9;i++)
            this.possibleValues.add(element.possibleValues.get(i));
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int[] getPossibleValues() {
        int size = 0;
        for(boolean b : possibleValues)
            if(b)
                size++;
        int[] pv = new int[size];
        int counter = 0;
        for(int i = 0;i < 9;i++)
            if(possibleValues.get(i)) {
                pv[counter] = i + 1;
                counter++;
            }
        return pv;
    }

    public boolean isPossibleValue(int value) {
        return possibleValues.get(value - 1);
    }

    public void removePossibleValue(int value) {
        possibleValues.set(value - 1, false);
    }

}