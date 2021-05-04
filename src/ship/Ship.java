package ship;

import board.Square;

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

}
