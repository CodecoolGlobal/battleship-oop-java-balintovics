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

    public void placement() {
        Board board1 = new Board();
        Player player1 = new Player(input.getString("Player 1, what is your name? "), board1, 1, "🟥");
        Board board2 = new Board();
        Player player2 = new Player(input.getString("Player 2, what is your name? "), board2, 2, "🟦");
        Player player = player1;
        Board board;
        for (int j=0;j<2;j++) {
            if (j == 0) {
                player = player1;
                board = board1;
            } else {
                player = player2;
                board = board2;
            }
            Display.printSingleBoard(board);
        for (int i=5;i>1;i--) {
                bf.manualPlacement(player, i);
                Display.printSingleBoard(board);
            }
        }
        board1.setBoardVisibility(true);
        board2.setBoardVisibility(true);
        Display.printTwoBoards(board1, board2);

        //TODO: implement shooting phase
        swapPlayer(player1, player2, player);
        while (true) {
        int[] shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
        player1.handleShot(shot, player2);
        Display.printTwoBoards(board1, board2);
        swapPlayer(player1, player2, player);}
    }

    private void swapPlayer(Player player1, Player player2, Player player) {
        if (player == player1) {
            player = player2;
        } else { player = player1;}
    }

    public void pewPew() {
        while (true) {

        }
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
