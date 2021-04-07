package game.peons;

import game.items.EquipmentLocation;
import game.items.Item;

import java.util.HashMap;
import java.util.Map;

public abstract class Peon {
    private String name;
    private int healthMax;
    private int health;
    private int magicMax;
    private int magic;
    private int strength;// add attack, healthMax
    private int endurance;// add defence, healthMax
    private int agility;// add avoidChance
    private int luck;// add critChance
    private int intelligence;// add magicMax
    private int attack;
    private int defence;
    private int avoidChance;
    private int critChance;
    private Map<EquipmentLocation, Item> equipments;

    public Peon() {
        this("defaut", 100, 100, new HashMap<>());
    }

    public Peon(String name, int healthMax, int health, Map<EquipmentLocation, Item> equipments) {
        this.name = name;
        this.healthMax = healthMax;
        this.health = health;
        this.equipments = equipments;
        this.attack = 0;
        this.defence = 0;
        this.avoidChance = 0;
        this.critChance = 0;

    }

    public int getHealthMax() {
        return this.healthMax;
    }

    /**
     * level up/ add strength, endurance/ equip/ unequipped
     *
     * @param healthMax
     */
    public void setHealthMax(int healthMax) {
        this.healthMax = healthMax;
    }

    public int getHealth() {
        return this.health;
    }

    /**
     * level up/ heal/ take damage
     *
     * @param health
     */
    public void setHealth(int health) {
        if (health > this.healthMax) {
            this.health = this.healthMax;
        } else {
            this.health = health;
        }

    }

    public int getMagicMax() {
        return this.magicMax;
    }

    /**
     * level up/ add intelligence/ equip/ unequipped
     *
     * @param magicMax
     */
    public void setMagicMax(int magicMax) {
        this.magicMax = magicMax;

    }

    public int getMagic() {
        return this.magic;
    }

    /**
     * level up/ magic recover
     *
     * @param magic
     */
    public void setMagic(int magic) {
        if (magic > this.magicMax) {
            this.magic = this.magicMax;
        } else {
            this.magic = magic;
        }

    }

    public int getStrength() {
        return this.strength;
    }

    /**
     * add/minus attack, healthMax by the 2 times of strength.
     *
     * @param strength
     */
    public void setStrength(int strength) {
        int dif = strength - this.strength;
        this.strength = strength;
        this.setAttack(Math.max((this.getAttack() + (dif * 2)),0));
        this.setHealthMax(Math.max((this.getHealthMax() + (dif * 2)),0));


    }

    public int getEndurance() {
        return this.endurance;
    }

    /**
     * add/minus defence as the value of endurance
     * add/minus heathMax as 3 times of endurance
     *
     * @param endurance
     */
    public void setEndurance(int endurance) {
        int dif = endurance - this.endurance;
        this.endurance = endurance;
        this.setDefence(Math.max((this.getDefence() + dif),0));
        this.setHealthMax(Math.max((this.getHealthMax() + (dif * 3)),100));


    }

    public int getAgility() {
        return agility;
    }

    /**
     * add/minus avoidChance as the value of agility
     * @param agility
     */
    public void setAgility(int agility) {
        int dif = agility - this.agility;
        this.agility = agility;
        this.setAvoidChance(Math.max((this.getAgility() + dif), 0));

    }

    public int getLuck() {
        return luck;
    }

    /**
     * add/minus critChance as the value of luck
     * @param luck
     */
    public void setLuck(int luck) {
        int dif = luck - this.agility;
        this.luck = luck;
        this.setCritChance(Math.max((this.getCritChance() + dif),0 ));
    }

    public int getIntelligence() {
        return intelligence;
    }

    /**
     * add/minus magicMax as 3 times of intelligence
     * @param intelligence
     */
    public void setIntelligence(int intelligence) {
        int dif = intelligence - this.intelligence;
        this.intelligence = intelligence;
        setMagicMax(Math.max((this.getMagicMax() + (dif * 3)),100));
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getAvoidChance() {
        return avoidChance;
    }

    public void setAvoidChance(int avoidChance) {
        this.avoidChance = avoidChance;
    }

    public int getCritChance() {
        return critChance;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    public Map<EquipmentLocation, Item> getEquipments() {
        return equipments;
    }

    public void setEquipments(Map<EquipmentLocation, Item> equipments) {
        this.equipments = equipments;
    }
}
