package game.peons;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import game.locations.Location;

import java.io.FileReader;
import java.io.Reader;
import java.util.*;

public class Player extends Peon {
    private int exp;
    private Location location;
    private static HashMap<Integer, List<Integer>> characterLevels = new HashMap();

    public Player() {

    }

    private static int removeLastX(String str, int x) {
        if (str != null) {
            str = str.substring(0, str.length() - x);
        }
        return Integer.parseInt(str);
    }

    public void loadCharacterLevels() {
        Gson gson = new Gson();
        JsonReader jsonReader = null;
        try {
            jsonReader = new JsonReader(new FileReader("json/db/characterLevels.json"));
            List<JsonObject> list = gson.fromJson(jsonReader, List.class);
            for (Object b : list) {
                String[] tokens = b.toString().split("=");// 1: level; 2: exp; 3: healthMax; 4: magicMax; 5: strength; 6: endurance; 7: agility 8: luck; 9: intelligence.
                int level = removeLastX(tokens[1],7);
                int exp = removeLastX(tokens[2],13);
                int addHealthMax = removeLastX(tokens[3],12);
                int addMagicMax = removeLastX(tokens[4],12);
                int addStrength = removeLastX(tokens[5],13);
                int addEndurance = removeLastX(tokens[6],11);
                int addAgility = removeLastX(tokens[7],8);
                int addLuck = removeLastX(tokens[8],16);
                int addIntelligence = removeLastX(tokens[9],3);
                List<Integer> properties= Arrays.asList(exp,addHealthMax,addMagicMax,addStrength, addEndurance,addAgility,addLuck,addIntelligence);
                characterLevels.put(level, properties);
                System.out.println(characterLevels);
//                System.out.println(removeLastX(tokens[9],3));
//                System.out.println(b);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
