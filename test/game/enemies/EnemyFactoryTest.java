package game.enemies;

import game.peons.Player;
import org.junit.Test;

import java.io.File;
import java.util.Random;

import static org.junit.Assert.*;

public class EnemyFactoryTest {

    @Test
    public void generateEnemies() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Player player = new Player("Default Player");
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
        Random rand = new Random();
        EnemyFactory enemyFactory = new EnemyFactory();
        Player player = new Player("Default Player");
        Enemy slime = enemyFactory.generateEnemies(player);
        slime.view();

        int enemyDamage = slime.getAttack() - player.getDefence();
        System.out.println(enemyDamage);
        if (enemyDamage <= 0){
            enemyDamage = 1;
        }
        enemyDamage = rand.nextInt((int)(enemyDamage * 1.2)) + (int)(enemyDamage * 0.8);
        System.out.println(enemyDamage);
    }

    @Test
    public void testGenerateEnemies() {
    }

    @Test
    public void loadEnemies() {
        Random rand = new Random();
        EnemyFactory enemyFactory = new EnemyFactory();
        Player player = new Player("Default Player");
        File file = new File("json/db/enemies.json");
        enemyFactory.loadEnemies(file);
    }

    @Test
    public void loadBoss() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy boss = enemyFactory.loadBoss();
        boss.view();
    }

    @Test
    public void testLoadEnemyProperties() {
    }

    @Test
    public void testLoadEnemies() {
    }

    @Test
    public void testGenerateEnemies1() {
    }

    @Test
    public void generateBoss() {
    }
}
