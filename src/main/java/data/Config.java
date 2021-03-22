package data;


import enums.DotType;

public class Config implements ConfigService{
    public static DotType DOT_HUMAN;
    public static DotType DOT_COMP;
    public static final DotType DOT_EMPTY = DotType.EMPTY;
    public static final char DOT_AI = 'b';

    public static int SIZE;
    public static int DOTS_TO_WIN;

    public Config (int mapSize, int winLen, DotType dotHuman) {
        initSettings(mapSize, winLen, dotHuman);
    }

    @Override
    public void initSettings(int mapSize, int winLen, DotType dotHuman) {
        Config.SIZE = mapSize;
        Config.DOTS_TO_WIN = winLen;
        Config.DOT_HUMAN = dotHuman;
        Config.DOT_COMP = DotType.getEnemyType(dotHuman);
    }
}

