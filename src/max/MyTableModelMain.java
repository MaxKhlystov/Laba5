package max;

import view.MainWindow;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class MyTableModelMain extends AbstractTableModel {
    private Battlefield data;
    private boolean editMode = false;

    public MyTableModelMain(Battlefield field) {
        this.data = field;
    }

    public void setEditMode(boolean enabled) {
        this.editMode = enabled;
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return editMode;
    }
    @Override
    public int getRowCount() {
        return data.getCount();
    }
    public Tank getTank(int index) {
        return data.getTank(index);
    }
    

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column){
        switch (column){
            case 0: return "Название";
            case 1: return "Прочность";
            case 2: return "Дальность обзора";
            case 3: return "Толщина брони";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 0:
                return String.class;
            default:
                return Integer.class;
        }
    }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Tank tank = data.getTank(rowIndex);
            try {
                switch (columnIndex) {
                    case 0:
                        tank.setName((String) aValue);
                        break;
                    case 1:
                        tank.setHPTank(Integer.parseInt(aValue.toString()));
                        break;
                    case 2:
                        if (tank instanceof LightTank) {
                            ((LightTank) tank).setViewRange(Integer.parseInt(aValue.toString()));
                        }
                        break;
                    case 3:
                        if (tank instanceof HeavyTank) {
                            ((HeavyTank) tank).setArmorThickness(Integer.parseInt(aValue.toString()));
                        }
                        break;
                }
                fireTableCellUpdated(rowIndex, columnIndex);
            } catch (NumberFormatException e) {

            }
        }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0: return data.getTank(rowIndex).getName();
            case 1: return data.getTank(rowIndex).getHPTank();
            case 2: {
                Tank tank = data.getTank(rowIndex);
                if(tank instanceof LightTank) {
                    return ((LightTank) tank).getViewRange();
                }else return "-";
            }
            case 3:{
                Tank tank = data.getTank(rowIndex);
                if (tank instanceof HeavyTank){
                    return ((HeavyTank) tank).getArmorThickness();
                }else return "-";
            }
        }
        return "default";
    }

    public void deleteTank(int index){
        this.data.remove(index);
        fireTableDataChanged();
    }

    public void addLightTank(String name, int HP, int viewRange){
        data.addTank(new LightTank(name, HP, viewRange));
        fireTableDataChanged();
    }

    public void addHeavyTank(String name, int HP, int armorThickness){
        data.addTank(new HeavyTank(name, HP, armorThickness));
        fireTableDataChanged();
    }
}
