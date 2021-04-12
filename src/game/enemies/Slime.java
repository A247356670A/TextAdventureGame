package game.enemies;

import java.util.Random;

public class Slime extends Enemy {

    private final EnemyProperty enemyProperty;

    public Slime(int playerLevel, EnemyProperty enemyProperty, int basicHealth, int basicAttack, int basicDefence, int basicAvoidChance, int basicCritChance, int expGain) {
        Random rand = new Random();
        this.enemyProperty = enemyProperty;
        int health =  basicHealth + playerLevel * (rand.nextInt(5) + 1);// 20
        this.setName(enemyProperty.name() + " Slime");
        this.setHealthMax(health);
        this.setHealth(health);
        this.setAttack(basicAttack + playerLevel * (rand.nextInt(5) + 1));// 10
        this.setDefence(basicDefence + playerLevel * (rand.nextInt(5) + 1));// 5
        this.setAvoidChance(basicAvoidChance + playerLevel * (rand.nextInt(5) + 1));// 1
        this.setCritChance(basicCritChance + playerLevel * (rand.nextInt(5) + 1));// 1
        this.setExpGain(expGain + playerLevel * (rand.nextInt(2) + 1));// 1

    }


}
