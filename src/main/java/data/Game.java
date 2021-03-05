package data;

import ai.Ai;

import java.util.Scanner;

public class Game {
    private static CombLists combLists;

    private static Scanner scanner = new Scanner(System.in);

    //Поиграем?))
    public static void letsGo() {
        combLists = new CombLists();
        while (true) {
            humanTurn();
            Map.printMap();
            if (combLists.checkWin(Config.DOT_X)) {
                System.out.println("Хомосапиенс победил ;)");
                break;
            }
            if (Map.mapIsFull()) {
                System.out.println("Ничья :/");
                break;
            }
            Ai.computerTurn();
            Map.printMap();
            if (combLists.checkWin(Config.DOT_O)) {
                System.out.println("Компуктер победил ;(");
                break;
            }
            if (Map.mapIsFull()) {
                System.out.println("Ничья :/");
                break;
            }
        }
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Ваш ход (в формате X Y):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!Map.isCellValid(x, y));
        Map.move(x, y, Config.DOT_X);
    }



}

