package game.peons;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import game.locations.Location;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player extends Peon {
    private int exp;
    private Location location;
    private static HashMap<Integer, List<Integer>> characterLevels = new HashMap();
    public Player(){

    }
    public void loadCharacterLevels(){
        Gson gson = new Gson();
        JsonReader jsonReader = null;
        try {
            jsonReader = new JsonReader(new FileReader("json/db/characterLevels.json"));
            List<Integer> list = gson.fromJson(jsonReader, List.class);
            for (Object b : list) {
//                Integer[] tokens = b;//0: name; 1: author; 2:year; 3:BookGenre
                System.out.println(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
