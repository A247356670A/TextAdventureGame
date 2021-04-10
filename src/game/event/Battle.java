package game.event;

import game.enemies.Enemy;
import game.enemies.EnemyFactory;
import game.locations.Location;
import game.locations.LocationUtility;
import game.peons.Peon;
import game.peons.Player;

import java.util.Random;

public class Battle {
    Boolean battleFlag;


    public Battle(Player player, Enemy enemy){
        battleFlag = true;
        while (battleFlag) {
            System.out.println("What would you do?");
            System.out.println("1. Attack");
            System.out.println("2. Defence");
            System.out.println("3. Escape");
            char key = LocationUtility.readMapSelection();
            System.out.println();

            switch (key) {
                case '1':
                    attack(player,enemy);
                    if (player.getHealth() <= 0 ){
                        death(enemy);
                        battleFlag = false;
                        break;
                    }
                    if (enemy.getHealth() <= 0){
                        win(player,enemy);
                        battleFlag = false;
                        break;
                    }
                    continue;
                case '2':


                    continue;
                case '3':
                    System.out.print("Do you want to Escape? (Y/N):");
                    char confirm = LocationUtility.readConfirmSelection();
                    if(confirm == 'Y'){
                        battleFlag = false;
                    }
                    break;
            }
        }
    }

    public void death(Enemy enemy){
        System.out.println("unfortunately you dead when you flight with " + enemy.getName());
        System.out.print("Do you want to restart this game? (Y/N):");
        char confirm = LocationUtility.readConfirmSelection();
        if(confirm == 'Y'){
            Location location = new Location();
            Player player = new Player();
            location.mainMap(player);
        }
    }

    public void win(Player player, Enemy enemy){
        System.out.println("Congratulations!, you killed " + enemy.getName());
        System.out.println("you get " + enemy.getExpGain() + "exp from this battle.");
        player.setExp(player.getExp() + enemy.getExpGain());
    }

    public void attack(Player player, Enemy enemy){
        // player turn
        System.out.println("you attack " + enemy.getName() + " with some weapon");
        int playerDamage = player.getAttack() - enemy.getDefence();
        if (!avoid(enemy)){
            if (!crit(player)){
                enemy.setHealth(enemy.getHealth() - playerDamage);
                System.out.println("You deal " + playerDamage + " points damage to the " + enemy.getName() + "  (" + enemy.getHealth() + "/" + enemy.getHealthMax() + ")");
            }else {
                playerDamage = playerDamage * 2;
                enemy.setHealth(enemy.getHealth() - playerDamage);
                System.out.println("You deal " + playerDamage + " points damage to the " + enemy.getName() + "  (" + enemy.getHealth() + "/" + enemy.getHealthMax() + ")");
            }

        }
        if (enemy.getHealth() > 0){
            // enemy turn
            System.out.println(enemy.getName() + " attack you with some weapon");
            int enemyDamage = enemy.getAttack() - player.getDefence();
            if (!avoid(player)){
                if (!crit(enemy)){
                    player.setHealth(player.getHealth() - enemyDamage);//sdada
                    System.out.println("You get " + enemyDamage + " points damage from the " + enemy.getName() + "  (" + player.getHealth() + "/" + player.getHealthMax() + ")");
                }else {
                    enemyDamage = enemyDamage * 2;
                    player.setHealth(player.getHealth() - enemyDamage);
                    System.out.println("You get " + enemyDamage + " points damage from the 2" + enemy.getName() + "  (" + player.getHealth() + "/" + player.getHealthMax() + ")");
                }

            }
        }



    }

    public boolean avoid(Peon peon){
        Random random = new Random();
        int peonAvoidChance = peon.getAvoidChance();
        if (random.nextInt(100) <= peonAvoidChance){
            System.out.println("The attack was missed");
            return true;
        }
        return false;
    }

    public boolean crit(Peon peon){
        Random random = new Random();
        int peonCritChance = peon.getCritChance();
        if (random.nextInt(100) <= peonCritChance){
            System.out.println("This attack took a crit");
            return true;
        }
        return false;
    }
}
