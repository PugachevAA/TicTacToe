package gui;


import ai.Ai;
import data.*;
import domain.MatrixCoordinate;
import enums.DotType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private GameService gameService;
    private MapService mapService;
    private JButton[][] buttons;
    private static CombLists combLists;
    StatusBar statusBar = new StatusBar();

    public MainWindow() {
        combLists = new CombLists(Config.SIZE, Config.DOTS_TO_WIN);
        setTitle("Крестики нолики на максималках :)");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);

        statusBar.setMessage("Ожидание хода игрока");

        mapService = new Map(Config.SIZE);
        gameService = new Game();

        JPanel gridPanel = createGridButtons(Config.SIZE);

        setLayout(new BorderLayout());
        add(gridPanel);
        add(statusBar, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createGridButtons(int mapSize) {
        JPanel gridPanel = new JPanel(new GridLayout(mapSize, mapSize));

        buttons = new JButton[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                JButton btn = new JButton();
                Font font = new Font(btn.getFont().getName(),
                        btn.getFont().getStyle(),
                        32);
                btn.setFont(font);
                btn.putClientProperty("INDEX_ROW", i);
                btn.putClientProperty("INDEX_COLUMN", j);

                btn.addActionListener(getButtonListener());

                buttons[i][j] = btn;
                gridPanel.add(btn);
            }
        }
        return gridPanel;
    }

    private ActionListener getButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doHumanTurn((JButton) e.getSource());
                if (mapService.mapIsFull()) {
                    statusBar.setMessage("Ничья");
                    disableAllButtons();
                } else {
                    if (combLists.checkWin(Config.DOT_HUMAN)) {
                        statusBar.setMessage("Вы победили");
                        disableAllButtons();
                    } else {
                        doAiTurn();
                        if (combLists.checkWin(Config.DOT_COMP)) {
                            System.out.println("Компьютер победил");
                            disableAllButtons();
                        }
                    }
                }
            }
        };
    }

    private void doAiTurn() {
        MatrixCoordinate matrixCoordinate = Ai.computerTurn();
        JButton aiSelectedButton = buttons[matrixCoordinate.getRowIndex()][matrixCoordinate.getColumnIndex()];
        disableButtonWithMark(aiSelectedButton, Config.DOT_COMP);
        statusBar.setMessage("Компьютер походил в точку: " + (matrixCoordinate.getColumnIndex()+1) + " " + (matrixCoordinate.getRowIndex()+1));
    }

    private void doHumanTurn(JButton sourceBtn) {
        int indexRow = (int) sourceBtn.getClientProperty("INDEX_ROW");
        int indexColumn = (int) sourceBtn.getClientProperty("INDEX_COLUMN");

        gameService.humanTurn(indexRow, indexColumn);
        disableButtonWithMark(sourceBtn, Config.DOT_HUMAN);
        statusBar.setMessage("Вы походили в точку: " + (indexColumn+1) + " " + (indexRow+1));

    }

    private void disableButtonWithMark(JButton button, DotType dotType) {
        button.setEnabled(false);
        button.setText(dotType.toString());
    }

    private void disableAllButtons() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

}
