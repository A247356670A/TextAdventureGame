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
                String[] tokens = b.toString().split("=");// 1: Property; 2: health; 3: Attack; 4: Defence; 5: AvoidChance; 6: CritChance; 7: ExpGain
                String enemyProperty = tokens[1].substring(0, tokens[1].length() - 13);
                int basicHealth = removeLastX(tokens[2],15);
                int basicAttack = removeLastX(tokens[3],16);
                int basicDefence = removeLastX(tokens[4],20);
                int basicAvoidChance = removeLastX(tokens[5],19);
                int basicCritChance = removeLastX(tokens[6],11);
                int ExpGain = removeLastX(tokens[7],3);
                List<Integer> properties= Arrays.asList(basicHealth,basicAttack,basicDefence, basicAvoidChance,basicCritChance,ExpGain);
                enemyProperties.put(enemyProperty, properties);
//                System.out.println(removeLastX(tokens[9],3));
//                System.out.println(enemyProperties);
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
        Random random = new Random();
        int result = random.nextInt(100);
        if (result >= 0 && result < 22){// NORMAL 22%
            if (slimeProperties.containsKey("NORMAL")){
                List<Integer> normalProperties = slimeProperties.get("NORMAL");
                System.out.println("A wind slime shows up!");
                return new Slime(playerLevel,EnemyProperty.NORMAL,normalProperties.get(0),normalProperties.get(1),normalProperties.get(2), normalProperties.get(3), normalProperties.get(4), normalProperties.get(5));
            }

        }else if (result >= 22 && result < 24){// KAWAI 2%
            if (slimeProperties.containsKey("KAWAI")){
                List<Integer> normalProperties = slimeProperties.get("KAWAI");
                System.out.println("A KAWAI slime shows up!");
                return new Slime(playerLevel,EnemyProperty.KAWAI,normalProperties.get(0),normalProperties.get(1),normalProperties.get(2), normalProperties.get(3), normalProperties.get(4), normalProperties.get(5));
            }

        }else if (result >= 24 && result < 46){// ICED 22%
            if (slimeProperties.containsKey("ICED")){
                List<Integer> normalProperties = slimeProperties.get("ICED");
                System.out.println("A ICED slime shows up!");
                return new Slime(playerLevel,EnemyProperty.ICED,normalProperties.get(0),normalProperties.get(1),normalProperties.get(2), normalProperties.get(3), normalProperties.get(4), normalProperties.get(5));
            }

        }else if (result >= 46 && result < 68){// FIRED 22%
            if (slimeProperties.containsKey("FIRED")){
                List<Integer> normalProperties = slimeProperties.get("FIRED");
                System.out.println("A FIRED slime shows up!");
                return new Slime(playerLevel,EnemyProperty.FIRED,normalProperties.get(0),normalProperties.get(1),normalProperties.get(2), normalProperties.get(3), normalProperties.get(4), normalProperties.get(5));
            }

        }else if (result >= 68 && result < 90){// ROCKED 22%
            if (slimeProperties.containsKey("ROCKED")){
                List<Integer> normalProperties = slimeProperties.get("ROCKED");
                System.out.println("A ROCKED slime shows up!");
                return new Slime(playerLevel,EnemyProperty.ROCKED,normalProperties.get(0),normalProperties.get(1),normalProperties.get(2), normalProperties.get(3), normalProperties.get(4), normalProperties.get(5));
            }

        }else{// GHOSTED 10%
            if (slimeProperties.containsKey("GHOSTED")){
                List<Integer> normalProperties = slimeProperties.get("GHOSTED");
                System.out.println("A GHOSTED slime shows up!");
                return new Slime(playerLevel,EnemyProperty.GHOSTED,normalProperties.get(0),normalProperties.get(1),normalProperties.get(2), normalProperties.get(3), normalProperties.get(4), normalProperties.get(5));
            }

        }
        return null;
    }
}
