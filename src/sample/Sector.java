package sample;

import com.jfoenix.controls.JFXButton;
import javafx.scene.image.ImageView;
import javafx.scene.shape.SVGPath;

import java.util.ArrayList;

public class Sector {

    private String name;
    private String imageURL; //icon on world map
    private String battlefieldBackgroundURL;
    private String description;
    private ArrayList<Unit> army = new ArrayList<Unit>();
    private  ArrayList<Unit> waterType;
    private  ArrayList<Unit> landType;
    private  ArrayList<Unit> airType;
    private String defaultFraction,actualFraction;
    private FractionGameData owner;
    private JFXButton attackSectorButton,mapSectorButton;
    private SVGPath mapButton;
    private ImageView itemOnMap;
    private boolean isCityHere=false;
    // TODO: 23.10.2020 fill it up somehow
    private ArrayList<Sector> neighbours = new ArrayList<>();

    public Sector(String name, String defaultFraction) {
        this.name = name;
        this.defaultFraction=defaultFraction;
        description="Temporary description";
        this.actualFraction=defaultFraction;

    }

    public ImageView getItemOnMap() {
        return itemOnMap;
    }

    public void setItemOnMap(ImageView itemOnMap) {
        this.itemOnMap = itemOnMap;
    }

    public SVGPath getMapButton() {
        return mapButton;
    }

    public void setMapButton(SVGPath mapButton) {
        this.mapButton = mapButton;
    }

    public ArrayList<Sector> getNeighbours() {
        return neighbours;
    }

    public boolean isCityHere() {
        return isCityHere;
    }

    public void setIsCityHere(boolean cityHere) {
        isCityHere = cityHere;
    }

    public JFXButton getMapSectorButton() {
        return mapSectorButton;
    }

    public void setMapSectorButton(JFXButton mapSectorButton) {
        this.mapSectorButton = mapSectorButton;
    }

    public void fillSectorOwner(){
        switch(defaultFraction){
            case "Jungle":
                Sectors.getSectorByName(name).setOwner(Players.computerJungle);
                break;
            case "Atlantis":
                Sectors.getSectorByName(name).setOwner(Players.computerAtlantis);
                break;
            case "Cyberpunk":
                Sectors.getSectorByName(name).setOwner(Players.computerCyberpunk);
                break;
        }
    }

    public JFXButton getAttackSectorButton() {
        return attackSectorButton;
    }

    public void setAttackSectorButton(JFXButton attackSectorButton) {
        this.attackSectorButton = attackSectorButton;
    }

    public void setArmy(ArrayList<Unit> army) {
        this.army = army;
    }

    public String getDescriptionOfArmySize(){
        StringBuilder temp = new StringBuilder();
        for (Unit unit : army){
            if (unit.getAmount()!=0){
                temp.append(unit.getName()).append(" ").append(unit.getAmount()).append("\n");
            }
        }
        return temp.toString();

    }

    public void fillNumberOfUnitsInSector(int T11, int T12, int T21, int T22, int T23, int T31, int T32, int T4){
        army.get(0).setAmount(T11);
        army.get(1).setAmount(T12);
        army.get(2).setAmount(T21);
        army.get(3).setAmount(T22);
        army.get(4).setAmount(T23);
        army.get(5).setAmount(T31);
        army.get(6).setAmount(T32);
        army.get(7).setAmount(T4);
    }

    public void setWaterType(ArrayList<Unit> waterType) {
        this.waterType = waterType;
    }

    public void setLandType(ArrayList<Unit> landType) {
        this.landType = landType;
    }

    public void setAirType(ArrayList<Unit> airType) {
        this.airType = airType;
    }

    public void setOwner(FractionGameData owner) {
        this.owner = owner;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Unit> getArmy() {
        return army;
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

    public String getBattlefieldBackgroundURL() {
        return battlefieldBackgroundURL;
    }

    public String getDefaultFraction() {
        return defaultFraction;
    }

    public String getActualFraction() {
        return actualFraction;
    }

    public FractionGameData getOwner() {
        return owner;
    }
}
