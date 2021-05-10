package board;

public class Square {
    private enum SquareStatus {
        EMPTY("☁ "),
        SHIP("🛩 "),
        HIT("🔥"),
        MISS("💩"),
        SUNK("💀");

        String squareCharatcter;

        SquareStatus(String squareCharatcter) {
            this.squareCharatcter = squareCharatcter;
        }

        public String getCharacter() {
            return squareCharatcter;
        }
    }
    private boolean isShip, isHit, isHidden, isSunk;
    private final int x, y;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.isShip = false;
        this.isHit = false;
        this.isHidden = false;
        this.isSunk = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setShip() {
        this.isShip = true;
    }

    public void setHit() {
        this.isHit = true;
    }

    public void setSunk() {
        this.isSunk = true;
    }

    public void setVisibility(boolean state) {
        this.isHidden = state;
    }

    public boolean isShip() {
        return isShip;
    }

    public boolean isHit() {
        return isHit;
    }

    public boolean isSunk() {
        return isSunk;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public String toString() {
        if (isHidden()) return SquareStatus.EMPTY.getCharacter();
        if (isShip() && isHit() && isSunk()) return SquareStatus.SUNK.getCharacter();
        else if (isShip() && isHit()) return SquareStatus.HIT.getCharacter();
        else if (isShip()) return SquareStatus.SHIP.getCharacter();
        else if (isHit()) return SquareStatus.MISS.getCharacter();
        else return SquareStatus.EMPTY.getCharacter();
    }
}