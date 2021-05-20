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
                //System.out.println(b);//loaded checked
                String[] tokens = b.toString().split("=");
                String itemProperty = tokens[1].substring(0, tokens[1].length() - 8);
//                System.out.println(itemProperty);
                //System.out.println("itemProperty:"+itemProperty);//loaded checked
                int health = removeLastX(tokens[2], 10);
                //System.out.println("health:"+health);//loaded checked
                int attack = removeLastX(tokens[3], 11);
                //System.out.println("attack:"+attack);//loaded checked
                int defence = removeLastX(tokens[4], 15);
                //System.out.println("defence:"+defence);//loaded checked
                int avoidChance = removeLastX(tokens[5], 14);
                //System.out.println("avoidchance:"+avoidChance);//loaded checked
                int critChance = removeLastX(tokens[6], 8);
                //System.out.println("critchance:"+critChance);//loaded checked
                int rate = removeLastX(tokens[7], 3);
                //System.out.println("rate:"+rate);//loaded checked
                List<Integer> properties = Arrays.asList(health, attack, defence, avoidChance, critChance, rate);
                itemProperties.put(itemProperty, properties);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemProperties;
    }

    public static HashMap<String, Integer> loadItem(File file){
        HashMap<String, Integer> items = new HashMap<>();
        Gson gson = new Gson();
        JsonReader jsonReader;
        try{
            jsonReader = new JsonReader(new FileReader(file));
            List<JsonObject> list = gson.fromJson(jsonReader, List.class);
            for(Object b : list){
                //System.out.println(b);//loaded checked
                String[] tokens = b.toString().split("=");
                String itemName = tokens[1].substring(0,tokens[1].length() -6);
                //System.out.println("itemname:"+itemName);//loaded checked
                int rate = removeLastX(tokens[2],3);
                //System.out.println("rate:"+rate);//loaded checked
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
        //System.out.println("result:"+result);//loaded checked
        if(result <items.get("Sword")) {
            //System.out.println("less than sword:"+result);//loaded checked
            return this.getSword(player.getLevel());
        }else {
            //System.out.println("greater than sword:"+result);//loaded checked
            return this.getDress(player.getLevel());
        }
        //return this.getDress(player.getLevel());
    }
    public Item getSword(int playerLevel){
        File file = new File("json/db/swordProperties.json");
        HashMap<String, List<Integer>> itemProperties = loadItemProperties(file);

        List<Integer> locationLeftHand = itemProperties.get("LEFT_HAND");
        //System.out.println("locationLeftHand:"+locationLeftHand);//loaded checked
        List<Integer> locationRightHand = itemProperties.get("RIGHT_HAND");
        //System.out.println("locationRightHand:"+locationRightHand);//loaded checked
        List<Integer> locationBothHands = itemProperties.get("BOTH_HANDS");
        //System.out.println("locationBothHands:"+locationBothHands);//loaded checked
        Random random = new Random();
        int result = random.nextInt(4);
        //int result = random.nextInt(locationLeftHand.get(5)+locationRightHand.get(5)+locationBothHands.get(5));
//        System.out.println("Sword result:"+result);
//        System.out.println(itemProperties.get("LEFT_HAND"));
//        System.out.println(itemProperties.get("RIGHT_HAND"));
//        System.out.println(itemProperties.get("BOTH_HANDS"));
        //System.out.println("0"+locationLeftHand.get(0));
        //System.out.println("1"+locationLeftHand.get(1));
        if(result == 0){
            if(itemProperties.containsKey("LEFT_HAND")){
                System.out.println("You got a left hand sword!");
                return new Item(playerLevel,EquipmentLocation.LEFT_HAND,locationLeftHand.get(0),locationLeftHand.get(1),locationLeftHand.get(2),locationLeftHand.get(3),locationLeftHand.get(4));
            }
        }else if(result == 1){
            if(itemProperties.containsKey("RIGHT_HAND")){
                System.out.println("You got a right hand sword!");
                return new Item(playerLevel,EquipmentLocation.RIGHT_HAND,locationRightHand.get(0),locationRightHand.get(1),locationRightHand.get(2),locationRightHand.get(3),locationRightHand.get(4));
            }
        }else{
            if(itemProperties.containsKey("BOTH_HANDS")){
                System.out.println("You got a both hands sword!");
                return new Item(playerLevel,EquipmentLocation.BOTH_HANDS,locationBothHands.get(0),locationBothHands.get(1),locationBothHands.get(2),locationBothHands.get(3),locationBothHands.get(4));
            }
        }
        return null;
    }
    public Item getDress(int playerLevel){
        File file = new File("json/db/dressProperties.json");
        HashMap<String, List<Integer>> itemProperties = loadItemProperties(file);
        List<Integer> locationHead = itemProperties.get("HEAD");
        //System.out.println("locationHead:"+locationHead);//loaded checked
        List<Integer> locationChest = itemProperties.get("CHEST");
        //System.out.println("locationChest:"+locationChest);//loaded checked
        List<Integer> locationLeftArm = itemProperties.get("LEFT_ARM");
        List<Integer> locationRightArm = itemProperties.get("RIGHT_ARM");
        List<Integer> locationLeftHand = itemProperties.get("LEFT_HAND");
        List<Integer> locationRightHand = itemProperties.get("RIGHT_HAND");
        List<Integer> locationBothHands = itemProperties.get("BOTH_HANDS");
        List<Integer> locationLegs = itemProperties.get("LEGS");
        List<Integer> locationFeet = itemProperties.get("FEET");
        Random random = new Random();
        int result = random.nextInt(10);
//        System.out.println(result);
        if(result <=1){
            if(itemProperties.containsKey("HEAD")){
                System.out.println("You got a head dress!");
                return new Item(playerLevel,EquipmentLocation.HEAD,locationHead.get(0),locationHead.get(1),locationHead.get(2),locationHead.get(3),locationHead.get(4));
            }
        }else if(result == 2){
            if(itemProperties.containsKey("CHEST")){
                System.out.println("You got a chest dress!");
                return new Item(playerLevel,EquipmentLocation.CHEST,locationChest.get(0),locationChest.get(1),locationChest.get(2),locationChest.get(3),locationChest.get(4));
            }
        }else if(result==3){
            if(itemProperties.containsKey("LEFT_ARM")){
                System.out.println("You got a left arm dress!");
                return new Item(playerLevel,EquipmentLocation.LEFT_ARM,locationLeftArm.get(0),locationLeftArm.get(1),locationLeftArm.get(2),locationLeftArm.get(3),locationLeftArm.get(4));
            }
        }else if(result==4){
            if(itemProperties.containsKey("RIGHT_ARM")){
                System.out.println("You got a right arm dress!");
                return new Item(playerLevel,EquipmentLocation.RIGHT_ARM,locationRightArm.get(0),locationRightArm.get(1),locationRightArm.get(2),locationRightArm.get(3),locationRightArm.get(4));
            }
        }else if(result == 5){
            if(itemProperties.containsKey("LEFT_HAND")){
                System.out.println("You got a left hand dress!");
                return new Item(playerLevel,EquipmentLocation.LEFT_HAND,locationLeftHand.get(0),locationLeftHand.get(1),locationLeftHand.get(2),locationLeftHand.get(3),locationLeftHand.get(4));
            }
        }else if(result ==6){
            if(itemProperties.containsKey("RIGHT_HAND")){
                System.out.println("You got a right hand dress!");
                return new Item(playerLevel,EquipmentLocation.RIGHT_HAND,locationRightHand.get(0),locationRightHand.get(1),locationRightHand.get(2),locationRightHand.get(3),locationRightHand.get(4));
            }
        }else if(result == 7){
            if(itemProperties.containsKey("BOTH_HANDS")){
                System.out.println("You got a both hands dress!");
                return new Item(playerLevel,EquipmentLocation.BOTH_HANDS,locationBothHands.get(0),locationBothHands.get(1),locationBothHands.get(2),locationBothHands.get(3),locationBothHands.get(4));
            }
        }else if(result == 8){
            if(itemProperties.containsKey("FEET")){
                System.out.println("You got feet dress!");
                return new Item(playerLevel,EquipmentLocation.FEET,locationFeet.get(0),locationFeet.get(1),locationFeet.get(2),locationFeet.get(3),locationFeet.get(4));
            }
        }else{
            if(itemProperties.containsKey("LEGS")){
                System.out.println("You got legs dresses!");
                return new Item(playerLevel,EquipmentLocation.LEGS,locationLegs.get(0),locationLegs.get(1),locationLegs.get(2),locationLegs.get(3),locationLegs.get(4));
            }
        }
        return null;
    }

}
