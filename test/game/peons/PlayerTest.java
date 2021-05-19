package game.peons;

import game.GameLauncher;
import game.locations.LocationUtility;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player = new Player("testPlayer");
    @Test
    public void saveToJSONFile() {
        assertEquals("testPlayer",player.getName());
        assertEquals(100,player.getHealth());
        assertEquals(8,player.getStrength());
        player.setName("newTestPlayer");
        player.setStrength(3);
        player.saveToJSONFile("json/profile/player.json");
        Player newTestPlayer = GameLauncher.loadPlayer();
        assertEquals("newTestPlayer",newTestPlayer.getName());
        assertEquals(3,newTestPlayer.getStrength());

    }
    @Test
    public void loadCharacterLevels() {
        player.loadCharacterLevels("json/db/characterLevels.json");
    }

    @Test
    public void getCharacterLevels() {
        System.out.println(player.getCharacterLevels());
    }

    @Test
    public void getExp() {
        assertEquals(0,player.getExp());
    }

    @Test
    public void setExp() {
        player.setExp(4);
        assertEquals(4,player.getExp());
    }

    @Test
    public void getLevel() {
        assertEquals(1,player.getLevel());

    }

    @Test
    public void setLevel() {
        player.setLevel(10);
        assertEquals(10,player.getLevel());
    }
    private void testUserInput(String in) {
        System.setIn(new ByteArrayInputStream(in.getBytes()));
    }

    @Test
    public void levelUp() {
//        player.setExp(101);
//
//        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
//
//        new Thread(() -> {
//            player.levelUp();
//        }).start();



    }

    @Test
    public void healAll() {
        player.setHealth(1);
        assertEquals(1,player.getHealth());
        player.healAll();
        assertEquals(player.getHealthMax(),player.getHealth());
    }

    @Test
    public void heal() {
        player.setHealth(1);
        assertEquals(1,player.getHealth());
        player.heal(9);
        assertEquals(10,player.getHealth());
    }

    @Test
    public void magicRecoverAll() {
        player.setMagic(1);
        assertEquals(1,player.getMagic());
        player.magicRecoverAll();
        assertEquals(player.getMagicMax(),player.getMagic());
    }

    @Test
    public void magicRecover() {
        player.setMagic(1);
        assertEquals(1,player.getMagic());
        player.magicRecover(9);
        assertEquals(10,player.getMagic());
    }

}
