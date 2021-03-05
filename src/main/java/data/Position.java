package data;

public class Position {
    private int x, y;
    private char symbol;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Position(Position pos, char symb) {
        this.x = pos.x;
        this.y = pos.y;
        this.symbol = symb;
    }
    public int getPositionX() {
        return x;
    }
    public int getPositionY() {
        return y;
    }
    public int getSymbol() {
        return symbol;
    }
}

