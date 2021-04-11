package game.enemies;

import game.peons.Player;

import java.util.Random;

public class EnemyFactory {


    public EnemyFactory() {
    }

    public Enemy generateEnemies(Player player) {
        return this.getSlime(player.getLevel());
    }

    private Enemy getSlime(int playerLevel) {
        Random random = new Random();
        int result = random.nextInt(5);
        if (result == 0){// NORMAL

        }else if (result == 1){// KAWAI

        }else if (result == 2){// ICED

        }else if (result == 3){// FIRED

        }else if (result == 4){// ROCKED

        }else if (result == 5){// GHOSTED

        }
        System.out.println("A wind slime shows up!");
        return new Slime(playerLevel);
    }
}
