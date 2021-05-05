import board.Board;
import player.Player;
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
        game.placement();
//        while (true){
//
//        }

    }
}
