import board.Board;
import utilities.*;

import java.util.Arrays;

public class Battleship {
    public static void main(String[] args) {
        //display menu
        //    Press any button to play
        Board board1 = new Board();
        Board board2 = new Board();
        Display.printTwoBoards(board1, board2);
        Display.clear();
        System.out.println(ASCII.tomCruise());
    }
}
