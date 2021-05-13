package game;


//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import game.locations.Location;
import game.peons.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static game.locations.LocationUtility.readKeyBoard;
import static org.junit.Assert.*;

public class AutomaticTester {

    private static int removeLastX(String str, int x) {
        if (str != null) {
            str = str.substring(0, str.length() - x);
        }
        return Integer.parseInt(str);
    }
    private static String removeLastXS(String str, int x) {
        if (str != null) {
            str = str.substring(0, str.length() - x);
        }
        return str;
    }
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    /**
     * {
     * "Level": 1,
     * "exp": 10,
     * "healthMax": 0,
     * "magicMax": 0,
     * "strength": 0,
     * "endurance": 0,
     * "agility": 0,
     * "luck": 0,
     * "intelligence": 0
     * },
     */
    @Test
    public void automaticTester1() {
        System.out.println("Checking CharacterLevels.json....");
        boolean success = false;
        final HashMap<Integer, List<Integer>> characterLevels = new HashMap();
        File automaticTester1 = new File("json/db/CharacterLevels.json");
        Gson gson = new Gson();
        JsonReader jsonReader;
        try {
            jsonReader = new JsonReader(new FileReader(automaticTester1));
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
                success = true;
            }
        } catch (Exception e) {
            System.err.println("There is an error when load: \"json/db/characterLevels.json\"");

            e.printStackTrace();
        }
        if (success) {
            System.out.println("CharacterLevels.json Status: OK!");
        }

    }

    @Test
    public void automaticTester2() {
        System.out.println("Checking enemies.json....");
        boolean success = false;
        File automaticTester = new File("json/db/enemies.json");
        HashMap<String, Integer> enemies = new HashMap();
        Gson gson = new Gson();
        JsonReader jsonReader;
        try {
            jsonReader = new JsonReader(new FileReader(automaticTester));
            List<JsonObject> list = gson.fromJson(jsonReader, List.class);
            for (Object b : list) {
                String[] tokens = b.toString().split("=");
                String enemyName = tokens[1].substring(0, tokens[1].length() - 6);
                int rate = removeLastX(tokens[2], 3);
                enemies.put(enemyName, rate);
                success = true;
            }
        } catch (Exception e) {
            System.err.println("There is an error when load: \"json/db/enemies.json\"");
            e.printStackTrace();
        }

        if (success) {
            System.out.println("enemies.json Status: OK!");
        }

    }

    @Test
    public void automaticTester3() {
        System.out.println("Checking goblinProperties.json....");
        boolean success = false;
        final HashMap<Integer, List<Integer>> characterLevels = new HashMap();
        File automaticTester = new File("json/db/goblinProperties.json");
        HashMap<String, List<Integer>> enemyProperties = new HashMap();
        Gson gson = new Gson();
        JsonReader jsonReader;
        try {
            jsonReader = new JsonReader(new FileReader(automaticTester));
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
                success = true;
            }
        }catch (Exception e) {
            System.err.println("There is an error when load: \"json/db/goblinProperties.json\"");
            e.printStackTrace();
        }
        if (success) {
            System.out.println("goblinProperties.json Status: OK!");
        }

    }

    @Test
    public void automaticTester4() {
        System.out.println("Checking slimeProperties.json....");
        boolean success = false;
        final HashMap<Integer, List<Integer>> characterLevels = new HashMap();
        File automaticTester = new File("json/db/slimeProperties.json");
        HashMap<String, List<Integer>> enemyProperties = new HashMap();
        Gson gson = new Gson();
        JsonReader jsonReader;
        try {
            jsonReader = new JsonReader(new FileReader(automaticTester));
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
                success = true;
            }
        }catch (Exception e) {
            System.err.println("There is an error when load: \"json/db/slimeProperties.json\"");
            e.printStackTrace();
        }
        if (success) {
            System.out.println("slimeProperties.json Status: OK!");
        }

    }
    @Test
    public void automaticTester5() {
        System.out.println("Checking player.json....");
        boolean success = false;
        final HashMap<Integer, List<Integer>> characterLevels = new HashMap();
        File automaticTester = new File("json/profile/player.json");
        HashMap<String, List<Integer>> enemyProperties = new HashMap();
        Gson gson = new Gson();
        JsonReader jsonReader;
        try {
            jsonReader = new JsonReader(new FileReader(automaticTester));
            JsonObject player = gson.fromJson(jsonReader, JsonObject.class);
            String[] tokens = player.toString().split(":");
//            System.out.println(player);
            int exp = removeLastX(tokens[1], 8);
            int level  = removeLastX(tokens[2], 7);
            String name = removeLastXS(tokens[3], 12);
            name = name.replaceAll("\"","");
//            System.out.println(name);
            int healthMax = removeLastX(tokens[4], 9);
            int health = removeLastX(tokens[5], 11);
            int magicMax = removeLastX(tokens[6], 8);
            int magic = removeLastX(tokens[7], 11);
            int strength = removeLastX(tokens[8], 12);
            int endurance = removeLastX(tokens[9], 10);
            int agility = removeLastX(tokens[10], 7);
            int luck = removeLastX(tokens[11], 15);
            int intelligence = removeLastX(tokens[12], 9);
            success = true;
        } catch (Exception e) {
            System.err.println("There is an error when load: \"json/profile/player.json\"");
            e.printStackTrace();
        }
        if (success) {
            System.out.println("player.json Status: OK!");
        }

    }
}
