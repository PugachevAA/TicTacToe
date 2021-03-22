package data;


import enums.DotType;

import java.util.ArrayList;

//Класс списков выигрышных и предвыигрышных комбинаций
public class CombLists {
    private static ArrayList<Comb> winCombsList;
    private static ArrayList<Comb> preWinCombsList;

    //Конструктор использованием вложенных методов инициализации каждого списка
    public CombLists(int gridSize, int lenForWin) {
        winCombsList = new ArrayList<>();
        preWinCombsList = new ArrayList<>();
        initWinCombs(gridSize, lenForWin);
        initPreWinCombs();
    }
    public static ArrayList<Comb> getWinCombsList() {
        return winCombsList;
    }
    public static ArrayList<Comb> getPreWinCombsList() {
        return preWinCombsList;
    }

    //заполняем список всеми возможными выигрышными комбинациями,
    //пробегаясь по всем горизонталям, вертикалям и диагоналям
    //(по побочным без учета диагоналей короче длины выигрышной комбинации)
    private void initWinCombs(int gridSize, int lenForWin) {
        Comb winCombo;
        //int winLen = Config.DOTS_TO_WIN;
        int winLen = lenForWin;
        int len = gridSize-1;
        int size = gridSize;
        //Горизонтальные и вертикальные комбинации
        for (int x = 0; x <= len; x++) {
            for (int y = 0; y <= size - winLen; y++) {
                winCombo = new Comb();
                for (int shift = 0; shift < winLen; shift++) {
                    winCombo.addPosition(new Position(y + shift, x));
                }
                winCombsList.add(winCombo);
            }
        }
        //Вертикальные комбинации
        for (int y = 0; y <= len; y++) {
            for (int x = 0; x <= size - winLen; x++) {

                winCombo = new Comb();
                for (int shift = 0; shift < winLen; shift++) {
                    winCombo.addPosition(new Position(y, x + shift));
                }
                winCombsList.add(winCombo);
            }
        }
        //Главные диагонали
        for (int x = 0; x <= size - winLen; x++) {
            winCombo = new Comb();
            for (int shift = 0; shift < winLen; shift++) {
                winCombo.addPosition(new Position(x + shift, x + shift));
            }
            winCombsList.add(winCombo);
            winCombo = new Comb();
            for (int shift = 0; shift < winLen; shift++) {
                winCombo.addPosition(new Position(x + shift, len - x - shift));
            }
            winCombsList.add(winCombo);
        }
        //Побочные диагонали исключая те, которые короче длины выигрышной комбинации
        for (int x = 1; x <= size - winLen; x++) {
            for (int y = 0; y <= size - winLen - x; y++) {
                winCombo = new Comb();
                for (int shift = 0; shift < winLen; shift++) {
                    winCombo.addPosition(new Position(x + y + shift, y + shift));
                }
                winCombsList.add(winCombo);

                winCombo = new Comb();
                for (int shift = 0; shift < winLen; shift++) {
                    winCombo.addPosition(new Position(y + shift, y + x + shift));
                }
                winCombsList.add(winCombo);

                winCombo = new Comb();
                for (int shift = 0; shift < winLen; shift++) {
                    winCombo.addPosition(new Position(y + shift, len - x - y - shift));
                }
                winCombsList.add(winCombo);

                winCombo = new Comb();
                for (int shift = 0; shift < winLen; shift++) {
                    winCombo.addPosition(new Position(x + y + shift, len - y - shift));
                }
                winCombsList.add(winCombo);
            }
        }

    }

    //на основе списка выигрышных комбинаций составляем список всех предвыигрышных комбинаций
    //поочередно убирая из каждой выигрышной комбинации по одной из позиций по очереди
    //и добавляя в новый список полученную комбинацию
    private void initPreWinCombs() {
        Comb winCombo;
        for (int i = 0; i < winCombsList.size(); i++) {
            for (int j = 0; j < winCombsList.get(i).getComb().size(); j++) {
                winCombo = new Comb((ArrayList) winCombsList.get(i).getComb().clone());
                winCombo.getComb().set(j, new Position(winCombo.getComb().get(j), DotType.getEnemyType(DotType.X)));
                preWinCombsList.add(winCombo);
            }
        }
    }
    //Проверяем все возможные комбинации на поле
    //Если по одной из комбинаций на поле проверяемый символ - возвращаяем победу
    public boolean checkWin(DotType symb) {
        int x, y;
        int winCount = 0;
        for (int i = 0; i < winCombsList.size(); i++) {
            for (int j = 0; j < winCombsList.get(i).getComb().size(); j++){
                x = winCombsList.get(i).getComb().get(j).getPositionX();
                y = winCombsList.get(i).getComb().get(j).getPositionY();
                if (Map.getSymbol(x,y) == symb) winCount++;
            }
            if (winCount == Config.DOTS_TO_WIN) return true;
            winCount = 0;
        }
        return false;
    }




    //Методы для вывода в консоль выигрышных и предвыигрышных комбинаций (для тестов)
    public void printWinCombs() {
        System.out.println("Выигрышные");
        for (int i = 0; i < winCombsList.size(); i++){
            for (int j = 0; j < winCombsList.get(i).getComb().size(); j ++) {
                System.out.print(winCombsList.get(i).getComb().get(j).getPositionX() + "" + winCombsList.get(i).getComb().get(j).getPositionY() + " ");
            }
            System.out.println("");
        }
    }
    public void printPreWinCombs() {
        System.out.println("Предыигрышные");
        for (int i = 0; i < winCombsList.size(); i++){
            for (int j = 0; j < winCombsList.get(i).getComb().size(); j ++) {
                System.out.print(winCombsList.get(i).getComb().get(j).getPositionX() + "" + winCombsList.get(i).getComb().get(j).getPositionY() + " ");
            }
            System.out.println("");
        }
    }
}

