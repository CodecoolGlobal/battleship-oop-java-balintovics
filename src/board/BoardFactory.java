package board;

import player.Player;
import ship.Ship;
import utilities.*;

import java.util.ArrayList;
import java.util.Random;

public class BoardFactory {
    Input input = new Input();
    Display display = new Display();
    Ship ship = new Ship();

    public void randomPlacement(Player player, int shipSize) { //Randomizing direction and coordinates until getting a valid placement.
        display.shout(String.format("Time to place your %s" , ship.getShipName(shipSize)));
        String[] directions = {"h", "v"}; //Then calling setPlacement() to mark the Squares as ships.
        String direction = directions[new Random().nextInt(2)];
        int[] coordinate = {new Random().nextInt(player.board.ocean.length), new Random().nextInt(player.board.ocean.length)};
        while (!validPlace(player, shipSize, coordinate, direction)) {
            coordinate = new int[]{new Random().nextInt(player.board.ocean.length), new Random().nextInt(player.board.ocean.length)};
        }
        setPlacement(coordinate, player, direction, shipSize);
    }

    public void manualPlacement(Player player, int shipSize) {
        String direction = isInputHOrV();
        String coordinate = input.getString("Choose a coordinate: ");
        int[] placement = input.convertPlacement(coordinate);
        while (!validPlace(player, shipSize, placement, direction)){
            placement = input.convertPlacement(input.getString("Invalid placement. Choose another coordinate: "));
        } setPlacement(placement, player, direction, shipSize);
    }

    private boolean validPlace(Player player, int shipSize, int[] coordinate, String direction) { //Checks if chosen squares are empty, returns false if not, or out of bounds.
        try {
            for (int i=0;i<shipSize;i++) {
                if (direction.equals("h")) {
                    if (!player.board.ocean[coordinate[0]][coordinate[1] + i].toString().equals("\uD83C\uDF0A")) {
                        return false;
                    }
                } else if (direction.equals("v")) {
                    if (!player.board.ocean[coordinate[0] + i][coordinate[1]].toString().equals("\uD83C\uDF0A")) {
                        return false;
                    }
                }}}
        catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public void setPlacement(int[] coordinate, Player player, String direction, int shipSize) { //Marks chosen Squares as SHIP.
        ArrayList<Square> squares = new ArrayList<>();
        boolean isHorizontal =  true;
        for (int i=0;i<shipSize;i++) {
            if (direction.equals("h")) {
                player.board.ocean[coordinate[0]][coordinate[1] + i].setShip();
                squares.add(player.board.ocean[coordinate[0]][coordinate[1] + i]);
            } else {
                isHorizontal = false;
                player.board.ocean[coordinate[0] + i][coordinate[1]].setShip();
                squares.add(player.board.ocean[coordinate[0] + i][coordinate[1]]);
            }
        }
        Ship ship = new Ship(isHorizontal, squares);
        player.addShip(ship);

    }

    public String isInputHOrV() {
        boolean isOk = false;
        String string;
        do {
            string = input.getString("Horizontal or vertical? ");
            String letter = string.toLowerCase();
            switch (letter) {
                case "h", "v" -> isOk = true;
                default -> string = input.getString("Only h or v please!");
            }
        } while (!isOk);
        return string;
    }
}