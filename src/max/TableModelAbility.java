package max;

import javax.swing.table.AbstractTableModel;

public class TableModelAbility extends AbstractTableModel {
    private Battlefield data;

    public TableModelAbility(Battlefield field){
        this.data=field;
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
            case 1: return "Кол-во обнаруженных противников";
            case 2: return "Кол-во непробитий";
            case 3: return "Кол-во пробитий";
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

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0: return data.getTank(rowIndex).getName();
            case 1: {
                Tank tank = data.getTank(rowIndex);
                if(tank instanceof LightTank) {
                    return ((LightTank) tank).getNumberDetectedEnemies();
                }else return "-";
            }
            case 2: {
                Tank tank = data.getTank(rowIndex);
                if(tank instanceof HeavyTank) {
                    return ((HeavyTank) tank).getNumberNotBreakouts();
                }else return "-";
            }
            case 3:{
                Tank tank = data.getTank(rowIndex);
                if (tank instanceof HeavyTank){
                    return ((HeavyTank) tank).getNumberBreakouts();
                }else return "-";
            }
        }
        return "default";
    }

    public void useAbilityTanks(){
        data.performAbilityAllTanks();
        fireTableDataChanged();
    }
}
