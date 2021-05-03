package game.enemies;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import game.peons.Player;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class EnemyFactory {

//    private static final HashMap<String, List<Integer>> enemyProperties = new HashMap();

    public EnemyFactory() {
    }

    private static int removeLastX(String str, int x) {
        if (str != null) {
            str = str.substring(0, str.length() - x);
        }
        return Integer.parseInt(str);
    }

    public HashMap<String, List<Integer>> loadEnemyProperties(File file) {
        HashMap<String, List<Integer>> enemyProperties = new HashMap();
        Gson gson = new Gson();
        JsonReader jsonReader;
        try {
            jsonReader = new JsonReader(new FileReader(file));
            List<JsonObject> list = gson.fromJson(jsonReader, List.class);
            for (Object b : list) {
                String[] tokens = b.toString().split("=");// 1: Property; 2: health; 3: Attack; 4: Defence; 5: AvoidChance; 6: CritChance; 7: ExpGain; 8:rate
                String enemyProperty = tokens[1].substring(0, tokens[1].length() - 13);
                int basicHealth = removeLastX(tokens[2], 15);
                int basicAttack = removeLastX(tokens[3], 16);
                int basicDefence = removeLastX(tokens[4], 20);
                int basicAvoidChance = removeLastX(tokens[5], 19);
                int basicCritChance = removeLastX(tokens[6], 15);
                int skillChance = removeLastX(tokens[7], 11);
                int expGain = removeLastX(tokens[8], 8);
                int rate = removeLastX(tokens[9], 3);
                List<Integer> properties = Arrays.asList(basicHealth, basicAttack, basicDefence, basicAvoidChance, basicCritChance, expGain, rate, skillChance);
                enemyProperties.put(enemyProperty, properties);
//                System.out.println(removeLastX(tokens[9],3));
//                System.out.println(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enemyProperties;
    }

    public HashMap<String, Integer> loadEnemies(File file) {
        HashMap<String, Integer> enemies = new HashMap();
        Gson gson = new Gson();
        JsonReader jsonReader;
        try {
            jsonReader = new JsonReader(new FileReader(file));
            List<JsonObject> list = gson.fromJson(jsonReader, List.class);
            for (Object b : list) {
                String[] tokens = b.toString().split("=");
//                System.out.println(b);
                String enemyName = tokens[1].substring(0, tokens[1].length() - 6);
                int rate = removeLastX(tokens[2], 3);
                enemies.put(enemyName, rate);
//                System.out.println(removeLastX(tokens[9],3));
//                System.out.println(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enemies;
    }
    public Enemy generateEnemies(Player player) {
        File file = new File("json/db/enemies.json");
        HashMap<String, Integer> enemies = loadEnemies(file);
        Random random = new Random();
        int result = random.nextInt(enemies.get("SLIME") + enemies.get("GOBLIN"));
        if (result < enemies.get("SLIME") - player.getLevel() * 2){
            return this.getSlime(player.getLevel());
        }else {
            return this.getGoblin(player.getLevel());
        }

    }

    private Enemy getSlime(int playerLevel) {
        File file = new File("json/db/slimeProperties.json");
        HashMap<String, List<Integer>> slimeProperties = loadEnemyProperties(file);
        List<Integer> normalProperties = slimeProperties.get("NORMAL");
        List<Integer> kawaiProperties = slimeProperties.get("KAWAI");
        List<Integer> icedProperties = slimeProperties.get("ICED");
        List<Integer> firedProperties = slimeProperties.get("FIRED");
        List<Integer> rockedProperties = slimeProperties.get("ROCKED");
        List<Integer> ghostedProperties = slimeProperties.get("GHOSTED");
        Random random = new Random();
        int result = random.nextInt(normalProperties.get(6) + kawaiProperties.get(6) + icedProperties.get(6) + firedProperties.get(6) + rockedProperties.get(6) + ghostedProperties.get(6));
        if (result >= 0 && result < normalProperties.get(6)) {// NORMAL 22%
            if (slimeProperties.containsKey("NORMAL")) {
                System.out.println("A wild slime shows up!");
                return new Slime(playerLevel, EnemyProperty.NORMAL, normalProperties.get(0), normalProperties.get(1), normalProperties.get(2), normalProperties.get(3), normalProperties.get(4), normalProperties.get(5));
            }
        } else if (result >= normalProperties.get(6) && result < normalProperties.get(6) + kawaiProperties.get(6)) {// KAWAI 2%
            if (slimeProperties.containsKey("KAWAI")) {
                System.out.println("A KAWAI slime shows up!");
                return new Slime(playerLevel, EnemyProperty.KAWAI, kawaiProperties.get(0), kawaiProperties.get(1), kawaiProperties.get(2), kawaiProperties.get(3), kawaiProperties.get(4), kawaiProperties.get(5));
            }

        } else if (result >= normalProperties.get(6) + kawaiProperties.get(6) && result < normalProperties.get(6) + kawaiProperties.get(6) + icedProperties.get(6)) {// ICED 22%
            if (slimeProperties.containsKey("ICED")) {
                System.out.println("A ICED slime shows up!");
                return new Slime(playerLevel, EnemyProperty.ICED, icedProperties.get(0), icedProperties.get(1), icedProperties.get(2), icedProperties.get(3), icedProperties.get(4), icedProperties.get(5));
            }

        } else if (result >= normalProperties.get(6) + kawaiProperties.get(6) + icedProperties.get(6) && result < normalProperties.get(6) + kawaiProperties.get(6) + icedProperties.get(6) + firedProperties.get(6)) {// FIRED 22%
            if (slimeProperties.containsKey("FIRED")) {
                System.out.println("A FIRED slime shows up!");
                return new Slime(playerLevel, EnemyProperty.FIRED, firedProperties.get(0), firedProperties.get(1), firedProperties.get(2), firedProperties.get(3), firedProperties.get(4), firedProperties.get(5));
            }

        } else if (result >= normalProperties.get(6) + kawaiProperties.get(6) + icedProperties.get(6) + firedProperties.get(6) && result < normalProperties.get(6) + kawaiProperties.get(6) + icedProperties.get(6) + firedProperties.get(6) + rockedProperties.get(6)) {// ROCKED 22%
            if (slimeProperties.containsKey("ROCKED")) {
                System.out.println("A ROCKED slime shows up!");
                return new Slime(playerLevel, EnemyProperty.ROCKED, rockedProperties.get(0), rockedProperties.get(1), rockedProperties.get(2), rockedProperties.get(3), rockedProperties.get(4), rockedProperties.get(5));
            }

        } else {// GHOSTED 10%
            if (slimeProperties.containsKey("GHOSTED")) {

                System.out.println("A GHOSTED slime shows up!");
                return new Slime(playerLevel, EnemyProperty.GHOSTED, ghostedProperties.get(0), ghostedProperties.get(1), ghostedProperties.get(2), ghostedProperties.get(3), ghostedProperties.get(4), ghostedProperties.get(5));
            }

        }
        return null;
    }
    private Enemy getGoblin(int playerLevel) {
        File file = new File("json/db/goblinProperties.json");
        HashMap<String, List<Integer>> goblinProperties = loadEnemyProperties(file);
        List<Integer> normalProperties = goblinProperties.get("NORMAL");
        List<Integer> poisonedProperties = goblinProperties.get("POISONED");
        List<Integer> wickedProperties = goblinProperties.get("WICKED");
        List<Integer> heavyArmoredProperties = goblinProperties.get("HEAVYARMORED");
        List<Integer> eliteProperties = goblinProperties.get("ELITE");
        Random random = new Random();
        int result = random.nextInt(normalProperties.get(6) + poisonedProperties.get(6) + wickedProperties.get(6) + heavyArmoredProperties.get(6) + eliteProperties.get(6));
        if (result >= 0 && result < normalProperties.get(6)) {
            if (goblinProperties.containsKey("NORMAL")) {
                System.out.println("A goblin shows up!");
                return new Goblin(playerLevel, EnemyProperty.NORMAL, normalProperties.get(0), normalProperties.get(1), normalProperties.get(2), normalProperties.get(3), normalProperties.get(4), normalProperties.get(5));
            }
        } else if (result >= normalProperties.get(6) && result < normalProperties.get(6) + poisonedProperties.get(6)) {
            if (goblinProperties.containsKey("POISONED")) {
                System.out.println("A POISONED goblin shows up!");
                return new Goblin(playerLevel, EnemyProperty.POISONED, poisonedProperties.get(0), poisonedProperties.get(1), poisonedProperties.get(2), poisonedProperties.get(3), poisonedProperties.get(4), poisonedProperties.get(5));
            }

        } else if (result >= normalProperties.get(6) + poisonedProperties.get(6) && result < normalProperties.get(6) + poisonedProperties.get(6) + wickedProperties.get(6)) {
            if (goblinProperties.containsKey("WICKED")) {
                System.out.println("A WICKED goblin shows up!");
                return new Goblin(playerLevel, EnemyProperty.WICKED, wickedProperties.get(0), wickedProperties.get(1), wickedProperties.get(2), wickedProperties.get(3), wickedProperties.get(4), wickedProperties.get(5));
            }

        } else if (result >= normalProperties.get(6) + poisonedProperties.get(6) + wickedProperties.get(6) && result < normalProperties.get(6) + poisonedProperties.get(6) + wickedProperties.get(6) + heavyArmoredProperties.get(6)) {
            if (goblinProperties.containsKey("HEAVYARMORED")) {
                System.out.println("A HEAVYARMORED goblin shows up!");
                return new Goblin(playerLevel, EnemyProperty.HEAVYARMORED, heavyArmoredProperties.get(0), heavyArmoredProperties.get(1), heavyArmoredProperties.get(2), heavyArmoredProperties.get(3), heavyArmoredProperties.get(4), heavyArmoredProperties.get(5));
            }

        }  else {
            if (goblinProperties.containsKey("ELITE")) {
                System.out.println("A ELITE goblin shows up!");
                return new Goblin(playerLevel, EnemyProperty.ELITE, eliteProperties.get(0), eliteProperties.get(1), eliteProperties.get(2), eliteProperties.get(3), eliteProperties.get(4), eliteProperties.get(5));
            }

        }
        return null;
    }
}
