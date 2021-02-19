package sample;

import java.util.ArrayList;

public class FractionGameData {


    private  ArrayList<Unit> playersArmy;
    private  ArrayList<Mine> playerMines ;
    private  ArrayList<MilitaryBuilding> playerMilitaryBuildings ;
    private  ArrayList<DefenseBuilding> playerDefenseBuildings ;
    private  ArrayList<Building> playerSpecialBuildings ;
    private  HouseBuilding houses;
    private  ArrayList<Unit> waterType;
    private  ArrayList<Unit> landType;
    private  ArrayList<Unit> airType;
    private Workers playerWorkers;
    private Arena airArena  = new Arena("Air");
    private Arena landArena  = new Arena("Land");
    private Arena waterArena  = new Arena("Water");
    private ArrayList<Arena> arenasList = new ArrayList<>();
    private String avatar;
    // TODO: 23.10.2020 fill it up somehow
    private ArrayList<Sector> playerSectors = new ArrayList<>();

    private final String name;
    private FractionGraphicData fraction;
    private  ArrayList<SingleResource> playerResources;



    public FractionGameData(String name, FractionGraphicData fraction) {
        this.name = name;
        this.fraction = fraction;
        arenasList.add(airArena);
        arenasList.add(landArena);
        arenasList.add(waterArena);
    }

    public ArrayList<Arena> getArenasList() {
        return arenasList;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public  void setFraction(FractionGraphicData fraction) {
        this.fraction = fraction;
    }

    public String getName() {
        return name;
    }

    public FractionGraphicData getFraction() {
        return fraction;
    }

    public void fillUnitsPerWeek(){
        for (int i=0;i<playersArmy.size();i++){
            playersArmy.get(i).setAmountPerWeek(playerMilitaryBuildings.get(i).getAmountPerWeek());
        }
    }

    public  ArrayList<SingleResource> getPlayerResources() {
        return playerResources;
    }

    public  void setPlayerResources(ArrayList<SingleResource> playerResources) {
        this.playerResources = playerResources;
    }

    public  ArrayList<Unit> getPlayersArmy() {
        return playersArmy;
    }

    public  void setPlayersArmy(ArrayList<Unit> playersArmy) {
        this.playersArmy = playersArmy;

    }

    public  ArrayList<Mine> getPlayerMines() {
        return playerMines;
    }

    public  void setPlayerMines(ArrayList<Mine> playerMines) {
        this.playerMines = playerMines;
    }

    public  ArrayList<MilitaryBuilding> getPlayerMilitaryBuildings() {
        return playerMilitaryBuildings;
    }

    public  void setPlayerMilitaryBuildings(ArrayList<MilitaryBuilding> playerMilitaryBuildings) {
        this.playerMilitaryBuildings = playerMilitaryBuildings;
    }

    public  ArrayList<DefenseBuilding> getPlayerDefenseBuildings() {
        return playerDefenseBuildings;
    }

    public  void setPlayerDefenseBuildings(ArrayList<DefenseBuilding> playerDefenseBuildings) {
        this.playerDefenseBuildings = playerDefenseBuildings;
    }

    public  ArrayList<Building> getPlayerSpecialBuildings() {
        return playerSpecialBuildings;
    }

    public  void setPlayerSpecialBuildings(ArrayList<Building> playerSpecialBuildings) {
        this.playerSpecialBuildings = playerSpecialBuildings;
    }

    public  HouseBuilding getHouses() {
        return houses;
    }

    public  void setHouses(HouseBuilding houses) {
        this.houses = houses;
    }

    public  ArrayList<Unit> getWaterType() {
        return waterType;
    }

    public  ArrayList<Unit> getLandType() {
        return landType;
    }

    public  ArrayList<Unit> getAirType() {
        return airType;
    }

    public  void setWaterType(ArrayList<Unit> waterType) {
        this.waterType = waterType;
    }

    public  void setLandType(ArrayList<Unit> landType) {
        this.landType = landType;
    }

    public  void setAirType(ArrayList<Unit> airType) {
        this.airType = airType;
    }

    private  int getIndexOf(String unitName){
        for (int i=0; i<this.playersArmy.size();i++){
            if (this.playersArmy.get(i).getName().equals(unitName)){
                return i;
            }
        }
        return -1;
    }

    public  Unit getUnit(String unitName){
        return this.playersArmy.get(getIndexOf(unitName));
    }

    public  Unit getUnit(int x, int y){
        for (int i=0;i<playersArmy.size();i++){
            if (playersArmy.get(i).getActualX()==x && playersArmy.get(i).getActualY()==y){
                return playersArmy.get(i);
            }
        }
        return null;
    }

    public Workers getPlayerWorkers() {
        return playerWorkers;
    }

    public void setPlayerWorkers(Workers playerWorkers) {
        this.playerWorkers = playerWorkers;
    }
}
