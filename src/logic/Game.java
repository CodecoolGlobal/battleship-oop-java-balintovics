package logic;

import board.Board;
import board.BoardFactory;
import player.ComputerPlayer;
import player.Player;
import utilities.Display;
import utilities.Input;

import java.util.concurrent.TimeUnit;

public class Game {
    Player player = new Player();
    Player opponent = new Player();
    ComputerPlayer ai;
    ComputerPlayer ia;
    Input input = new Input();
    BoardFactory bf = new BoardFactory();
    boolean isTest = false;
    Board board1 = new Board();
    Board board2 = new Board();

    public void mainGame() {
        player = new Player(input.getString("Player 1, what is your name? "), this.board1, 1, "🟥");
        opponent = new Player(input.getString("Player 2, what is your name? "), this.board2, 2, "🟦");
        choosePlacement();
        setBoardVisibility(board1, board2);
        Display.printTwoBoards(board1, board2, isTest);

        while (player.isAlive() && opponent.isAlive()) {
            shootingPhase(this.player, this.opponent, board1, board2);
        }
        victory(player, opponent);
    }

    private void choosePlacement() {
        String placementType = input.getString("Please choose: (m)anual or (r)andom ship placement: ").toLowerCase();
        switch (placementType) {
            case "m":
                manualGameplay();
                break;
            case "t":
                testGameplay(board1, board2);
                isTest = true;
                break;
            default:
                randomGameplay();
                break;
        }
    }

    private void setBoardVisibility(Board board1, Board board2) {
        board1.setBoardVisibility(true);
        board2.setBoardVisibility(true);
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

    public void playVsAi(int mode) throws InterruptedException {
        player = new Player(input.getString("Player 1, what is your name? "), board1, 1, "🟥");
        ai = new ComputerPlayer("AI", board2);
        randomVsAiGameplay();
        setBoardVisibility(board1, board2);
        Display.printTwoBoards(board1, board2, isTest);
        if (mode == 2) PlayerVsEasyAI();
        else PlayerVsNormalAI();
        victory(player, ai);
    }

    private void aiVsAi() throws InterruptedException {
        ai = new ComputerPlayer("AI", board1);
        ia = new ComputerPlayer("IA", board2);
        AivsAiPlacement();
        setBoardVisibility(board1, board2);
        Display.printTwoBoards(board1, board2, false);
        while (ai.isAlive() && ia.isAlive()) {
            int[] aiShot = ai.ComputerPlayerEasy();
            ai.aiShootingAi(aiShot, ia);
            waitingForAI();
            Display.printTwoBoards(ai.board, ia.board, isTest);
            int[] iaShot = ai.ComputerPlayerEasy();
            ia.aiShootingAi(iaShot, ai);
            waitingForAI();
            Display.printTwoBoards(ai.board, ia.board, isTest);
        }

    }

    public void PlayerVsEasyAI() throws InterruptedException {
        while ((player.isAlive() && ai.isAlive())) {
            int[] shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
            while (!player.validShot(shot, ai)) {
                shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
            }
            player.handleShot(shot, ai);
            int[] aiShot = ai.ComputerPlayerEasy();
            ai.handleAIShot(aiShot, player);
            waitingForAI();
            Display.printTwoBoards(player.board, ai.board, isTest);
        }
    }

    public void PlayerVsNormalAI() throws InterruptedException {
        while ((player.isAlive() && ai.isAlive())) {
            int[] shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
            while (!player.validShot(shot, ai)) {
                shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
            }
            player.handleShot(shot, ai);
            ai.determineDirection();
            int[] aiShot = ai.shootByDirection(player.board);
            ai.handleAIShot(aiShot, player);
            waitingForAI();
            Display.printTwoBoards(player.board, ai.board, isTest);
        }
    }

    private void waitingForAI() throws InterruptedException {
        Display.shout("Calculating...");
        TimeUnit.SECONDS.sleep(1);
    }

    private void victory(Player player, Player opponent) {
        String victoryShout;
        if (player.isAlive()) {
            victoryShout = player.name + " has won! GLORY!";
        } else {
            victoryShout = opponent.name + " has won!";
        }
        Display.shout(victoryShout);
    }

    private void randomGameplay() {
        Display.printSingleBoard(board1);
        for (int j = 0; j < 2; j++) {
            for (int i = 5; i > 1; i--) {
                if (j == 0) {
                    bf.randomPlacement(player, i);
                    Display.printSingleBoard(board1);
                } else {
                    bf.randomPlacement(opponent, i);
                }
            }
        }
    }

    private void randomVsAiGameplay() {
        Display.printSingleBoard(board1);
        for (int j = 0; j < 2; j++) {
            for (int i = 5; i > 1; i--) {
                if (j == 0) {
                    bf.randomPlacement(player, i);
                    Display.printSingleBoard(board1);
                } else {
                    bf.randomPlacement(ai, i);
                }
            }
        }
    }

    private void manualGameplay() {
        for (int j = 0; j < 2; j++) {
            for (int i = 5; i > 1; i--) {
                if (j == 0) {
                    Display.clear();
                    Display.printSingleBoard(board1);
                    bf.manualPlacement(player, i);
                    Display.printSingleBoard(board1);
                    if (i == 2) {
                        Display.clear();
                    }
                } else {
                    Display.clear();
                    Display.printSingleBoard(board2);
                    bf.manualPlacement(opponent, i);
                    Display.printSingleBoard(board2);
                    if (i == 2) {
                        Display.clear();
                    }
                }
            }
        }
    }

    private void AivsAiPlacement() {
        for (int j = 0; j < 2; j++) {
            for (int i = 5; i > 1; i--) {
                if (j == 0) {
                    Display.clear();
                    bf.randomPlacement(ai, i);
                } else {
                    bf.randomPlacement(ia, i);
                    if (i == 2) {
                        Display.clear();
                    }
                }
            }
        }
    }


    private void testGameplay(Board board1, Board board2) {

        Display.printSingleBoard(board1);
        bf.randomPlacement(player, 2);
        bf.randomPlacement(opponent, 2);
        bf.randomPlacement(player, 2);
        bf.randomPlacement(opponent, 2);
        Display.printSingleBoard(board1);
        Display.printSingleBoard(board2);
    }

    private void shootingPhase(Player player, Player opponent, Board board1, Board board2) {
        int[] shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
        while (!player.validShot(shot, opponent)) {
            shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
        }
        player.handleShot(shot, opponent);
        Display.printTwoBoards(board1, board2, isTest);
        swapPlayer(this.player, this.opponent);
    }

    public void mainMenu() throws InterruptedException {
        int mode = input.getInt("Welcome! What game mode do you prefer? \n1: Pilot vs Pilot\n2: Top Gun\n3: Terminator II");
        while (mode < 1 || mode > 3) {
            mode = input.getInt("Cheeky, cheeky user... Pick between 1 and 3 ;)\n");
        }
        switch (mode) {
            case 1:
                mainGame();
                break;
            case 2:
                playVsAi(2);
                break;
            case 3:
                aiVsAi();
                break;
        }
    }
}
