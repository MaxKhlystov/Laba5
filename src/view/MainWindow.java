package view;

import javax.swing.*;

import max.Battlefield;
import max.MyTableModel;
import max.WindowAbility;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JTable jTable;//отображает данные, но не хранит
    private MyTableModel myTableModel;
    private JButton buttonDelete;
    private JButton buttonYes;
    private JButton buttonNo;
    private JButton buttonAbility;
    public MainWindow(){
        super("Наши танки");
        myTableModel = new MyTableModel(new Battlefield());
        jTable = new JTable();
        jTable.setModel(myTableModel);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        JPanel buttonPanelMain = new JPanel();
        buttonAbility = new JButton("Возможности танков");
        buttonAbility.setSize(30, 30);
        buttonAbility.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WindowAbility();
                //fireTableDataChanged();
            }
        });

        buttonDelete = new JButton("Удалить танк");
        buttonDelete.setSize(30, 30);
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    JDialog jDialog = new JDialog(MainWindow.this,"Окно удаления", true);
                    jDialog.setSize(400, 200);
                    jDialog.setLocationRelativeTo(MainWindow.this);
                    JPanel messagePanelDelete = new JPanel();
                    messagePanelDelete.add(new JLabel("Вы уверены, что хотите удалить выбранный танк?"));
                    jDialog.add(messagePanelDelete, BorderLayout.CENTER);
                    JPanel buttonPanelDelete = new JPanel();
                    buttonYes = new JButton("Да");
                    buttonNo = new JButton("Нет");
                    buttonYes.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                myTableModel.deleteTank(jTable.getSelectedRow());
                                jDialog.dispose();
                            }catch(ArrayIndexOutOfBoundsException ex){
                            JDialog errorDialog = new JDialog(MainWindow.this, "Ошибка", true);
                            errorDialog.setSize(300, 100);
                            errorDialog.setLocationRelativeTo(MainWindow.this);

                            // Добавляем сообщение об ошибке
                            JPanel messagePanelError = new JPanel();
                            messagePanelError.add(new JLabel("Выделите танк для удаления"));
                            errorDialog.add(messagePanelError);

                            // Добавляем кнопку OK
                            JPanel buttonPanelError = new JPanel();
                            JButton buttonOk = new JButton("OK");
                            buttonOk.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    errorDialog.dispose();//закрываем окно ошибки
                                    jDialog.dispose();//закрываем модальное окно
                                }
                            });
                            buttonPanelError.add(buttonOk);
                            errorDialog.add(buttonPanelError, BorderLayout.SOUTH);
                            errorDialog.setVisible(true);
                        }
                        }
                    });
                    buttonNo.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jDialog.dispose();
                        }
                    });
                    buttonPanelDelete.add(buttonYes);
                    buttonPanelDelete.add(buttonNo);
                    jDialog.add(buttonPanelDelete, BorderLayout.SOUTH);
                    jDialog.setVisible(true);//выводим окно на экран
            }
        });
        buttonPanelMain.add(buttonDelete);
        buttonPanelMain.add(buttonAbility);
        this.add(buttonPanelMain, BorderLayout.SOUTH);//расположение кнопки внизу
        this.add(jScrollPane);//крутить таблицу
        this.pack();
        this.setLocationRelativeTo(null);//расположение посередине
        this.setVisible(true);//чтобы выводилось
    }
}
