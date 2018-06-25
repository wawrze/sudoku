package sudoku;

import sudoku.board.SudokuBoard;
import sudoku.exceptions.IncorrectValueException;
import sudoku.user_interface.ConsoleBoardPrinting;
import sudoku.user_interface.ConsoleIO;

import java.util.LinkedList;
import java.util.List;

public class Main {

    private static List<Integer[]> movesList;

    public static void main(String[] args) {
        SudokuGame game = new SudokuGame();
        SudokuBoard board = game.getBoard();
        movesList = new LinkedList<>();
        do {
            if(movesList.isEmpty())
                movesList = ConsoleIO.getMovesList(board);
            else {
                if(movesList.get(0)[0] == 0)
                    break;
                int row, col, value;
                row = movesList.get(0)[0];
                col = movesList.get(0)[1];
                value = movesList.get(0)[2];
                movesList.remove(0);
                try {
                    game.setValue(row, col, value);
                    ConsoleIO.settingValue(row, col, value);
                }
                catch(IncorrectValueException e) {
                    ConsoleIO.incorrectValue(row, col, value);
                }
                if(movesList.isEmpty())
                    ConsoleIO.pause();
                continue;
            }
            if(movesList == null)
                break;
        } while(true);
        if(movesList != null)
            try {
                game.resolveSudoku();
                ConsoleIO.printSolution(game.getBoard());
            }
            catch(IncorrectValueException e) {}
    }

}