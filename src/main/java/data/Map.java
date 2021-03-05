package data;

public class Map {

    public static char[][] map;
    private static int size = Config.SIZE;

    public static void initMap() {
        map = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = Config.DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void move(int x, int y, char dot) {
        map[y][x] = dot;
    }

    public static boolean mapIsFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == Config.DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= Config.SIZE || y < 0 || y >= Config.SIZE
                || Map.map[y][x] != Config.DOT_EMPTY) {
            return false;
        } else {
            return true;
        }
    }
}

