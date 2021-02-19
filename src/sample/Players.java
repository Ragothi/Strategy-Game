package sample;

import java.util.ArrayList;

public class Players {

    static final FractionGraphicData jungleFraction = new FractionGraphicData("Jungle",GraphicsStorage.jungleBackground,ColorsStorage.jungle);
    static final FractionGraphicData atlantisFraction = new FractionGraphicData("Atlantis",GraphicsStorage.atlantisBackground,ColorsStorage.atlantis);
    static final FractionGraphicData cyberpunkFraction = new FractionGraphicData("Cyberpunk",GraphicsStorage.cyberpunkBackground,ColorsStorage.cyberpunk);
    static final FractionGraphicData computerJungleFraction = new FractionGraphicData("Jungle",GraphicsStorage.jungleBackground,ColorsStorage.computerJungle);
    static final FractionGraphicData computerAtlantisFraction = new FractionGraphicData("Atlantis",GraphicsStorage.atlantisBackground,ColorsStorage.computerAtlantis);
    static final FractionGraphicData computerCyberpunkFraction = new FractionGraphicData("Cyberpunk",GraphicsStorage.cyberpunkBackground,ColorsStorage.computerCyberpunk);

    private  FractionGameData humanJungle = new FractionGameData("humanJungle",jungleFraction);
    private  FractionGameData humanAtlantis = new FractionGameData("humanAtlantis",atlantisFraction);
    private  FractionGameData humanCyberpunk = new FractionGameData("humanCyberpunk",cyberpunkFraction);
    public static FractionGameData computerJungle = new FractionGameData("computerJungle",computerJungleFraction);
    public static FractionGameData computerAtlantis = new FractionGameData("computerAtlantis",computerAtlantisFraction);
    public static FractionGameData computerCyberpunk = new FractionGameData("computerCyberpunk",computerCyberpunkFraction);


    private static FractionGameData actualPlayer;

    private static ArrayList<FractionGameData> fractions;

    private static ArrayList<FractionGameData> humanPlayers;
    private static ArrayList<FractionGameData> computerPlayers = new ArrayList<>();

    public static ArrayList<FractionGameData> getHumanPlayers() {
        return humanPlayers;
    }

    public static ArrayList<FractionGameData> getComputerPlayers() {
        return computerPlayers;
    }

    public static void playMapMusic(){
        switch (actualPlayer.getFraction().getName()){
            case "Jungle":
                Soundtracks.playMusic(Soundtracks.mapJngMusic);
                break;
            case "Atlantis":
                Soundtracks.playMusic(Soundtracks.mapAtlMusic);
                break;
            case "Cyberpunk":
                Soundtracks.playMusic(Soundtracks.mapCbrMusic);
                break;
        }
    }

    public static void playCityMusic(){
        switch (actualPlayer.getFraction().getName()){
            case "Jungle":
                Soundtracks.playMusic(Soundtracks.cityJngMusic);
                break;
            case "Atlantis":
                Soundtracks.playMusic(Soundtracks.cityAtlMusic);
                break;
            case "Cyberpunk":
                Soundtracks.playMusic(Soundtracks.cityCbrMusic);
                break;
        }
    }

    public Players(int numberOfPlayers) {
        fractions =new ArrayList<FractionGameData>(3);
        fractions.add(humanJungle);
        fractions.add(humanAtlantis);
        fractions.add(humanCyberpunk);
        humanPlayers = new ArrayList<FractionGameData>(numberOfPlayers);
        fillFractionsProperties();
        fillFractionDescriptions();
        computerPlayers.add(computerJungle);
        computerPlayers.add(computerAtlantis);
        computerPlayers.add(computerCyberpunk);
    }



   public static FractionGameData getActualPlayer(){
        return actualPlayer;
   }

   public static void selectPlayerFraction(int player, int fraction){
        humanPlayers.add(player-1,fractions.get(fraction-1));
   }

    public static void setActualPlayer(int i){
         actualPlayer= humanPlayers.get(i-1);
    }

    private static void setActualPlayer(FractionGameData player){
        actualPlayer= player;
    }

    private void fillFractionDescriptions(){
        humanJungle.setAvatar(GraphicsStorage.pooh);
        humanAtlantis.setAvatar(GraphicsStorage.cthulhu);
        humanCyberpunk.setAvatar(GraphicsStorage.cl4ptr4p);
        computerJungle.setAvatar(GraphicsStorage.pooh);
        computerAtlantis.setAvatar(GraphicsStorage.cthulhu);
        computerCyberpunk.setAvatar(GraphicsStorage.cl4ptr4p);

        humanJungle.getFraction().setFractionDescription("Jungle is the fraction of true nature creatures living in harmony with " +
                "world. They contain wild beasts eager to destroy everyone trying to desecrate their home." +
                "\n \n " +
                "Pros: \n" +
                "   - More land units than other races \n" +
                "   - Large amount of natural resources\n" +
                "   - High population\n" +
                "\n" +
                "Cons: \n" +
                "   - Only one water and air type unit \n" +
                "   - Weakest fortification \n" +
                "   - Life in jungle is tough");

        humanAtlantis.getFraction().setFractionDescription("Atlantis are the people from the deepest depths of the ocean. They " +
                "learn how to tame wild animals and master the ability to sabotage their enemies from below through net of " +
                "hidden caves." +
                "\n \n " +
                "Pros: \n" +
                "   - More water units than other races \n" +
                "   - Moderate amount of resources\n" +
                "   - Moderate population\n" +
                "\n" +
                "Cons: \n" +
                "   - Can have troubles in land fights \n" +
                "   - Just nice fortification \n" +
                "   - Water units die after sabotage");

        humanCyberpunk.getFraction().setFractionDescription("Highly technological advanced fraction relying on its strong air units. " +
                "They live in the middle of the ocean on big steel platform and live from scavenging wrecks and " +
                "plundering their neighbours." +
                "\n \n " +
                "Pros: \n" +
                "   - More air units than other races \n" +
                "   - Best sabotage opportunities\n" +
                "   - Best fortifications \n" +
                "\n" +
                "Cons: \n" +
                "   - Can have troubles in land fights \n" +
                "   - Low natural resources \n" +
                "   - Low population");
    }

    private void fillFractionsProperties(){

        for (int i=0;i<3;i++){
            actualPlayer= fractions.get(i);
            ResourcesTypes tempRes = new ResourcesTypes();
            actualPlayer.setPlayerResources(tempRes.getResourceArrayList());
            Army tempArmy = new Army();
            actualPlayer.setPlayersArmy(tempArmy.getArmy());
            tempArmy.fillElementalTypeArrays();
            actualPlayer.setAirType(tempArmy.getAirType());
            actualPlayer.setWaterType(tempArmy.getWaterType());
            actualPlayer.setLandType(tempArmy.getLandType());

            CityBuildings tempCity = new CityBuildings();
            actualPlayer.setPlayerMines(tempCity.getMines());
            actualPlayer.setPlayerDefenseBuildings(tempCity.getDefenseBuildings());
            actualPlayer.setPlayerMilitaryBuildings(tempCity.getMilitaryBuildings());
            actualPlayer.setPlayerSpecialBuildings(tempCity.getSpecialBuildings());
            actualPlayer.setHouses(tempCity.getHouse());

            Workers tempWorkers = new Workers(0);
            actualPlayer.setPlayerWorkers(tempWorkers);
            actualPlayer.getPlayerWorkers().increaseTotal(Players.getActualPlayer().getHouses().getTotalVillagersAmount());

            actualPlayer.fillUnitsPerWeek();
        }




    }

}
