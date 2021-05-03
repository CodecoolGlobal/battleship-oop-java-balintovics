package utilities;

import java.util.InputMismatchException;
import java.util.Scanner;
import board.Square;

public class Input {
    public Scanner input = new Scanner(System.in);

    public String getString() {
        return input.nextLine();
    }

    public int getInt() {
        try {
            return input.nextInt();
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Invalid input, please try again.\n");
            getInt();
        } return -1;
    }

    public int[] convertPlacement(String coordinate) {
        try {
            char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            String letter = coordinate.substring(0, 1);
            int row = new String(alphabet).indexOf(letter);
            int col = Integer.parseInt(coordinate.substring(1)) - 1;
            return new int[]{row, col};
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public boolean coordinateCheck(String coordinate) {
        if (coordinate.length() == 2) {
            return coordinate.matches("\\D\\d");
        } else if (coordinate.length() == 3) {
            return coordinate.matches("\\D\\d\\d");
        }
        return false;
    }

    public boolean isItOnBoard(int[] placement, Square[][] board) {
        try {
            if (board[placement[0]][placement[1]]) {
                return true;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Outside board, please choose your coordinate mindfully.\n");
            convertPlacement(getString());
        }
        return false;
    }
}
