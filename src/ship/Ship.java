package ship;

import board.Square;
import utilities.Display;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private List<Square> squares;
    private boolean isSunk;
    private ShipType shipType;
    Display display;


    public Ship(ArrayList<Square> squares, ShipType shipType) {
        this.squares = squares;
        this.isSunk = false;
        this.shipType = shipType;
    }

    public void setSunk() {
        for (Square square : this.squares) {
            square.setSunk();
        }
        display.shout("You've sunk a ship!");
        this.isSunk = true;
        Display.wait(1500);
    }

    public boolean isSunk() {
        return this.isSunk;
    }

    public void canShipSink() {
        boolean shouldBeSunk = true;
        for (Square nextSquare : squares) {
            if (!nextSquare.isHit()) shouldBeSunk = false;
        }
        if (shouldBeSunk && !this.isSunk) setSunk();
    }
}
