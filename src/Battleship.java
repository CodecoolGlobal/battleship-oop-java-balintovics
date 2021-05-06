import logic.Game;
import utilities.Display;


public class Battleship {

    public static void main(String[] args) throws InterruptedException {
        Display display = new Display();

        Display.displayMainMenu();
        Game game = new Game();
        game.mainMenu();
    }
}
