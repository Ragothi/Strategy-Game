package sample;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Unit {

    private String name;
    private ResourcesTypes cost;
    private int ATK,HP,ATKRange,movementRange,amount,totalHP,amountPerWeek,lostUnits;
    private String description;
    private String type;
    private String icon;
    private int actualX,actualY,initialX,initialY;
    private boolean isSet=false;
    private double initiative;
    private String fraction;
    private String fractionBackground;
    private Paint fractionColor;
    private ImageView image;

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public Paint getFractionColor() {
        return fractionColor;
    }

    public void setFractionColor(Paint fractionColor) {
        this.fractionColor = fractionColor;
    }

    public Unit(String name, int ATK, int HP, int ATKRange, int movementRange, String type, int initiative, String fraction) {
        this.name = name;
        this.ATK = ATK;
        this.HP = HP;
        this.ATKRange = ATKRange;
        this.movementRange = movementRange;
        this.type = type;
        this.amount=0;
        this.amountPerWeek=0;
        this.actualX=0;
        this.actualY=0;
        this.initialX=0;
        this.initialY=0;
        this.initiative =initiative;
        this.icon="null";
        this.fraction=fraction;
        this.totalHP=HP*amount;
    }

    public Unit() {
        this.name = "error404 unit";
        this.ATK = -1;
        this.HP = -1;
        this.ATKRange = -1;
        this.movementRange = -1;
        this.type = "no type selected";
        this.amount=-1;
        this.amountPerWeek=-1;
        this.actualX=-1;
        this.actualY=-1;
        this.initialX=-1;
        this.initialY=-1;
        this.totalHP=-1;
        this.initiative=-1;
        this.icon="-1";
    }

    public void fillUnit(Unit unit) {
        this.name = unit.getName();
        this.ATK = unit.getATK();
        this.HP = unit.getHP();
        this.ATKRange = unit.getATKRange();
        this.movementRange = unit.getMovementRange();
        this.type = unit.getType();
        this.amount=0;
        this.amountPerWeek=0;
        this.actualX=0;
        this.actualY=0;
        this.initialX=0;
        this.initialY=0;
        this.initiative =unit.getInitiative();
        this.fraction=unit.getFraction();
        this.totalHP=HP*amount;
        switch (this.fraction){
            case "Jungle" :
                this.fractionBackground=GraphicsStorage.jngUnitBackground;
                this.fractionColor=ColorsStorage.jungle;
                break;
            case "Atlantis":
                this.fractionBackground=GraphicsStorage.atlUnitBackground;
                this.fractionColor=ColorsStorage.atlantis;
                break;
            case "Cyberpunk":
                this.fractionBackground=GraphicsStorage.cbrUnitBackground;
                this.fractionColor=ColorsStorage.cyberpunk;
                break;
        }
    }

    public void addLostUnits(int lostUnits){
        this.lostUnits+=lostUnits;
    }



    public void zeroLostUnits(){
        this.lostUnits=0;
    }

    public int getLostUnits(){
        return lostUnits;
    }

    public String getFractionBackground() {
        return fractionBackground;
    }

    public String getFraction() {
        return fraction;
    }

    public void setSet(boolean set) {
        isSet = set;
    }

    public double getInitiative() {
        return initiative;
    }

    public void setInitiative(double initiative) {
        this.initiative = initiative;
    }

    public int getAmountPerWeek() {
        return amountPerWeek;
    }

    public void setAmountPerWeek(int amountPerWeek) {
        this.amountPerWeek = amountPerWeek;
    }

    public int getAmount() {
        return amount;
    }

    public int getTotalHP() {
        return totalHP;
    }

    public void setActualPosition(int actualX,int actualY) {
        this.actualX = actualX;
        this.actualY = actualY;
    }



    public void updateTotalHP(int restOfDamageDealt) {
        this.totalHP = amount*HP - restOfDamageDealt;
    }



    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public ResourcesTypes getCost() {
        return cost;
    }

    public void setCost(ResourcesTypes cost) {
        this.cost = cost;
    }

    public int getATK() {
        return ATK;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getATKRange() {
        return ATKRange;
    }

    public void setATKRange(int ATKRange) {
        this.ATKRange = ATKRange;
    }

    public int getMovementRange() {
        return movementRange;
    }

    public void setMovementRange(int movementRange) {
        this.movementRange = movementRange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStats(){
        return "all stats in one place";
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public static void buyUnit(Unit unit){
        if (Prices.canBuy(unit.getCost())){
            takeResourcesFromPlayersWallet(unit);
            unit.amount++;
        }
    }





    private static void takeResourcesFromPlayersWallet(Unit unit){
        ResourcesTypes price = unit.getCost();
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

    public void setInitialPosition(int x,int y) {
        if (x>0 && x <=Arena.height && y>0 && y<=2){
            this.initialX = x;
            this.actualX = x;
            this.initialY = y;
            this.actualY = y;
            isSet=true;
        }

    }

    public void removeUnit() {
            this.initialX = 0;
            this.actualX = 0;
            this.initialY = 0;
            this.actualY = 0;
            isSet=false;
    }

    public void setInitialX(int initialX) {
        this.initialX = initialX;
        this.actualX = initialX;
    }

    public void setInitialY(int initialY) {
        this.initialY = initialY;
        this.actualY = initialY;
    }

    public int getActualX() {
        return actualX;
    }

    public boolean isSet(){
        return isSet;
    }

    public int getActualY() {
        return actualY;
    }

    public int getInitialX() {
        return initialX;
    }

    public int getInitialY() {
        return initialY;
    }
}
