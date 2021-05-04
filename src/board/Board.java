package board;

import java.util.Arrays;

public class Board {

    public final int BOARD_SIZE = 10;
    public final Square[][] ocean = new Square[BOARD_SIZE][BOARD_SIZE];

    public Board() {
        for (int i=0;i<BOARD_SIZE;i++) {
            for (int j=0;j<BOARD_SIZE;j++) {
                this.ocean[i][j] = new Square(i, j);
            }
        }
        this.ocean[0][1].setShip();
        this.ocean[0][0].setShip();
        this.ocean[0][1].setHit();
    }

    public boolean isPlacementOk(int[] placement) {
        int positionX = placement[0];
        int positionY = placement[1];
        return positionX < this.BOARD_SIZE && positionY < this.BOARD_SIZE && // Is it on board?
        this.ocean[positionX][positionY].toString().equals("EMPTY"); // DO NOT put it on other ships
    }
}
