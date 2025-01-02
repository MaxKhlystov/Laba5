package max;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
    private Battlefield data;
    public MyTableModel(Battlefield field){
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
}
