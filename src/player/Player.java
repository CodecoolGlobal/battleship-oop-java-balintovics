package player;

import board.Board;
import board.Square;
import ship.Ship;

import java.util.List;
import java.util.ArrayList;

public class Player {
    public final String name;
    public ArrayList<Ship> ships = new ArrayList<>();
    public Board board;
    public int rank;
    public String symbol;

    public Player(String name, Board board, int rank, String symbol) {
        this.name = name;
        this.board = board;
        this.rank = rank;
        this.symbol = symbol;
    }

    public void addShip(Ship ship) {
        this.ships.add(ship);
    }

    public boolean isAlive() {
        return ships.size() > 0;
    }

    public void handleShot(int[] shot, Player opponent) {
        opponent.board.ocean[shot[0]][shot[1]].setVisibility(false);
        opponent.board.ocean[shot[0]][shot[1]].setHit();
    }
}
