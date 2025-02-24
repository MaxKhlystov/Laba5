package max;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Battlefield {
    private List<Tank> tanks = new ArrayList();

    public Battlefield(){
        this.tanks.add(new LightTank("т100лт", 1500, 400));
        this.tanks.add(new HeavyTank("ис7", 2100, 350));
        this.tanks.add(new HeavyTank("ис4", 2500, 450));
        this.tanks.add(new HeavyTank("ис8", 1800, 300));
        this.tanks.add(new HeavyTank("кв4", 1600, 250));
        this.tanks.add(new HeavyTank("ис3", 1500, 200));
    }

    public void addTank(Tank tank) {
        this.tanks.add(tank);
    }

    public boolean removeTank(String name) {
        return this.tanks.removeIf((tank) -> {
            return tank.getName().equalsIgnoreCase(name);
        });
    }

    public void performAbilityAllTanks() {
        if (tanks==null){
            System.out.println("На поле боя нет ни одного танка.");
        }
        else this.tanks.forEach(Tank::useAbility);
    }

    public void displayTanks() {
        if (this.tanks.isEmpty()) {
            System.out.println("На поле боя нет ни одного танка.");
        } else {
            System.out.println("Список танков на поле боя:");
            this.tanks.stream()
                    .map(Tank::toString)
                    .collect(Collectors.toList())
                    .forEach(System.out::println);
        }
    }

    public int getCount(){
        return  this.tanks.size();
    }

    public Tank getTank(int index) {
        return tanks.get(index);
    }

    public void remove(int index) {
        this.tanks.remove(index);
    }
}
