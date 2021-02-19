package sample;

import java.util.ArrayList;

public class Prices {

    public static final ResourcesTypes ZERO_PRICE = new ResourcesTypes(0);

    // TODO: 18.10.2020 set prices
    //jng res1 = gold res2=wood res3=stone res4=leaf res5=rope res6=resin res7=ruby
    public static final ResourcesTypes jngT11minePrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT12minePrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT13minePrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT21minePrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT22minePrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT3minePrice = new ResourcesTypes(50,0,0,0,0,0,0);
    public static final ResourcesTypes jngT11armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT12armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT21armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT22armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT23armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT31armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT32armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT4armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT11armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT12armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT21armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT22armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT23armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT31armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT32armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT4armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT1wallPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT2wallPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT1towerPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngT2towerPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngTrapPrice = new ResourcesTypes(0);
    public static final ResourcesTypes jngSpecial1Price = new ResourcesTypes(0);
    public static final ResourcesTypes jngSpecial2Price = new ResourcesTypes(0);
    public static final ResourcesTypes jngSpecial3Price = new ResourcesTypes(0);
    public static final ResourcesTypes jngSpecial4Price = new ResourcesTypes(0);
    public static final ResourcesTypes jngSpecial5Price = new ResourcesTypes(0);
    public static final ResourcesTypes jngHousePrice = new ResourcesTypes(0);

    //atl res1 = gold res2=stone res3=clay res4=air res5=shell res6=coral res7=crystals
    public static final ResourcesTypes atlT11minePrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT12minePrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT13minePrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT21minePrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT22minePrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT3minePrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT11armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT12armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT21armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT22armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT23armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT31armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT32armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT4armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT11armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT12armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT21armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT22armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT23armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT31armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT32armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT4armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT1wallPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT2wallPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT1towerPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlT2towerPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlTrapPrice = new ResourcesTypes(0);
    public static final ResourcesTypes atlSpecial1Price = new ResourcesTypes(0);
    public static final ResourcesTypes atlSpecial2Price = new ResourcesTypes(0);
    public static final ResourcesTypes atlSpecial3Price = new ResourcesTypes(0);
    public static final ResourcesTypes atlSpecial4Price = new ResourcesTypes(0);
    public static final ResourcesTypes atlSpecial5Price = new ResourcesTypes(0);
    public static final ResourcesTypes atlHousePrice = new ResourcesTypes(0);

    //cbr res1 = gold res2=wood res3=stone res4=coal res5=steel res6=water res7=booze
    public static final ResourcesTypes cbrT11minePrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT12minePrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT13minePrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT21minePrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT22minePrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT3minePrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT11armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT12armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT21armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT22armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT23armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT31armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT32armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT4armyBuildingPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT11armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT12armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT21armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT22armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT23armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT31armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT32armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT4armyUnitPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT1wallPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT2wallPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT1towerPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrT2towerPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrTrapPrice = new ResourcesTypes(0);
    public static final ResourcesTypes cbrSpecial1Price = new ResourcesTypes(0);
    public static final ResourcesTypes cbrSpecial2Price = new ResourcesTypes(0);
    public static final ResourcesTypes cbrSpecial3Price = new ResourcesTypes(0);
    public static final ResourcesTypes cbrSpecial4Price = new ResourcesTypes(0);
    public static final ResourcesTypes cbrSpecial5Price = new ResourcesTypes(0);
    public static final ResourcesTypes cbrHousePrice = new ResourcesTypes(0);
    
    public static final ResourcesTypes[] jngBuildings = new ResourcesTypes[]{jngT11minePrice,jngT12minePrice, jngT13minePrice,
            jngT21minePrice, jngT22minePrice, jngT3minePrice,jngT11armyBuildingPrice, jngT12armyBuildingPrice, jngT21armyBuildingPrice,
            jngT22armyBuildingPrice, jngT23armyBuildingPrice, jngT31armyBuildingPrice, jngT32armyBuildingPrice, jngT4armyBuildingPrice,
            jngT1wallPrice, jngT2wallPrice, jngT1towerPrice, jngT2towerPrice, jngTrapPrice, jngSpecial1Price, jngSpecial2Price,
            jngSpecial3Price, jngSpecial4Price, jngSpecial5Price, jngHousePrice };

    public static final ResourcesTypes[] atlBuildings = new ResourcesTypes[]{atlT11minePrice,atlT12minePrice, atlT13minePrice,
            atlT21minePrice, atlT22minePrice, atlT3minePrice,atlT11armyBuildingPrice, atlT12armyBuildingPrice, atlT21armyBuildingPrice,
            atlT22armyBuildingPrice, atlT23armyBuildingPrice, atlT31armyBuildingPrice, atlT32armyBuildingPrice, atlT4armyBuildingPrice,
            atlT1wallPrice, atlT2wallPrice, atlT1towerPrice, atlT2towerPrice, atlTrapPrice, atlSpecial1Price, atlSpecial2Price,
            atlSpecial3Price, atlSpecial4Price, atlSpecial5Price, atlHousePrice };

    public static final ResourcesTypes[] cbrBuildings = new ResourcesTypes[]{cbrT11minePrice,cbrT12minePrice, cbrT13minePrice,
            cbrT21minePrice, cbrT22minePrice, cbrT3minePrice,cbrT11armyBuildingPrice, cbrT12armyBuildingPrice, cbrT21armyBuildingPrice,
            cbrT22armyBuildingPrice, cbrT23armyBuildingPrice, cbrT31armyBuildingPrice, cbrT32armyBuildingPrice, cbrT4armyBuildingPrice,
            cbrT1wallPrice, cbrT2wallPrice, cbrT1towerPrice, cbrT2towerPrice, cbrTrapPrice, cbrSpecial1Price, cbrSpecial2Price,
            cbrSpecial3Price, cbrSpecial4Price, cbrSpecial5Price, cbrHousePrice };
    
    public static boolean canBuy( ResourcesTypes price){
        int counter =0;
        ArrayList<SingleResource> wallet = Players.getActualPlayer().getPlayerResources();
        for (int k=0; k<Players.getActualPlayer().getPlayerResources().size();k++){
            String temp=wallet.get(k).getName();
            for (int i=0; i<Prices.ZERO_PRICE.getResourceArrayList().size();i++){
                if (temp.equals(price.getResourceArrayList().get(i).getName())){
                    if ( wallet.get(k).getAmount() >= price.getResourceArrayList().get(i).getAmount()){
                            counter++;
                    }
                }
            }
        }

        return counter == Players.getActualPlayer().getPlayerResources().size();
    }




}
