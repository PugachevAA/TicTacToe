package data;

import java.util.Scanner;

public class Config {
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final char DOT_EMPTY = '*';
    public static final char DOT_AI = 'b';

    public static int SIZE;
    public static int DOTS_TO_WIN;


    private static Scanner scanner = new Scanner(System.in);

    public static void initSettings() {
        System.out.print("Задайте размер игрового поля: ");
        Config.SIZE = scanner.nextInt();
        do {
            System.out.print("Задайте размер выйгрышной комбинации (от 2 до " + Config.SIZE + "): ");
            Config.DOTS_TO_WIN = scanner.nextInt();
        } while (Config.DOTS_TO_WIN > Config.SIZE && Config.DOTS_TO_WIN < 2);
    }
}
