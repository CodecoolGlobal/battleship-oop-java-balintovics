package logic;

import board.*;
import player.Player;
import utilities.*;
import utilities.Display;

public class Game {
    Board playerBoard1;
    Board playerBoard2;
    Input input = new Input();
    BoardFactory bf = new BoardFactory();

    public void startGame() {
        Board board1 = new Board();
        Player player1 = new Player(input.getString("What is your name? "), board1);
        Board board2 = new Board();
        Player player2 = new Player(input.getString("What is your name? "), board2);
        Display.printSingleBoard(board2);
        bf.manualPlacement(player1, 5);
        Display.printSingleBoard(board1);
        bf.manualPlacement(player1, 4);
        Display.printSingleBoard(board1);
        bf.manualPlacement(player1, 3);
        Display.printSingleBoard(board1);
        bf.manualPlacement(player1, 2);
        Display.printSingleBoard(board1);

        bf.randomPlacement(player2, 5);
        Display.printSingleBoard(board2);
        bf.randomPlacement(player2, 4);
        Display.printSingleBoard(board2);
        bf.randomPlacement(player2, 3);
        Display.printSingleBoard(board2);
        bf.randomPlacement(player2, 2);
        Display.printSingleBoard(board2);
        board1.setBoardVisibility(true);
        board2.setBoardVisibility(true);

        //        Display.printTwoBoards(board1, board2);
//        int[] shot = input.convertPlacement(input.getString("Lets sink something!"));
//        player1.handleShot(shot, player2);
//        Display.clear();
        Display.printTwoBoards(board1, board2);

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
