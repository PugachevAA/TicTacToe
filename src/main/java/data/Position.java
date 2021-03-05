package data;

public class Position {
    public int x, y;
    public char symbol;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Position(Position pos, char symb) {
        this.x = pos.x;
        this.y = pos.y;
        this.symbol = symb;
    }

}
