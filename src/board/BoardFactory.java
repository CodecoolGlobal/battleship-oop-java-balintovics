package board;

import player.IPlayer;
import player.Player;
import ship.Ship;
import ship.ShipType;
import utilities.*;

import java.util.ArrayList;
import java.util.Random;

public class BoardFactory {
    Input input = new Input();
    Display display = new Display();

    public void randomPlacement(IPlayer player, ShipType shipType) {
        display.shout(String.format("Time to place your %s (size: %s), %s" , shipType, shipType, player.name));
        String[] directions = {"h", "v"};
        String direction = directions[new Random().nextInt(2)];
        int[] coordinate = {new Random().nextInt(player.getBoard().ocean.length), new Random().nextInt(player.getBoard().ocean.length)};
        while (!validPlace(player, shipType.getLength(), coordinate, direction)) {
            coordinate = new int[]{new Random().nextInt(player.getBoard().ocean.length), new Random().nextInt(player.getBoard().ocean.length)};
        }
        setPlacement(coordinate, player, direction, shipType);
    }

    public void manualPlacement(Player player, ShipType shipType) {
        display.shout(String.format("Time to place your %s (size: %s), %s" , shipType, shipType.getLength(), player.name));
        String direction = isInputHorizontalOrVertical();
        String coordinate = input.getString(String.format("Choose a coordinate, %s: ", player.name));
        int[] placement = input.convertPlacement(coordinate);
        while (!validPlace(player, shipType.getLength(), placement, direction)){
            placement = input.convertPlacement(input.getString("Invalid placement. Choose another coordinate: "));
        } setPlacement(placement, player, direction, shipType);
    }

    private boolean validPlace(IPlayer player, int shipSize, int[] coordinate, String direction) { //Checks if chosen squares are empty, returns false if not, or out of bounds.
        int[] targetCoordinate;
        try {
            for (int i=0;i<shipSize;i++) {
                if (direction.equals("h")) {
                    targetCoordinate = new int[]{player.getBoard().ocean[coordinate[0]][coordinate[1] + i].getX(),player.getBoard().ocean[coordinate[0]][coordinate[1] + i].getY()};
                } else {
                    targetCoordinate = new int[]{player.getBoard().ocean[coordinate[0] + i][coordinate[1]].getX(),player.getBoard().ocean[coordinate[0] + i][coordinate[1]].getY()};
                }
                if (!player.getBoard().isPlacementOk(targetCoordinate) || !checkNeighbours(targetCoordinate, direction, player)) return false;
            } if (!checkEdgeNeighbours(coordinate, direction, player, shipSize)) return false;
        }
        catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public boolean checkEdgeNeighbours(int[] coordinate, String direction, IPlayer player, int shipSize) {
        if (direction.equals("h")) {
            int[] leftNeighbour = new int[]{coordinate[0], coordinate[1] - 1};
            int[] rightNeighbour = new int[]{coordinate[0], coordinate[1] + shipSize};
            if (leftNeighbour[1] < 0) return player.getBoard().isPlacementOk(rightNeighbour);
            else if (rightNeighbour[1] > player.getBoard().BOARD_SIZE) return player.getBoard().isPlacementOk(leftNeighbour);
            else return player.getBoard().isPlacementOk(leftNeighbour) && player.getBoard().isPlacementOk(rightNeighbour);
        } else {
            int[] topNeighbour = new int[]{coordinate[0] - 1, coordinate[1]};
            int[] bottomNeighbour = new int[]{coordinate[0] + shipSize, coordinate[1]};
            if (topNeighbour[0] < 0) return player.getBoard().isPlacementOk(bottomNeighbour);
            else if (bottomNeighbour[0] > player.getBoard().BOARD_SIZE) return player.getBoard().isPlacementOk(topNeighbour);
            else return player.getBoard().isPlacementOk(topNeighbour) && player.getBoard().isPlacementOk(bottomNeighbour);
        }
    }

    public boolean checkNeighbours(int[] coordinate, String direction, IPlayer player) {
        if (direction.equals("h")) {
            int[] topNeighbour = new int[]{coordinate[0] - 1, coordinate[1]};
            int[] bottomNeighbour = new int[]{coordinate[0] + 1, coordinate[1]};
            if (topNeighbour[0] < 0) return player.getBoard().isPlacementOk(bottomNeighbour);
            else if (bottomNeighbour[0] >= player.getBoard().BOARD_SIZE) return player.getBoard().isPlacementOk(topNeighbour);
            else return player.getBoard().isPlacementOk(topNeighbour) && player.getBoard().isPlacementOk(bottomNeighbour);
        }
        else {
            int[] leftNeighbour = new int[]{coordinate[0], coordinate[1] - 1};
            int[] rightNeighbour = new int[]{coordinate[0], coordinate[1] + 1};
            if (leftNeighbour[1] < 0) return player.getBoard().isPlacementOk(rightNeighbour);
            else if (rightNeighbour[1] >= player.getBoard().BOARD_SIZE) return player.getBoard().isPlacementOk(leftNeighbour);
            else return player.getBoard().isPlacementOk(leftNeighbour) && player.getBoard().isPlacementOk(rightNeighbour);
        }
    }

    public void setPlacement(int[] coordinate, IPlayer player, String direction, ShipType shipType) { //Marks chosen Squares as SHIP.
        ArrayList<Square> squares = new ArrayList<>();
        for (int i=0;i<shipType.getLength();i++) {
            if (direction.equals("h")) {
                player.getBoard().ocean[coordinate[0]][coordinate[1] + i].setShip();
                squares.add(player.getBoard().ocean[coordinate[0]][coordinate[1] + i]);
            } else {
                player.getBoard().ocean[coordinate[0] + i][coordinate[1]].setShip();
                squares.add(player.getBoard().ocean[coordinate[0] + i][coordinate[1]]);
            }
        }
        Ship ship = new Ship(squares, shipType);
        player.addShip(ship);
    }

    public String isInputHorizontalOrVertical() {
        boolean isOk = false;
        String string;
        do {
            string = input.getString("Horizontal or vertical? ");
            String letter = string.toLowerCase();
            switch (letter) {
                case "h":
                case "v":
                    isOk = true;
                    break;
                default: string = input.getString("Only h or v please!");
            }
        } while (!isOk);
        return string;
    }
}