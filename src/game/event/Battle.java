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
    Boolean escapeFlag;


    public Battle(Player player, Enemy enemy) {
        battleFlag = true;
        escapeFlag = true;
        while (battleFlag) {
            System.out.println("What would you do?");
            System.out.println("1. Attack");
            System.out.println("2. Defence");
            System.out.println("3. View");
            System.out.println("4. Escape");
            char key = LocationUtility.readMapSelection();
            System.out.println();

            switch (key) {
                case '1':
                    System.out.println("You choose to Attack " + enemy.getName());
                    attack(player, enemy);
                    if (player.getHealth() <= 0) {
                        death(enemy);
                        battleFlag = false;
                        break;
                    }
                    if (enemy.getHealth() <= 0) {
                        win(player, enemy);
                        battleFlag = false;
                        break;
                    }
                    continue;
                case '2':
                    System.out.println("You choose to Defence.");
                    defence(player, enemy);
                    if (player.getHealth() <= 0) {
                        death(enemy);
                        battleFlag = false;
                        break;
                    }
                    if (enemy.getHealth() <= 0) {
                        win(player, enemy);
                        battleFlag = false;
                        break;
                    }

                    continue;
                case '3':
                    System.out.println("You choose to inspect");
                    boolean inspectFlag = true;
                    while (inspectFlag) {
                        System.out.println("what would you like to inspect?");
                        System.out.println("1. Check " + player.getName());
                        System.out.println("2. Check " + enemy.getName());
                        System.out.println("3. leave");
                        char target = LocationUtility.readMapSelection();
                        switch (target) {
                            case '1':
                                System.out.println("You choose to Check " + player.getName());
                                player.view();
                                continue;
                            case '2':
                                System.out.println("You choose to Check " + enemy.getName());
                                enemy.view();
                                continue;
                            case '3':
                                System.out.println("You choose to leave.");
                                inspectFlag = false;
                                break;
                        }
                    }
                    continue;
                case '4':
                    System.out.println("You choose to Escape.");
                    if (escapeFlag){
                        System.out.print("Do you want to Escape? (Y/N):");
                        char confirm = LocationUtility.readConfirmSelection();
                        if (confirm == 'Y') {
                            System.out.println("You choose YES.");
                            Random random = new Random();

                            int luck = 50 + player.getLuck() - enemy.getSkillChance();
                            int result = random.nextInt(100);
                            if (result < luck){
                                System.out.println("You are luck to escape from that battle.");
                                battleFlag = false;

                            }else {
                                System.out.println("You are failed to escape from this battle.");
                                escapeFlag = false;
                            }

                        }else {
                            System.out.println("You don’t want to be a coward. You clung to your weapon and returned to the fight.");
                        }
                        break;
                    }else {
                        System.out.println("You can't escape anymore.");
                    }

            }
        }
    }

    public void death(Enemy enemy) {
        System.out.println("Unfortunately you dead when you flight with " + enemy.getName());
        System.out.print("Do you want to restart this game? (Y/N):");
        char confirm = LocationUtility.readConfirmSelection();
        if (confirm == 'Y') {
            Location location = new Location();
            Player player = new Player();
            location.mainMap(player);
        }
    }

    public void win(Player player, Enemy enemy) {
        System.out.println("Congratulations!, you killed " + enemy.getName());
        System.out.println("You get " + enemy.getExpGain() + " exp from this battle.");
        player.setExp(player.getExp() + enemy.getExpGain());
    }

    public void attack(Player player, Enemy enemy) {
        // player turn
        Random rand = new Random();
        System.out.println("You attack " + enemy.getName() + " with some weapon");
        int playerDamage = player.getAttack() - enemy.getDefence();

        if (playerDamage <= 0){
            playerDamage = 1;
        }
        playerDamage = rand.nextInt((int)(playerDamage * 1.2)) + (int)(playerDamage * 0.8);
        if (!avoid(enemy)) {
            if (!crit(player)) {
                enemy.setHealth(enemy.getHealth() - playerDamage);
                System.out.println("You deal " + playerDamage + " points damage to the " + enemy.getName() + "  (" + enemy.getHealth() + "/" + enemy.getHealthMax() + ")");
            } else {
                playerDamage = playerDamage * 2;
                enemy.setHealth(enemy.getHealth() - playerDamage);
                System.out.println("You deal " + playerDamage + " points damage to the " + enemy.getName() + "  (" + enemy.getHealth() + "/" + enemy.getHealthMax() + ")");
            }

        }
        if (enemy.getHealth() > 0) {
            // enemy turn
            System.out.println(enemy.getName()+  " attack you with some weapon");
            int enemyDamage = enemy.getAttack() - player.getDefence();
            if (enemyDamage <= 0){
                enemyDamage = 1;
            }
            enemyDamage = rand.nextInt((int)(enemyDamage * 1.2)) + (int)(enemyDamage * 0.8);
            if (!avoid(player)) {
                if (!crit(enemy)) {
                    player.setHealth(player.getHealth() - enemyDamage);
                    System.out.println("You get " + enemyDamage + " points damage from the " + enemy.getName() + "  (" + player.getHealth() + "/" + player.getHealthMax() + ")");
                } else {
                    enemyDamage = enemyDamage * 2;
                    player.setHealth(player.getHealth() - enemyDamage);
                    System.out.println("You get " + enemyDamage + " points damage from the 2" + enemy.getName() + "  (" + player.getHealth() + "/" + player.getHealthMax() + ")");
                }
            }
        }
    }

    public void defence(Player player, Enemy enemy) {
        Random random = new Random();
        System.out.println("You get ready for the coming attack.");
        int playerDefence = player.getDefence();
        player.setDefence(playerDefence * (100 + player.getEndurance()) / 100);
        System.out.println("Your Defence temporarily increase " + (player.getDefence() - playerDefence) + " points!");
        int lostHealth = player.getHealthMax() - player.getHealth();
        int healHealth = lostHealth * player.getEndurance() / 100 - random.nextInt(player.getEndurance());
        if (healHealth <= 0){
            healHealth = 1;
        }
        player.setHealth(player.getHealth() + healHealth);
        System.out.println("You take a breath and recover " + healHealth + " points health.");
        System.out.println("You health is current " + player.getHealth() + "/" + player.getHealthMax());

        // enemy turn
        System.out.println(enemy.getName() + " attack you with some weapon");
        int enemyDamage = enemy.getAttack() - player.getDefence();
        if (enemyDamage <= 0){
            enemyDamage = 1;
        }
        enemyDamage = random.nextInt((int)(enemyDamage * 1.2)) + (int)(enemyDamage * 0.8);
        if (!avoid(player)) {
            if (!crit(enemy)) {
                player.setHealth(player.getHealth() - enemyDamage);//sdada
                System.out.println("You get " + enemyDamage + " points damage from the " + enemy.getName() + "  (" + player.getHealth() + "/" + player.getHealthMax() + ")");
            } else {
                enemyDamage = enemyDamage * 2;
                player.setHealth(player.getHealth() - enemyDamage);
                System.out.println("You get " + enemyDamage + " points damage from the " + enemy.getName() + "  (" + player.getHealth() + "/" + player.getHealthMax() + ")");
            }
        }
        player.setDefence(playerDefence);

    }

    public boolean avoid(Peon peon) {
        Random random = new Random();
        int peonAvoidChance = peon.getAvoidChance();
        if (random.nextInt(100) <= peonAvoidChance) {
            System.out.println("The attack was missed");
            return true;
        }
        return false;
    }

    public boolean crit(Peon peon) {
        Random random = new Random();
        int peonCritChance = peon.getCritChance();
        if (random.nextInt(100) <= peonCritChance) {
            System.out.println("This attack took a crit");
            return true;
        }
        return false;
    }
    public boolean poison(Enemy enemy){
        Random random = new Random();
        int enemyPoisonChance = enemy.getSkillChance();
        if (random.nextInt(100) + 1 <= enemyPoisonChance) {
            System.out.println("This attack deal a poison");
            return true;
        }
        return false;
    }
}
