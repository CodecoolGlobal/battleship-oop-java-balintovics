package ship;

import board.Square;
import utilities.Display;

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
    Display display;


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
        switch (shipLength) {
            case 5: return ShipType.Carrier.toString();
            case 4: return ShipType.Cruiser.toString();
            case 3: return ShipType.Battleship.toString();
            case 2: return ShipType.Submarine.toString();
            default: return ShipType.Destroyer.toString();
        }
    }

    public void setSunk() {
        for (Square square : this.squares) {
            square.setSunk();
        }
        Display.shout("You've sunk a ship!");
        this.isSunk = true;
        Display.wait(1500);
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
