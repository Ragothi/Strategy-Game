package sample;

public class Mine extends Building {

    private int amountPerTurn;
    private SingleResource resType;

    public Mine(String name, int amountPerTurn, SingleResource resType) {
        super(name);
        this.amountPerTurn = amountPerTurn;
        this.resType = resType;
    }

    public int getAmountPerTurn() {
        return amountPerTurn;
    }

    public SingleResource getResType() {
        return resType;
    }

    public void setAmountPerTurn(int amountPerTurn) {
        this.amountPerTurn = amountPerTurn;
    }



}
