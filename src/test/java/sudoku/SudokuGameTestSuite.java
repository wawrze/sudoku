package sudoku;

import org.junit.Assert;
import org.junit.Test;
import sudoku.exceptions.IncorrectValueException;

public class SudokuGameTestSuite {

    @Test
    public void testSetValue() throws IncorrectValueException {
        //Given
        SudokuGame game = new SudokuGame();
        SudokuBoard board = game.getBoard();
        boolean[] result = {true, true, true, true, true, true, true, true, true};
        //When
        game.setValue(1, 1, 1);
        if(board.isPossibleValue(1, 9, 1))
            result[0] = false;
        if(board.isPossibleValue(9, 1, 1))
            result[0] = false;
        if(board.isPossibleValue(2, 2, 1))
            result[0] = false;
        game.setValue(2, 5, 2);
        if(board.isPossibleValue(2, 9, 2))
            result[1] = false;
        if(board.isPossibleValue(9, 5, 2))
            result[1] = false;
        if(board.isPossibleValue(1, 4, 2))
            result[1] = false;
        game.setValue(3, 9, 3);
        if(board.isPossibleValue(3, 9, 3))
            result[2] = false;
        if(board.isPossibleValue(9, 9, 3))
            result[2] = false;
        if(board.isPossibleValue(1, 7, 3))
            result[2] = false;
        game.setValue(4, 9, 4);
        if(board.isPossibleValue(4, 9, 4))
            result[3] = false;
        if(board.isPossibleValue(9, 9, 4))
            result[3] = false;
        if(board.isPossibleValue(5, 7, 4))
            result[3] = false;
        game.setValue(5, 5, 5);
        if(board.isPossibleValue(5, 9, 5))
            result[4] = false;
        if(board.isPossibleValue(9, 5, 5))
            result[4] = false;
        if(board.isPossibleValue(4, 4, 5))
            result[4] = false;
        game.setValue(6, 1, 6);
        if(board.isPossibleValue(6, 9, 6))
            result[5] = false;
        if(board.isPossibleValue(9, 1, 6))
            result[5] = false;
        if(board.isPossibleValue(4, 2, 6))
            result[5] = false;
        game.setValue(7, 5, 7);
        if(board.isPossibleValue(7, 9, 7))
            result[6] = false;
        if(board.isPossibleValue(9, 5, 7))
            result[6] = false;
        if(board.isPossibleValue(8, 6, 7))
            result[6] = false;
        game.setValue(8, 1, 8);
        if(board.isPossibleValue(8, 9, 8))
            result[7] = false;
        if(board.isPossibleValue(1, 1, 8))
            result[7] = false;
        if(board.isPossibleValue(7, 3, 8))
            result[7] = false;
        game.setValue(9, 9, 9);
        if(board.isPossibleValue(9, 1, 9))
            result[8] = false;
        if(board.isPossibleValue(3, 9, 9))
            result[8] = false;
        if(board.isPossibleValue(7, 7, 9))
            result[8] = false;
        //Then
        Assert.assertTrue(result[0]);
        Assert.assertTrue(result[1]);
        Assert.assertTrue(result[2]);
        Assert.assertTrue(result[3]);
        Assert.assertTrue(result[4]);
        Assert.assertTrue(result[5]);
        Assert.assertTrue(result[6]);
        Assert.assertTrue(result[7]);
        Assert.assertTrue(result[8]);
    }

    @Test
    public void testSetIncorrectValue() {
        //Given
        SudokuGame game = new SudokuGame();
        SudokuBoard board = game.getBoard();
        boolean[] result = {false, false, false};
        //When
        try {
            game.setValue(1, 1, 1);
        }
        catch(IncorrectValueException e) {}
        try {
            game.setValue(1, 9, 1);
        }
        catch (IncorrectValueException e) {
            result[0] = true;
        }
        try {
            game.setValue(9, 1, 1);
        }
        catch (IncorrectValueException e) {
            result[1] = true;
        }
        try {
            game.setValue(3, 3, 1);
        }
        catch (IncorrectValueException e) {
            result[2] = true;
        }
        //Then
        Assert.assertTrue(result[0]);
        Assert.assertTrue(result[1]);
        Assert.assertTrue(result[2]);
    }

}