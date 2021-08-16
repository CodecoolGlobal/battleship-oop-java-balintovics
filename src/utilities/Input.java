package utilities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    private final Scanner input = new Scanner(System.in);
    private final Display display = new Display();

    public String getString(String message) {
        Scanner sc = new Scanner(System.in); //Workaround of double shouting problem
        display.shout(message);

        String text = "";
        while (text.length() < 1) {
            text = sc.nextLine();
        }
        return text;
    }

    public int getInt(String message) {
        display.shout(message);
        try {
            return input.nextInt();
        } catch (NumberFormatException | InputMismatchException e) {
            display.shout("Invalid input, please try again.\n");
            getInt(message);
        }
        return -1;
    }

    public int[] convertPlacement(String coordinate) {
        try {
            while (!coordinateCheck(coordinate)) {
                coordinate = getString("Invalid input, try again! ").toLowerCase();
            }
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


}
