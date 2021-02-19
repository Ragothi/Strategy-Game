package sample;

public class DefenseBuilding extends Building{

    private int defBoost;
    private int dmgDealt;

    public DefenseBuilding(String name,  int defBoost, int dmgDealt) {
        super(name);
        this.defBoost = defBoost;
        this.dmgDealt = dmgDealt;
    }

    public int getDefBoost() {
        return defBoost;
    }

    public int getDmgDealt() {
        return dmgDealt;
    }

    public void setDefBoost(int defBoost) {
        this.defBoost = defBoost;
    }

    public void setDmgDealt(int dmgDealt) {
        this.dmgDealt = dmgDealt;
    }
}
