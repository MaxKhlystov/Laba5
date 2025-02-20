package max;

import javax.swing.table.AbstractTableModel;

public class MyTableModelMain extends AbstractTableModel {
    private Battlefield data;

    public MyTableModelMain(Battlefield field){
        this.data = field;
    }
    @Override
    public int getRowCount() {
        return data.getCount();
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
        switch(columnIndex){
            case 0: return String.class;
            //case 1: return int.class;
            //case 2: return int.class;
            //case 3: return int.class;
        }
        return int.class;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: data.getTank(rowIndex).setName((String)aValue);
                //case 1: data.getTank(rowIndex).setHPTank((int)aValue);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return true;
            case 1: return true;
            case 2: return true;
            case 3: return true;
        }
        return false;
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
