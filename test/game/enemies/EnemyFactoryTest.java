package game.enemies;

import game.peons.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnemyFactoryTest {

    @Test
    public void generateEnemies() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Player player = new Player();
        Enemy slime = enemyFactory.generateEnemies(player);
        System.out.println(slime.getName());
        System.out.println(slime.getHealthMax());
        System.out.println(slime.getHealth());
        System.out.println(slime.getMagicMax());
        System.out.println(slime.getMagic());
        System.out.println(slime.getAttack());
        System.out.println(slime.getDefence());
        System.out.println(slime.getAvoidChance());
        System.out.println(slime.getCritChance());
        System.out.println(slime.getExpGain());

        System.out.println(slime.getStrength());
        System.out.println(slime.getEndurance());
        System.out.println(slime.getAgility());
        System.out.println(slime.getLuck());
        System.out.println(slime.getIntelligence());

    }

    @Test
    public void loadEnemyProperties() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Player player = new Player();
        Enemy slime = enemyFactory.generateEnemies(player);
    }

    @Test
    public void testGenerateEnemies() {
    }
}
