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
    public abstract void useAbility();
}
