package sample;

public class Workers {

    private int total,freeToMine, goldMine,free,aT11,aT12,aT13,aT21,aT22,aT3,aMine;

    public Workers(int total) {
        this.total = total;
        free=0;
        freeToMine=0;
        goldMine =0;
        aT11=0;
        aT12=0;
        aT13=0;
        aT21=0;
        aT22=0;
        aT3=0;
        aMine=0;
    }

    public void increaseTotal(int amount){
        this.total+=amount;
        free+=amount;
    }

    public void addWorkerToArmy(){
        if (free>0 ){
            goldMine++;
            free--;
        }
        updateAssignedToMine();
    }

    public void subWorkerFromArmy(){
        if (goldMine >0 ){
            goldMine--;
            free++;
        }
        updateAssignedToMine();
    }

    public void addWorkerToMine(){
        if (free>0 ){
            freeToMine++;
            free--;
        }
        updateAssignedToMine();
    }

    public void subWorkerFromMine(){
        if (freeToMine>0 ){
            freeToMine--;
            free++;
        }
        updateAssignedToMine();
    }

    public void addWorkerToAT11(){
        if (freeToMine>0 ){
            aT11++;
            freeToMine--;
        }
        updateAssignedToMine();
    }

    public void subWorkerFromAT11(){
        if (getaT11()>0 ){
            freeToMine++;
            aT11--;
        }
        updateAssignedToMine();
    }


    public void addWorkerToAT12(){
        if (freeToMine>0 ){
            aT12++;
            freeToMine--;
        }
        updateAssignedToMine();
    }

    public void subWorkerFromAT12(){
        if (getaT12()>0 ){
            freeToMine++;
            aT12--;
        }
        updateAssignedToMine();
    }

    public void addWorkerToAT13(){
        if (freeToMine>0 ){
            aT13++;
            freeToMine--;
        }
        updateAssignedToMine();
    }

    public void subWorkerFromAT13(){
        if (getaT13()>0 ){
            freeToMine++;
            aT13--;
        }
        updateAssignedToMine();
    }

    public void addWorkerToAT21(){
        if (freeToMine>0 ){
            aT21++;
            freeToMine--;
        }
        updateAssignedToMine();
    }

    public void subWorkerFromAT21(){
        if (getaT21()>0 ){
            freeToMine++;
            aT21--;
        }
        updateAssignedToMine();
    }

    public void addWorkerToAT22(){
        if (freeToMine>0 ){
            aT22++;
            freeToMine--;
        }
        updateAssignedToMine();
    }

    public   void subWorkerFromAT22(){
        if (getaT22()>0 ){
            freeToMine++;
            aT22--;
        }
        updateAssignedToMine();
    }

    public   void addWorkerToAT3(){
        if (freeToMine>0 ){
            aT3++;
            freeToMine--;
        }
        updateAssignedToMine();
    }

    public   void subWorkerFromAT3(){
        if (getaT3()>0 ){
            freeToMine++;
            aT3--;
        }
        updateAssignedToMine();
    }


    public   void updateAssignedToMine(){
        aMine=aT11+aT12+aT13+aT21+aT22+aT3;
    }

    public   int getTotal() {
        return total;
    }

    public   int getFreeToMine() {
        return freeToMine;
    }

    public   int getGoldMine() {
        return goldMine;
    }

    public   int getFree() {
        return free;
    }

    public   int getaT11() {
        return aT11;
    }

    public   int getaT12() {
        return aT12;
    }

    public   int getaT13() {
        return aT13;
    }

    public   int getaT21() {
        return aT21;
    }

    public   int getaT22() {
        return aT22;
    }

    public   int getaT3() {
        return aT3;
    }

    public   int getaMine() {
        return aMine;
    }
}
