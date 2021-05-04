package utilities;

import board.Board;
import board.Square;

import java.util.*;

public class Display {

    public static void printSingleBoard(Board board) {
        StringBuilder sb = new StringBuilder();
        sb.append("🟩 ".repeat(board.BOARD_SIZE + 3));
        sb.append("\n");
        for (int k=0;k<board.BOARD_SIZE;k++) {
            if (k == 0) sb.append("🟩 ").append("  ");
            String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            sb.append(" ").append(Color.PURPLE).append(alphabet.charAt(k)).append(" ").append(Color.RESET);
        }
        sb.append(" 🟩").append("\n");
        for (int i = 0; i < board.BOARD_SIZE; i++) {
            for (int j = 0; j < board.BOARD_SIZE; j++) {
                if (j == 0) sb.append(Color.PURPLE).append("🟩").append(" ").append(i).append("  ").append(Color.RESET);
                sb.append(board.ocean[i][j]).append(" ");
            }
            sb.append("🟩").append("\n");
        }
        sb.append("🟩 ".repeat(board.BOARD_SIZE + 3));
        System.out.println(sb);
    }
}
