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
                int basicCritChance = removeLastX(tokens[6], 11);
                int ExpGain = removeLastX(tokens[7], 8);
                int rate = removeLastX(tokens[8], 3);
                List<Integer> properties = Arrays.asList(basicHealth, basicAttack, basicDefence, basicAvoidChance, basicCritChance, ExpGain, rate);
                enemyProperties.put(enemyProperty, properties);
//                System.out.println(removeLastX(tokens[9],3));
//                System.out.println(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enemyProperties;
    }

    public Enemy generateEnemies(Player player) {
        return this.getSlime(player.getLevel());
    }

    private Enemy getSlime(int playerLevel) {
        File file = new File("json/db/enemyLevels.json");
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
            System.out.println("A wild slime shows up!");
            return new Slime(playerLevel, EnemyProperty.NORMAL, normalProperties.get(0), normalProperties.get(1), normalProperties.get(2), normalProperties.get(3), normalProperties.get(4), normalProperties.get(5));

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
}
