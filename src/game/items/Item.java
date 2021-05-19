package game.items;

import game.enemies.Enemy;

import java.util.Random;

public class Item {
    private final EquipmentLocation equipmentLocation;
    private String name;
    private int level;
    private int health;
    private int attack;
    private int defence;
    private int avoidChance;
    private int critChance;

    public Item(int playerLevel, EquipmentLocation equipmentLocation, int health, int attack, int defence, int avoidChance, int critChance) {
        Random rand = new Random();
        this.equipmentLocation = equipmentLocation;
        this.setLevel(playerLevel);
        int swordHealth =  health + playerLevel * (rand.nextInt(2) + 1);
        this.setName(equipmentLocation.name());
        this.setHealth(swordHealth);
        this.setAttack(attack + playerLevel * (rand.nextInt(2) + 1));
        this.setDefence(defence + playerLevel * (rand.nextInt(2) + 1));
        this.setAvoidChance(avoidChance + playerLevel * (rand.nextInt(2) + 1));
        this.setCritChance(critChance + playerLevel * (rand.nextInt(2) + 1));
    }
    public int getLevel(){ return level; }

    public void setLevel(int level){this.level = level;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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
}
/*package game.items;

import java.util.Map;
import java.util.TreeMap;

public class Item {
    private final int playerLevel;
    private final String id;
    private final String type;
    private final String name;
    private final String description;
    private final EquipmentLocation position;
    private final Map<String, Integer> properties;

    public Item(int playerLevel, String id, String type, String name, String description, Map<String, Integer> properties) {
        this(playerLevel,id, type, name, description, (EquipmentLocation)null, properties);
    }

    public Item(int playerLevel, String id, String type, String name, String description, EquipmentLocation position,  Map<String, Integer> properties) {
        this.playerLevel = playerLevel;
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.position = position;
        if (properties != null) {
            this.properties = properties;
        } else {
            this.properties = new TreeMap();
        }

    }
}*/
