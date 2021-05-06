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
        display.shout(String.format("Time to place your %s (size: %s)" , ship.getShipName(shipSize), shipSize));
        String[] directions = {"h", "v"}; //Then calling setPlacement() to mark the Squares as ships.
        String direction = directions[new Random().nextInt(2)];
        int[] coordinate = {new Random().nextInt(player.board.ocean.length), new Random().nextInt(player.board.ocean.length)};
        while (!validPlace(player, shipSize, coordinate, direction)) {
            coordinate = new int[]{new Random().nextInt(player.board.ocean.length), new Random().nextInt(player.board.ocean.length)};
        }
        setPlacement(coordinate, player, direction, shipSize);
    }

    public void manualPlacement(Player player, int shipSize) {
        display.shout(String.format("Time to place your %s (size: %s)" , ship.getShipName(shipSize), shipSize));
        String direction = isInputHorizontalOrVertical();
        String coordinate = input.getString("Choose a coordinate: ");
        int[] placement = input.convertPlacement(coordinate);
        while (!validPlace(player, shipSize, placement, direction)){
            placement = input.convertPlacement(input.getString("Invalid placement. Choose another coordinate: "));
        } setPlacement(placement, player, direction, shipSize);
    }

    private boolean validPlace(Player player, int shipSize, int[] coordinate, String direction) { //Checks if chosen squares are empty, returns false if not, or out of bounds.
        int[] targetCoordinate;
        try {
            for (int i=0;i<shipSize;i++) {
                if (direction.equals("h")) {
                    targetCoordinate = new int[]{player.board.ocean[coordinate[0]][coordinate[1] + i].getX(),player.board.ocean[coordinate[0]][coordinate[1] + i].getY()};
                } else {
                    targetCoordinate = new int[]{player.board.ocean[coordinate[0] + i][coordinate[1]].getX(),player.board.ocean[coordinate[0] + i][coordinate[1]].getY()};
                }
                if (!player.board.isPlacementOk(targetCoordinate) || !checkNeighbours(targetCoordinate, direction, player)) return false;
            } if (!checkEdgeNeighbours(coordinate, direction, player, shipSize)) return false;
        }
        catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public boolean checkEdgeNeighbours(int[] coordinate, String direction, Player player, int shipSize) {
        if (direction.equals("h")) {
            int[] leftNeighbour = new int[]{coordinate[0], coordinate[1] - 1};
            int[] rightNeighbour = new int[]{coordinate[0], coordinate[1] + shipSize};
            if (leftNeighbour[1] < 0) return player.board.isPlacementOk(rightNeighbour);
            else if (rightNeighbour[1] > player.board.BOARD_SIZE) return player.board.isPlacementOk(leftNeighbour);
            else return player.board.isPlacementOk(leftNeighbour) && player.board.isPlacementOk(rightNeighbour);
        } else {
            int[] topNeighbour = new int[]{coordinate[0] - 1, coordinate[1]};
            int[] bottomNeighbour = new int[]{coordinate[0] + shipSize, coordinate[1]};
            if (topNeighbour[0] < 0) return player.board.isPlacementOk(bottomNeighbour);
            else if (bottomNeighbour[0] > player.board.BOARD_SIZE) return player.board.isPlacementOk(topNeighbour);
            else return player.board.isPlacementOk(topNeighbour) && player.board.isPlacementOk(bottomNeighbour);
        }
    }

    public boolean checkNeighbours(int[] coordinate, String direction, Player player) {
        if (direction.equals("h")) {
            int[] topNeighbour = new int[]{coordinate[0] - 1, coordinate[1]};
            int[] bottomNeighbour = new int[]{coordinate[0] + 1, coordinate[1]};
            if (topNeighbour[0] < 0) return player.board.isPlacementOk(bottomNeighbour);
            else if (bottomNeighbour[0] >= player.board.BOARD_SIZE) return player.board.isPlacementOk(topNeighbour);
            else return player.board.isPlacementOk(topNeighbour) && player.board.isPlacementOk(bottomNeighbour);
        }
        else {
            int[] leftNeighbour = new int[]{coordinate[0], coordinate[1] - 1};
            int[] rightNeighbour = new int[]{coordinate[0], coordinate[1] + 1};
            if (leftNeighbour[1] < 0) return player.board.isPlacementOk(rightNeighbour);
            else if (rightNeighbour[1] >= player.board.BOARD_SIZE) return player.board.isPlacementOk(leftNeighbour);
            else return player.board.isPlacementOk(leftNeighbour) && player.board.isPlacementOk(rightNeighbour);
        }
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

    public String isInputHorizontalOrVertical() {
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