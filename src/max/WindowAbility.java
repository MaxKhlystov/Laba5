package max;

import view.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowAbility extends JFrame {
    private JTable jTable;//отображает данные, но не хранит
    private TableModelAbility myTableModelAbility;
    private JButton buttonUseAbility;
    private JButton buttonYes;
    private JButton buttonNo;
    public WindowAbility(){
        super("Результаты использования возможностей");
        myTableModelAbility = new TableModelAbility(new Battlefield());
        jTable = new JTable();
        jTable.setModel(myTableModelAbility);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        JPanel jPanelWindowAbility = new JPanel();
        buttonUseAbility = new JButton("Использовать возможности");
        buttonUseAbility.setSize(30, 30);
        buttonUseAbility.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myTableModelAbility.useAbilityTanks();
            }
        });
        jPanelWindowAbility.add(buttonUseAbility);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                JDialog saveDialog = new JDialog(WindowAbility.this, "Сохранение данных", true);
                saveDialog.setSize(300, 100);
                saveDialog.setLocationRelativeTo(WindowAbility.this);
                JPanel writeData = new JPanel();
                writeData.add(new JLabel("Хотите ли вы сохранить данные?"));
                JPanel buttonWriteData = new JPanel();
                buttonYes = new JButton("Да");
                buttonNo = new JButton("Нет");
                buttonYes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Сохранение");
                    }
                });
                buttonNo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Выход");
                        saveDialog.dispose();
                    }
                });
                buttonWriteData.add(buttonYes);
                buttonWriteData.add(buttonNo);
                saveDialog.add(buttonWriteData, BorderLayout.SOUTH);
                saveDialog.add(writeData);
                saveDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                saveDialog.setVisible(true);
            }
        });
        this.add(jPanelWindowAbility, BorderLayout.SOUTH);
        this.add(jScrollPane);//крутить таблицу
        this.pack();
        this.setLocationRelativeTo(null);//расположение посередине
        this.setVisible(true);
    }
}
