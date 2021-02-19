package sample;

public class SingleResource {

    private String name;
    private int amount;
    private String Tier;

    public SingleResource(String name, int amount, String tier) {
        this.name = name;
        this.amount = amount;
        Tier = tier;
    }


    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTier() {
        return Tier;
    }

    public void addAmount(int amount){
        if (amount>0){
            this.amount+=amount;
        }
    }

    public void subtractAmount(int amount){
        if (amount>0){
            this.amount-=amount;
        }
    }

}
