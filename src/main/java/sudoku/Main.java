package sudoku;

import sudoku.board.SudokuBoard;
import sudoku.exceptions.IncorrectValueException;
import sudoku.user_interface.ConsoleIO;

import java.util.LinkedList;
import java.util.List;

public class Main {

    private static List<Integer[]> movesList;

    public static void main(String[] args) throws IncorrectValueException {
        SudokuGame game = new SudokuGame();
        SudokuBoard board = game.getBoard();
        movesList = new LinkedList<>();
        ConsoleIO ui = new ConsoleIO();
        do {
            if(movesList.isEmpty())
                movesList = ui.getMovesList(board);
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
                    ui.settingValue(row, col, value);
                }
                catch(IncorrectValueException e) {
                    ui.incorrectValue(row, col, value);
                }
                if(movesList.isEmpty())
                    ui.pause();
                continue;
            }
            if(movesList == null)
                break;
        } while(true);
        if(movesList != null) {
            game.resolveSudoku();
            ui.printSolution(game.getBoard(), game.getGuesses());
        }
    }

}