package player;

import board.Board;
import ship.Ship;

import java.util.ArrayList;

public class Player {
    public String name;
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

    public Player(String name) {
        this.name = name;
    }

    public Player(Board board) {
        this.board = board;
    }

    public Player() {
    }

    public void addShip(Ship ship) {
        this.ships.add(ship);
    }

    public boolean isAlive() {
        for (Ship ship: this.ships) {
            if (!ship.isSunk()) return true;
        }
        return false;
    }

    public void handleShot(int[] shot, Player opponent) {
        opponent.board.ocean[shot[0]][shot[1]].setVisibility(false);
        opponent.board.ocean[shot[0]][shot[1]].setHit();
        for (Ship ship: opponent.ships) {
                ship.canShipSink();
            }
    }

    public boolean validShot(int[] shot, Player opponent) {
        try {
            opponent.board.ocean[shot[0]][shot[1]].setHit();
        } catch (IndexOutOfBoundsException e) {
            return false;
        } return true;
    }
}