package logic;

import board.Board;
import board.BoardFactory;
import player.ComputerPlayer;
import player.IPlayer;
import player.Player;
import ship.ShipType;
import utilities.Display;
import utilities.Input;

public class Game {
    Display display = new Display();
    IPlayer player;
    IPlayer opponent;
    ComputerPlayer ai;
    ComputerPlayer ia;
    Input input = new Input();
    BoardFactory bf = new BoardFactory();
    boolean isTest = false;
    Board board1 = new Board();
    Board board2 = new Board();

    public void mainGame() {
        String player1Name = "";
        String player2Name = "";

//        while (!player1Name.equals("Maverick")) {
            player1Name = input.getString("Player 1, what is your name? ");
         player2Name = input.getString("Player 2, what is your name? ");

//        }

        this.player = new Player(player1Name, board1, 1, "🟥");
        this.opponent = new Player(input.getString("Player 2, what is your name? "), board2, 2, "🟦");
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

    private void swapPlayer(IPlayer player, IPlayer opponent) {
        if (this.player == player) {
            this.player = opponent;
            this.opponent = player;
        } else {
            this.player = player;
            this.opponent = opponent;
        }
    }

    public void playVsAi(int mode) {
        player = new Player("Maverick", board1, 1, "🟥");
        ai = new ComputerPlayer("AI", board2);
        randomVsAiGameplay();
        setBoardVisibility(board1, board2);
        Display.printTwoBoards(board1, board2, isTest);
        if (mode == 2) PlayerVsEasyAI();
        else PlayerVsNormalAI();
        victory(player, ai);
    }

    private void aiVsAi() {
        ai = new ComputerPlayer("AI", board1);
        ia = new ComputerPlayer("IA", board2);
        AivsAiPlacement();
        setBoardVisibility(board1, board2);
        Display.printTwoBoards(board1, board2, false);
        while (ai.isAlive() && ia.isAlive()) {
            int[] aiShot = ai.shot();
            ai.aiShootingAi(aiShot, ia);
            waitingForAI();
            Display.printTwoBoards(ai.getBoard(), ia.getBoard(), isTest);
            int[] iaShot = ai.shot();
            ia.aiShootingAi(iaShot, ai);
            waitingForAI();
            Display.printTwoBoards(ai.getBoard(), ia.getBoard(), isTest);
        }

    }

    public void PlayerVsEasyAI() {
        while ((player.isAlive() && ai.isAlive())) {
            int[] shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
            while (!player.validShot(shot, ai)) {
                shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
            }
            player.handleShot(shot, ai);
            int[] aiShot = ai.shot();
            ai.handleShot(aiShot, player);
            waitingForAI();
            Display.printTwoBoards(player.getBoard(), ai.getBoard(), isTest);
        }
    }

    public void PlayerVsNormalAI() {
        while ((player.isAlive() && ai.isAlive())) {
            int[] shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
            while (!player.validShot(shot, ai)) {
                shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
            }
            player.handleShot(shot, ai);
            ai.determineDirection();
            int[] aiShot = ai.shootByDirection(player.getBoard());
            ai.handleShot(aiShot, player);
            waitingForAI();
            Display.printTwoBoards(player.getBoard(), ai.getBoard(), isTest);
        }
    }

    private void waitingForAI() {
        display.shout("Calculating...");
        Display.wait(1500);
    }

    private void victory(IPlayer player, IPlayer opponent) {
        String victoryShout;
        if (player.isAlive()) {
            victoryShout = player.name + " has won! GLORY!";
        } else {
            victoryShout = opponent.name + " has won!";
        }
        display.shout(victoryShout);
    }

    // TODO: concatenate randomGameplay and randomVsAiGameplay by parameterizing the input
    private void randomGameplay() {
        Display.printSingleBoard(board1);
        for (int j = 0; j < 2; j++) {
            for (int i = 4; i > 1; i--) {
                if (j == 0) {
                    bf.randomPlacement((Player) player, ShipType.values()[i]); //TODO: check if right
                    Display.printSingleBoard(board1);
                } else {
                    bf.randomPlacement(opponent, ShipType.values()[i]);
                }
            }
        }
    }

    private void randomVsAiGameplay() {
        Display.printSingleBoard(board1);
        for (int j = 0; j < 2; j++) {
            for (int i = 4; i > 1; i--) {
                if (j == 0) {
                    bf.randomPlacement(player, ShipType.values()[i]);
                    Display.printSingleBoard(board1);
                } else {
                    bf.randomPlacement(ai, ShipType.values()[i]);
                }
            }
        }
    }

    private void manualGameplay() {
        var listOfShips = new ShipType[]{
                ShipType.Battleship,
                ShipType.Destroyer,
                ShipType.Carrier
        };

        for (int j = 0; j < 2; j++) {
            for (var ship : listOfShips) {
                if (j == 0) {
                    Display.clear();
                    Display.printSingleBoard(board1);
                    bf.manualPlacement((Player) player, ship);
                    Display.printSingleBoard(board1);
                    if (ship.getLength() == 2) {
                        Display.clear();
                    }
                } else {
                    Display.clear();
                    Display.printSingleBoard(board2);
                    bf.manualPlacement((Player) opponent, ship);
                    Display.printSingleBoard(board2);
                    if (ship.getLength() == 2) {
                        Display.clear();
                    }
                }
            }
        }
    }

    private void AivsAiPlacement() {
        for (int j = 0; j < 2; j++) {
            for (int i = 4; i > 1; i--) {
                if (j == 0) {
                    Display.clear();
                    bf.randomPlacement(ai, ShipType.values()[i]);
                } else {
                    bf.randomPlacement(ia, ShipType.values()[i]);
                    if (i == 2) {
                        Display.clear();
                    }
                }
            }
        }
    }


    private void testGameplay(Board board1, Board board2) {

        Display.printSingleBoard(board1);
        bf.randomPlacement(player, ShipType.values()[4]);
        bf.randomPlacement(opponent, ShipType.values()[4]);
        bf.randomPlacement(player, ShipType.values()[4]);
        bf.randomPlacement(opponent, ShipType.values()[4]);
        Display.printSingleBoard(board1);
        Display.printSingleBoard(board2);
    }

    private void shootingPhase(IPlayer player, IPlayer opponent, Board board1, Board board2) {
        int[] shot = input.convertPlacement(input.getString(String.format("Time for shooting, %s! GET'EM!!!!", player.name)));
//        while (player.struck.contains(shot)) shot = input.convertPlacement(input.getString("You already shot there, dude..."));
        while (!player.validShot(shot, opponent) || player.struck.contains(shot)) { //TODO: FIX -> struck contains doesn't work
            shot = input.convertPlacement(input.getString(String.format("%s, dude... That's just not good...", player.name)));
        }
        player.handleShot(shot, opponent);
        Display.printTwoBoards(board1, board2, isTest);
        swapPlayer(this.player, this.opponent);
    }

    public void mainMenu() {
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
