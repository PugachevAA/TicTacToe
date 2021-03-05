import static data.Config.initSettings;
import static data.Map.*;

public class Main {
    public static void main(String[] args) {
        initSettings();
        initMap();
        printMap();
        Game.letsGo();
    }

}

