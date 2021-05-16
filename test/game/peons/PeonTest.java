package game.peons;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *         this.exp = 0;
 *         this.level = 1;
 *         this.setMagicMax(100);
 *         this.setMagic(100);
 *         this.setHealthMax(100);
 *         this.setHealth(100);
 *         this.setStrength(8);
 *         this.setEndurance(8);
 *         this.setAgility(8);
 *         this.setLuck(8);
 *         this.setIntelligence(8);
 */
public class PeonTest {
    Player player = new Player("testPeon");

    @Test
    public void getHealthMax() {
        assertEquals(100,player.getHealthMax());
    }

    @Test
    public void setHealthMax() {
        player.setHealthMax(1);
        assertEquals(1,player.getHealthMax());
    }

    @Test
    public void getHealth() {
        assertEquals(100,player.getHealth());
    }

    @Test
    public void setHealth() {
        player.setHealth(1);
        assertEquals(1,player.getHealth());
    }

    @Test
    public void getMagicMax() {
        assertEquals(100,player.getMagicMax());
    }

    @Test
    public void setMagicMax() {
        player.setMagicMax(1);
        assertEquals(1,player.getMagicMax());
    }

    @Test
    public void getMagic() {
        assertEquals(100,player.getMagic());
    }

    @Test
    public void setMagic() {
        player.setMagic(1);
        assertEquals(1,player.getMagic());
    }

    @Test
    public void getStrength() {
        assertEquals(8,player.getStrength());
    }

    @Test
    public void setStrength() {
        player.setStrength(1);
        assertEquals(1,player.getStrength());
    }

    @Test
    public void getEndurance() {
        assertEquals(8,player.getEndurance());
    }

    @Test
    public void setEndurance() {
        player.setEndurance(1);
        assertEquals(1,player.getEndurance());
    }

    @Test
    public void getAgility() {
        assertEquals(8,player.getAgility());
    }

    @Test
    public void setAgility() {
        player.setAgility(1);
        assertEquals(1,player.getAgility());
    }

    @Test
    public void getLuck() {
        assertEquals(8,player.getLuck());
    }

    @Test
    public void setLuck() {
        player.setLuck(1);
        assertEquals(1,player.getLuck());
    }

    @Test
    public void getIntelligence() {
        assertEquals(8,player.getIntelligence());
    }

    @Test
    public void setIntelligence() {
        player.setIntelligence(1);
        assertEquals(1,player.getIntelligence());
    }

    @Test
    public void getAttack() {
        assertEquals(16,player.getAttack());
    }

    @Test
    public void setAttack() {
        player.setAttack(1);
        assertEquals(1,player.getAttack());
    }

    @Test
    public void getDefence() {
        assertEquals(8,player.getDefence());
    }

    @Test
    public void setDefence() {
        player.setDefence(1);
        assertEquals(1,player.getDefence());
    }

    @Test
    public void getAvoidChance() {
        assertEquals(8,player.getAvoidChance());
    }

    @Test
    public void setAvoidChance() {
        player.setAvoidChance(1);
        assertEquals(1,player.getAvoidChance());
    }

    @Test
    public void getCritChance() {
        assertEquals(8,player.getCritChance());
    }

    @Test
    public void setCritChance() {
        player.setCritChance(1);
        assertEquals(1,player.getCritChance());
    }

    @Test
    public void getEquipments() {
    }

    @Test
    public void setEquipments() {
    }

    @Test
    public void getName() {
        assertEquals("testPeon",player.getName());
    }

    @Test
    public void setName() {
        player.setName("F91");
        assertEquals("F91",player.getName());
    }

    @Test
    public void view() {
        player.view();
    }
}
