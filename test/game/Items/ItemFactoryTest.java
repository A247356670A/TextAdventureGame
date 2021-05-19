package game.items;

import game.peons.Player;
import org.junit.Test;

import java.io.File;
import java.util.Random;

import static org.junit.Assert.*;

public class ItemFactoryTest {
    @Test
    public void testLoadItemProperties() {
        ItemFactory itemFactory= new ItemFactory();
        File file = new File("json/db/swordProperties.json");
        itemFactory.loadItemProperties(file);
    }
    @Test
    public void testLoadItem() {
        ItemFactory itemFactory= new ItemFactory();
        File file = new File("json/db/items.json");
        itemFactory.loadItem(file);
    }
    @Test
    public void testGenerateItems() {
        ItemFactory itemFactory= new ItemFactory();
        Player player = new Player("Default Player");
        itemFactory.generateItem(player);
    }
    @Test
    public void testgetSword(){
        ItemFactory itemFactory = new ItemFactory();
        Player player = new Player("Default Player");
        itemFactory.getSword(player.getLevel());

    }
    @Test
    public void testgetDress(){
        ItemFactory itemFactory = new ItemFactory();
        Player player = new Player("Default Player");
        itemFactory.getDress(player.getLevel());

    }
    @Test
    public void generateItem() {
        ItemFactory itemFactory = new ItemFactory();
        Player player = new Player("Default Player");
        Item sword = itemFactory.generateItem(player);
        System.out.println("level:"+sword.getLevel());
        System.out.println("name:"+sword.getName());
        System.out.println("health:"+sword.getHealth());
        System.out.println("attack:"+sword.getAttack());
        System.out.println("defence:"+sword.getDefence());
        System.out.println("avoidchance:"+sword.getAvoidChance());
        System.out.println("critchance:"+sword.getCritChance());
    }

}
