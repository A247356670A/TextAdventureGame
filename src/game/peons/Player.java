package game.peons;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import game.items.EquipmentLocation;
import game.items.Item;
import game.locations.Location;
import game.locations.LocationUtility;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.*;

public class Player extends Peon {
    private int exp;
    private int level;
    private Location location;
    private static final HashMap<Integer, List<Integer>> characterLevels = new HashMap();

    public Player(String name) {
        this.setName(name);
        loadCharacterLevels("json/db/characterLevels.json");
        this.exp = 0;
        this.level = 1;
        this.setMagicMax(100);
        this.setMagic(100);
        this.setHealthMax(100);
        this.setHealth(100);
        this.setStrength(8);
        this.setEndurance(8);
        this.setAgility(8);
        this.setLuck(8);
        this.setIntelligence(8);
        this.setEquipments(new HashMap<>());
        saveToJSONFile("json/profile/player.json");

    }

    public Player(String name, int exp, int level, int healthMax, int health, int magicMax, int magic, int strength, int endurance, int agility, int luck, int intelligence, Map<EquipmentLocation, Item> equipments) {
        loadCharacterLevels("json/db/characterLevels.json");
        this.setName(name);
        this.exp = exp;
        this.level = level;
        this.setMagicMax(magicMax);
        this.setMagic(magic);
        this.setHealthMax(healthMax);
        this.setHealth(health);
        this.setStrength(strength);
        this.setEndurance(endurance);
        this.setAgility(agility);
        this.setLuck(luck);
        this.setIntelligence(intelligence);
        this.setEquipments(equipments);
        saveToJSONFile("json/profile/player.json");
    }

    private static int removeLastX(String str, int x) {
        if (str != null) {
            str = str.substring(0, str.length() - x);
        }
        return Integer.parseInt(str);
    }

    public void loadCharacterLevels(String fileName) {
        Gson gson = new Gson();
        JsonReader jsonReader;
        try {
            jsonReader = new JsonReader(new FileReader(fileName));
            List<JsonObject> list = gson.fromJson(jsonReader, List.class);
            for (Object b : list) {
                String[] tokens = b.toString().split("=");// 1: level; 2: exp; 3: healthMax; 4: magicMax; 5: strength; 6: endurance; 7: agility 8: luck; 9: intelligence.
                int level = removeLastX(tokens[1], 7);
                int exp = removeLastX(tokens[2], 13);
                int addHealthMax = removeLastX(tokens[3], 12);
                int addMagicMax = removeLastX(tokens[4], 12);
                int addStrength = removeLastX(tokens[5], 13);
                int addEndurance = removeLastX(tokens[6], 11);
                int addAgility = removeLastX(tokens[7], 8);
                int addLuck = removeLastX(tokens[8], 16);
                int addIntelligence = removeLastX(tokens[9], 3);
                List<Integer> properties = Arrays.asList(exp, addHealthMax, addMagicMax, addStrength, addEndurance, addAgility, addLuck, addIntelligence);
                characterLevels.put(level, properties);
//                System.out.println(removeLastX(tokens[9],3));
//                System.out.println(b);
            }
        } catch (Exception e) {
            System.err.println("There is an error when load: \"json/db/characterLevels.json\"");
            e.printStackTrace();
        }

    }

    public void saveToJSONFile(String fileName) {
        File file = new File(fileName);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter fw = new FileWriter(file, false)) {
            gson.toJson(this, fw);
        } catch (Exception e) {
            System.err.println("There is an error when save to : \"json/profile/player.json\"");
            e.printStackTrace();
        }

    }

    public HashMap<Integer, List<Integer>> getCharacterLevels() {
        return characterLevels;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
        System.out.println("(" + this.exp + "/" + this.getCharacterLevels().get(this.getLevel()).get(0) + ")");
        levelUp();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        System.out.println("You are now level " + this.level + " !");
    }
    public void healAll(){
        this.setHealth(this.getHealthMax());
    }

    public void heal(int healthPoint){
        if (healthPoint <= this.getHealthMax()){
            this.setHealth(healthPoint + this.getHealth());
        }else {
            this.setHealth(this.getHealthMax());
        }

    }
    public void magicRecoverAll(){
        this.setMagic(this.getMagicMax());
    }
    public void magicRecover(int magicPoint){
        if (magicPoint <= this.getMagicMax()){
            this.setMagic(magicPoint + this.getMagic());
        }else {
            this.setMagic(this.getMagicMax());
        }
    }

    public void levelUp() {// 0: exp; 1: healthMax; 2: magicMax; 3: strength; 4: endurance; 5: agility 6: luck; 7: intelligence.
//        boolean levelUpFlag = true;
        int pointLeft = 3;
        int currentLevel = this.getLevel();
        int expMax = this.getCharacterLevels().get(currentLevel).get(0);
        if (this.getExp() >= expMax) {
            if (this.getCharacterLevels().containsKey(currentLevel + 1)) {
                currentLevel = currentLevel + 1;
                this.setLevel(currentLevel);
                while (pointLeft > 0){
                    System.out.println("You now get " + pointLeft + " skill point.");
                    System.out.println("which skill would you like to improve?");
                    System.out.println("1. Strength");
                    System.out.println("2. Endurance");
                    System.out.println("3. Agility");
                    System.out.println("4. Luck");
                    System.out.println("5. Intelligence");
                    char key = LocationUtility.readMapSelection();
                    System.out.println();

                    if (key == '1') {
                        System.out.println("Strength will increase your HealthMax and Attack.");
                        System.out.println("Do you want to increase Strength? (Y/N)");
                        char confirm = LocationUtility.readConfirmSelection();
                        if (confirm == 'Y'){
                            this.setStrength(this.getStrength() + this.getCharacterLevels().get(currentLevel).get(3));
                            System.out.println("your Strength increased " + this.getCharacterLevels().get(currentLevel).get(3) + " points!");
                            pointLeft--;
                            continue;
                        }else if (confirm == 'N'){
                            continue;
                        }
                    } else if (key == '2') {
                        System.out.println("Endurance will increase your HealthMax and Defence.");
                        System.out.println("Do you want to increase Endurance? (Y/N)");
                        char confirm = LocationUtility.readConfirmSelection();
                        if (confirm == 'Y'){
                            this.setEndurance(this.getEndurance() + this.getCharacterLevels().get(currentLevel).get(4));
                            System.out.println("your Endurance increased " + this.getCharacterLevels().get(currentLevel).get(4) + " points!");
                            pointLeft--;
                            continue;
                        }else if (confirm == 'N'){
                            continue;
                        }

                    } else if (key == '3') {
                        System.out.println("Agility will increase your AvoidChance.");
                        System.out.println("Do you want to increase Agility? (Y/N)");
                        char confirm = LocationUtility.readConfirmSelection();
                        if (confirm == 'Y'){
                            this.setAgility(this.getAgility() + this.getCharacterLevels().get(currentLevel).get(5));
                            System.out.println("your Agility increased " + this.getCharacterLevels().get(currentLevel).get(5) + " points!");
                            pointLeft--;
                            continue;
                        }else if (confirm == 'N'){
                            continue;
                        }

                    } else if (key == '4') {
                        System.out.println("Luck will increase your CritChance.");
                        System.out.println("Do you want to increase Luck? (Y/N)");
                        char confirm = LocationUtility.readConfirmSelection();
                        if (confirm == 'Y'){
                            this.setLuck(this.getLuck() + this.getCharacterLevels().get(currentLevel).get(6));
                            System.out.println("your Luck increased " + this.getCharacterLevels().get(currentLevel).get(6) + " points!");
                            pointLeft--;
                            continue;
                        }else if (confirm == 'N'){
                            continue;
                        }

                    } else if (key == '5') {
                        System.out.println("Intelligence will increase your magicMax.");
                        System.out.println("Do you want to increase Intelligence? (Y/N)");
                        char confirm = LocationUtility.readConfirmSelection();
                        if (confirm == 'Y'){
                            this.setIntelligence(this.getIntelligence() + this.getCharacterLevels().get(currentLevel).get(7));
                            System.out.println("your Intelligence increased " + this.getCharacterLevels().get(currentLevel).get(7) + " points!");
                            pointLeft--;
                            continue;
                        }else if (confirm == 'N'){
                            continue;
                        }

                    }
                }

                this.setHealthMax(this.getHealthMax() + this.getCharacterLevels().get(currentLevel).get(1));
                this.setMagicMax(this.getMagicMax() + this.getCharacterLevels().get(currentLevel).get(2));
                this.healAll();
                this.magicRecoverAll();
                this.view();
                saveToJSONFile("json/profile/player.json");
            }


        }
    }

    public void equip(Item item){
        if (this.getEquipment(item.getEquipmentLocation()) == null){
            System.out.println("You choose to equip " + item.getName());
           setTheEquipments(item.getEquipmentLocation(),item);
           this.saveToJSONFile("json/profile/player.json");
        }else {
            System.out.println("The current equipment is:");
            this.getEquipment(item.getEquipmentLocation()).view();
            System.out.println("Do you want to replace it?");
            char confirm = LocationUtility.readConfirmSelection();
            if (confirm == 'Y'){
                System.out.println("You choose to replace this.");
                setTheEquipments(item.getEquipmentLocation(),item);
                this.saveToJSONFile("json/profile/player.json");
            }else {
                System.out.println("You choose not to replace this.");
            }
        }
    }


    public Item getEquipment(EquipmentLocation equipmentLocation){
        if (this.getEquipments().get(equipmentLocation)== null){
//            System.out.println("The " + equipmentLocation.name() + " is currently equipped nothing.");
            return null;
        }else {
            return this.getEquipments().get(equipmentLocation);
        }

    }

    @Override
    public void view() {
        System.out.println("The current status of " + this.getName() + " is: ");
        System.out.println("EXP: "+ this.getExp() + "/" + this.getCharacterLevels().get(this.getLevel()).get(0));
        System.out.println("HP: " + this.getHealth() + "/" + this.getHealthMax());
        System.out.println("MP: " + this.getMagic() + "/" + this.getMagicMax());
        System.out.println("STR: " + this.getStrength());
        System.out.println("EDU: " + this.getEndurance());
        System.out.println("AGI: " + this.getAgility());
        System.out.println("LUCK: " + this.getLuck());
        System.out.println("INT: " + this.getIntelligence());
    }

    public Map<EquipmentLocation, Item> getTheEquipments() {
        return this.getEquipments();
    }

    public void setTheEquipments(EquipmentLocation equipmentLocation, Item item) {

        this.getEquipments().put(equipmentLocation,item);
    }
}
