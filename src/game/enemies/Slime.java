package game.enemies;

import java.util.Random;

public class Slime extends Enemy{

    private final EnemyProperty enemyProperty;

    public Slime(int playerLevel, int basicHealth, EnemyProperty enemyProperty, int basicAttack, int basicDefence, int basicAvoidChance, int basicCritChance, int expGain){
        Random rand = new Random();
        this.enemyProperty = enemyProperty;
        int health = 20 + basicHealth + playerLevel * (rand.nextInt(5) + 1);// 20
        this.setName( enemyProperty.name() + " Slime");
        this.setHealthMax(health);
        this.setHealth(health);
        this.setAttack(10 + basicAttack + playerLevel * (rand.nextInt(5) + 1));// 10
        this.setDefence(5 + basicDefence + playerLevel * (rand.nextInt(5) + 1));// 5
        this.setAvoidChance(1 + basicAvoidChance + playerLevel * (rand.nextInt(5) + 1));// 1
        this.setCritChance(1 + basicCritChance + playerLevel * (rand.nextInt(5) + 1));// 1
        this.setExpGain(1 + expGain + playerLevel * (rand.nextInt(2) + 1));// 1

    }




}
