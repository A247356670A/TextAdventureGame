package game.event;

import com.google.gson.Gson;
import game.peons.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class Talk {

    public Talk(Player player, String str) {
        List<String> contents = getContent(str);
        List<Map<String, Object>> mapList = getTalk(str);
        switch (str) {
            case "Background":
                contents.forEach(System.out::println);
                break;
            case "Chapter 1":
                contents.stream().limit(2).forEach(System.out::println);
                mapList.forEach(map -> System.out.println(map.get("name") + ":" + map.get("content")));
                contents.stream().skip(2).forEach(System.out::println);
                break;
            case "Chapter 2-1":
            case "Chapter 2-2":
                contents.forEach(System.out::println);
                mapList.forEach(map -> System.out.println(map.get("name") + ":" + map.get("content")));
                break;
            case "Chapter 2-3":
            case "Chapter 3":
                contents.stream().limit(1).forEach(System.out::println);
                mapList.forEach(map -> System.out.println(map.get("name") + ":" + map.get("content")));
                contents.stream().skip(1).forEach(System.out::println);
                break;
        }
    }

    /**
     * 读取故事情节
     *
     * @param key
     * @return
     */
    public List<String> getContent(String key) {
        File file = new File("json/db/storyline.json");
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            StringBuffer sb = new StringBuffer();
            String temp = "";
            while ((temp = bf.readLine()) != null) {
                sb.append(temp);
            }
            String jsonStr = sb.toString();
            Gson gson = new Gson();
            Map<String, List<String>> map = gson.fromJson(jsonStr, Map.class);
            return map.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取对话
     *
     * @param key
     * @return
     */
    public List<Map<String, Object>> getTalk(String key) {
        File file = new File("json/db/dialogue.json");
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            StringBuffer sb = new StringBuffer();
            String temp = "";
            while ((temp = bf.readLine()) != null) {
                sb.append(temp);
            }
            String jsonStr = sb.toString();
            Gson gson = new Gson();
            Map<String, List<Map<String, Object>>> map = gson.fromJson(jsonStr, Map.class);
            return map.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
