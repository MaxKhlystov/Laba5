package view;

import javax.swing.*;

import max.Battlefield;
import max.MyTableModelMain;
import view.WindowAbility;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JTable jTable;//отображает данные, но не хранит
    private MyTableModelMain myTableModelMain;
    private JTextField NameTankField;
    private JTextField HPTankField;
    private JTextField AbilityTankField;
    private JButton buttonDelete;
    private JButton buttonYes;
    private JButton buttonNo;
    private JButton buttonAbility;
    private JButton buttonAd;
    private JButton buttonHeavyTank;
    private JButton buttonLightTank;
    private JButton buttonOk;
    public MainWindow(){
        super("Наши танки");
        myTableModelMain = new MyTableModelMain(new Battlefield());
        jTable = new JTable();
        jTable.setModel(myTableModelMain);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        JPanel buttonPanelMain = new JPanel();
        buttonAd = new JButton("Добавить танк");
        buttonAd.setSize(30,30);
        buttonAd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog jDialog = new JDialog(MainWindow.this,"Выбор танка", true);
                jDialog.setSize(400, 200);
                jDialog.setLocationRelativeTo(MainWindow.this);
                buttonHeavyTank = new JButton("Тяжёлый танк");
                buttonLightTank = new JButton("Лёгкий танк");
                JPanel messageChoice = new JPanel();
                JPanel buttonPanelChoice = new JPanel();
                messageChoice.add(new JLabel("Какой танк вы хотите добавить?"));
                JDialog jDialogAddTank = new JDialog(MainWindow.this,"Добавление танка", true);
                jDialogAddTank.setSize(400,200);
                jDialogAddTank.setLocationRelativeTo(MainWindow.this);
                buttonHeavyTank.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jDialog.dispose();
                        JDialog jDialogAddTank = new JDialog(MainWindow.this,"Добавление танка", true);
                        jDialogAddTank.setSize(500,200);
                        jDialogAddTank.setLocationRelativeTo(MainWindow.this);

                        JPanel jPanelInputNameTank = new JPanel();
                        jPanelInputNameTank.add(new JLabel("Введите название танка: "));
                        NameTankField = new JTextField(20);
                        jPanelInputNameTank.add(NameTankField);

                        JPanel jPanelInputHPTank = new JPanel();
                        jPanelInputHPTank.add(new JLabel("Введите кол-во здоровья танка: "));
                        HPTankField = new JTextField(20);
                        jPanelInputHPTank.add(HPTankField);

                        JPanel jPanelInputAbilityTank = new JPanel();
                        jPanelInputAbilityTank.add(new JLabel("Введите толщину брони танка: "));
                        AbilityTankField = new JTextField(20);
                        jPanelInputAbilityTank.add(AbilityTankField);

                        JPanel generalPanelAdd = new JPanel();
                        generalPanelAdd.setLayout(new BoxLayout(generalPanelAdd, BoxLayout.Y_AXIS));
                        generalPanelAdd.add(jPanelInputNameTank);
                        generalPanelAdd.add(jPanelInputHPTank);
                        generalPanelAdd.add(jPanelInputAbilityTank);

                        buttonOk = new JButton("ОК");
                        buttonOk.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try{
                                    String TankName = NameTankField.getText().trim();
                                    if (TankName.isEmpty()){
                                        JDialog jDialogErrorNullName = new JDialog(MainWindow.this, "Ошибка", true);
                                        jDialogErrorNullName.setSize(400,75);
                                        jDialogErrorNullName.setLocationRelativeTo(MainWindow.this);

                                        JPanel jPanelError = new JPanel();
                                        jPanelError.add(new JLabel("Ошибка: У танка должно быть название."));

                                        JButton buttonErrorOk = new JButton("ОК");
                                        buttonErrorOk.setSize(30,30);

                                        jPanelError.add(buttonErrorOk);
                                        buttonErrorOk.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                jDialogErrorNullName.dispose();
                                            }
                                        });
                                        jDialogErrorNullName.add(jPanelError, BorderLayout.NORTH);
                                        jDialogErrorNullName.setVisible(true);
                                    }else {
                                        String HPTankText = HPTankField.getText().trim();
                                        int HPTankInt = Integer.parseInt(HPTankText);
                                        String AbilityTankText = AbilityTankField.getText().trim();
                                        int armorThickness = Integer.parseInt(AbilityTankText);
                                        myTableModelMain.addHeavyTank(TankName, HPTankInt, armorThickness);
                                        jDialogAddTank.dispose();
                                    }
                                }catch(NumberFormatException ex){
                                    JDialog jDialogError = new JDialog(MainWindow.this, "Ошибка", true);
                                    jDialogError.setSize(400,75);
                                    jDialogError.setLocationRelativeTo(MainWindow.this);

                                    JPanel jPanelError = new JPanel();
                                    jPanelError.add(new JLabel("Ошибка: Вы ввели не число или неверный его формат."));

                                    JButton buttonErrorOk = new JButton("ОК");
                                    buttonErrorOk.setSize(30,30);

                                    jPanelError.add(buttonErrorOk);
                                    buttonErrorOk.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            jDialogError.dispose();
                                        }
                                    });
                                    jDialogError.add(jPanelError, BorderLayout.NORTH);
                                    jDialogError.setVisible(true);
                                }
                            }
                        });
                        generalPanelAdd.add(buttonOk);
                        jDialogAddTank.add(generalPanelAdd);
                        jDialogAddTank.setVisible(true);
                    }
                });

                buttonLightTank.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jDialog.dispose();
                        JDialog jDialogAddTank = new JDialog(MainWindow.this,"Добавление танка", true);
                        jDialogAddTank.setSize(500,200);
                        jDialogAddTank.setLocationRelativeTo(MainWindow.this);

                        JPanel jPanelInputNameTank = new JPanel();
                        jPanelInputNameTank.add(new JLabel("Введите название танка: "));
                        NameTankField = new JTextField(20);
                        jPanelInputNameTank.add(NameTankField);

                        JPanel jPanelInputHPTank = new JPanel();
                        jPanelInputHPTank.add(new JLabel("Введите кол-во здоровья танка: "));
                        HPTankField = new JTextField(20);
                        jPanelInputHPTank.add(HPTankField);

                        JPanel jPanelInputAbilityTank = new JPanel();
                        jPanelInputAbilityTank.add(new JLabel("Введите дальность обзора танка: "));
                        AbilityTankField = new JTextField(20);
                        jPanelInputAbilityTank.add(AbilityTankField);

                        JPanel generalPanelAdd = new JPanel();
                        generalPanelAdd.setLayout(new BoxLayout(generalPanelAdd, BoxLayout.Y_AXIS));
                        generalPanelAdd.add(jPanelInputNameTank);
                        generalPanelAdd.add(jPanelInputHPTank);
                        generalPanelAdd.add(jPanelInputAbilityTank);

                        buttonOk = new JButton("ОК");
                        buttonOk.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    String TankName = NameTankField.getText().trim();
                                    if (TankName.isEmpty()) {
                                        jDialogAddTank.dispose();
                                        JDialog jDialogErrorNullName = new JDialog(MainWindow.this, "Ошибка", true);
                                        jDialogErrorNullName.setSize(400, 75);
                                        jDialogErrorNullName.setLocationRelativeTo(MainWindow.this);

                                        JPanel jPanelError = new JPanel();
                                        jPanelError.add(new JLabel("Ошибка: У танка должно быть название."));

                                        JButton buttonErrorOk = new JButton("ОК");
                                        buttonErrorOk.setSize(30, 30);

                                        jPanelError.add(buttonErrorOk);
                                        buttonErrorOk.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                jDialogErrorNullName.dispose();
                                            }
                                        });
                                        jDialogErrorNullName.add(jPanelError, BorderLayout.NORTH);
                                        jDialogErrorNullName.setVisible(true);
                                    }else {
                                        String HPTankText = HPTankField.getText().trim();
                                        int HPTankInt = Integer.parseInt(HPTankText);
                                        String AbilityTankText = AbilityTankField.getText().trim();
                                        int viewRange = Integer.parseInt(AbilityTankText);
                                        myTableModelMain.addLightTank(TankName, HPTankInt, viewRange);
                                        jDialogAddTank.dispose();
                                    }
                                }catch(NumberFormatException ex){
                                    JDialog jDialogError = new JDialog(MainWindow.this, "Ошибка", true);
                                    jDialogError.setSize(400,75);
                                    jDialogError.setLocationRelativeTo(MainWindow.this);

                                    JPanel jPanelError = new JPanel();
                                    jPanelError.add(new JLabel("Ошибка: Вы ввели не число или неверный его формат."));

                                    JButton buttonErrorOk = new JButton("ОК");
                                    buttonErrorOk.setSize(30,30);

                                    jPanelError.add(buttonErrorOk);
                                    buttonErrorOk.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            jDialogError.dispose();
                                        }
                                    });
                                    jDialogError.add(jPanelError, BorderLayout.NORTH);
                                    jDialogError.setVisible(true);
                                }
                            }
                        });
                        generalPanelAdd.add(buttonOk);
                        jDialogAddTank.add(generalPanelAdd);
                        jDialogAddTank.setVisible(true);
                    }
                });

                buttonPanelChoice.add(buttonHeavyTank);
                buttonPanelChoice.add(buttonLightTank);
                jDialog.add(messageChoice, BorderLayout.CENTER);
                jDialog.add(buttonPanelChoice, BorderLayout.SOUTH);
                jDialog.setVisible(true);
            }
        });
        buttonAbility = new JButton("Возможности танков");
        buttonAbility.setSize(30, 30);
        buttonAbility.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WindowAbility();
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
                            myTableModelMain.deleteTank(jTable.getSelectedRow());
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
        buttonPanelMain.add(buttonAd);
        this.add(buttonPanelMain, BorderLayout.SOUTH);//расположение кнопки внизу
        this.add(jScrollPane);//крутить таблицу
        this.pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);//расположение посередине
        this.setVisible(true);//чтобы выводилось
    }
}
