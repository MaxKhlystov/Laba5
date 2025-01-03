package max;

import javax.swing.*;

public class WindowAbility extends JFrame {
    private JTable jTable;//отображает данные, но не хранит
    private MyTableModel myTableModel;
    public WindowAbility(){
        super("Результаты использования возможностей");
        myTableModel = new MyTableModel(new Battlefield());
        jTable = new JTable();
        jTable.setModel(myTableModel);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        this.add(jScrollPane);//крутить таблицу
        //this.pack();
        this.setLocationRelativeTo(null);//расположение посередине
        this.setVisible(true);
    }
}
