package logic;

import board.*;
import player.Player;
import utilities.*;
import utilities.Display;

public class Game {
    Board playerBoard1;
    Board playerBoard2;
    Input input = new Input();

    public void startGame() {
        Board playerBoard1 = new Board();
        Board playerBoard2 = new Board();
        Player player1 = new Player(input.getString("What is your name? "), playerBoard1);
        Player player2 = new Player(input.getString("What is your name? "), playerBoard2);
        playerBoard1.ocean[2][5].setShip();
        Display.printTwoBoards(playerBoard1, playerBoard2);
//        Display.clear();
//        System.out.println(ASCII.tomCruise());
    }

    public void setShips() {
        playerBoard1.ocean[2][5].setShip();
    }

    public static void runGame() {

    }
    //generate board full of water
    //Setting phase:
    //Set the 5 ships in an order (choose starting point-endpoint (check in between)/ then second player
    //hide ships
    //Shooting phase:
    //Player choose a coordinate on the opponents board, check if its a hit, change icon,change player
    // check if every ship sunk
    //Player x Win
}
