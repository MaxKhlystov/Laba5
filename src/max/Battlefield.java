package max;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Battlefield implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Tank> tanks = new ArrayList();

    public Battlefield(){
        this.tanks.add(new LightTank(1, "т100лт", 1500, 400));
        this.tanks.add(new HeavyTank(2, "ис7", 2100, 350));
        this.tanks.add(new HeavyTank(3, "ис4", 2500, 450));
        this.tanks.add(new HeavyTank(4, "ис8", 1800, 300));
        this.tanks.add(new HeavyTank(5, "кв4", 1600, 250));
        this.tanks.add(new HeavyTank(6, "ис3", 1500, 200));
    }

    public void addTank(Tank tank) {
        this.tanks.add(tank);
    }

    public boolean removeTank(String name) {
        return this.tanks.removeIf((tank) -> {
            return tank.getName().equalsIgnoreCase(name);
        });
    }

    public String performAbilityTank(int index) {
        if (tanks == null || tanks.isEmpty()) {
            return "На поле боя нет ни одного танка.";
        }
        if (index < 0 || index >= tanks.size()) {
            return "Неверный индекс танка.";
        }
        Tank tank = tanks.get(index);
        return tank.useAbility();
    }

    public int getCount(){
        return  this.tanks.size();
    }

    public Tank getTank(int index) {
        return tanks.get(index);
    }

    public List<Tank> getTanks() {
        if (tanks == null) {
            tanks =new ArrayList<>();
        }
        return new ArrayList<>(tanks);
    }

    public void remove(int index) {
        this.tanks.remove(index);
    }
}
