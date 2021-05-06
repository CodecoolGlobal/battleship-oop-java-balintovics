package logic;

import board.*;
import player.Player;
import utilities.*;
import utilities.Display;

import java.util.List;
import java.util.Arrays;

public class Game {
    Player player;
    Player opponent;
    Input input = new Input();
    BoardFactory bf = new BoardFactory();

    public void placement() {
        Board board1 = new Board();
//        Player player1 = new Player(input.getString("Player 1, what is your name? "), board1, 1, "🟥");
        Board board2 = new Board();
//        Player player2 = new Player(input.getString("Player 2, what is your name? "), board2, 2, "🟦");
        this.player = new Player(input.getString("Player 1, what is your name? "), board1, 1, "🟥");
        this.opponent = new Player(input.getString("Player 2, what is your name? "), board2, 2, "🟦");
        String placementType = input.getString("Please choose: (m)anual or (r)andom ship placement: ");
        switch (placementType) {
            case "r":
                randomGameplay(board1, board2);
                break;
            case "m":
                manualGameplay(board1,board2);
                break;
            default:
                randomGameplay(board1, board2);
                break;
        }

//        // todo testing version
//        for (int j = 0; j < 2; j++) {
//                bf.randomPlacement(player, 2);
//                bf.randomPlacement(opponent, 2);
//                bf.randomPlacement(player, 2);
//                bf.randomPlacement(opponent, 2);
//                Display.printSingleBoard(board1);
//                Display.printSingleBoard(board2);
//            }

//            for (int i = 5; i > 1; i--) {
//                if (j == 0) {
//                    bf.randomPlacement(player, i);
//                    Display.printSingleBoard(board1);
//                } else {
//                    bf.randomPlacement(opponent, i);
//                    Display.printSingleBoard(board2);
//                }
//            }

            board1.setBoardVisibility(true);
            board2.setBoardVisibility(true);
            Display.printTwoBoards(board1, board2);
//        swapPlayer(player, opponent);
            while (player.isAlive() && opponent.isAlive()) {
                int[] shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
                while (!player.validShot(shot, opponent)) {
                    shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
                }
                player.handleShot(shot, opponent);
                Display.printTwoBoards(board1, board2);
                swapPlayer(player, opponent);
            }
            victory(player, opponent);
        }

        private void swapPlayer (Player player, Player opponent){
            if (this.player == player) {
                this.player = opponent;
                this.opponent = player;
            } else {
                this.player = player;
                this.opponent = opponent;
            }
        }

        private void victory (Player player, Player opponent){
            String victoryShout;
            if (player.isAlive()) {
                victoryShout = player.name + " has won! GLORY!";
            } else {
                victoryShout = opponent.name + " has won!";
            }
            System.out.println(victoryShout);
        }


        public void pewPew () {
            while (true) {
            }
        }

        public static void runGame () {

        }
        //generate board full of water
        //Setting phase:
        //Set the 5 ships in an order (choose starting point-endpoint (check in between)/ then second player
        //hide ships
        //Shooting phase:
        //Player choose a coordinate on the opponents board, check if its a hit, change icon,change player
        // check if every ship sunk
        //Player x Win


        private void randomGameplay (Board board1, Board board2){
            for (int j = 0; j < 2; j++) {
                for (int i = 5; i > 1; i--) {
                    if (j == 0) {
                        bf.randomPlacement(player, i);
                        Display.printSingleBoard(board1);
                    } else {
                        bf.randomPlacement(opponent, i);
                        Display.printSingleBoard(board2);
                    }
                }
            }
        }
    private void manualGameplay (Board board1, Board board2){
        for (int j = 0; j < 2; j++) {
            for (int i = 5; i > 1; i--) {
                if (j == 0) {
                    bf.manualPlacement(player, i);
                    Display.printSingleBoard(board1);
                } else {
                    bf.manualPlacement(opponent, i);
                    Display.printSingleBoard(board2);
                }
            }
        }
    }
}