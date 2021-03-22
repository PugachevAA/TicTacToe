package data;


import enums.DotType;

import static enums.DotType.EMPTY;

public class Map implements MapService{

    private static DotType[][] map;
    private static int size;

    public Map (int size) {
        initMap(size);
        printMap();
    }

    @Override
    public void initMap(int mapSize) {
        size = mapSize;
        map = new DotType[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = EMPTY;
            }
        }
    }
    public static DotType getSymbol(int x, int y) {
        return map[y][x];
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

    public static void move(int x, int y, DotType dot) {
        map[y][x] = dot;
    }

    public boolean mapIsFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == EMPTY) return false;
            }
        }
        return true;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size
                || Map.map[y][x] != EMPTY) {
            return false;
        } else {
            return true;
        }
    }

}


