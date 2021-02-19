package sample;

import java.util.ArrayList;

public class Building {

    private String name;
    private boolean isBuilt;
    private ResourcesTypes cost;
    private String description;
    private String icon;

    public Building(String name) {
        this.name = name;
        isBuilt = false;
        this.cost=Prices.ZERO_PRICE;
        this.description="No description";
    }


    public void setMany(Building[] buildings,String[] name,ResourcesTypes[] price,String[] icon){
        for (int i=0; i<25;i++){
            buildings[i].setName(name[i]);
            buildings[i].setCost(price[i]);
            buildings[i].setIcon(icon[i]);

        }
    }

    public void setMany(Building[] buildings,String[] name,ResourcesTypes[] price){
        for (int i=0; i<25;i++){
            buildings[i].setName(name[i]);
            buildings[i].setCost(price[i]);
        }
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBuilt() {
        return isBuilt;
    }

    private void setBuilt() {
        isBuilt = true;
    }

    public ResourcesTypes getCost() {
        return cost;
    }

    public static void buyBuilding(Building building){
        if (Prices.canBuy(building.getCost())){
            takeResourcesFromPlayersWallet(building);
            building.setBuilt();
            if (building.getClass().getName().equals("sample.MilitaryBuilding")){
                MilitaryBuilding mb = (MilitaryBuilding) building;
                mb.setAmountAvailable(mb.getAmountPerWeek());
            }
        }
    }

    public void setCost(ResourcesTypes cost) {
        this.cost = cost;
    }

    private static void takeResourcesFromPlayersWallet(Building building){
        ResourcesTypes price = building.getCost();
        ArrayList<SingleResource> wallet = Players.getActualPlayer().getPlayerResources();
        for (int k=0; k<Players.getActualPlayer().getPlayerResources().size();k++){
            String temp=wallet.get(k).getName();
            for (int i=0; i<Prices.ZERO_PRICE.getResourceArrayList().size();i++){
                if (temp.equals(price.getResourceArrayList().get(i).getName())){
                    wallet.get(k).subtractAmount(price.getResourceArrayList().get(i).getAmount());
                }
            }
        }

    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
