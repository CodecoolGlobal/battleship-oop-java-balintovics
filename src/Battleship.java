import board.Board;
import board.Square;

import java.util.Arrays;

public class Battleship {
    public static void main(String[] args) {
        Square cica = new Square(1, 1);
        System.out.println(cica.getStatus());
        System.out.println();
        Board kutya = new Board();
        System.out.println(kutya.print());
    }
}
