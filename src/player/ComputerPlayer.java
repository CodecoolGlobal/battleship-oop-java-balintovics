package player;

import board.Board;
import ship.Ship;

import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player {
    public ArrayList<int[]> struck = new ArrayList<>();
    public ArrayList<int[]> hit = new ArrayList<>();
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
        return shot;
    }

    public boolean setWasHit(boolean wasHit) {
        return wasHit;
    }

    public void setHotDirection(String hotDirection) {
        this.hotDirection = hotDirection;
    }

    public void determineDirection() {
        String[] directions = new String[]{"l", "r", "u", "d"}; //TODO: Shoot around first hit!!
            if (this.wasHit.size() < 2) {
                setHotDirection(null);}
        else {
            if (this.wasHit.get(0) && this.wasHit.get(1)) {
                int[] secondHit = this.struck.get(0);
                int[] firstHit = this.struck.get(1);
                if (firstHit[0] - secondHit[0] > 1 || firstHit[1] - secondHit[1] > 1) {
                    setHotDirection(directions[new Random().nextInt(directions.length-1)]);}
                else if (firstHit[0] - secondHit[0] == 1 && firstHit[1] == secondHit[1]) {setHotDirection("u");}
                else if (firstHit[0] - secondHit[0] == -1 && firstHit[1] == secondHit[1]) {setHotDirection("d");}
                else if (firstHit[1] - secondHit[1] == 1 && firstHit[0] == secondHit[0]) {setHotDirection("l");}
                else if (firstHit[1] - secondHit[1] == -1 && firstHit[0] == secondHit[0]) {setHotDirection("r");}
            }
            }
        setHotDirection(null);
        }

        public int[] shootByDirection(Board board) {
        int[] nextShot = ComputerPlayerEasy(); //TODO: FIX THIS! Doesn't check already struck squares, doesn't return the neighbour coordinates!
        if (this.hotDirection != null && this.hit.size() > 0) {
            switch (this.hotDirection) {
                    case "u":
                        if (this.hit.get(0)[0] - 1 > 0) nextShot = new int[] {this.hit.get(0)[0] - 1, this.hit.get(0)[1]};
                    else {
                            setHotDirection("d");
                            nextShot =  new int[] {this.hit.get(0)[0] + 1, this.hit.get(0)[1]};}
                        break;
                    case "d":
                        if (this.hit.get(0)[0] + 1 < board.BOARD_SIZE) nextShot =  new int[] {this.hit.get(0)[0] + 1, this.hit.get(0)[1]};
                        else {
                            setHotDirection("u");
                            nextShot =  new int[] {this.hit.get(0)[0] - 1, this.hit.get(0)[1]};}
                        break;
                    case "l":
                        if (this.hit.get(0)[1] - 1 > 0) nextShot =  new int[] {this.hit.get(0)[0], this.hit.get(0)[1] - 1};
                        else {
                            setHotDirection("r");
                            nextShot =  new int[] {this.hit.get(0)[0], this.hit.get(0)[1] + 1};}
                        break;
                    case "r":
                        if (this.hit.get(0)[1] + 1 < board.BOARD_SIZE) nextShot =  new int[] {this.hit.get(0)[0], this.hit.get(0)[1] + 1};
                        else {
                            setHotDirection("l");
                            nextShot =  new int[] {this.hit.get(0)[0], this.hit.get(0)[1] - 1};}
                        break;
                }
        }
        else if (this.hit.size() > 0) {
            if (this.hit.get(0) == this.struck.get(0)) {
                String[] directions = new String[]{"l", "r", "u", "d"};
                setHotDirection(directions[new Random().nextInt(directions.length-1)]);
                shootByDirection(board);}}
        return nextShot;
        }

    public void handleAIShot(int[] shot, Player opponent) {
        this.struck.add(0, shot);
        this.lastShot = shot;
        opponent.board.ocean[shot[0]][shot[1]].setVisibility(false);
        opponent.board.ocean[shot[0]][shot[1]].setHit();
        this.wasHit.add(0, setWasHit(opponent.board.ocean[shot[0]][shot[1]].isShip()));
        if (opponent.board.ocean[shot[0]][shot[1]].isShip()) this.hit.add(0, shot);
        for (Ship ship: opponent.ships) {
            ship.canShipSink();
        }
    }
}
