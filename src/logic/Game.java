package logic;

import board.*;
import player.Player;
import utilities.*;
import utilities.Display;

import java.util.List;
import java.util.Arrays;

public class Game {
    Board playerBoard1;
    Board playerBoard2;
    Input input = new Input();
    BoardFactory bf = new BoardFactory();

    public void placement() {
        Board board1 = new Board();
//        Player player1 = new Player(input.getString("Player 1, what is your name? "), board1, 1, "🟥");
        Board board2 = new Board();
        List<Player> players = Arrays.asList(new Player(input.getString("Player 1, what is your name? "), board1, 1, "🟥"), new Player(input.getString("Player 2, what is your name? "), board2, 2, "🟦"));
//        Player player2 = new Player(input.getString("Player 2, what is your name? "), board2, 2, "🟦");
        Player player = players.get(0);
        Player opponent = players.get(1);
        for (int j=0;j<2;j++) {
            for (int i=5;i>1;i--) {
            if (j == 0) {
                bf.randomPlacement(player, i);
                Display.printSingleBoard(board1);
            } else {
                bf.randomPlacement(opponent, i);
                Display.printSingleBoard(board2);
            }


//            if (i == 3) {bf.manualPlacement(player, i);Display.printSingleBoard(board);}
//            bf.manualPlacement(player, i);
//            bf.randomPlacement(player, i);
//            Display.printSingleBoard(board);
            }
        }
        board1.setBoardVisibility(true);
        board2.setBoardVisibility(true);
        Display.printTwoBoards(board1, board2);
        //TODO: implement shooting phase
        swapPlayer(player, opponent, players);
        while (player.isAlive() || opponent.isAlive()) {
            int[] shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
            while (!player.validShot(shot, opponent)) {
                shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
            }
            player.handleShot(shot, opponent);
            Display.printTwoBoards(board1, board2);
            swapPlayer(player, opponent, players);}
    }

    private void swapPlayer(Player player, Player opponent, List<Player> players) {
        if (player == players.get(0)) {
            player = players.get(1);
            opponent = players.get(0);
        } else {
            player = players.get(0);
            opponent = players.get(1);
        }
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
