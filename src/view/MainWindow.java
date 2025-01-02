package view;

import javax.swing.*;

import max.Battlefield;
import max.MyTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JTable jTable;//отображает данные, но не хранит
    private MyTableModel myTableModel;
    private JButton buttonDelete;
    public MainWindow(){
        super("Наши танки");
        myTableModel = new MyTableModel(new Battlefield());
        jTable = new JTable();
        jTable.setModel(myTableModel);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        buttonDelete = new JButton("Удалить танк");
        buttonDelete.setSize(30, 30);
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    myTableModel.deleteTank(jTable.getSelectedRow());
                }catch(IndexOutOfBoundsException ex){
                    JDialog jDialog = new JDialog(MainWindow.this,"Выделите танк для удаления.", true);
                    jDialog.setSize(300, 100);
                    jDialog.setLocationRelativeTo(MainWindow.this);
                    jDialog.setVisible(true);//выводим окно на экран
                }
            }
        });
        this.add(buttonDelete, BorderLayout.SOUTH);//расположение кнопки внизу
        this.add(jScrollPane);//крутить таблицу
        this.pack();
        this.setLocationRelativeTo(null);//расположение посередине
        this.setVisible(true);//чтобы выводилось
    }
}
