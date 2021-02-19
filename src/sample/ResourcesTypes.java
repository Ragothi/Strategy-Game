package sample;

import java.util.ArrayList;

public class ResourcesTypes {

    //T1: wood,stone,clay,air,leaf,coal
    //T2: steel,drink watter,rope,resin,big shell,coral
    //T3: heart of jungle,booze
    //none: gold

   private  SingleResource wood ;
   private  SingleResource stone;
   private  SingleResource clay ;
   private  SingleResource air ;
   private  SingleResource leaf ;
   private  SingleResource coal ;

   private  SingleResource steel;
   private  SingleResource water;
   private  SingleResource rope ;
   private  SingleResource resin;
   private  SingleResource shell;
   private  SingleResource coral;

   private  SingleResource ruby;
   private  SingleResource booze;
   private  SingleResource crystals;

   private  SingleResource gold;

    private ArrayList<SingleResource> resourceArrayList;

    public ResourcesTypes() {
        emptyResMaker();
        resourceArrayList= new ArrayList<>();
        if (Players.getActualPlayer().getFraction().getName().equals("Jungle")){
            resourceArrayList.add(gold);
            resourceArrayList.add(wood);
            resourceArrayList.add(stone);
            resourceArrayList.add(leaf);
            resourceArrayList.add(rope);
            resourceArrayList.add(resin);
            resourceArrayList.add(ruby);
        } else if (Players.getActualPlayer().getFraction().getName().equals("Atlantis")){
            resourceArrayList.add(gold);
            resourceArrayList.add(stone);
            resourceArrayList.add(clay);
            resourceArrayList.add(air);
            resourceArrayList.add(shell);
            resourceArrayList.add(coral);
            resourceArrayList.add(crystals);
        } else if (Players.getActualPlayer().getFraction().getName().equals("Cyberpunk")){
            resourceArrayList.add(gold);
            resourceArrayList.add(wood);
            resourceArrayList.add(stone);
            resourceArrayList.add(coal);
            resourceArrayList.add(steel);
            resourceArrayList.add(water);
            resourceArrayList.add(booze);
        }
    }

    public ResourcesTypes(int startSetup) {
        emptyResMaker();
        resourceArrayList= new ArrayList<>();
        this.wood.setAmount(startSetup);
        this.stone.setAmount(startSetup);
        this.clay.setAmount(startSetup);
        this.air.setAmount(startSetup);
        this.leaf.setAmount(startSetup);
        this.coal.setAmount(startSetup);
        this.steel.setAmount(startSetup);
        this.water.setAmount(startSetup);
        this.rope.setAmount(startSetup);
        this.resin.setAmount(startSetup);
        this.shell.setAmount(startSetup);
        this.coral.setAmount(startSetup);
        this.ruby.setAmount(startSetup);
        this.booze.setAmount(startSetup);
        this.crystals.setAmount(startSetup);
        this.gold.setAmount(startSetup);
        fillArray();
    }

    public ResourcesTypes(int res1, int res2, int res3, int res4, int res5, int res6, int res7) {
        emptyResMaker();
        resourceArrayList= new ArrayList<>();
        if (Players.getActualPlayer().getFraction().getName().equals("Jungle")){
            this.gold.setAmount(res1);
            this.wood.setAmount(res2);
            this.stone.setAmount(res3);
            this.leaf.setAmount(res4);
            this.rope.setAmount(res5);
            this.resin.setAmount(res6);
            this.ruby.setAmount(res7);
        } else if (Players.getActualPlayer().getFraction().getName().equals("Atlantis")){
            this.gold.setAmount(res1);
            this.stone.setAmount(res2);
            this.clay.setAmount(res3);
            this.air.setAmount(res4);
            this.shell.setAmount(res5);
            this.coral.setAmount(res6);
            this.crystals.setAmount(res7);
        } else if (Players.getActualPlayer().getFraction().getName().equals("Cyberpunk")){
            this.gold.setAmount(res1);
            this.wood.setAmount(res2);
            this.stone.setAmount(res3);
            this.coal.setAmount(res4);
            this.steel.setAmount(res5);
            this.water.setAmount(res6);
            this.booze.setAmount(res7);
        }
        fillArray();
    }

    private void fillArray(){
        resourceArrayList.add(wood);
        resourceArrayList.add(stone);
        resourceArrayList.add(clay);
        resourceArrayList.add(air);
        resourceArrayList.add(leaf);
        resourceArrayList.add(coal);
        resourceArrayList.add(steel);
        resourceArrayList.add(water);
        resourceArrayList.add(rope);
        resourceArrayList.add(resin);
        resourceArrayList.add(shell);
        resourceArrayList.add(coral);
        resourceArrayList.add(ruby);
        resourceArrayList.add(booze);
        resourceArrayList.add(crystals);
        resourceArrayList.add(gold);


    }

    private void emptyResMaker(){
        wood = new SingleResource("Wood",0, "T1");
        stone = new SingleResource("Stone",0, "T1");
        clay = new SingleResource("Clay",0, "T1");
        air = new SingleResource("Air",0, "T1");
        leaf = new SingleResource("Leaf",0, "T1");
        coal = new SingleResource("Coal",0, "T1");
        steel = new SingleResource("Steel",0, "T2");
        water = new SingleResource("Water",0, "T2");
        rope = new SingleResource("Rope",0, "T2");
        resin = new SingleResource("Resin",0, "T2");
        shell = new SingleResource("Shell",0, "T2");
        coral = new SingleResource("Coral",0, "T2");
        ruby = new SingleResource("Ruby",0, "T3");
        booze = new SingleResource("Booze",0, "T3");
        crystals = new SingleResource("Crystals",0, "T3");
        gold = new SingleResource("Gold",0, "NaN");
    }

    public ArrayList<SingleResource> getResourceArrayList() {
        return resourceArrayList;
    }

    public SingleResource getWood() {
        return wood;
    }

    public SingleResource getStone() {
        return stone;
    }

    public SingleResource getClay() {
        return clay;
    }

    public SingleResource getAir() {
        return air;
    }

    public SingleResource getLeaf() {
        return leaf;
    }

    public SingleResource getCoal() {
        return coal;
    }

    public SingleResource getSteel() {
        return steel;
    }

    public SingleResource getWater() {
        return water;
    }

    public SingleResource getRope() {
        return rope;
    }

    public SingleResource getResin() {
        return resin;
    }

    public SingleResource getShell() {
        return shell;
    }

    public SingleResource getCoral() {
        return coral;
    }

    public SingleResource getRuby() {
        return ruby;
    }

    public SingleResource getBooze() {
        return booze;
    }

    public SingleResource getCrystals() {
        return crystals;
    }

    public SingleResource getGold() {
        return gold;
    }



}
