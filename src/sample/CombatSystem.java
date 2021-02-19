package sample;

import java.util.ArrayList;

public class CombatSystem {

    public static ArrayList<Unit> attackingArmy;
    public static ArrayList<Unit> defendingArmy;
    public static int attackerBaseHP;
    public static int defenderBaseHP;

    //returns true if unit is killed and false if unit is alive
    public static boolean dealDamageIsKilled(Unit attackingUnit, Unit defendingUnit){
        if (isInATKRange(attackingUnit,defendingUnit)){
            int damageDealt = attackingUnit.getATK()*attackingUnit.getAmount();
            int lostUnits = damageDealt/defendingUnit.getHP();
            if (lostUnits>defendingUnit.getAmount()){
                lostUnits=defendingUnit.getAmount();
            }
            int remainingUnits = defendingUnit.getAmount() - lostUnits;
            int restOfDamageDealt = damageDealt%defendingUnit.getHP();
            defendingUnit.addLostUnits(lostUnits);
            defendingUnit.setAmount(remainingUnits);
            defendingUnit.updateTotalHP(restOfDamageDealt);
            if (defendingUnit.getTotalHP()<=0){
                defendingUnit.setAmount(0);
                return true;
            }
        }
        return false;
    }

    private static int dx(Unit actualPosition,Unit targetPosition){
        return Math.abs(actualPosition.getActualX()-targetPosition.getActualX());
    }

    private static int dy(Unit actualPosition,Unit targetPosition){
        return Math.abs(actualPosition.getActualY()-targetPosition.getActualY());
    }

    public static int dx(Unit unit,Field field){
        return Math.abs(unit.getActualX()-field.getX());
    }


    public static int dy(Unit unit,Field field){
        return Math.abs(unit.getActualY()-field.getY());
    }

    public static boolean isInATKRange(Unit attackingUnit, Unit defendingUnit){
        if (dx(attackingUnit,defendingUnit)<= attackingUnit.getATKRange()
                &&dy(attackingUnit,defendingUnit) <= attackingUnit.getATKRange()){
            return true;
        }
        return false;
    }


    public static boolean isFieldInUnitRange(Unit unit, Field field){
        if (dx(unit,field)<=unit.getMovementRange() && dy(unit,field)<=unit.getMovementRange()){
//            System.out.println("Unit: " + unit.getName() + " unit x: " + unit.getActualX() + " unit Y: " + unit.getActualY()
//                    + "field x: " + field.getX() + " field Y: " + field.getY()
//                    + "\n dx: " + dx(unit,field) + " dy: " + dy(unit,field));


            return true;
        }
        return false;
    }

//    public static boolean isFieldInUnitRangev2(Unit unit, Field field){
//        if (Math.sqrt (dx(unit,field)+dy(unit,field))
//                <= unit.getATKRange()){
//            return true;
//        }
//        return false;
//    }

    public static ArrayList<Unit> getAttackingArmy() {
        return attackingArmy;
    }

    public static void setAttackingArmy(ArrayList<Unit> attackingArmy) {
        CombatSystem.attackingArmy = attackingArmy;
    }

    public static ArrayList<Unit> getDefendingArmy() {
        return defendingArmy;
    }

    public static void setDefendingArmy(ArrayList<Unit> defendingArmy) {
        CombatSystem.defendingArmy = defendingArmy;
    }

    public static int getAttackerBaseHP() {
        return attackerBaseHP;
    }

    public static void setAttackerBaseHP(int attackerBaseHP) {
        CombatSystem.attackerBaseHP = attackerBaseHP;
    }

    public static int getDefenderBaseHP() {
        return defenderBaseHP;
    }

    public static void setDefenderBaseHP(int defenderBaseHP) {
        CombatSystem.defenderBaseHP = defenderBaseHP;
    }
}

