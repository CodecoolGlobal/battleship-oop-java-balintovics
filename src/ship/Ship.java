package ship;

import board.Square;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private enum ShipType {
        Carrier,
        Cruiser,
        Battleship,
        Submarine,
        Destroyer
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
            case 5 -> ShipType.Carrier.toString();
            case 4 -> ShipType.Cruiser.toString();
            case 3 -> ShipType.Battleship.toString();
            case 2 -> ShipType.Submarine.toString();
            default -> ShipType.Destroyer.toString();
        };
    }

    public void setSunk() {
        for (Square square : this.squares) {
            square.setSunk();
        }
        System.out.println("Sunk");
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

    public List<Square> getShipSquares() {
        return this.squares;
    }

    public Square getSquareByIndex(int index) {
        return squares.get(index);
    }

    public void canShipSink() {
        boolean shouldBeSunk = true;
        for (Square nextSquare : squares) {
            if (!nextSquare.isHit()) shouldBeSunk = false;
        }
        if (shouldBeSunk && !this.isSunk) setSunk();
    }
}
