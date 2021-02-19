package sample;

public class TestingMethods {


    public static void test1(){
        for (FractionGameData player:Players.getHumanPlayers()){
            Unit unit = player.getPlayersArmy().get(0);
            unit.setAmount(1);
            unit.setInitialPosition(5,1);

            unit = player.getPlayersArmy().get(1);
            unit.setAmount(1);
            unit.setInitialPosition(1,2);

            unit = player.getPlayersArmy().get(2);
            unit.setAmount(1);
            unit.setInitialPosition(2,1);

            unit = player.getPlayersArmy().get(3);
            unit.setAmount(1);
            unit.setInitialPosition(2,2);

            unit = player.getPlayersArmy().get(4);
            unit.setAmount(1);
            unit.setInitialPosition(3,1);

            unit = player.getPlayersArmy().get(5);
            unit.setAmount(1);
            unit.setInitialPosition(3,2);

            unit = player.getPlayersArmy().get(6);
            unit.setAmount(1);
            unit.setInitialPosition(4,1);

            unit = player.getPlayersArmy().get(7);
            unit.setAmount(1);
            unit.setInitialPosition(4,2);

            for (Arena arena:player.getArenasList()){
                for (Unit unite:player.getPlayersArmy()){
                    if (arena.getType().equals(unite.getType())){
                        arena.setIsUnitHere(unite.getActualX(),unite.getActualY(),true);
                    }
                }
            }
        }
        System.out.println("Test 1 implemented");
    }

    public static void test2(){
        for (FractionGameData player:Players.getHumanPlayers()){
            Unit unit = player.getPlayersArmy().get(0);
            unit.setAmount(0);
            unit.setInitialPosition(0,0);

            unit = player.getPlayersArmy().get(1);
            unit.setAmount(0);
            unit.setInitialPosition(0,0);

            unit = player.getPlayersArmy().get(2);
            unit.setAmount(0);
            unit.setInitialPosition(0,0);

            unit = player.getPlayersArmy().get(3);
            unit.setAmount(0);
            unit.setInitialPosition(0,0);

            unit = player.getPlayersArmy().get(4);
            unit.setAmount(0);
            unit.setInitialPosition(0,0);

            unit = player.getPlayersArmy().get(5);
            unit.setAmount(0);
            unit.setInitialPosition(0,0);

            unit = player.getPlayersArmy().get(6);
            unit.setAmount(0);
            unit.setInitialPosition(0,0);

            unit = player.getPlayersArmy().get(7);
            unit.setAmount(100);
            unit.setInitialPosition(5,2);

            for (Arena arena:player.getArenasList()){
                for (Unit unite:player.getPlayersArmy()){
                    if (arena.getType().equals(unite.getType()) && unite.getActualX()!=0){
                        arena.setIsUnitHere(unite.getActualX(),unite.getActualY(),true);
                    }
                }
            }
        }
        System.out.println("Test 2 implemented");
    }

    public static void printNeighboursOf(String sectorName){
        Sector sector = Sectors.getSectorByName(sectorName);
        int k = Sectors.sectorsList.indexOf(sector)/9*10+Sectors.sectorsList.indexOf(sector)%9+11;
        System.out.println("S" + k);
        for (int i=0;i<sector.getNeighbours().size();i++){
            System.out.println(sector.getNeighbours().get(i).getName());
        }
        System.out.println("***********");
    }



}
