package board;

public class Square {
    private enum SquareStatus {
        EMPTY,
        SHIP,
        HIT,
        MISS;
        public String getCharacter() {
            return this.equals(EMPTY) ? "🌊" : this.equals(SHIP) ? "⛵" :
                    this.equals(HIT) ? "🔥" : "💩";
        };
    }
    private boolean isShip, isHit, isHidden;
    private final int x, y;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.isShip = false;
        this.isHit = false;
        this.isHidden = true;
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

    public void setHidden(boolean state) {
        this.isHidden = state;
    }

    public boolean isShip() {
        return isShip;
    }

    public boolean isHit() {
        return isHit;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public String toString() {
        if (isHidden) return SquareStatus.EMPTY.getCharacter();
        if (isShip() && isHit()) return SquareStatus.HIT.getCharacter();
        else if (isShip()) return SquareStatus.SHIP.getCharacter();
        else if (isHit()) return SquareStatus.MISS.getCharacter();
        else return SquareStatus.EMPTY.getCharacter();
    }
}
