package board;

import player.Player;
import utilities.*;

import java.util.Random;

public class BoardFactory {
    Input input = new Input();

    public void randomPlacement(Player player, int shipSize) { //Randomizing direction and coordinates until getting a valid placement.
        String[] directions = {"h", "v"}; //Then calling setPlacement() to mark the Squares as ships.
        String direction = directions[new Random().nextInt(2)];
        int[] coordinate = {new Random().nextInt(player.board.ocean.length), new Random().nextInt(player.board.ocean.length)};
        while (!validPlace(player, shipSize, coordinate, direction)) {randomPlacement(player, shipSize);}
        setPlacement(coordinate, player, direction, shipSize);
    }

    public void manualPlacement(String direction, int[] coordinate, Player player, int shipSize) {
        while (!validPlace(player, shipSize, coordinate, direction)){
            coordinate = input.convertPlacement(input.getString("Invalid placement. Choose another coordinate: "));
            direction = input.getString("Horizontal or vertical? Please type h or v: ");
            while (!direction.equalsIgnoreCase("h") || !direction.equalsIgnoreCase("v")) {input.getString("Please only type h or v: ");}
        } setPlacement(coordinate, player, direction, shipSize);
    }

    private boolean validPlace(Player player, int shipSize, int[] coordinate, String direction) { //Checks if chosen squares are empty, returns false if not, or out of bounds.
        try {
            for (int i=0;i<shipSize;i++) {
                if (direction.equals("h")) {
                    if (!player.board.ocean[coordinate[0]][coordinate[1] + i].toString().equals("EMPTY")) return false;
                } else {
                    if (!player.board.ocean[coordinate[0] + i][coordinate[1]].toString().equals("EMPTY")) return false;
                }}}
        catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public void setPlacement(int[] coordinate, Player player, String direction, int shipSize) { //Marks chosen Squares as SHIP.
        for (int i=0;i<shipSize;i++) {
            if (direction.equals("h")) {
                player.board.ocean[coordinate[0]][coordinate[1] + i].setShip();
            } else {
                player.board.ocean[coordinate[0]+i][coordinate[1]].setShip();
            }
        }
    }
}