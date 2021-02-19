package sample;

import javafx.scene.shape.SVGPath;

import java.util.ArrayList;

public class Sectors {

    public Sectors() {
        fillSectorList();
        fillElementalSectorsLists();
        addAttributesToSectors();
        addUnitsToSectors();
    }

    public static ArrayList<Sector> sectorsList = new ArrayList<>();

    public static ArrayList<Sector> jngSectorsList = new ArrayList<>();
    public static ArrayList<Sector> atlSectorsList = new ArrayList<>();
    public static ArrayList<Sector> cbrSectorsList = new ArrayList<>();

    public static Sector selectedSector;

    public static Sector getSelectedSector() {
        return selectedSector;
    }

    public void correctSectorsArmies(){
        for (FractionGameData humanPlayer:Players.getHumanPlayers()){
            if (Sectors.getSectorByName("Sector11").getOwner().getName().equals(humanPlayer.getName())) {
                Sectors.getSectorByName("Sector11").fillNumberOfUnitsInSector(0, 0, 0, 0, 0, 0, 0, 0);
                for (Unit unit: Sectors.getSectorByName("Sector11").getArmy()){
                    unit.setInitialPosition(0,0);
                }
            }
            if (Sectors.getSectorByName("Sector28").getOwner().getName().equals(humanPlayer.getName())) {
                Sectors.getSectorByName("Sector28").fillNumberOfUnitsInSector(0, 0, 0, 0, 0, 0, 0, 0);
                for (Unit unit: Sectors.getSectorByName("Sector28").getArmy()){
                    unit.setInitialPosition(0,0);
                }
            }
            if (Sectors.getSectorByName("Sector75").getOwner().getName().equals(humanPlayer.getName())) {
                Sectors.getSectorByName("Sector75").fillNumberOfUnitsInSector(0, 0, 0, 0, 0, 0, 0, 0);
                for (Unit unit: Sectors.getSectorByName("Sector75").getArmy()){
                    unit.setInitialPosition(0,0);
                }
            }
        }


    }

    public static void setSelectedSector(Sector selectedSector) {
        Sectors.selectedSector = selectedSector;
    }

    public static void playCombatMusic(){
        switch (selectedSector.getActualFraction()){
            case "Jungle":
                Soundtracks.playMusic(Soundtracks.combatJngMusic);
                break;
            case "Atlantis":
                Soundtracks.playMusic(Soundtracks.combatAtlMusic);
                break;
            case "Cyberpunk":
                Soundtracks.playMusic(Soundtracks.combatCbrMusic);
                break;
        }
    }

    private void fillSectorList(){
        sectorsList.add(sector11);
        sectorsList.add(sector12);
        sectorsList.add(sector13);
        sectorsList.add(sector14);
        sectorsList.add(sector15);
        sectorsList.add(sector16);
        sectorsList.add(sector17);
        sectorsList.add(sector18);
        sectorsList.add(sector19);

        sectorsList.add(sector21);
        sectorsList.add(sector22);
        sectorsList.add(sector23);
        sectorsList.add(sector24);
        sectorsList.add(sector25);
        sectorsList.add(sector26);
        sectorsList.add(sector27);
        sectorsList.add(sector28);
        sectorsList.add(sector29);

        sectorsList.add(sector31);
        sectorsList.add(sector32);
        sectorsList.add(sector33);
        sectorsList.add(sector34);
        sectorsList.add(sector35);
        sectorsList.add(sector36);
        sectorsList.add(sector37);
        sectorsList.add(sector38);
        sectorsList.add(sector39);

        sectorsList.add(sector41);
        sectorsList.add(sector42);
        sectorsList.add(sector43);
        sectorsList.add(sector44);
        sectorsList.add(sector45);
        sectorsList.add(sector46);
        sectorsList.add(sector47);
        sectorsList.add(sector48);
        sectorsList.add(sector49);

        sectorsList.add(sector51);
        sectorsList.add(sector52);
        sectorsList.add(sector53);
        sectorsList.add(sector54);
        sectorsList.add(sector55);
        sectorsList.add(sector56);
        sectorsList.add(sector57);
        sectorsList.add(sector58);
        sectorsList.add(sector59);

        sectorsList.add(sector61);
        sectorsList.add(sector62);
        sectorsList.add(sector63);
        sectorsList.add(sector64);
        sectorsList.add(sector65);
        sectorsList.add(sector66);
        sectorsList.add(sector67);
        sectorsList.add(sector68);
        sectorsList.add(sector69);

        sectorsList.add(sector71);
        sectorsList.add(sector72);
        sectorsList.add(sector73);
        sectorsList.add(sector74);
        sectorsList.add(sector75);
        sectorsList.add(sector76);
        sectorsList.add(sector77);
        sectorsList.add(sector78);
        sectorsList.add(sector79);
    }

    private void fillElementalSectorsLists(){
        for (int i=0; i<sectorsList.size();i++){
            switch (sectorsList.get(i).getActualFraction()) {
                case "Jungle":
                    jngSectorsList.add(sectorsList.get(i));
                    break;
                case "Atlantis":
                    atlSectorsList.add(sectorsList.get(i));
                    break;
                case "Cyberpunk":
                    cbrSectorsList.add(sectorsList.get(i));
                    break;
            }
            sectorsList.get(i).setArmy(new Army(sectorsList.get(i).getActualFraction()).getArmy());
        }
    }

    // TODO: 16.10.2020 set initial neutral army units positions
    private void addUnitsToSectors(){
        sector11.fillNumberOfUnitsInSector(1,2,1,3,0,0,0,0);
        sector11.getArmy().get(0).setInitialPosition(1,2);
        sector11.getArmy().get(1).setInitialPosition(1,1);
        sector11.getArmy().get(2).setInitialPosition(2,2);
        sector11.getArmy().get(3).setInitialPosition(3,2);
        sector12.fillNumberOfUnitsInSector(1,2,1,3,0,0,0,0);
        sector12.getArmy().get(0).setInitialPosition(1,2);
        sector12.getArmy().get(1).setInitialPosition(1,1);
        sector12.getArmy().get(2).setInitialPosition(2,2);
        sector12.getArmy().get(3).setInitialPosition(3,2);
        sector13.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector14.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector15.fillNumberOfUnitsInSector(1,0,0,0,0,0,0,0);
        sector15.getArmy().get(0).setInitialPosition(1,2);
        sector16.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector17.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector18.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector19.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector21.fillNumberOfUnitsInSector(1,0,0,0,0,0,0,0);
        sector21.getArmy().get(0).setInitialPosition(1,2);

        sector22.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector23.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector24.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector25.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector26.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector27.fillNumberOfUnitsInSector(1,1,1,1,1,1,1,1);
        sector27.getArmy().get(0).setInitialPosition(1,2);
        sector27.getArmy().get(1).setInitialPosition(1,1);
        sector27.getArmy().get(2).setInitialPosition(2,2);
        sector27.getArmy().get(3).setInitialPosition(3,2);
        sector27.getArmy().get(4).setInitialPosition(1,2);
        sector27.getArmy().get(5).setInitialPosition(1,1);
        sector27.getArmy().get(6).setInitialPosition(2,2);
        sector27.getArmy().get(7).setInitialPosition(3,2);
        sector28.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector29.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector31.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector32.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector33.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector34.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector35.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector36.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector37.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector38.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector39.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector41.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector42.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector43.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector44.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector45.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector46.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector47.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector48.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector49.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector51.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector52.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector53.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector54.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector55.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector56.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector57.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector58.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector59.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector61.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector62.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector63.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector64.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector65.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector66.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector67.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector68.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector69.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector71.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector72.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector73.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector74.fillNumberOfUnitsInSector(2,1,0,0,0,3,0,0);
        sector74.getArmy().get(0).setInitialPosition(1,2);
        sector74.getArmy().get(1).setInitialPosition(1,1);
        sector74.getArmy().get(5).setInitialPosition(2,2);
        sector75.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector76.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector77.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector78.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
        sector79.fillNumberOfUnitsInSector(0,0,0,0,0,0,0,0);
    }

    private void addAttributesToSectors(){
        for (int i=0; i<sectorsList.size();i++){
            SVGPath tempSVG = new SVGPath();
            tempSVG.setContent(ShapeStorage.hexMap);
            switch (sectorsList.get(i).getActualFraction()) {
                case "Jungle":
                    sectorsList.get(i).setImageURL(GraphicsStorage.hexJng);
                    tempSVG.setFill(ColorsStorage.computerJungle);
                    sectorsList.get(i).setMapButton(tempSVG);
                    break;
                case "Atlantis":
                    sectorsList.get(i).setImageURL(GraphicsStorage.hexAtl);
                    tempSVG.setFill(ColorsStorage.computerAtlantis);
                    sectorsList.get(i).setMapButton(tempSVG);
                    break;
                case "Cyberpunk":
                    sectorsList.get(i).setImageURL(GraphicsStorage.hexCbr);
                    tempSVG.setFill(ColorsStorage.computerCyberpunk);
                    sectorsList.get(i).setMapButton(tempSVG);
                    break;
            }
            sectorsList.get(i).getMapButton().setStrokeWidth(3);
            sectorsList.get(i).getMapButton().setStroke(ColorsStorage.black);
            sectorsList.get(i).getMapButton().setPickOnBounds(false);
        }
    }


    public static Sector getSectorByName(String sectorName){
        for (Sector sector:sectorsList){
            if (sector.getName().equals(sectorName)){
                return sector;
            }
        }

        return new Sector("No such sector","Null fraction");
    }

    private Sector sector11 = new Sector("Sector11","Jungle");
    private Sector sector12 = new Sector("Sector12","Jungle");
    private Sector sector13 = new Sector("Sector13","Jungle");
    private Sector sector14 = new Sector("Sector14","Jungle");
    private Sector sector15 = new Sector("Sector15","Jungle");
    private Sector sector16 = new Sector("Sector16","Cyberpunk");
    private Sector sector17 = new Sector("Sector17","Cyberpunk");
    private Sector sector18 = new Sector("Sector18","Cyberpunk");
    private Sector sector19 = new Sector("Sector19","Cyberpunk");

    private Sector sector21 = new Sector("Sector21","Jungle");
    private Sector sector22 = new Sector("Sector22","Jungle");
    private Sector sector23 = new Sector("Sector23","Jungle");
    private Sector sector24 = new Sector("Sector24","Jungle");
    private Sector sector25 = new Sector("Sector25","Cyberpunk");
    private Sector sector26 = new Sector("Sector26","Cyberpunk");
    private Sector sector27 = new Sector("Sector27","Cyberpunk");
    private Sector sector28 = new Sector("Sector28","Cyberpunk");
    private Sector sector29 = new Sector("Sector29","Cyberpunk");

    private Sector sector31 = new Sector("Sector31","Jungle");
    private Sector sector32 = new Sector("Sector32","Jungle");
    private Sector sector33 = new Sector("Sector33","Jungle");
    private Sector sector34 = new Sector("Sector34","Jungle");
    private Sector sector35 = new Sector("Sector35","Cyberpunk");
    private Sector sector36 = new Sector("Sector36","Cyberpunk");
    private Sector sector37 = new Sector("Sector37","Cyberpunk");
    private Sector sector38 = new Sector("Sector38","Cyberpunk");
    private Sector sector39 = new Sector("Sector39","Cyberpunk");

    private Sector sector41 = new Sector("Sector41","Jungle");
    private Sector sector42 = new Sector("Sector42","Jungle");
    private Sector sector43 = new Sector("Sector43","Jungle");
    private Sector sector44 = new Sector("Sector44","Jungle");
    private Sector sector45 = new Sector("Sector45","Atlantis");
    private Sector sector46 = new Sector("Sector46","Cyberpunk");
    private Sector sector47 = new Sector("Sector47","Cyberpunk");
    private Sector sector48 = new Sector("Sector48","Cyberpunk");
    private Sector sector49 = new Sector("Sector49","Cyberpunk");

    private Sector sector51 = new Sector("Sector51","Jungle");
    private Sector sector52 = new Sector("Sector52","Jungle");
    private Sector sector53 = new Sector("Sector53","Jungle");
    private Sector sector54 = new Sector("Sector54","Atlantis");
    private Sector sector55 = new Sector("Sector55","Atlantis");
    private Sector sector56 = new Sector("Sector56","Atlantis");
    private Sector sector57 = new Sector("Sector57","Atlantis");
    private Sector sector58 = new Sector("Sector58","Cyberpunk");
    private Sector sector59 = new Sector("Sector59","Cyberpunk");

    private Sector sector61 = new Sector("Sector61","Jungle");
    private Sector sector62 = new Sector("Sector62","Atlantis");
    private Sector sector63 = new Sector("Sector63","Atlantis");
    private Sector sector64 = new Sector("Sector64","Atlantis");
    private Sector sector65 = new Sector("Sector65","Atlantis");
    private Sector sector66 = new Sector("Sector66","Atlantis");
    private Sector sector67 = new Sector("Sector67","Atlantis");
    private Sector sector68 = new Sector("Sector68","Atlantis");
    private Sector sector69 = new Sector("Sector69","Cyberpunk");

    private Sector sector71 = new Sector("Sector71","Atlantis");
    private Sector sector72 = new Sector("Sector72","Atlantis");
    private Sector sector73 = new Sector("Sector73","Atlantis");
    private Sector sector74 = new Sector("Sector74","Atlantis");
    private Sector sector75 = new Sector("Sector75","Atlantis");
    private Sector sector76 = new Sector("Sector76","Atlantis");
    private Sector sector77 = new Sector("Sector77","Atlantis");
    private Sector sector78 = new Sector("Sector78","Atlantis");
    private Sector sector79 = new Sector("Sector79","Atlantis");

}
