package game.enemies;

import game.items.EquipmentLocation;
import game.items.Item;
import game.peons.Peon;

import java.util.Map;

public abstract class Enemy extends Peon {
    private int expGain;
    private int healthMax;
    private int health;
    private int attack;
    private int defence;
    private int avoidChance;
    private int critChance;
    private Map<EquipmentLocation, Item> equipments;

    public Enemy(){

    }

    public int getExpGain() {
        return expGain;
    }

    public void setExpGain(int expGain) {
        this.expGain = expGain;
    }

    @Override
    public int getHealthMax() {
        return healthMax;
    }

    @Override
    public void setHealthMax(int healthMax) {
        this.healthMax = healthMax;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getDefence() {
        return defence;
    }

    @Override
    public void setDefence(int defence) {
        this.defence = defence;
    }

    @Override
    public int getAvoidChance() {
        return avoidChance;
    }

    @Override
    public void setAvoidChance(int avoidChance) {
        this.avoidChance = avoidChance;
    }

    @Override
    public int getCritChance() {
        return critChance;
    }

    @Override
    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    @Override
    public void view() {
        System.out.println("The current status of " + this.getName() + " is: ");
        System.out.println("HP: " + this.getHealth() + "/" + this.getHealthMax());
        System.out.println("MP: " + this.getMagic() + "/" + this.getMagicMax());
        System.out.println("Attack: " + this.getAttack());
        System.out.println("Defence: " + this.getDefence());
        System.out.println("AvoidChance: " + this.getAvoidChance());
        System.out.println("CritChance: " + this.getCritChance());
    }
}
