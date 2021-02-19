package sample;

public class MilitaryBuilding extends Building {

    private Unit  unitType;
    private final int amountPerWeek;
    private int amountAvailable;

    public MilitaryBuilding(String name, Unit unitType, int amountPerWeek) {
        super(name);
        this.unitType = unitType;
        this.amountPerWeek = amountPerWeek;
        this.amountAvailable=0;
    }

    public Unit getUnitType() {
        return unitType;
    }

    public int getAmountPerWeek() {
        return amountPerWeek;
    }

    public int getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(int amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public void increaseAmountAvailable() {
        amountAvailable+=amountPerWeek;
    }
}
