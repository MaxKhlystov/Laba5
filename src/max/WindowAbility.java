package max;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowAbility extends JFrame {
    private JTable jTable;//отображает данные, но не хранит
    private TableModelAbility myTableModelAbility;
    private JButton buttonUseAbility;
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
                myTableModelAbility.fireTableStructureChanged();
                myTableModelAbility.useAbilityTanks();
            }
        });
        jPanelWindowAbility.add(buttonUseAbility);
        this.add(jPanelWindowAbility, BorderLayout.SOUTH);
        this.add(jScrollPane);//крутить таблицу
        this.pack();
        this.setLocationRelativeTo(null);//расположение посередине
        this.setVisible(true);
    }
}
