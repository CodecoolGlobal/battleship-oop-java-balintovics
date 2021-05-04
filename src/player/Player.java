package player;

import ship.Ship;

import java.util.List;

public class Player {

    private List<Ship> ships;

    public boolean isAlive() {
        return ships.size() > 0;
    }
}
