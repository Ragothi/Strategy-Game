package sample;

import java.util.ArrayList;

public class CityBuildings {

    private Mine T11Mine = new Mine("forTestingPurposes",2,Players.getActualPlayer().getPlayerResources().get(1));
    private Mine T12Mine = new Mine("forTestingPurposes",2,Players.getActualPlayer().getPlayerResources().get(2));
    private Mine T13Mine = new Mine("forTestingPurposes",2,Players.getActualPlayer().getPlayerResources().get(3));
    private Mine T21Mine = new Mine("forTestingPurposes",1,Players.getActualPlayer().getPlayerResources().get(4));
    private Mine T22Mine = new Mine("forTestingPurposes",1,Players.getActualPlayer().getPlayerResources().get(5));
    private Mine T3Mine = new Mine("forTestingPurposes",1, Players.getActualPlayer().getPlayerResources().get(6));

    private MilitaryBuilding T11armyBuilding = new MilitaryBuilding("forTestingPurposes",Players.getActualPlayer().getPlayersArmy().get(0),5);
    private MilitaryBuilding T12armyBuilding = new MilitaryBuilding("forTestingPurposes",Players.getActualPlayer().getPlayersArmy().get(1),5);
    private MilitaryBuilding T21armyBuilding = new MilitaryBuilding("forTestingPurposes",Players.getActualPlayer().getPlayersArmy().get(2),5);
    private MilitaryBuilding T22armyBuilding = new MilitaryBuilding("forTestingPurposes",Players.getActualPlayer().getPlayersArmy().get(3),5);
    private MilitaryBuilding T23armyBuilding = new MilitaryBuilding("forTestingPurposes",Players.getActualPlayer().getPlayersArmy().get(4),5);
    private MilitaryBuilding T31armyBuilding = new MilitaryBuilding("forTestingPurposes",Players.getActualPlayer().getPlayersArmy().get(5),5);
    private MilitaryBuilding T32armyBuilding = new MilitaryBuilding("forTestingPurposes",Players.getActualPlayer().getPlayersArmy().get(6),5);
    private MilitaryBuilding T4armyBuilding = new MilitaryBuilding("forTestingPurposes", Players.getActualPlayer().getPlayersArmy().get(7),5);

    private DefenseBuilding T1wall = new DefenseBuilding("forTestingPurposes" ,0,0);
    private DefenseBuilding T2wall = new DefenseBuilding("forTestingPurposes" ,0,0);
    private DefenseBuilding T1tower = new DefenseBuilding("forTestingPurposes" ,0,0);
    private DefenseBuilding T2tower = new DefenseBuilding("forTestingPurposes" ,0,0);
    private DefenseBuilding trap = new DefenseBuilding("forTestingPurposes" ,0,0);

    private Building special1 = new Building("forTestingPurposes");
    private Building special2 = new Building("forTestingPurposes");
    private Building special3 = new Building("forTestingPurposes");
    private Building special4 = new Building("forTestingPurposes");
    private Building special5 = new Building("forTestingPurposes");

    private HouseBuilding house = new HouseBuilding("forTestingPurposes",1,3,10);

    private Building[] all25Buildings = new Building[]{T11Mine, T12Mine, T13Mine, T21Mine, T22Mine, T3Mine, T11armyBuilding,
            T12armyBuilding, T21armyBuilding, T22armyBuilding, T23armyBuilding, T31armyBuilding, T32armyBuilding, T4armyBuilding,
            T1wall, T2wall, T1tower, T2tower, trap,special1, special2, special3, special4, special5, house };


    private ArrayList<Mine> mines = new ArrayList<>();
    private ArrayList<MilitaryBuilding> militaryBuildings = new ArrayList<>();
    private ArrayList<DefenseBuilding> defenseBuildings = new ArrayList<>();
    private  ArrayList<Building> specialBuildings = new ArrayList<>();

    public CityBuildings() {
        formBuildings();
        if (Players.getActualPlayer().getFraction().getName().equals("Jungle")){

            selectJungle();
//             TODO: 29.09.2020 set names and functions to buildings from each fraction
        } else if (Players.getActualPlayer().getFraction().getName().equals("Atlantis")){
          selectAtlantis();
        } else if (Players.getActualPlayer().getFraction().getName().equals("Cyberpunk")){
           selectCyberpunk();
        }
    }

    private void selectJungle(){

        String[] jngNames = new String[]{ "Wood picker","Stone Mine","Leaf Collector","Rope maker","Resin flow","Ruby","Tiger Ring",
                "Viper's Nest","Panda's Tree","Piranha's Lake","Monkey Playground","Gorilla Sanctuary","Fly Airport","Pile of rusty stones",
                "Wooden Palisade T1","Wooden Palisade T2","Low tower","High Tower","Widow-maker" ,"Viper tunnels",
                "Monkey King Palace","Fountain of Life","Wide Platform","Pray to Earth","Tree House"};

        String[] icons = new String[]{"n","n","n","n","n","n",GraphicsStorage.jngT11armyBuilding,GraphicsStorage.jngT12armyBuilding,GraphicsStorage.jngT21armyBuilding,
                GraphicsStorage.jngT22armyBuilding,GraphicsStorage.jngT23armyBuilding,GraphicsStorage.jngT31armyBuilding,
                GraphicsStorage.jngT32armyBuilding,GraphicsStorage.jngT4armyBuilding,"n","n","n","n","n","n","n","n","n","n","n"};
        
        T11Mine.setMany(all25Buildings,jngNames,Prices.jngBuildings,icons);
        Players.getActualPlayer().getFraction().setUnitBackground(GraphicsStorage.jngUnitBackground);

        


    }

    private void selectAtlantis(){
        String[] atlNames = new String[]{"Stone Fountain","Clay Pool","Bubble Tube","Wild Snail Hunter","Nemo Origins","Tryton Treasury"
                ,"Mer-person Shell House","Toxic Fish Swamp","Shark's Cave","Echo Hall","Croc's Farms","Breathless Warriors Barracks"
                ,"Inky Land","Deep Church","Stone Wall T1","Stone Wall T2","Stone tower","Coral Tower","Coral Bomb","Cornucopia",
                "Clean Lake","Angel's Lungs","Divine Blessing","Offensive Stone","Residence"  };

        String[] icons = new String[]{"n","n","n","n","n","n",GraphicsStorage.atlT11armyBuilding,GraphicsStorage.atlT12armyBuilding,GraphicsStorage.atlT21armyBuilding,
                GraphicsStorage.atlT22armyBuilding,GraphicsStorage.atlT23armyBuilding,GraphicsStorage.atlT31armyBuilding,
                GraphicsStorage.atlT32armyBuilding,GraphicsStorage.atlT4armyBuilding,"n","n","n","n","n","n","n","n","n","n","n"};
        
        T11Mine.setMany(all25Buildings,atlNames,Prices.atlBuildings,icons);

        Players.getActualPlayer().getFraction().setUnitBackground(GraphicsStorage.atlUnitBackground);

        
        // special1     T1resources +1/day
        // special2     toxic fish/week increase
        // special3     allows Angel to fight on Land
        // special4     land and air units no longer require resource.Air/turn to be alive
        // special5     unit of choose have +1 atk
    }

    private void selectCyberpunk(){
        
        String[] cbrNames = new String[]{"Wooden Wreck","Sea Rocks","Deep Coal Mine","Ironworks","Secret Lake","Alchemist"
                ,"Barracks","Landing Dock","Jet's Runway","Tank Factory","Hidden Dock's","Polish Car Manufacturer"
                ,"Human Resourceless Office","Capitan's Hidden Secret Mystery","Steel Construct T1","Steel Construct T2","Spike Trap",
                "Flame Tower","Benzin Field","","","","","","Favela"};

        String[] icons = new String[]{"n","n","n","n","n","n",GraphicsStorage.cbrT11armyBuilding,GraphicsStorage.cbrT12armyBuilding,GraphicsStorage.cbrT21armyBuilding,
                GraphicsStorage.cbrT22armyBuilding,GraphicsStorage.cbrT23armyBuilding,GraphicsStorage.cbrT31armyBuilding,
                GraphicsStorage.cbrT32armyBuilding,GraphicsStorage.cbrT4armyBuilding,"n","n","n","n","n","n","n","n","n","n","n"};
        
        T11Mine.setMany(all25Buildings,cbrNames,Prices.cbrBuildings,icons);

        Players.getActualPlayer().getFraction().setUnitBackground(GraphicsStorage.cbrUnitBackground);
    }

    private void formBuildings(){
        mines.add(T11Mine);
        mines.add(T12Mine);
        mines.add(T13Mine);
        mines.add(T21Mine);
        mines.add(T22Mine);
        mines.add(T3Mine);

        militaryBuildings.add(T11armyBuilding);
        militaryBuildings.add(T12armyBuilding);
        militaryBuildings.add(T21armyBuilding);
        militaryBuildings.add(T22armyBuilding);
        militaryBuildings.add(T23armyBuilding);
        militaryBuildings.add(T31armyBuilding);
        militaryBuildings.add(T32armyBuilding);
        militaryBuildings.add(T4armyBuilding);

        defenseBuildings.add(T1wall);
        defenseBuildings.add(T2wall);
        defenseBuildings.add(T1tower);
        defenseBuildings.add(T2tower);
        defenseBuildings.add(trap);

        specialBuildings.add(special1);
        specialBuildings.add(special2);
        specialBuildings.add(special3);
        specialBuildings.add(special4);
        specialBuildings.add(special5);

    }

    public HouseBuilding getHouse() {
        return house;
    }

    public ArrayList<Mine> getMines() {
        return mines;
    }

    public ArrayList<MilitaryBuilding> getMilitaryBuildings() {
        return militaryBuildings;
    }

    public ArrayList<DefenseBuilding> getDefenseBuildings() {
        return defenseBuildings;
    }

    public ArrayList<Building> getSpecialBuildings() {
        return specialBuildings;
    }



}
