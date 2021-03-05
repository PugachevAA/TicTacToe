import ai.Ai;
import data.*;

import java.util.Scanner;

public class Game {
    static CombLists combLists;

    private static Scanner scanner = new Scanner(System.in);

    //Поиграем?))
    public static void letsGo() {
        while (true) {
            combLists = new CombLists();
            humanTurn();
            Map.printMap();
            if (checkWin(Config.DOT_X)) {
                System.out.println("Хомосапиенс победил ;)");
                break;
            }
            if (Map.mapIsFull()) {
                System.out.println("Ничья :/");
                break;
            }
            Ai.computerTurn();
            Map.printMap();
            if (checkWin(Config.DOT_O)) {
                System.out.println("Компуктер победил ;(");
                break;
            }
            if (Map.mapIsFull()) {
                System.out.println("Ничья :/");
                break;
            }
        }
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Ваш ход (в формате X Y):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!Map.isCellValid(x, y));
        Map.move(x, y, Config.DOT_X);
        Ai.lastHumanMove = new Position(x, y);
    }

    //Проверяем все возможные комбинации на поле
    //Если по одной из комбинаций на поле проверяемый символ - возвращаяем победу
    public static boolean checkWin(char symb) {
        int x, y;
        int winCount = 0;
        for (int i = 0; i < combLists.winCombsList.size(); i++) {
            for (int j = 0; j < combLists.winCombsList.get(i).combination.size(); j++){
                x = combLists.winCombsList.get(i).combination.get(j).x;
                y = combLists.winCombsList.get(i).combination.get(j).y;
                if (Map.map[y][x] == symb) winCount++;
            }
            if (winCount == Config.DOTS_TO_WIN) return true;
            winCount = 0;
        }
        return false;
    }

}
