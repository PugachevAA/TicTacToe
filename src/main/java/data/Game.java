package data;


import static enums.DotType.*;

public class Game implements GameService{

    @Override
    public void humanTurn(int rowIndex, int columnIndex) {
        int x, y;
        x = columnIndex;
        y = rowIndex;
        Map.move(x, y, X);
        Map.printMap();
    }

    public Game() {
    }

}

