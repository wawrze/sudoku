package sudoku.user_interface;

import sudoku.board.SudokuBoard;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ConsoleIO {

    private boolean simplePrint;
    private List<Integer[]> movesList;
    private Scanner sc;

    public ConsoleIO() {
        simplePrint = false;
        sc = new Scanner(System.in);
    }

    public List<Integer[]> getMovesList(SudokuBoard board) {
        movesList = new LinkedList<>();
        String input;
        do {
            cls();
            printWaitingForInput(board);
            input = sc.nextLine();
        } while(!option(input));
        return movesList;
    }

    private boolean option(String input) {
        if(input.equals("x")) {
            movesList = null;
            return true;
        }
        if(input.equals("p")) {
            simplePrint = !simplePrint;
            return false;
        }
        if(input.equals("s")) {
            Integer[] i = {0};
            movesList.add(i);
            return true;
        }
        String[] sArray = input.split(",");
        if(sArray.length == 0 || (sArray.length % 3) != 0)
            return false;
        for(int i = 0;i < sArray.length;i++) {
            if(!(sArray[i].length() == 1 && "123456789".contains(sArray[i])))
                return false;
            else {
                if(i > 0 && ((i + 1) % 3) == 0) {
                    Integer[] array = {
                            sArray[i - 2].charAt(0) - 48,
                            sArray[i - 1].charAt(0) - 48,
                            sArray[i].charAt(0) - 48
                    };
                    movesList.add(array);
                }
            }
        }
        if(simplePrint)
            System.out.println("+------------------------------------------------------+");
        else
            System.out.println("╔══════════════════════════════════════════════════════╗");
        return true;
    }

    private void printWaitingForInput(SudokuBoard board) {
        ConsoleBoardPrinting.printBoard(board, simplePrint);
        String s;
        if(simplePrint) {
            System.out.println("+------------------------------------------------------+");
            s = "|";
        }
        else {
            System.out.println("╔══════════════════════════════════════════════════════╗");
            s = "║";
        }
        System.out.println(s + " Enter \"row, column, value\" to fill next field(s) or: " + s);
        System.out.println(s + "                                                      " + s);
        System.out.println(s + " (s) Resolve Sudoku                                   " + s);
        System.out.println(s + " (p) Switch interface style                           " + s);
        System.out.println(s + " (x) Exit                                             " + s);
        if(simplePrint)
            System.out.println("+------------------------------------------------------+");
        else
            System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
    }

    private static void cls() {
        for (int i = 0; i < 100; i++)
            System.out.println();
    }

    public void pause() {
        if(simplePrint) {
            System.out.println("+------------------------------------------------------+");
            System.out.println("+------------------------------------------------------+");
            System.out.println("|  Press \"Enter\" to continue.                          |");
            System.out.println("+------------------------------------------------------+");
        }
        else {
            System.out.println("╚══════════════════════════════════════════════════════╝");
            System.out.println("╔══════════════════════════════════════════════════════╗");
            System.out.println("║  Press \"Enter\" to continue.                          ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");
        }
        sc.nextLine();
    }

    public void settingValue(int row, int col, int value) {
        if(simplePrint) {
            System.out.println("| Setting row " + row + ", col " + col + " to " + value
                    + "...                         |");
        }
        else {
            System.out.println("║ Setting row " + row + ", col " + col + " to " + value
                    + "...                         ║");
        }
    }

    public void incorrectValue(int row, int col, int value) {
        if(simplePrint) {
            System.out.println("| Row " + row + ", col " + col + " cannot be set to " + value
                    + "! Skipping...         |");
        }
        else {
            System.out.println("║ Row " + row + ", col " + col + " cannot be set to " + value
                    + "! Skipping...         ║");
        }
    }

    public void printSolution(SudokuBoard board, int guesses, Set<SudokuBoard> solutions) {
        cls();
        String s = "" + (guesses < 100 ? " " : "") + (guesses < 10 ? " " : "");
        if(simplePrint)
            System.out.println("+------------------------------------------------------+");
        else
            System.out.println("╔══════════════════════════════════════════════════════╗");
        if(solutions.size() == 1) {
            if(simplePrint) {
                System.out.println("|             This board has one solution              |");
                System.out.println("|               (" + guesses + " guesses to resolve):              " + s + "|");
            }
            else {
                System.out.println("║             This board has one solution              ║");
                System.out.println("║               (" + guesses + " guesses to resolve):              " + s + "║");

            }
            if(simplePrint)
                System.out.println("+------------------------------------------------------+");
            else
                System.out.println("╚══════════════════════════════════════════════════════╝");
            ConsoleBoardPrinting.printBoard(board, simplePrint);
        }
        else if(solutions.size() < 5) {
            if(simplePrint) {
                System.out.println("|              This board has " + solutions.size() + " solutions             |");
                System.out.println("|           (" + guesses + " guesses to get all of them):            " + s + "|");
            }
            else {
                System.out.println("║              This board has " + solutions.size() + " solutions             ║");
                System.out.println("║           (" + guesses + " guesses to get all of them):            " + s + "║");
            }
            if(simplePrint)
                System.out.println("+------------------------------------------------------+");
            else
                System.out.println("╚══════════════════════════════════════════════════════╝");
            printMultipleSolutions(solutions);
        }
        else {
            if(simplePrint) {
                System.out.println("|          This board has 5 or more solutions          |");
                System.out.println("|             (" + guesses + " guesses to get first 5):            " + s + "|");
            }
            else {
                System.out.println("║          This board has 5 or more solutions          ║");
                System.out.println("║             (" + guesses + " guesses to get first 5):            " + s + "║");
            }
            if(simplePrint)
                System.out.println("+------------------------------------------------------+");
            else
                System.out.println("╚══════════════════════════════════════════════════════╝");
            printMultipleSolutions(solutions);
        }
    }

    private void printMultipleSolutions(Set<SudokuBoard> solutions) {
        int counter = 1;
        for(SudokuBoard sb : solutions) {
            if(simplePrint)
                System.out.print("+-----------------------------------+\n|");
            else
                System.out.print("╔═══════════════════════════════════╗\n║");
            System.out.print("            Solution " + counter + ":            ");
            if(simplePrint)
                System.out.println("|\n+-----------------------------------+");
            else
                System.out.println("║\n╚═══════════════════════════════════╝");
            counter++;
            ConsoleBoardPrinting.printBoard(sb, simplePrint);
        }
    }

}