package sudoku.user_interface;

import sudoku.board.SudokuBoard;
import sudoku.board.SudokuElement;

public class ConsoleBoardPrinting {

    public static void printBoard(SudokuBoard board, boolean simplePrint) {
        if(simplePrint) {
            printBoardSimple(board);
            return;
        }
        System.out.println("╔═══════════╦═══════════╦═══════════╗");
        for(int i = 1;i < 10;i++) {
            printRow(board, i);
            if(i == 9)
                System.out.println("╚═══════════╩═══════════╩═══════════╝");
            else if(i == 3 || i == 6)
                System.out.println("╠═══════════╬═══════════╬═══════════╣");
            else
                System.out.println("║───┼───┼───║───┼───┼───║───┼───┼───║");
        }
    }

    private static void printBoardSimple(SudokuBoard board) {
        for(int i = 1;i < 10;i++){
            System.out.println("+---+---+---+---+---+---+---+---+---+");
            printRowSimple(board, i);
        }
        System.out.println("+---+---+---+---+---+---+---+---+---+");
    }

    private static void printRow(SudokuBoard board, int row) {
        System.out.print("║");
        for(int i = 1;i < 10;i++){
            if(board.getValue(row, i) == SudokuElement.EMPTY)
                System.out.print("   ");
            else
                System.out.print(" " + board.getValue(row, i) + " ");
            if((i % 3) == 0)
                System.out.print("║");
            else
                System.out.print("│");
        }
        System.out.println();
    }

    private static void printRowSimple(SudokuBoard board, int row) {
        System.out.print("|");
        for(int i = 1;i < 10;i++){
            if(board.getValue(row, i) == SudokuElement.EMPTY)
                System.out.print("   ");
            else
                System.out.print(" " + board.getValue(row, i) + " ");
            System.out.print("|");
        }
        System.out.println();
    }

}