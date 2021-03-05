package data;

import java.util.ArrayList;

//Класс списков выигрышных и предвыигрышных комбинаций
public class CombLists {
    public ArrayList<Comb> winCombsList;
    public ArrayList<Comb> preWinCombsList;

    //Конструктор использованием вложенных методов инициализации каждого списка
    public CombLists() {
        winCombsList = new ArrayList<>();
        preWinCombsList = new ArrayList<>();
        initWinCombs();
        initPreWinCombs();
    }

    //заполняем список всеми возможными выигрышными комбинациями,
    //пробегаясь по всем горизонталям, вертикалям и диагоналям
    //(по побочным без учета диагоналей короче длины выигрышной комбинации)
    public void initWinCombs() {
        Comb winCombo;
        int winLen = Config.DOTS_TO_WIN;
        int len = Config.SIZE-1;
        int size = Config.SIZE;
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
        //Побочные диагонали
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
    public void initPreWinCombs() {
        Comb winCombo;
        for (int i = 0; i < winCombsList.size(); i++) {
            for (int j = 0; j < winCombsList.get(i).combination.size(); j++) {
                winCombo = new Comb((ArrayList) winCombsList.get(i).combination.clone());
                winCombo.combination.set(j, new Position(winCombo.combination.get(j), Config.DOT_AI));
                preWinCombsList.add(winCombo);
            }
        }
    }




    //Методы для вывода в консоль выигрышных и предвыигрышных комбинаций (для тестов)
    public void printWinCombs() {
        System.out.println("Выигрышные");
        for (int i = 0; i < winCombsList.size(); i++){
            for (int j = 0; j < winCombsList.get(i).combination.size(); j ++) {
                System.out.print(winCombsList.get(i).combination.get(j).x + "" + winCombsList.get(i).combination.get(j).y + " ");
            }
            System.out.println("");
        }
    }
    public void printPreWinCombs() {
        System.out.println("Предыигрышные");
        for (int i = 0; i < winCombsList.size(); i++){
            for (int j = 0; j < winCombsList.get(i).combination.size(); j ++) {
                System.out.print(winCombsList.get(i).combination.get(j).x + "" + winCombsList.get(i).combination.get(j).y + " ");
            }
            System.out.println("");
        }
    }

}
