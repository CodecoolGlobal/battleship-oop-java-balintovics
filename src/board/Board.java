package board;

public class Board {

    public final int BOARD_SIZE = 10;
    public final Square[][] ocean = new Square[BOARD_SIZE][BOARD_SIZE];

    public Board() {
        for (int i=0;i<BOARD_SIZE;i++) {
            for (int j=0;j<BOARD_SIZE;j++) {
                this.ocean[i][j] = new Square(i, j);
            }
        }
    }

    public void setBoardVisibility(boolean status) {
        for (Square[] row: this.ocean) {
            for (Square col: row) {
                col.setVisibility(status);
            }
        }
    }

    public boolean isPlacementOk(int[] placement) {
        int positionX = placement[0];
        int positionY = placement[1];
        return 0 <= positionX && positionX < this.BOARD_SIZE &&
               0 <= positionY && positionY < this.BOARD_SIZE && // Is it on board?
               !this.ocean[positionX][positionY].isShip(); // DO NOT put it on other ships
    }

}
