package game.enemies;

import game.peons.Player;

import java.util.Random;

public class EnemyFactory {
    Random random = new Random();

    public EnemyFactory() {
    }

    public Enemy generateEnemies(Player player) {
        return this.getSlime(player.getLevel());
    }

    private Enemy getSlime(int playerLevel) {
        System.out.println("A wind slime shows up!");
        return new Slime(playerLevel);
    }
}
