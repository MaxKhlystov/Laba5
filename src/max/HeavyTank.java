package max;

import java.util.Random;

public class HeavyTank extends Tank {
    private int armorThickness;
    private String classTank = "Тяжёлый танк";
    private int numberBreakouts;
    private int numberNotBreakouts;
    public HeavyTank (String name, int HP, int armorThickness){
        super(name, HP);
        this.armorThickness=armorThickness;
    }

    public int getArmorThickness(){
        return this.armorThickness;
    }

    public void setArmorThickness(int armorThickness){
        this.armorThickness = armorThickness;
    }

    public int getNumberBreakouts(){
        return numberBreakouts;
    }

    public int getNumberNotBreakouts(){
        return numberNotBreakouts;
    }
    public void useAbility(){
        Random random = new Random();
        int projectileCaliber = random.nextInt((armorThickness+(armorThickness/2)) + 1 - (armorThickness-(armorThickness/2))) + (armorThickness-(armorThickness/2));
        if (armorThickness>=projectileCaliber) {
            //System.out.println(this.getName() + " танкует снаряд");
            numberNotBreakouts+=1;
        }
        else {
            //System.out.println("Броня " + this.getName() + " была пробита");
            numberBreakouts+=1;
        }
    }

    public String toString(){
        String descriptionTank = ("Класс танка: " + classTank + ", название танка: " + this.getName() + ", кол-во очков прочности: " + this.getHPTank()+
                ", имеющий толщину брони: " + this.getArmorThickness() + ", получил пробитий: " + numberBreakouts + " и непробитий: " + numberNotBreakouts);
        return descriptionTank;
    }
}
