package ai;

import java.util.ArrayList;
import java.util.Random;

import data.*;

public class Ai {
    static ArrayList<Comb> preWinCombList = CombLists.getPreWinCombsList();

    static ArrayList<Position> movesToClose;
    private static final Random random = new Random();

    public Ai() {
        movesToClose = new ArrayList<>();
    }

    public static ArrayList<Position> checkMovesToClose(char symb) {
        ArrayList<Position> potencialMoves = new ArrayList<>();
        int x, y;
        int potX = -1, potY = -1;
        int count = 0;
        for (int i = 0; i < preWinCombList.size(); i++) {
            for (int j = 0; j < preWinCombList.get(i).combination.size(); j++) {
                x = preWinCombList.get(i).combination.get(j).x;
                y = preWinCombList.get(i).combination.get(j).y;
                if (Map.map[y][x] == symb) {
                    count++;
                }
                if (preWinCombList.get(i).combination.get(j).symbol == Config.DOT_AI &&
                    Map.map[y][x] == Config.DOT_EMPTY) {
                    potX = preWinCombList.get(i).combination.get(j).x;
                    potY = preWinCombList.get(i).combination.get(j).y;
                }
            }
            if (count == preWinCombList.get(i).combination.size() - 1 && potX != -1) {
                potencialMoves.add(new Position(potX, potY));
            }
            count = 0;
            potX = -1;
            potY = -1;
        }
        return potencialMoves;
    }

    public static void computerTurn() {
        int x, y;
        movesToClose =  checkMovesToClose(Config.DOT_X);
        if (movesToClose.size() > 0) {
            int n = random.nextInt(movesToClose.size());
            x = movesToClose.get(n).x;
            y = movesToClose.get(n).y;
        } else {
            do {
                x = random.nextInt(Config.SIZE);
                y = random.nextInt(Config.SIZE);
            } while (!Map.isCellValid(x, y));
            Map.move(x, y, Config.DOT_O);
            System.out.println("Компьютер походил в точку: " + (x + 1) + " " + (y + 1));
        }
        Map.move(x, y, Config.DOT_O);
        System.out.println("Компьютер походил в точку: " + (x + 1) + " " + (y + 1));
    }

}

