package board;

public class Board {
    private final Square[][] ocean;
    private final int BOARD_SIZE = 10;

    public Board() {
        this.ocean = new Square[BOARD_SIZE][BOARD_SIZE];
    }

    public boolean isItOnBoard(int[] placement) {
        return placement[0] > this.BOARD_SIZE && placement[1] > this.BOARD_SIZE ;
    }
}
