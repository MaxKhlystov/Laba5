package max;
import java.io.Serializable;

public abstract class  Tank implements Serializable{
    private int id;
    private String name;
    private int HP;
    private static final long serialVersionUID = 1L;

    public Tank(int id, String name, int HP){
        this.id = id;
        this.name=name;
        this.HP=HP;
    }
    public int getId() {return this.id;}

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

    public abstract String toString();
}
