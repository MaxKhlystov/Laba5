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
        return 3;
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
                    }else{
                        return ((HeavyTank) tank).getArmorThickness();
                    }
            }
        }
        return "dsvvsvsdv";
    }
}
