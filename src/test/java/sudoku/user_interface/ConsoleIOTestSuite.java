package sudoku.user_interface;

import org.junit.Assert;
import org.junit.Test;
import sudoku.board.SudokuBoard;

import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConsoleIOTestSuite {

    @Test
    public void testGetMovesListOptionPX() {
        //Given
        ByteArrayInputStream in = new ByteArrayInputStream("p\nx\n".getBytes());
        List<Integer[]> movesList;
        SudokuBoard board = new SudokuBoard();
        System.setIn(in);
        ConsoleIO ui = new ConsoleIO();
        //When
        movesList = ui.getMovesList(board);
        System.setIn(System.in);
        //Then
        Assert.assertEquals(null, movesList);
    }

    @Test
    public void testGetMovesListOptionS() {
        //Given
        ByteArrayInputStream in = new ByteArrayInputStream("s\n".getBytes());
        List<Integer[]> movesList;
        SudokuBoard board = new SudokuBoard();
        System.setIn(in);
        ConsoleIO ui = new ConsoleIO();
        int result;
        //When
        movesList = ui.getMovesList(board);
        System.setIn(System.in);
        result = movesList.get(0)[0];
        //Then
        Assert.assertEquals(0, result);
    }

    @Test
    public void testGetMovesListOneMove() {
        //Given
        ByteArrayInputStream in = new ByteArrayInputStream("1,1,1\nx\n".getBytes());
        List<Integer[]> movesList;
        SudokuBoard board = new SudokuBoard();
        System.setIn(in);
        ConsoleIO ui = new ConsoleIO();
        //When
        movesList = ui.getMovesList(board);
        System.setIn(System.in);
        int[] result = {
                movesList.get(0)[0],
                movesList.get(0)[1],
                movesList.get(0)[2]
        };
        //Then
        Assert.assertEquals(1, result[0]);
        Assert.assertEquals(1, result[1]);
        Assert.assertEquals(1, result[2]);
    }

    @Test
    public void testGetMovesListMultipleMoves() {
        //Given
        ByteArrayInputStream in = new ByteArrayInputStream("1,1,1,2,2,2\nx\n".getBytes());
        List<Integer[]> movesList;
        SudokuBoard board = new SudokuBoard();
        System.setIn(in);
        ConsoleIO ui = new ConsoleIO();
        //When
        movesList = ui.getMovesList(board);
        System.setIn(System.in);
        int[] result1 = {
                movesList.get(0)[0],
                movesList.get(0)[1],
                movesList.get(0)[2]
        };
        int[] result2 = {
                movesList.get(1)[0],
                movesList.get(1)[1],
                movesList.get(1)[2]
        };
        //Then
        Assert.assertEquals(1, result1[0]);
        Assert.assertEquals(1, result1[1]);
        Assert.assertEquals(1, result1[2]);
        Assert.assertEquals(2, result2[0]);
        Assert.assertEquals(2, result2[1]);
        Assert.assertEquals(2, result2[2]);
    }

    @Test
    public void testGetMovesListIncorrectInput() {
        //Given
        ByteArrayInputStream in1 = new ByteArrayInputStream("1,1,1,2,2\nx\n".getBytes());
        ByteArrayInputStream in2 = new ByteArrayInputStream("1,1,1,2,2,0\nx\n".getBytes());
        SudokuBoard board = new SudokuBoard();
        System.setIn(in1);
        ConsoleIO ui1 = new ConsoleIO();
        System.setIn(in2);
        ConsoleIO ui2 = new ConsoleIO();
        //When
        ui1.getMovesList(board);
        ui2.getMovesList(board);
        System.setIn(System.in);
        //Then
    }

    @Test
    public void testPrintingMethods() {
        //Given
        SudokuBoard board = new SudokuBoard();
        ByteArrayInputStream in1 = new ByteArrayInputStream("\n".getBytes());
        ByteArrayInputStream in2 = new ByteArrayInputStream("p\nx\n\n".getBytes());
        SudokuBoard board1 = new SudokuBoard();
        SudokuBoard board2 = new SudokuBoard();
        SudokuBoard board3 = new SudokuBoard();
        SudokuBoard board4 = new SudokuBoard();
        SudokuBoard board5 = new SudokuBoard();
        Set<SudokuBoard> set1 = new HashSet<SudokuBoard>();
        Set<SudokuBoard> set2 = new HashSet<SudokuBoard>();
        System.setIn(in1);
        ConsoleIO ui1 = new ConsoleIO();
        System.setIn(in2);
        ConsoleIO ui2 = new ConsoleIO();
        //When
        board1.setValue(1,1,1);
        board2.setValue(1,1,2);
        board3.setValue(1,1,3);
        board4.setValue(1,1,4);
        board5.setValue(1,1,5);
        set1.add(board1);
        set2.add(board1);
        set2.add(board2);
        set2.add(board3);
        set2.add(board4);
        set2.add(board5);
        ui1.pause();
        ui1.settingValue(1,1,1);
        ui1.incorrectValue(1,1,1);
        ui1.printSolution(board, 1, new HashSet<SudokuBoard>());
        ui1.printSolution(board, 1, set1);
        ui1.printSolution(board, 1, set2);
        ui2.getMovesList(board);
        ui2.pause();
        ui2.settingValue(1,1,1);
        ui2.incorrectValue(1,1,1);
        ui2.printSolution(board, 1, new HashSet<SudokuBoard>());
        ui2.printSolution(board, 1, set1);
        ui2.printSolution(board, 1, set2);
        System.setIn(System.in);
        //Then
    }

}