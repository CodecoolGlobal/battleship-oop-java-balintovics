package board;

import java.util.Arrays;

public class Board {
    private final Square[][] ocean;
    private final int BOARD_SIZE = 10;

    public Board() {
        this.ocean = new Square[BOARD_SIZE][BOARD_SIZE];
        for (int i=0;i<BOARD_SIZE;i++) {
            for (int j=0;j<BOARD_SIZE;j++) {
                ocean[i][j] = new Square(i, j);
            }
        }
    }

    public boolean isPlacementOk(int[] placement) {
        int positionX = placement[0];
        int positionY = placement[1];
        return positionX < this.BOARD_SIZE && positionY < this.BOARD_SIZE && // Is it on board?
        this.ocean[positionX][positionY].getStatus().equals("EMPTY"); // DO NOT put it on other ships
    }

    public String print() {
        return Arrays.deepToString(this.ocean);
    }
}
