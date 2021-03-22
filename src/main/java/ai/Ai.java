package ai;

import java.util.ArrayList;
import java.util.Random;

import data.*;
import domain.MatrixCoordinate;
import enums.DotType;


public class Ai {
    private static ArrayList<Comb> preWinCombList = CombLists.getPreWinCombsList();
    private static ArrayList<Position> movesToClose;

    private static final Random random = new Random();

    public Ai() {
        movesToClose = new ArrayList<>();
    }

    private static ArrayList<Position> checkMovesToClose(DotType symb) {
        ArrayList<Position> potencialMoves = new ArrayList<>();
        int x, y;
        int potX = -1, potY = -1;
        int count = 0;
        for (int i = 0; i < preWinCombList.size(); i++) {
            for (int j = 0; j < preWinCombList.get(i).getComb().size(); j++) {
                x = preWinCombList.get(i).getComb().get(j).getPositionX();
                y = preWinCombList.get(i).getComb().get(j).getPositionY();
                if (Map.getSymbol(x,y) == symb) {
                    count++;
                }
                if (preWinCombList.get(i).getComb().get(j).getSymbol() == DotType.O &&
                    Map.getSymbol(x,y) == DotType.EMPTY) {
                    potX = preWinCombList.get(i).getComb().get(j).getPositionX();
                    potY = preWinCombList.get(i).getComb().get(j).getPositionY();
                }
            }
            if (count == preWinCombList.get(i).getComb().size() - 1 && potX != -1) {
                potencialMoves.add(new Position(potX, potY));
            }
            count = 0;
            potX = -1;
            potY = -1;
        }
        return potencialMoves;
    }

    public static MatrixCoordinate computerTurn() {
        int x, y;
        movesToClose =  checkMovesToClose(DotType.X);
        if (movesToClose.size() > 0) {
            int n = random.nextInt(movesToClose.size());
            x = movesToClose.get(n).getPositionX();
            y = movesToClose.get(n).getPositionY();
        } else {
            do {
                x = random.nextInt(Config.SIZE);
                y = random.nextInt(Config.SIZE);
            } while (!Map.isCellValid(x, y));
        }
        Map.move(x, y, DotType.O);
        System.out.println("Компьютер походил в точку: " + (x + 1) + " " + (y + 1));
        return new MatrixCoordinate(y, x);
    }
}

