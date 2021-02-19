package sample;

public class HouseBuilding  extends  Building{

    private int totalVillagersAmount,villagersPerHouse;
    private int goldPerVillager;
    private int houseAmount;

    public HouseBuilding(String name, int houseAmount, int villagersPerHouse, int goldPerVillager) {
        super(name);
        this.villagersPerHouse=villagersPerHouse;
        this.houseAmount=houseAmount;
        this.totalVillagersAmount = villagersPerHouse*houseAmount;
        this.goldPerVillager = goldPerVillager;


    }

    public int getTotalVillagersAmount() {
        return totalVillagersAmount;
    }

    public int getGoldPerVillager() {
        return goldPerVillager;
    }

    public int getHouseAmount() {
        return houseAmount;
    }

    public int getGoldPerTurn(){
        return getGoldPerVillager() * Players.getActualPlayer().getPlayerWorkers().getGoldMine();
    }

    public void buyHouse(){
        houseAmount++;
        totalVillagersAmount +=villagersPerHouse;
        Players.getActualPlayer().getPlayerWorkers().increaseTotal(villagersPerHouse);
    }

}
