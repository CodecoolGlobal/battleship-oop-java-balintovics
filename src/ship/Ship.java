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
        DESTROYER;
        public int shipLength() {
            return this.equals(CARRIER) ? 5 : this.equals(CRUISER) ? 4 :
                    this.equals(BATTLESHIP) ? 3 : this.equals(SUBMARINE) ? 2 : 1;  // 1 = Destroyer
        }
    }

    private List<Square> squares = new ArrayList<>();
    private boolean isSunk, isHorizontal;
    private final int x, y, shipLength;


    public Ship(String shipName, int x, int y, boolean isHorizontal) {
        this.x = x;
        this.y = y;
        this.isSunk = false;
        this.shipLength = getShipLength(shipName);
        this.isHorizontal = isHorizontal;

        for(int i=0; i < this.shipLength; i++) {
            Square temp = new Square();
            temp.setShip();
            this.squares.add(temp);
        }
    }

    private int getShipLength(String shipName) {  // some other way maybe?
        return switch (shipName) {
            case "Carrier" -> ShipType.CARRIER.shipLength();
            case "Cruiser" -> ShipType.CRUISER.shipLength();
            case "Battleship" -> ShipType.BATTLESHIP.shipLength();
            case "Submarine" -> ShipType.SUBMARINE.shipLength();
            default -> ShipType.DESTROYER.shipLength();
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

    public int getShipLength() {
        return this.shipLength;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

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
