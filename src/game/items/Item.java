package game.items;

import game.enemies.Enemy;

import java.util.Random;

/* DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!!
  DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!!
  DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!!
  DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!!*/

public class Item extends Enemy {
    private final EquipmentLocation equipmentLocation;

    public Item(int playerLevel, EquipmentLocation equipmentLocation, int health, int attack, int defence, int avoidChance, int critChance) {
        Random rand = new Random();
        this.equipmentLocation = equipmentLocation;
        int swordHealth =  health + playerLevel * (rand.nextInt(2) + 1);
        this.setName(equipmentLocation.name() + "Sword");
        this.setHealth(health);
        this.setAttack(attack + playerLevel * (rand.nextInt(2) + 1));
        this.setDefence(defence + playerLevel * (rand.nextInt(2) + 1));
        this.setAvoidChance(avoidChance + playerLevel * (rand.nextInt(2) + 1));
        this.setCritChance(critChance + playerLevel * (rand.nextInt(2) + 1));
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
