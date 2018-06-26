package sudoku.board;

import org.junit.Assert;
import org.junit.Test;

public class BoardTestSuite {

    @Test
    public void testValue() {
        //Given
        SudokuBoard board = new SudokuBoard();
        int value = 1;
        int result1, result2;
        //When
        result1 = board.getValue(1, 1);
        board.setValue(2, 2, 1);
        result2 = board.getValue(2, 2);
        //Then
        Assert.assertEquals(SudokuElement.EMPTY, result1);
        Assert.assertEquals(value, result2);
    }

    @Test
    public void testPossibleValues() {
        //Given
        SudokuBoard board = new SudokuBoard();
        int[] result1;
        int[] result2;
        boolean result3;
        //When
        result1 = board.getPossibleValues(1, 1);
        board.removePossibleValue(2, 2, 5);
        result2 = board.getPossibleValues(2, 2);
        result3 = board.isPossibleValue(2, 2, 5);
        //Then
        Assert.assertEquals(9, result1.length);
        Assert.assertEquals(8, result2.length);
        Assert.assertFalse(result3);
    }

    @Test
    public void testBoardCopy() {
        //Given
        SudokuBoard originalBoard = new SudokuBoard();
        SudokuBoard copiedBoard = new SudokuBoard(originalBoard);
        boolean result1 = true;
        boolean result2 = true;
        boolean result3 = true;
        //When
        for(int i = 1;i < 10;i++)
            for(int j = 1;j < 10;j++) {
                if (originalBoard.getValue(i, j) != copiedBoard.getValue(i, j))
                    result1 = false;
                if (originalBoard.getPossibleValues(i, j).length != copiedBoard.getPossibleValues(i, j).length)
                    result2 = false;
            }
        originalBoard.setValue(1, 1, 5);
        originalBoard.removePossibleValue(2, 2, 5);
        if(originalBoard.getValue(1, 1) == copiedBoard.getValue(1, 1))
            result3 = false;
        if(originalBoard.getPossibleValues(2, 2).length ==
                copiedBoard.getPossibleValues(2, 2).length)
            result3 = false;
        //Then
        Assert.assertTrue(result1);
        Assert.assertTrue(result2);
        Assert.assertTrue(result3);
    }

    @Test
    public void testEquals() {
        //Given
        SudokuBoard originalBoard = new SudokuBoard();
        SudokuBoard copiedBoard = new SudokuBoard(originalBoard);
        boolean result1;
        boolean result2;
        //When
        result1 = originalBoard.equals(copiedBoard);
        originalBoard.setValue(1,1,1);
        result2 = originalBoard.equals(copiedBoard);
        //Then
        Assert.assertTrue(result1);
        Assert.assertFalse(result2);
    }

}