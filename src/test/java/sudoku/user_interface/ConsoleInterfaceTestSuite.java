package sudoku.user_interface;

import org.junit.Test;
import sudoku.board.SudokuBoard;
import sudoku.SudokuGame;
import sudoku.exceptions.IncorrectValueException;

public class ConsoleInterfaceTestSuite {

    @Test
    public void testPrintBoard() throws IncorrectValueException {
        //Given
        SudokuGame game = new SudokuGame();
        SudokuBoard board = game.getBoard();
        //When
        game.setValue(1, 1, 1);
        game.setValue(2, 5, 2);
        game.setValue(3, 9, 3);
        game.setValue(4, 9, 4);
        game.setValue(5, 5, 5);
        game.setValue(6, 1, 6);
        game.setValue(7, 5, 7);
        game.setValue(8, 1, 8);
        game.setValue(9, 9, 9);
        ConsoleInterface.printBoard(board);
        ConsoleInterface.switchSimplePrint();
        ConsoleInterface.printBoard(board);
        //Then
    }

}