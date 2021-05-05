package ship;

import board.Square;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private enum ShipType {
        CARRIER,
        CRUISER,
        BATTLESHIP,
        SUBMARINE,
        DESTROYER
    }

    private List<Square> squares;
    private boolean isSunk, isHorizontal;
    private int shipLength;


    public Ship(boolean isHorizontal, ArrayList<Square> squares) {
        this.squares = squares;
        this.isSunk = false;
        this.shipLength = squares.size();
        this.isHorizontal = isHorizontal;
    }

    public Ship() {}

    public void addSquare(Square square) {
        this.squares.add(square);
    }

    public String getShipName(int shipLength) {
        return switch (shipLength) {
            case 5 -> ShipType.CARRIER.toString();
            case 4 -> ShipType.CRUISER.toString();
            case 3 -> ShipType.BATTLESHIP.toString();
            case 2 -> ShipType.SUBMARINE.toString();
            default -> ShipType.DESTROYER.toString();
        };
    }


    public void setSunk() {
        this.isSunk = true;
    }

    public boolean isSunk() {
        return this.isSunk;
    }

    public boolean isHorizontal() {
        return this.isHorizontal;
    }

    public void setHorizontal() {
        this.isHorizontal = true;
    }

    public void setVertical() {
        this.isHorizontal = false;
    }

//    public int getX() {
//        return this.x;
//    }
//
//    public int getY() {
//        return this.y;
//    }

    public List<Square> getShipSquares() {
        return this.squares;
    }

    public Square getSquareByIndex(int index) {
        return squares.get(index);
    }

    public void sinkShip() {
        boolean shouldBeSunk = true;
        for (Square nextSquare : squares) {
            if (!nextSquare.isHit()) shouldBeSunk = false;
        }
        if (shouldBeSunk && !this.isSunk) this.isSunk = true;
    }
}
