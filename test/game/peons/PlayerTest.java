package game.peons;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player = new Player();

    @Before
    public void createPlayer(){

    }
    @Test
    public void loadCharacterLevels() {
        // Save it to the specified file.
//        File file = new File("json/db/characterLevels.json");

        // Ensure that there is nothing there right now:
//		file.delete();


//        player.loadCharacterLevels();
        // Load it from the specified file.
        // Delete file after test
//		file.delete();
    }

    @Test
    public void getCharacterLevels() {
        System.out.println(player.getCharacterLevels());
    }

    @Test
    public void getExp() {
        System.out.println(player.getExp());
    }

    @Test
    public void setExp() {
    }

    @Test
    public void getLevel() {
        System.out.println(player.getLevel());
    }

    @Test
    public void setLevel() {
    }

    @Test
    public void levelUp() {

    }
    @Test
    public void getHealthMax() {
        System.out.println(player.getHealthMax());
    }

    @Test
    public void setHealthMax() {
    }

    @Test
    public void getHealth() {
        System.out.println(player.getHealth());
    }

    @Test
    public void setHealth() {
    }

    @Test
    public void getMagicMax() {
        System.out.println(player.getMagicMax());
    }

    @Test
    public void setMagicMax() {
    }

    @Test
    public void getMagic() {
        System.out.println(player.getMagic());
    }

    @Test
    public void setMagic() {
    }

    @Test
    public void getStrength() {
        System.out.println(player.getStrength());
    }

    @Test
    public void setStrength() {
    }

    @Test
    public void getEndurance() {
        System.out.println(player.getEndurance());
    }

    @Test
    public void setEndurance() {
    }

    @Test
    public void getAgility() {
        System.out.println(player.getAgility());
    }

    @Test
    public void setAgility() {
    }

    @Test
    public void getLuck() {
        System.out.println(player.getLuck());
    }

    @Test
    public void setLuck() {
    }

    @Test
    public void getIntelligence() {
        System.out.println(player.getIntelligence());
    }

    @Test
    public void setIntelligence() {
    }

    @Test
    public void getAttack() {
        System.out.println(player.getAttack());
    }

    @Test
    public void setAttack() {
    }

    @Test
    public void getDefence() {
        System.out.println(player.getDefence());
    }

    @Test
    public void setDefence() {
    }

    @Test
    public void getAvoidChance() {
        System.out.println(player.getAvoidChance());
    }

    @Test
    public void setAvoidChance() {
    }

    @Test
    public void getCritChance() {
        System.out.println(player.getCritChance());
    }

    @Test
    public void setCritChance() {
    }

    @Test
    public void getEquipments() {
        System.out.println(player.getEquipments());
    }

    @Test
    public void setEquipments() {
    }
    @Test
    public void getName() {
        System.out.println(player.getName());
    }

    @Test
    public void setName() {
    }
}
