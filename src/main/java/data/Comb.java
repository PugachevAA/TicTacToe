package data;

import java.util.ArrayList;

public class Comb {
    private ArrayList<Position> combination;

    public Comb() {
        combination = new ArrayList<>();
    }
    public Comb(ArrayList comb) {
        combination = comb;
    }
    public void addPosition(Position pos) {
        combination.add(pos);
    }
    public ArrayList<Position> getComb() {
        return combination;
    }

}

