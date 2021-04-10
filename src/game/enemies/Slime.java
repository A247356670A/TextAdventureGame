package game.enemies;

import java.util.Random;

public class Slime extends Enemy{

    public Slime(int playerLevel){
        Random rand = new Random();
        int health = 10 + playerLevel * (rand.nextInt(5) + 1);
        this.setName("Slime");
        this.setHealthMax(health);
        this.setHealth(health);
        this.setAttack(10 + playerLevel * (rand.nextInt(5) + 1));
        this.setDefence(5 + playerLevel * (rand.nextInt(5) + 1));
        this.setAvoidChance(1 + playerLevel * (rand.nextInt(5) + 1));
        this.setCritChance(1 + playerLevel * (rand.nextInt(5) + 1));
        this.setExpGain(1 + playerLevel * (rand.nextInt(2) + 1));
    }


}
