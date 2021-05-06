package utilities;

import board.Board;

public class Display {

    public static void clear() {
        System.out.print("\033\143");
        System.out.flush();
    }

    public static void shout(String message) {
        System.out.println(message);
    }

    public static void printSingleBoard(Board board) {
//        clear();
//        System.out.println();
        String border = "🟩";
//        board.setBoardVisibility(false);
        StringBuilder sb = new StringBuilder();
        String repeat = (border + " ").repeat(board.BOARD_SIZE + 3);
        sb.append(repeat);
        sb.append("\n");
        for (int k = 0; k < board.BOARD_SIZE; k++) {
            if (k == 0) sb.append(border).append("   ");
            String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            sb.append(" ").append(Color.YELLOW).append(alphabet.charAt(k)).append(" ").append(Color.RESET);
        }
        sb.append(" ").append(border).append("\n");
        for (int i = 0; i < board.BOARD_SIZE; i++) {
            for (int j = 0; j < board.BOARD_SIZE; j++) {

                if (i == board.BOARD_SIZE - 1 && j == 0) sb.append(Color.YELLOW).append(border).append(String.format("%3d", i+1)).append(" ").append(Color.RESET);
                else if (j == 0) sb.append(Color.YELLOW).append(border).append(String.format("%3d", i+1)).append(" ").append(Color.RESET);
                sb.append(board.ocean[i][j]).append(" ");
            }
            sb.append(border).append("\n");
        }
        sb.append(repeat);
        System.out.println(sb);
    }

    public static void printTwoBoards(Board board1, Board board2, boolean isTest) {
        if (!isTest) clear();
        System.out.println();
        String border1 = "🟥";
        String border2 = "🟦";
        StringBuilder sd = new StringBuilder(); //PRINTING TOP & ALPHABET
        String repeat1 = (border1 + " ").repeat(board1.BOARD_SIZE + 3);
        String repeat2 = (border2 + " ").repeat(board2.BOARD_SIZE + 3);
        sd.append(repeat1).append(" ❕  ").append(repeat2).append("\n");
        for (int k=0;k<board1.BOARD_SIZE * 2 + 2;k++) {
            if (k == 0) sd.append(border1).append(" ").append("  ");
            String alphabet = "ABCDEFGHIJ";
            if (k<10) sd.append(" ").append(Color.YELLOW).append(alphabet.charAt(k)).append(" ").append(Color.RESET);
            if (k==10) sd.append(" ").append(border1).append("  ❕  ");
            if (k == 11) sd.append(border2).append("    ");
            if (k>11) sd.append(" ").append(Color.YELLOW).append(alphabet.charAt(k-12)).append(" ").append(Color.RESET);
            if (k == board1.BOARD_SIZE * 2 + 1) sd.append(border2).append("\n");
        }  //PRINTING ACTUAL BOARDS
        for (int i = 0; i < board1.BOARD_SIZE; i++) {
            for (int j = 0; j < board1.BOARD_SIZE*2+2; j++) {
                if (i == board1.BOARD_SIZE - 1 && j == 0) sd.append(Color.YELLOW).append(border1).append(" ").append(i+1).append(" ").append(Color.RESET);
                else if (j == 0) sd.append(Color.YELLOW).append(border1).append(" ").append(i+1).append("  ").append(Color.RESET);
                if (j<10) sd.append(board1.ocean[i][j]).append(" ");
                if (j == 10) sd.append(border1).append("  ❕  ").append(border2);
                if (j == 11) sd.append(Color.YELLOW).append(String.format("%3d", i+1)).append(" ").append(Color.RESET);
                if (j>11) sd.append(board2.ocean[i][j-12]).append(" ");
                if (j == board1.BOARD_SIZE * 2 + 1) sd.append(border2).append("\n");
            }
        }
        sd.append(repeat1).append(" ❕  ").append(repeat2).append("\n");
        System.out.println(sd);
    }

    public static void displayMainMenu() {
        String mainMenu = "Welcome to Tom Crew's game!\n" +
                "Press enter to continue!\n";
        //todo choosing set method, manual, random
        System.out.println(mainMenu);
        Input.promptEnterKey();
    }
}

