package game.items;

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

//DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!!
// DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!!
// DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!! DRAFT!!

public class ItemFactory {
    public ItemFactory(){

    }
    private static int removeLastX(String str, int x) {
        if (str != null) {
            str = str.substring(0, str.length() - x);
        }
        return Integer.parseInt(str);
    }
    public HashMap<String, List<Integer>> loadItemProperties(File file) {
        HashMap<String, List<Integer>> itemProperties = new HashMap();
        Gson gson = new Gson();
        JsonReader jsonReader;
        try {
            jsonReader = new JsonReader(new FileReader(file));
            List<JsonObject> list = gson.fromJson(jsonReader, List.class);
            for (Object b : list) {
                String[] tokens = b.toString().split("=");// 1: Property; 2: health; 3: Attack; 4: Defence; 5: AvoidChance; 6: CritChance; 7: ExpGain; 8:rate
                String itemProperty = tokens[1].substring(0, tokens[1].length() - 13);
                int health = removeLastX(tokens[2], 15);
                int attack = removeLastX(tokens[3], 16);
                int defence = removeLastX(tokens[4], 20);
                int avoidChance = removeLastX(tokens[5], 19);
                int critChance = removeLastX(tokens[6], 15);
                int rate = removeLastX(tokens[9], 3);
                List<Integer> properties = Arrays.asList(attack, defence, avoidChance, critChance, rate);
                itemProperties.put(itemProperty, properties);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemProperties;
    }
    public HashMap<String, Integer> loadItem(File file){
        HashMap<String, Integer> items = new HashMap<>();
        Gson gson = new Gson();
        JsonReader jsonReader;
        try{
            jsonReader = new JsonReader(new FileReader(file));
            List<JsonObject> list = gson.fromJson(jsonReader, List.class);
            for(Object b : list){
                String[] tokens = b.toString().split("=");
                String itemName = tokens[1].substring(0,tokens[1].length() -6);
                int rate = removeLastX(tokens[2],3);
                items.put(itemName, rate);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public Item generateItem(Player player){
        File file = new File("json/db/items.json");
        HashMap<String, Integer> items = loadItem(file);
        Random random = new Random();
        int result = random.nextInt(items.get("Sword")+items.get("Dress"));
        if(result == items.get("Sword")) {
            return this.getSword(player.getLevel());
        }else if(result == items.get("Dress")){
            return this.getDress(player.getLevel());
        }else return null;
    }
    private Item getSword(int playerLevel){
        File file = new File("json/db/swordProperties.json");
        HashMap<String, List<Integer>> itemProperties = loadItemProperties(file);
        List<Integer> locationLeftHand = itemProperties.get("LEFT HAND");
        List<Integer> locationRightHand = itemProperties.get("RIGHT HAND");
        List<Integer> locationBothHands = itemProperties.get("BOTH HANDS");
        Random random = new Random();
        int result = random.nextInt(locationLeftHand.get(5)+locationRightHand.get(5)+locationBothHands.get(5));
        if(result >=0&&result<locationLeftHand.get(5)){
            if(itemProperties.containsKey("LEFT HAND")){
                System.out.println("you got a left hand sword!");
                return new Item(playerLevel,EquipmentLocation.LEFT_HAND,locationLeftHand.get(0),locationLeftHand.get(1),locationLeftHand.get(2),locationLeftHand.get(3),locationLeftHand.get(4));
            }
        }else if(result >= locationLeftHand.get(5) && result < locationLeftHand.get(5)+locationRightHand.get(5)){
            if(itemProperties.containsKey("RIGHT HAND")){
                System.out.println("you got a right hand sword!");
                return new Item(playerLevel,EquipmentLocation.RIGHT_HAND,locationRightHand.get(0),locationRightHand.get(1),locationRightHand.get(2),locationRightHand.get(3),locationRightHand.get(4));
            }
        }else if(result >= locationLeftHand.get(5)+locationRightHand.get(5) && result < locationLeftHand.get(5)+locationRightHand.get(5)+locationBothHands.get(5)){
            if(itemProperties.containsKey("BOTH HANDS")){
                System.out.println("you got a both hands sword!");
                return new Item(playerLevel,EquipmentLocation.BOTH_HANDS,locationBothHands.get(0),locationBothHands.get(1),locationBothHands.get(2),locationBothHands.get(3),locationBothHands.get(4));
            }
        }return null;
    }
    private Item getDress(int playerLevel){
        File file = new File("json/db/dressProperties.json");
        HashMap<String, List<Integer>> itemProperties = loadItemProperties(file);
        List<Integer> locationHead = itemProperties.get("HEAD");
        List<Integer> locationChest = itemProperties.get("CHEST");
        List<Integer> locationLeftArm = itemProperties.get("LEFT ARM");
        List<Integer> locationRightArm = itemProperties.get("RIGHT ARM");
        List<Integer> locationLeftHand = itemProperties.get("LEFT HAND");
        List<Integer> locationRightHand = itemProperties.get("RIGHT HAND");
        List<Integer> locationBothHands = itemProperties.get("BOTH HANDS");
        List<Integer> locationLegs = itemProperties.get("LEGS");
        List<Integer> locationFeet = itemProperties.get("FEET");
        Random random = new Random();
        int result = random.nextInt(locationHead.get(5)+locationChest.get(5)+locationLeftArm.get(5)+locationRightArm.get(5)+locationLeftHand.get(5)+locationRightHand.get(5)+locationBothHands.get(5)+locationLegs.get(5)+locationFeet.get(5));
        if(result >= 0 && result<locationHead.get(5)){
            if(itemProperties.containsKey("HEAD")){
                System.out.println("you got a head dress!");
                return new Item(playerLevel,EquipmentLocation.HEAD,locationHead.get(0),locationHead.get(1),locationHead.get(2),locationHead.get(3),locationHead.get(4));
            }
        }else if(result >= locationHead.get(5) && result < locationHead.get(5)+locationChest.get(5)){
            if(itemProperties.containsKey("CHEST")){
                System.out.println("you got a chest dress!");
                return new Item(playerLevel,EquipmentLocation.CHEST,locationChest.get(0),locationChest.get(1),locationChest.get(2),locationChest.get(3),locationChest.get(4));
            }
        }else if(result >= locationHead.get(5)+locationChest.get(5) && result < locationHead.get(5)+locationChest.get(5)+locationLeftArm.get(5)){
            if(itemProperties.containsKey("LEFT ARM")){
                System.out.println("you got a left arm dress!");
                return new Item(playerLevel,EquipmentLocation.LEFT_ARM,locationLeftArm.get(0),locationLeftArm.get(1),locationLeftArm.get(2),locationLeftArm.get(3),locationLeftArm.get(4));
            }
        }else if(result >= locationHead.get(5)+locationChest.get(5)+locationLeftArm.get(5) && result < locationHead.get(5)+locationChest.get(5)+locationLeftArm.get(5) + locationRightArm.get(5)){
            if(itemProperties.containsKey("RIGHT ARM")){
                System.out.println("you got a right arm dress!");
                return new Item(playerLevel,EquipmentLocation.RIGHT_ARM,locationRightArm.get(0),locationRightArm.get(1),locationRightArm.get(2),locationRightArm.get(3),locationRightArm.get(4));
            }
        }else if(result>=locationHead.get(5)+locationChest.get(5)+locationLeftArm.get(5) + locationRightArm.get(5)&& result < locationHead.get(5)+locationChest.get(5)+locationLeftArm.get(5) + locationRightArm.get(5) +locationLeftHand.get(5)){
            if(itemProperties.containsKey("LEFT HAND")){
                System.out.println("you got a left hand dress!");
                return new Item(playerLevel,EquipmentLocation.LEFT_HAND,locationLeftHand.get(0),locationLeftHand.get(1),locationLeftHand.get(2),locationLeftHand.get(3),locationLeftHand.get(4));
            }
        }else if(result>=locationHead.get(5)+locationChest.get(5)+locationLeftArm.get(5) + locationRightArm.get(5) +locationLeftHand.get(5)&& result<locationHead.get(5)+locationChest.get(5)+locationLeftArm.get(5) + locationRightArm.get(5) +locationLeftHand.get(5)+locationRightHand.get(5)){
            if(itemProperties.containsKey("RIGHT HAND")){
                System.out.println("you got a right hand dress!");
                return new Item(playerLevel,EquipmentLocation.RIGHT_HAND,locationRightHand.get(0),locationRightHand.get(1),locationRightHand.get(2),locationRightHand.get(3),locationRightHand.get(4));
            }
        }else if(result>=locationHead.get(5)+locationChest.get(5)+locationLeftArm.get(5) + locationRightArm.get(5) +locationLeftHand.get(5)+locationRightHand.get(5)&& result<locationHead.get(5)+locationChest.get(5)+locationLeftArm.get(5) + locationRightArm.get(5) +locationLeftHand.get(5)+locationRightHand.get(5)+locationBothHands.get(5)){
            if(itemProperties.containsKey("BOTH HANDS")){
                System.out.println("you got a both hands dress!");
                return new Item(playerLevel,EquipmentLocation.BOTH_HANDS,locationBothHands.get(0),locationBothHands.get(1),locationBothHands.get(2),locationBothHands.get(3),locationBothHands.get(4));
            }
        }else if(result>=locationHead.get(5)+locationChest.get(5)+locationLeftArm.get(5) + locationRightArm.get(5) +locationLeftHand.get(5)+locationRightHand.get(5)+locationBothHands.get(5)&& result<locationHead.get(5)+locationChest.get(5)+locationLeftArm.get(5) + locationRightArm.get(5) +locationLeftHand.get(5)+locationRightHand.get(5)+locationBothHands.get(5)+locationLegs.get(5)){
            if(itemProperties.containsKey("FEET")){
                System.out.println("you got a feet dress!");
                return new Item(playerLevel,EquipmentLocation.FEET,locationFeet.get(0),locationFeet.get(1),locationFeet.get(2),locationFeet.get(3),locationFeet.get(4));
            }
        }else if(result>=locationHead.get(5)+locationChest.get(5)+locationLeftArm.get(5) + locationRightArm.get(5) +locationLeftHand.get(5)+locationRightHand.get(5)+locationBothHands.get(5)+locationLegs.get(5)&&result<locationHead.get(5)+locationChest.get(5)+locationLeftArm.get(5) + locationRightArm.get(5) +locationLeftHand.get(5)+locationRightHand.get(5)+locationBothHands.get(5)+locationLegs.get(5)+locationLegs.get(5)){
            if(itemProperties.containsKey("LEGS")){
                System.out.println("you got legs dresses!");
                return new Item(playerLevel,EquipmentLocation.LEGS,locationLegs.get(0),locationLegs.get(1),locationLegs.get(2),locationLegs.get(3),locationLegs.get(4));
            }
        }
        return null;
    }

}
