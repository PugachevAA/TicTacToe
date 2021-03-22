package data;

import enums.DotType;

public class Position {
    private int x, y;
    private DotType symbol;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Position(Position pos, DotType symb) {
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
    public DotType getSymbol() {
        return symbol;
    }
}

