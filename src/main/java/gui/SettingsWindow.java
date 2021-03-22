package gui;


import data.Config;
import enums.DotType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {
    private static final int ROWS = 4;
    private static final int COLS = 2;
    public Config settings;

    public SettingsWindow() {
        setTitle("Крестики нолики на максималках :)");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 200);

        JPanel panel = new JPanel(new GridLayout(ROWS, COLS));
        JPanel playerTypePanel = new JPanel();
        JLabel playerTypeStr = new JLabel("Выберите свой символ: ");

        JPanel radioContainer = new JPanel();

        ButtonGroup playerTypeBtnGroup = new ButtonGroup();
        JRadioButton playerTypeRadioX = new JRadioButton("X", true);
        playerTypeRadioX.putClientProperty("Type", DotType.X);
        JRadioButton playerTypeRadioO = new JRadioButton("O", false);
        playerTypeRadioO.putClientProperty("Type", DotType.O);
        playerTypeBtnGroup.add(playerTypeRadioX);
        playerTypeBtnGroup.add(playerTypeRadioO);

        radioContainer.add(playerTypeRadioX);
        radioContainer.add(playerTypeRadioO);

        playerTypePanel.add(playerTypeStr);
        playerTypePanel.add(radioContainer);


        JLabel mapSizeStr = new JLabel("Размер поля: ");
        JTextField mapSizeInput = new JTextField();

        JLabel winLenStr = new JLabel("Длина для победы: ");
        JTextField winLenInput = new JTextField();

        JButton btnOk = new JButton("OK");
        JButton btnExit = new JButton("Выход");


        add(panel);
        panel.add(playerTypeStr);
        panel.add(radioContainer);
        panel.add(mapSizeStr);
        panel.add(mapSizeInput);
        panel.add(winLenStr);
        panel.add(winLenInput);
        panel.add(btnOk);
        panel.add(btnExit);
        setVisible(true);

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DotType dotPlayer;
                int mapSize = Integer.parseInt(mapSizeInput.getText());
                int winLen = Integer.parseInt(winLenInput.getText());

                if (playerTypeRadioX.isSelected()) {
                    dotPlayer = DotType.X;
                } else {
                    dotPlayer = DotType.O;
                }
                settings = new Config(mapSize, winLen, dotPlayer);
                setVisible(false);
                MainWindow mainWindow = new MainWindow();
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
