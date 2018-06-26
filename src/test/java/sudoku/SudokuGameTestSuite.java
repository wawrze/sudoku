package sudoku;

import org.junit.Assert;
import org.junit.Test;
import sudoku.board.SudokuBoard;
import sudoku.exceptions.IncorrectValueException;
import sudoku.user_interface.ConsoleIO;

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

    @Test
    public void testResolveSudoku1() throws IncorrectValueException {
        //From https://en.wikipedia.org/wiki/Sudoku
        //Given
        SudokuGame game = new SudokuGame();
        SudokuBoard board = game.getBoard();
        ConsoleIO ui = new ConsoleIO();
        boolean result;
        //When
        game.setValue(1, 1, 5);
        game.setValue(1, 2, 3);
        game.setValue(1, 5, 7);
        game.setValue(2, 1, 6);
        game.setValue(2, 4, 1);
        game.setValue(2, 5, 9);
        game.setValue(2, 6, 5);
        game.setValue(3, 2, 9);
        game.setValue(3, 3, 8);
        game.setValue(3, 8, 6);
        game.setValue(4, 1, 8);
        game.setValue(4, 5, 6);
        game.setValue(4, 9, 3);
        game.setValue(5, 1, 4);
        game.setValue(5, 4, 8);
        game.setValue(5, 6, 3);
        game.setValue(5, 9, 1);
        game.setValue(6, 1, 7);
        game.setValue(6, 5, 2);
        game.setValue(6, 9, 6);
        game.setValue(7, 2, 6);
        game.setValue(7, 7, 2);
        game.setValue(7, 8, 8);
        game.setValue(8, 4, 4);
        game.setValue(8, 5, 1);
        game.setValue(8, 6, 9);
        game.setValue(8, 9, 5);
        game.setValue(9, 5, 8);
        game.setValue(9, 8, 7);
        game.setValue(9, 9, 9);
        result = game.resolveSudoku();
        //Then
        ui.printSolution(board, game.getGuesses(), game.getSolutions());
        Assert.assertTrue(result);
        Assert.assertEquals(5, board.getValue(1, 1));
        Assert.assertEquals(3, board.getValue(1, 2));
        Assert.assertEquals(4, board.getValue(1, 3));
        Assert.assertEquals(6, board.getValue(1, 4));
        Assert.assertEquals(7, board.getValue(1, 5));
        Assert.assertEquals(8, board.getValue(1, 6));
        Assert.assertEquals(9, board.getValue(1, 7));
        Assert.assertEquals(1, board.getValue(1, 8));
        Assert.assertEquals(2, board.getValue(1, 9));
        Assert.assertEquals(6, board.getValue(2, 1));
        Assert.assertEquals(7, board.getValue(2, 2));
        Assert.assertEquals(2, board.getValue(2, 3));
        Assert.assertEquals(1, board.getValue(2, 4));
        Assert.assertEquals(9, board.getValue(2, 5));
        Assert.assertEquals(5, board.getValue(2, 6));
        Assert.assertEquals(3, board.getValue(2, 7));
        Assert.assertEquals(4, board.getValue(2, 8));
        Assert.assertEquals(8, board.getValue(2, 9));
        Assert.assertEquals(1, board.getValue(3, 1));
        Assert.assertEquals(9, board.getValue(3, 2));
        Assert.assertEquals(8, board.getValue(3, 3));
        Assert.assertEquals(3, board.getValue(3, 4));
        Assert.assertEquals(4, board.getValue(3, 5));
        Assert.assertEquals(2, board.getValue(3, 6));
        Assert.assertEquals(5, board.getValue(3, 7));
        Assert.assertEquals(6, board.getValue(3, 8));
        Assert.assertEquals(7, board.getValue(3, 9));
        Assert.assertEquals(8, board.getValue(4, 1));
        Assert.assertEquals(5, board.getValue(4, 2));
        Assert.assertEquals(9, board.getValue(4, 3));
        Assert.assertEquals(7, board.getValue(4, 4));
        Assert.assertEquals(6, board.getValue(4, 5));
        Assert.assertEquals(1, board.getValue(4, 6));
        Assert.assertEquals(4, board.getValue(4, 7));
        Assert.assertEquals(2, board.getValue(4, 8));
        Assert.assertEquals(3, board.getValue(4, 9));
        Assert.assertEquals(4, board.getValue(5, 1));
        Assert.assertEquals(2, board.getValue(5, 2));
        Assert.assertEquals(6, board.getValue(5, 3));
        Assert.assertEquals(8, board.getValue(5, 4));
        Assert.assertEquals(5, board.getValue(5, 5));
        Assert.assertEquals(3, board.getValue(5, 6));
        Assert.assertEquals(7, board.getValue(5, 7));
        Assert.assertEquals(9, board.getValue(5, 8));
        Assert.assertEquals(1, board.getValue(5, 9));
        Assert.assertEquals(7, board.getValue(6, 1));
        Assert.assertEquals(1, board.getValue(6, 2));
        Assert.assertEquals(3, board.getValue(6, 3));
        Assert.assertEquals(9, board.getValue(6, 4));
        Assert.assertEquals(2, board.getValue(6, 5));
        Assert.assertEquals(4, board.getValue(6, 6));
        Assert.assertEquals(8, board.getValue(6, 7));
        Assert.assertEquals(5, board.getValue(6, 8));
        Assert.assertEquals(6, board.getValue(6, 9));
        Assert.assertEquals(9, board.getValue(7, 1));
        Assert.assertEquals(6, board.getValue(7, 2));
        Assert.assertEquals(1, board.getValue(7, 3));
        Assert.assertEquals(5, board.getValue(7, 4));
        Assert.assertEquals(3, board.getValue(7, 5));
        Assert.assertEquals(7, board.getValue(7, 6));
        Assert.assertEquals(2, board.getValue(7, 7));
        Assert.assertEquals(8, board.getValue(7, 8));
        Assert.assertEquals(4, board.getValue(7, 9));
        Assert.assertEquals(2, board.getValue(8, 1));
        Assert.assertEquals(8, board.getValue(8, 2));
        Assert.assertEquals(7, board.getValue(8, 3));
        Assert.assertEquals(4, board.getValue(8, 4));
        Assert.assertEquals(1, board.getValue(8, 5));
        Assert.assertEquals(9, board.getValue(8, 6));
        Assert.assertEquals(6, board.getValue(8, 7));
        Assert.assertEquals(3, board.getValue(8, 8));
        Assert.assertEquals(5, board.getValue(8, 9));
        Assert.assertEquals(3, board.getValue(9, 1));
        Assert.assertEquals(4, board.getValue(9, 2));
        Assert.assertEquals(5, board.getValue(9, 3));
        Assert.assertEquals(2, board.getValue(9, 4));
        Assert.assertEquals(8, board.getValue(9, 5));
        Assert.assertEquals(6, board.getValue(9, 6));
        Assert.assertEquals(1, board.getValue(9, 7));
        Assert.assertEquals(7, board.getValue(9, 8));
        Assert.assertEquals(9, board.getValue(9, 9));
    }

    @Test
    public void testResolveSudoku2() throws IncorrectValueException {
        //Last one from http://elmo.sbs.arizona.edu/sandiway/sudoku/examples.html
        //Given
        SudokuGame game = new SudokuGame();
        SudokuBoard board = null;
        boolean result;
        //When
        game.setValue(1, 2, 2);
        game.setValue(2, 4, 6);
        game.setValue(2, 9, 3);
        game.setValue(3, 2, 7);
        game.setValue(3, 3, 4);
        game.setValue(3, 5, 8);
        game.setValue(4, 6, 3);
        game.setValue(4, 9, 2);
        game.setValue(5, 2, 8);
        game.setValue(5, 5, 4);
        game.setValue(5, 8, 1);
        game.setValue(6, 1, 6);
        game.setValue(6, 4, 5);
        game.setValue(7, 5, 1);
        game.setValue(7, 7, 7);
        game.setValue(7, 8, 8);
        game.setValue(8, 1, 5);
        game.setValue(8, 6, 9);
        game.setValue(9, 8, 4);
        result = game.resolveSudoku();
        for(SudokuBoard sb : game.getSolutions()) {
            board = sb;
            break;
        }
        //Then
        Assert.assertTrue(result);
        Assert.assertEquals(1, board.getValue(1, 1));
        Assert.assertEquals(2, board.getValue(1, 2));
        Assert.assertEquals(6, board.getValue(1, 3));
        Assert.assertEquals(4, board.getValue(1, 4));
        Assert.assertEquals(3, board.getValue(1, 5));
        Assert.assertEquals(7, board.getValue(1, 6));
        Assert.assertEquals(9, board.getValue(1, 7));
        Assert.assertEquals(5, board.getValue(1, 8));
        Assert.assertEquals(8, board.getValue(1, 9));
        Assert.assertEquals(8, board.getValue(2, 1));
        Assert.assertEquals(9, board.getValue(2, 2));
        Assert.assertEquals(5, board.getValue(2, 3));
        Assert.assertEquals(6, board.getValue(2, 4));
        Assert.assertEquals(2, board.getValue(2, 5));
        Assert.assertEquals(1, board.getValue(2, 6));
        Assert.assertEquals(4, board.getValue(2, 7));
        Assert.assertEquals(7, board.getValue(2, 8));
        Assert.assertEquals(3, board.getValue(2, 9));
        Assert.assertEquals(3, board.getValue(3, 1));
        Assert.assertEquals(7, board.getValue(3, 2));
        Assert.assertEquals(4, board.getValue(3, 3));
        Assert.assertEquals(9, board.getValue(3, 4));
        Assert.assertEquals(8, board.getValue(3, 5));
        Assert.assertEquals(5, board.getValue(3, 6));
        Assert.assertEquals(1, board.getValue(3, 7));
        Assert.assertEquals(2, board.getValue(3, 8));
        Assert.assertEquals(6, board.getValue(3, 9));
        Assert.assertEquals(4, board.getValue(4, 1));
        Assert.assertEquals(5, board.getValue(4, 2));
        Assert.assertEquals(7, board.getValue(4, 3));
        Assert.assertEquals(1, board.getValue(4, 4));
        Assert.assertEquals(9, board.getValue(4, 5));
        Assert.assertEquals(3, board.getValue(4, 6));
        Assert.assertEquals(8, board.getValue(4, 7));
        Assert.assertEquals(6, board.getValue(4, 8));
        Assert.assertEquals(2, board.getValue(4, 9));
        Assert.assertEquals(9, board.getValue(5, 1));
        Assert.assertEquals(8, board.getValue(5, 2));
        Assert.assertEquals(3, board.getValue(5, 3));
        Assert.assertEquals(2, board.getValue(5, 4));
        Assert.assertEquals(4, board.getValue(5, 5));
        Assert.assertEquals(6, board.getValue(5, 6));
        Assert.assertEquals(5, board.getValue(5, 7));
        Assert.assertEquals(1, board.getValue(5, 8));
        Assert.assertEquals(7, board.getValue(5, 9));
        Assert.assertEquals(6, board.getValue(6, 1));
        Assert.assertEquals(1, board.getValue(6, 2));
        Assert.assertEquals(2, board.getValue(6, 3));
        Assert.assertEquals(5, board.getValue(6, 4));
        Assert.assertEquals(7, board.getValue(6, 5));
        Assert.assertEquals(8, board.getValue(6, 6));
        Assert.assertEquals(3, board.getValue(6, 7));
        Assert.assertEquals(9, board.getValue(6, 8));
        Assert.assertEquals(4, board.getValue(6, 9));
        Assert.assertEquals(2, board.getValue(7, 1));
        Assert.assertEquals(6, board.getValue(7, 2));
        Assert.assertEquals(9, board.getValue(7, 3));
        Assert.assertEquals(3, board.getValue(7, 4));
        Assert.assertEquals(1, board.getValue(7, 5));
        Assert.assertEquals(4, board.getValue(7, 6));
        Assert.assertEquals(7, board.getValue(7, 7));
        Assert.assertEquals(8, board.getValue(7, 8));
        Assert.assertEquals(5, board.getValue(7, 9));
        Assert.assertEquals(5, board.getValue(8, 1));
        Assert.assertEquals(4, board.getValue(8, 2));
        Assert.assertEquals(8, board.getValue(8, 3));
        Assert.assertEquals(7, board.getValue(8, 4));
        Assert.assertEquals(6, board.getValue(8, 5));
        Assert.assertEquals(9, board.getValue(8, 6));
        Assert.assertEquals(2, board.getValue(8, 7));
        Assert.assertEquals(3, board.getValue(8, 8));
        Assert.assertEquals(1, board.getValue(8, 9));
        Assert.assertEquals(7, board.getValue(9, 1));
        Assert.assertEquals(3, board.getValue(9, 2));
        Assert.assertEquals(1, board.getValue(9, 3));
        Assert.assertEquals(8, board.getValue(9, 4));
        Assert.assertEquals(5, board.getValue(9, 5));
        Assert.assertEquals(2, board.getValue(9, 6));
        Assert.assertEquals(6, board.getValue(9, 7));
        Assert.assertEquals(4, board.getValue(9, 8));
        Assert.assertEquals(9, board.getValue(9, 9));
    }

    @Test
    public void testResolveEmptySudoku() throws IncorrectValueException{
        //Given
        SudokuGame game = new SudokuGame();
        SudokuBoard board = null;
        boolean result;
        //When
        result = game.resolveSudoku();
        for(SudokuBoard sb : game.getSolutions()) {
            board = sb;
            break;
        }
        //Then
        Assert.assertTrue(result);
        Assert.assertEquals(2, board.getValue(1, 1));
        Assert.assertEquals(7, board.getValue(1, 2));
        Assert.assertEquals(5, board.getValue(1, 3));
        Assert.assertEquals(1, board.getValue(1, 4));
        Assert.assertEquals(4, board.getValue(1, 5));
        Assert.assertEquals(3, board.getValue(1, 6));
        Assert.assertEquals(8, board.getValue(1, 7));
        Assert.assertEquals(6, board.getValue(1, 8));
        Assert.assertEquals(9, board.getValue(1, 9));
        Assert.assertEquals(1, board.getValue(2, 1));
        Assert.assertEquals(3, board.getValue(2, 2));
        Assert.assertEquals(6, board.getValue(2, 3));
        Assert.assertEquals(7, board.getValue(2, 4));
        Assert.assertEquals(9, board.getValue(2, 5));
        Assert.assertEquals(8, board.getValue(2, 6));
        Assert.assertEquals(2, board.getValue(2, 7));
        Assert.assertEquals(4, board.getValue(2, 8));
        Assert.assertEquals(5, board.getValue(2, 9));
        Assert.assertEquals(8, board.getValue(3, 1));
        Assert.assertEquals(4, board.getValue(3, 2));
        Assert.assertEquals(9, board.getValue(3, 3));
        Assert.assertEquals(5, board.getValue(3, 4));
        Assert.assertEquals(6, board.getValue(3, 5));
        Assert.assertEquals(2, board.getValue(3, 6));
        Assert.assertEquals(7, board.getValue(3, 7));
        Assert.assertEquals(1, board.getValue(3, 8));
        Assert.assertEquals(3, board.getValue(3, 9));
        Assert.assertEquals(7, board.getValue(4, 1));
        Assert.assertEquals(1, board.getValue(4, 2));
        Assert.assertEquals(2, board.getValue(4, 3));
        Assert.assertEquals(8, board.getValue(4, 4));
        Assert.assertEquals(3, board.getValue(4, 5));
        Assert.assertEquals(5, board.getValue(4, 6));
        Assert.assertEquals(4, board.getValue(4, 7));
        Assert.assertEquals(9, board.getValue(4, 8));
        Assert.assertEquals(6, board.getValue(4, 9));
        Assert.assertEquals(4, board.getValue(5, 1));
        Assert.assertEquals(6, board.getValue(5, 2));
        Assert.assertEquals(3, board.getValue(5, 3));
        Assert.assertEquals(2, board.getValue(5, 4));
        Assert.assertEquals(1, board.getValue(5, 5));
        Assert.assertEquals(9, board.getValue(5, 6));
        Assert.assertEquals(5, board.getValue(5, 7));
        Assert.assertEquals(7, board.getValue(5, 8));
        Assert.assertEquals(8, board.getValue(5, 9));
        Assert.assertEquals(5, board.getValue(6, 1));
        Assert.assertEquals(9, board.getValue(6, 2));
        Assert.assertEquals(8, board.getValue(6, 3));
        Assert.assertEquals(4, board.getValue(6, 4));
        Assert.assertEquals(7, board.getValue(6, 5));
        Assert.assertEquals(6, board.getValue(6, 6));
        Assert.assertEquals(1, board.getValue(6, 7));
        Assert.assertEquals(3, board.getValue(6, 8));
        Assert.assertEquals(2, board.getValue(6, 9));
        Assert.assertEquals(6, board.getValue(7, 1));
        Assert.assertEquals(5, board.getValue(7, 2));
        Assert.assertEquals(4, board.getValue(7, 3));
        Assert.assertEquals(3, board.getValue(7, 4));
        Assert.assertEquals(2, board.getValue(7, 5));
        Assert.assertEquals(1, board.getValue(7, 6));
        Assert.assertEquals(9, board.getValue(7, 7));
        Assert.assertEquals(8, board.getValue(7, 8));
        Assert.assertEquals(7, board.getValue(7, 9));
        Assert.assertEquals(3, board.getValue(8, 1));
        Assert.assertEquals(2, board.getValue(8, 2));
        Assert.assertEquals(1, board.getValue(8, 3));
        Assert.assertEquals(9, board.getValue(8, 4));
        Assert.assertEquals(8, board.getValue(8, 5));
        Assert.assertEquals(7, board.getValue(8, 6));
        Assert.assertEquals(6, board.getValue(8, 7));
        Assert.assertEquals(5, board.getValue(8, 8));
        Assert.assertEquals(4, board.getValue(8, 9));
        Assert.assertEquals(9, board.getValue(9, 1));
        Assert.assertEquals(8, board.getValue(9, 2));
        Assert.assertEquals(7, board.getValue(9, 3));
        Assert.assertEquals(6, board.getValue(9, 4));
        Assert.assertEquals(5, board.getValue(9, 5));
        Assert.assertEquals(4, board.getValue(9, 6));
        Assert.assertEquals(3, board.getValue(9, 7));
        Assert.assertEquals(2, board.getValue(9, 8));
        Assert.assertEquals(1, board.getValue(9, 9));
    }

}