package sample;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Army {

    private Unit T11unit = new Unit();
    private Unit T12unit = new Unit();
    private Unit T21unit= new Unit();
    private Unit T22unit= new Unit();
    private Unit T23unit= new Unit();
    private Unit T31unit= new Unit();
    private Unit T32unit= new Unit();
    private Unit T4unit = new Unit();

    Units units = new Units();

    private ArrayList<Unit> army = new ArrayList<>();

    private ArrayList<Unit> waterType = new ArrayList<>();
    private ArrayList<Unit> landType = new ArrayList<>();
    private ArrayList<Unit> airType = new ArrayList<>();

    public Army() {
        fillArmy();
        if (Players.getActualPlayer().getFraction().getName().equals("Jungle")){
            T11unit.fillUnit(units.getUnitJngT11());
            T12unit.fillUnit(units.getUnitJngT12());
            T21unit.fillUnit(units.getUnitJngT21());
            T22unit.fillUnit(units.getUnitJngT22());
            T23unit.fillUnit(units.getUnitJngT23());
            T31unit.fillUnit(units.getUnitJngT31());
            T32unit.fillUnit(units.getUnitJngT32());
            T4unit.fillUnit(units.getUnitJngT4());
            
            String[] icons = new String[]{GraphicsStorage.jngT11armyUnit,GraphicsStorage.jngT12armyUnit,GraphicsStorage.jngT21armyUnit,
                    GraphicsStorage.jngT22armyUnit,GraphicsStorage.jngT23armyUnit,GraphicsStorage.jngT31armyUnit,
                    GraphicsStorage.jngT32armyUnit,GraphicsStorage.jngT4armyUnit};

            ResourcesTypes[] price = new ResourcesTypes[]{Prices.jngT11armyUnitPrice,Prices.jngT12armyUnitPrice,Prices.jngT21armyUnitPrice,
                    Prices.jngT22armyUnitPrice,Prices.jngT23armyUnitPrice,Prices.jngT31armyUnitPrice,Prices.jngT32armyUnitPrice,
                    Prices.jngT4armyUnitPrice} ;

            ImageView[] images = new ImageView[8];
            for (int i=0;i<8;i++){
                images[i]=new ImageView(icons[i]);
            }

            setIconsAndPrices(icons,price,images,ColorsStorage.jungle);

        } else if (Players.getActualPlayer().getFraction().getName().equals("Atlantis")){
            T11unit.fillUnit(units.getUnitAtlT11());
            T12unit.fillUnit(units.getUnitAtlT12());
            T21unit.fillUnit(units.getUnitAtlT21());
            T22unit.fillUnit(units.getUnitAtlT22());
            T23unit.fillUnit(units.getUnitAtlT23());
            T31unit.fillUnit(units.getUnitAtlT31());
            T32unit.fillUnit(units.getUnitAtlT32());
            T4unit.fillUnit(units.getUnitAtlT4());

            String[] icons = new String[]{GraphicsStorage.atlT11armyUnit,GraphicsStorage.atlT12armyUnit,GraphicsStorage.atlT21armyUnit,
                    GraphicsStorage.atlT22armyUnit,GraphicsStorage.atlT23armyUnit,GraphicsStorage.atlT31armyUnit,
                    GraphicsStorage.atlT32armyUnit,GraphicsStorage.atlT4armyUnit};

            ResourcesTypes[] price = new ResourcesTypes[]{Prices.atlT11armyUnitPrice,Prices.atlT12armyUnitPrice,Prices.atlT21armyUnitPrice,
                    Prices.atlT22armyUnitPrice,Prices.atlT23armyUnitPrice,Prices.atlT31armyUnitPrice,Prices.atlT32armyUnitPrice,
                    Prices.atlT4armyUnitPrice} ;
            ImageView[] images = new ImageView[8];
            for (int i=0;i<8;i++){
                images[i]=new ImageView(icons[i]);
            }
            setIconsAndPrices(icons,price,images,ColorsStorage.atlantis);
        } else if (Players.getActualPlayer().getFraction().getName().equals("Cyberpunk")){
            T11unit.fillUnit(units.getUnitCbrT11());
            T12unit.fillUnit(units.getUnitCbrT12());
            T21unit.fillUnit(units.getUnitCbrT21());
            T22unit.fillUnit(units.getUnitCbrT22());
            T23unit.fillUnit(units.getUnitCbrT23());
            T31unit.fillUnit(units.getUnitCbrT31());
            T32unit.fillUnit(units.getUnitCbrT32());
            T4unit.fillUnit(units.getUnitCbrT4());

            String[] icons = new String[]{GraphicsStorage.cbrT11armyUnit,GraphicsStorage.cbrT12armyUnit,GraphicsStorage.cbrT21armyUnit,
                    GraphicsStorage.cbrT22armyUnit,GraphicsStorage.cbrT23armyUnit,GraphicsStorage.cbrT31armyUnit,
                    GraphicsStorage.cbrT32armyUnit,GraphicsStorage.cbrT4armyUnit};

            ResourcesTypes[] price = new ResourcesTypes[]{Prices.cbrT11armyUnitPrice,Prices.cbrT12armyUnitPrice,Prices.cbrT21armyUnitPrice,
                    Prices.cbrT22armyUnitPrice,Prices.cbrT23armyUnitPrice,Prices.cbrT31armyUnitPrice,Prices.cbrT32armyUnitPrice,
                    Prices.cbrT4armyUnitPrice} ;
            ImageView[] images = new ImageView[8];
            for (int i=0;i<8;i++){
                images[i]=new ImageView(icons[i]);
            }
            setIconsAndPrices(icons,price,images,ColorsStorage.cyberpunk);
        }


    }

    public Army(String fractionName) {
        fillArmy();
        if (fractionName.equals("Jungle")){
            T11unit.fillUnit(units.getUnitJngT11());
            T12unit.fillUnit(units.getUnitJngT12());
            T21unit.fillUnit(units.getUnitJngT21());
            T22unit.fillUnit(units.getUnitJngT22());
            T23unit.fillUnit(units.getUnitJngT23());
            T31unit.fillUnit(units.getUnitJngT31());
            T32unit.fillUnit(units.getUnitJngT32());
             T4unit.fillUnit(units.getUnitJngT4());

            String[] icons = new String[]{GraphicsStorage.jngT11armyUnit,GraphicsStorage.jngT12armyUnit,GraphicsStorage.jngT21armyUnit,
                    GraphicsStorage.jngT22armyUnit,GraphicsStorage.jngT23armyUnit,GraphicsStorage.jngT31armyUnit,
                    GraphicsStorage.jngT32armyUnit,GraphicsStorage.jngT4armyUnit};

            ResourcesTypes[] price = new ResourcesTypes[]{Prices.jngT11armyUnitPrice,Prices.jngT12armyUnitPrice,Prices.jngT21armyUnitPrice,
                    Prices.jngT22armyUnitPrice,Prices.jngT23armyUnitPrice,Prices.jngT31armyUnitPrice,Prices.jngT32armyUnitPrice,
                    Prices.jngT4armyUnitPrice} ;
            ImageView[] images = new ImageView[8];
            for (int i=0;i<8;i++){
                images[i]=new ImageView(icons[i]);
            }
            setIconsAndPrices(icons,price,images,ColorsStorage.jungle);

        } else if (fractionName.equals("Atlantis")){
            T11unit.fillUnit(units.getUnitAtlT11());
            T12unit.fillUnit(units.getUnitAtlT12());
            T21unit.fillUnit(units.getUnitAtlT21());
            T22unit.fillUnit(units.getUnitAtlT22());
            T23unit.fillUnit(units.getUnitAtlT23());
            T31unit.fillUnit(units.getUnitAtlT31());
            T32unit.fillUnit(units.getUnitAtlT32());
             T4unit.fillUnit(units.getUnitAtlT4());

            String[] icons = new String[]{GraphicsStorage.atlT11armyUnit,GraphicsStorage.atlT12armyUnit,GraphicsStorage.atlT21armyUnit,
                    GraphicsStorage.atlT22armyUnit,GraphicsStorage.atlT23armyUnit,GraphicsStorage.atlT31armyUnit,
                    GraphicsStorage.atlT32armyUnit,GraphicsStorage.atlT4armyUnit};

            ResourcesTypes[] price = new ResourcesTypes[]{Prices.atlT11armyUnitPrice,Prices.atlT12armyUnitPrice,Prices.atlT21armyUnitPrice,
                    Prices.atlT22armyUnitPrice,Prices.atlT23armyUnitPrice,Prices.atlT31armyUnitPrice,Prices.atlT32armyUnitPrice,
                    Prices.atlT4armyUnitPrice} ;
            ImageView[] images = new ImageView[8];
            for (int i=0;i<8;i++){
                images[i]=new ImageView(icons[i]);
            }
            setIconsAndPrices(icons,price,images,ColorsStorage.atlantis);
        } else if (fractionName.equals("Cyberpunk")){
            T11unit.fillUnit(units.getUnitCbrT11());
            T12unit.fillUnit(units.getUnitCbrT12());
            T21unit.fillUnit(units.getUnitCbrT21());
            T22unit.fillUnit(units.getUnitCbrT22());
            T23unit.fillUnit(units.getUnitCbrT23());
            T31unit.fillUnit(units.getUnitCbrT31());
            T32unit.fillUnit(units.getUnitCbrT32());
             T4unit.fillUnit(units.getUnitCbrT4());

            String[] icons = new String[]{GraphicsStorage.cbrT11armyUnit,GraphicsStorage.cbrT12armyUnit,GraphicsStorage.cbrT21armyUnit,
                    GraphicsStorage.cbrT22armyUnit,GraphicsStorage.cbrT23armyUnit,GraphicsStorage.cbrT31armyUnit,
                    GraphicsStorage.cbrT32armyUnit,GraphicsStorage.cbrT4armyUnit};

            ResourcesTypes[] price = new ResourcesTypes[]{Prices.cbrT11armyUnitPrice,Prices.cbrT12armyUnitPrice,Prices.cbrT21armyUnitPrice,
                    Prices.cbrT22armyUnitPrice,Prices.cbrT23armyUnitPrice,Prices.cbrT31armyUnitPrice,Prices.cbrT32armyUnitPrice,
                    Prices.cbrT4armyUnitPrice} ;
            ImageView[] images = new ImageView[8];
            for (int i=0;i<8;i++){
                images[i]=new ImageView(icons[i]);
            }
            setIconsAndPrices(icons,price,images,ColorsStorage.cyberpunk);
        }


    }

    public ArrayList<Unit> getArmy() {
        return army;
    }

    private void fillArmy(){
        army.add(T11unit);
        army.add(T12unit);
        army.add(T21unit);
        army.add(T22unit);
        army.add(T23unit);
        army.add(T31unit);
        army.add(T32unit);
        army.add(T4unit);
    }
    
    private void setIconsAndPrices(String[] iconsURL, ResourcesTypes[] cost, ImageView[] images, Paint fractionColor){
        for (int i=0; i<army.size();i++){
            army.get(i).setIcon(iconsURL[i]);
            army.get(i).setCost(cost[i]);
            army.get(i).setImage(images[i]);
            army.get(i).setFractionColor(fractionColor);
        }
    }

    public void fillElementalTypeArrays(){
        for (int i=0; i<Players.getActualPlayer().getPlayersArmy().size();i++){
            if (Players.getActualPlayer().getPlayersArmy().get(i).getType().equals("Water")){
                waterType.add(Players.getActualPlayer().getPlayersArmy().get(i));
            }
        }

        for (int i=0; i<Players.getActualPlayer().getPlayersArmy().size();i++){
            if (Players.getActualPlayer().getPlayersArmy().get(i).getType().equals("Air")){
                airType.add(Players.getActualPlayer().getPlayersArmy().get(i));
            }
        }

        for (int i=0; i<Players.getActualPlayer().getPlayersArmy().size();i++){
            if (Players.getActualPlayer().getPlayersArmy().get(i).getType().equals("Land")){
                landType.add(Players.getActualPlayer().getPlayersArmy().get(i));
            }
        }
    }

    public ArrayList<Unit> getWaterType() {
        return waterType;
    }

    public ArrayList<Unit> getLandType() {
        return landType;
    }

    public ArrayList<Unit> getAirType() {
        return airType;
    }



}
