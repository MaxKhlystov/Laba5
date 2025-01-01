package view;

import javax.swing.*;

import max.Battlefield;
import max.MyTableModel;

public class MainWindow extends JFrame {
    private JTable jTable;//отображает данные, но не хранит
    public MainWindow(){
        super("Наши танки");

        jTable = new JTable();
        jTable.setModel(new MyTableModel(new Battlefield()));
        this.add(jTable);
        this.pack();
        this.setLocationRelativeTo(null);//расположение посередине
        this.setVisible(true);//чтобы выводилось
    }
}
