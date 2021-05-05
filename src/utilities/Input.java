package utilities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    private final Scanner input = new Scanner(System.in);;

    public String getString(String message) {
        System.out.println(message);
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
            while (!coordinateCheck(coordinate)) {coordinate = getString("Invalid input, try again! ").toLowerCase();}
            char[] letters = "abcdefghij".toCharArray();
            String letter = coordinate.substring(0, 1).toLowerCase();
            int row = new String(letters).indexOf(letter);
            int col = Integer.parseInt(coordinate.substring(1)) - 1;
            return new int[]{col, row};
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

    public void promptEnterKey(){
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
