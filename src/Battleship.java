import board.Board;
import utilities.*;
import logic.Game;
import player.*;
import java.util.Arrays;


public class Battleship {

    public static void main(String[] args) {
        Display display = new Display();
        Input input = new Input();

        display.displayMainMenu();
//        Input.promptEnterKey();    This is still needed
        Game game = new Game();
        game.startGame();
//        while (true){
//
//        }
        Board board1 = new Board();
        Player player1 = new Player(input.getString("What is your name? "), board1);
        Board board2 = new Board();
        Player player2 = new Player(input.getString("What is your name? "), board2);
        Display.printTwoBoards(board1, board2);
        int[] shot = {0,0};
        player1.handleShot(shot, player2);
        Display.printTwoBoards(board1, board2);
    }
}
