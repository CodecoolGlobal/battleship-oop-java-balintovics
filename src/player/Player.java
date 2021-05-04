package player;

import board.Board;
import ship.Ship;

import java.util.List;

public class Player {
    public final String name;
    private List<Ship> ships;
    public Board board;

    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
    }

    public boolean isAlive() {
        return ships.size() > 0;
    }

    public void handleShot(int[] shot, Player opponent) {
        opponent.board.ocean[shot[0]][shot[1]].setHidden(false);
        opponent.board.ocean[shot[0]][shot[1]].setHit();
    }
}
