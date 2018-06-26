package sudoku;

import org.junit.Test;
import sudoku.exceptions.IncorrectValueException;

import java.io.ByteArrayInputStream;

public class MainTestSuite {

    @Test
    public void testExit() throws IncorrectValueException {
        //Given
        ByteArrayInputStream in = new ByteArrayInputStream("x\n".getBytes());
        //When
        System.setIn(in);
        Main.main(null);
        System.setIn(System.in);
        //Then
    }

    @Test
    public void testResolve() throws IncorrectValueException {
        //Given
        ByteArrayInputStream in = new ByteArrayInputStream("s\n".getBytes());
        //When
        System.setIn(in);
        Main.main(null);
        System.setIn(System.in);
        //Then
    }

    @Test
    public void testSingleMove() throws IncorrectValueException {
        //Given
        ByteArrayInputStream in = new ByteArrayInputStream("1,1,1\nx\nx\n".getBytes());
        //When
        System.setIn(in);
        Main.main(null);
        System.setIn(System.in);
        //Then
    }

    @Test
    public void testMultipleMove() throws IncorrectValueException {
        //Given
        ByteArrayInputStream in = new ByteArrayInputStream("1,1,1,2,2,2,1,1,2\nx\nx\n".getBytes());
        //When
        System.setIn(in);
        Main.main(null);
        System.setIn(System.in);
        //Then
    }

}