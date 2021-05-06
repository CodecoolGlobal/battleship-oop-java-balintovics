import board.Board;
import player.Player;
import utilities.*;
import logic.Game;
import player.*;
import java.util.Arrays;


public class Battleship {

    public static void main(String[] args) {
        Display display = new Display();

        display.displayMainMenu();
        Game game = new Game();
        game.mainGame();


    }
}
