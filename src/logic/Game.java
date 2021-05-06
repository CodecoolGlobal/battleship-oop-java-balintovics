package logic;

import board.*;
import player.ComputerPlayer;
import player.Player;
import utilities.*;
import utilities.Display;

import java.util.List;
import java.util.Arrays;

public class Game {
    Player player;
    Player opponent;
    ComputerPlayer ai;
    Input input = new Input();
    BoardFactory bf = new BoardFactory();

    public void placement() {
        Board board1 = new Board();
//        Player player1 = new Player(input.getString("Player 1, what is your name? "), board1, 1, "🟥");
        Board board2 = new Board();
//        Player player2 = new Player(input.getString("Player 2, what is your name? "), board2, 2, "🟦");
        this.player = new Player(input.getString("Player 1, what is your name? "), board1, 1, "🟥");
//        this.opponent = new Player(input.getString("Player 2, what is your name? "), board2, 2, "🟦");
        this.ai = new ComputerPlayer("AI", board2);
        // todo testing version
//        for (int j=0;j<2;j++) {
        bf.randomPlacement(player, 2);
//        bf.randomPlacement(opponent, 2);
        bf.randomPlacement(ai, 2);
        Display.printSingleBoard(board2);
//            }
        // todo this is the main version
        for (int j=0;j<2;j++) {
            for (int i = 5; i > 1; i--) {
                if (j == 0) {
                    bf.randomPlacement(player, i);
                    Display.printSingleBoard(board1);
                } else {
                    bf.randomPlacement(ai, i);
                    Display.printSingleBoard(board2);
                }
            }
        }
        board1.setBoardVisibility(true);
        board2.setBoardVisibility(true);
        Display.printTwoBoards(board1, board2);
        //TODO: implement shooting phase
//        swapPlayer(player, opponent);
//        while ((player.isAlive() && opponent.isAlive())) {
//            int[] shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
//            while (!player.validShot(shot, opponent)) {
//                shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
//            }
//            player.handleShot(shot, opponent);
//            Display.printTwoBoards(board1, board2);
//            swapPlayer(player, opponent);
//            }
        }

    private void swapPlayer(Player player, Player opponent) {
        if (this.player == player) {
            this.player = opponent;
            this.opponent = player;
        } else {
            this.player = player;
            this.opponent = opponent;
        }
    }

    public void PlayerVsAI() {
        while ((player.isAlive() && ai.isAlive())) {
            int[] shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
            while (!player.validShot(shot, ai)) {
                shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
            }
            player.handleShot(shot, ai);
            int[] aiShot = ai.ComputerPlayerEasy();
            ai.handleAIShot(aiShot, player);
            Display.printTwoBoards(player.board, ai.board);

//            Display.printTwoBoards(board1, board2);
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
