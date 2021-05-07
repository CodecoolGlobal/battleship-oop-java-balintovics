import logic.Game;
import utilities.Display;


public class Battleship {

    public static void main(String[] args) {
        Display display = new Display();

        display.displayMainMenu();
        Game game = new Game();
        game.mainMenu();
    }
}
