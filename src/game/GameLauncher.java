package game;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import game.locations.Location;
import game.locations.LocationUtility;
import game.peons.Player;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import static game.locations.LocationUtility.readKeyBoard;

public class GameLauncher {

    public static void main(String[] args) {
        Location location = new Location();
        File playerSave = new File("json/profile/player.json");
        boolean mainFlag = true;
        do {
            if (playerSave.exists()){
                System.out.println("---------------Main Menu-----------------");
                System.out.println("        1.Continue");
                System.out.println("        2.New Game");
                System.out.println("          3.Quit    ");
                char key = LocationUtility.readMapSelection();
                System.out.println();
                switch (key) {
                    case '1':
                        Player loadPlayer = loadPlayer();
                        mainFlag = false;
                        location.mainMap(loadPlayer);
                        continue;
                    case '2':
                        System.out.println("Please type the name of the hero.");
                        String str = readKeyBoard(10, false);
                        Player player = new Player(str);
                        mainFlag = false;
                        location.mainMap(player);
                        continue;

                    case '3':
                        mainFlag = false;
                        break;

                }
            }else {
                System.out.println("---------------Main Menu-----------------");
                System.out.println("        1.New Game");
                System.out.println("          2.Quit    ");
                char key = LocationUtility.readMapSelection();
                System.out.println();
                switch (key) {
                    case '1':
                        System.out.println("Please type the name of the hero.");
                        String str = readKeyBoard(10, false);
                        Player player = new Player(str);
                        mainFlag = false;
                        location.mainMap(player);
                        continue;
                    case '2':
                        mainFlag = false;
                        break;

                }
            }
        }while (mainFlag);



    }

    public static Player loadPlayer() {
        Gson gson = new Gson();
        JsonReader jsonReader;
        try {
            jsonReader = new JsonReader(new FileReader("json/profile/player.json"));
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
            return new Player(name,exp,level,healthMax,health,magicMax,magic,strength,endurance,agility,luck,intelligence);
        } catch (Exception e) {
            System.err.println("There is an error when load: \"json/profile/player.json\"");
            System.err.println("Generated new Player");
            System.out.println("Please type the name of the hero.");
            String str = readKeyBoard(10, false);
            e.printStackTrace();
            return new Player(str);
        }
    }
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
}
