package player;

import board.Board;
import ship.Ship;

import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player {
    public ArrayList<int[]> struck = new ArrayList<>();
    public int[] lastShot;
    public ArrayList<Boolean> wasHit = new ArrayList<>();
    public String hotDirection = null;

    private enum ComputerPlayerType {
        EASY,
        NORMAL,
        HARD
    }

    public ComputerPlayer(String name, Board board) {
        super();
        this.name = name;
        this.board = board;
    }


    public int[] ComputerPlayerEasy() {
        int[] shot = new int[]{new Random().nextInt(10), new Random().nextInt(10)};
        while (this.struck.contains(shot)) {shot = new int[]{new Random().nextInt(10), new Random().nextInt(10)};}
        this.struck.add(0, shot);
        this.lastShot = shot;
        return shot;
    }

    public boolean setWasHit(boolean wasHit) {
        return wasHit;
    }

    public void setHotDirection(String hotDirection) {
        this.hotDirection = hotDirection;
    }

    public String determineDirection(int[] hit) {
        String[] directions = new String[]{"l", "r", "u", "d"};
        if (this.wasHit.size() < 2) {
            return directions[new Random().nextInt(directions.length-1)];}
        else {
            if (this.wasHit.get(0) && this.wasHit.get(1)) {
                int[] secondHit = this.struck.get(0);
                int[] firstHit = this.struck.get(1);
                if (firstHit[0] - secondHit[0] > 1 || firstHit[1] - secondHit[1] > 1) {
                    setHotDirection(null);
                    return directions[new Random().nextInt(directions.length-1)];}
                else if (firstHit[0] - secondHit[0] == 1) {setHotDirection("u"); return "u";}
                else if (firstHit[0] - secondHit[0] == -1) {setHotDirection("d");  return "d";}
                else if (firstHit[1] - secondHit[1] == 1) {setHotDirection("l"); return "l";}
                else if (firstHit[1] - secondHit[1] == -1) {setHotDirection("r"); return "r";}
            } //TODO: implement checking if direction is out of bounds
            } return directions[new Random().nextInt(directions.length-1)];
        }

    public void handleAIShot(int[] shot, Player opponent) {
        opponent.board.ocean[shot[0]][shot[1]].setVisibility(false);
        opponent.board.ocean[shot[0]][shot[1]].setHit();
        this.wasHit.add(0, setWasHit(opponent.board.ocean[shot[0]][shot[1]].isShip()));
        for (Ship ship: opponent.ships) {
            ship.canShipSink();
        }
    }
}
