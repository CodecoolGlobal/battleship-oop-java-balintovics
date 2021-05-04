package ship;

import board.Square;

import java.util.List;

public class Ship {
    private enum ShipType {
        Carrier,
        Cruiser,
        Battleship,
        Submarine,
        Destroyer;

        public int getShipLength() {
            return this.equals(Carrier) ? 5 : this.equals(Cruiser) ? 4 :
                    this.equals(Battleship) ? 3 : this.equals(Submarine) ? 2 : 1;
        }


        private List<Square> squares;

    }
}
