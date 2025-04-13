package max;

public abstract class Tank {
    private String name;
    private int HP;

    public Tank(String name, int HP){
        this.name=name;
        this.HP=HP;
    }

    public String getName() {
        return this.name;
    }

    public int getHPTank() {
        return this.HP;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setHPTank(int HP){
        this.HP = HP;
    }

    public abstract String useAbility();
}
