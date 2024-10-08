package game.event;

import game.enemies.Enemy;
import game.enemies.EnemyFactory;
import game.enemies.EnemyProperty;
import game.locations.Location;
import game.locations.LocationUtility;
import game.peons.Peon;
import game.peons.Player;

import java.util.Random;

import static game.locations.LocationUtility.readKeyBoard;
import static java.lang.Thread.sleep;

public class Battle {
    Boolean battleFlag;
    Boolean escapeFlag;


    public Battle(Player player, Enemy enemy) {
        battleFlag = true;
        escapeFlag = true;
        if (battleFlag)
            do {
            printOutSleep(800);
                System.out.println("What would you do?");
                System.out.println("1. Attack");
                System.out.println("2. Defence");
                System.out.println("3. Skill");
                System.out.println("4. View");
                System.out.println("5. Escape");
                char key = LocationUtility.readMapSelection();
                System.out.println();

                switch (key) {
                    case '1':
                    printOutSleep(800);
                        System.out.println("You choose to Attack " + enemy.getName());
                        attack(player, enemy);
                        if (player.getHealth() <= 0) {

                            if (death(enemy, player)) {
//                                String str = player.getName();
//                                player = new Player(str);
                                player.healAll();
                                battleFlag = false;
                                break;
                            }
                            if (death(enemy, player) && enemy.getEnemyProperty() == EnemyProperty.BOSS) {
                                Player cursedSoul = new Player("cursedSoul");
                                cursedSoul.setStrength(1);
                                cursedSoul.setEndurance(1);
                                cursedSoul.saveToJSONFile("json/profile/player.json");
                                battleFlag = false;
                                break;

                            }
                            Location.mainMapFlag = false;
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
                    printOutSleep(800);
                        System.out.println("You choose to Defence.");
                        defence(player, enemy);
                        if (player.getHealth() <= 0) {

                            if (death(enemy, player)){
//                                String str = player.getName();
//                                player = new Player(str);
                                player.healAll();
                                battleFlag = false;
                                break;
                            }
                            if (death(enemy, player) && enemy.getEnemyProperty() == EnemyProperty.BOSS) {
                                Player cursedSoul = new Player("cursedSoul");
                                cursedSoul.setStrength(1);
                                cursedSoul.setEndurance(1);
                                cursedSoul.saveToJSONFile("json/profile/player.json");
                                battleFlag = false;
                                break;

                            }
                            Location.mainMapFlag = false;
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
                        printOutSleep(800);
                        System.out.println("You choose to Attack " + enemy.getName());
                        continue;
                    case '4':
                    printOutSleep(800);
                        System.out.println("You choose to inspect");
                        boolean inspectFlag = true;
                        while (inspectFlag) {
//                        printOutSleep(800);
                            System.out.println("what would you like to inspect?");
                            System.out.println("1. Check " + player.getName());
                            System.out.println("2. Check " + enemy.getName());
                            System.out.println("3. leave");
                            char target = LocationUtility.readMapSelection();
                            switch (target) {
                                case '1':
                                printOutSleep(800);
                                    System.out.println("You choose to Check " + player.getName());
                                    player.view();
                                    continue;
                                case '2':
                                printOutSleep(800);
                                    System.out.println("You choose to Check " + enemy.getName());
                                    enemy.view();
                                    continue;
                                case '3':
//                                printOutSleep(800);
                                    System.out.println("You choose to leave.");
                                    inspectFlag = false;
                                    break;
                            }
                        }
                        continue;
                    case '5':
                    printOutSleep(800);
                        System.out.println("You choose to Escape.");
                        if (escapeFlag) {
//                        printOutSleep(800);
                            System.out.print("Do you want to Escape? (Y/N):");
                            char confirm = LocationUtility.readConfirmSelection();
                            if (confirm == 'Y') {
                            printOutSleep(800);
                                System.out.println("You choose YES.");
                                Random random = new Random();
                                if (enemy.getEnemyProperty() == EnemyProperty.BOSS){
                                    System.out.println("The dark power block the way back, you can't escape!");
                                    escapeFlag = false;
                                }else {
                                    int luck = 50 + player.getLuck() - enemy.getSkillChance();
                                    int result = random.nextInt(100);
                                    if (result < luck) {
                                        printOutSleep(800);
                                        System.out.println("You are luck to escape from that battle.");
                                        battleFlag = false;

                                    } else {
                                        printOutSleep(800);
                                        System.out.println("You are failed to escape from this battle.");
                                        escapeFlag = false;
                                    }
                                }


                            } else {
                            printOutSleep(800);
                                System.out.println("You don’t want to be a coward. You clung to your weapon and returned to the fight.");
                            }
                            break;
                        } else {
                        printOutSleep(800);
                            System.out.println("You can't escape anymore.");
                        }

                }
            } while (battleFlag);
    }

    public boolean death(Enemy enemy, Player player) {
        printOutSleep(800);
        System.out.println("Unfortunately you dead when you flight with " + enemy.getName());
        printOutSleep(800);
        if (enemy.getEnemyProperty() == EnemyProperty.BOSS){
            printOutSleep(800);
            System.out.println("\r\n\r\n");
            printOutSleep(800);
            System.out.println("-------------------------------GAME OVER-------------------------------");
            printOutSleep(800);
            printOutSleep(800);
            System.out.print(enemy.getName() + "punished your soul, You have lost everything.");
            System.out.println("\r\n\r\n");
            return true;

        }
        printOutSleep(800);
        System.out.print("Do you want to restart this game? (Y/N):");
        char confirm = LocationUtility.readConfirmSelection();
        if (confirm == 'Y') {
            return true;

        }
        return false;
    }

    public void win(Player player, Enemy enemy) {
        if (enemy.getEnemyProperty() ==EnemyProperty.BOSS){
            new Talk(player,"Chapter 3");
        }
        printOutSleep(800);
        System.out.println("Congratulations!, you killed " + enemy.getName());
        printOutSleep(800);
        System.out.println("You get " + enemy.getExpGain() + " exp from this battle.");
        player.setExp(player.getExp() + enemy.getExpGain());
        player.saveToJSONFile("json/profile/player.json");

    }

    private void printOutSleep(int sleepLength) {
        try {
            sleep(sleepLength);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void attack(Player player, Enemy enemy) {
        // player turn
        Random rand = new Random();
        printOutSleep(800);
        System.out.println("You attack " + enemy.getName() + " with some weapon");
        int playerDamage = player.getAttack() - enemy.getDefence();

        if (playerDamage <= 0) {
            playerDamage = 1;
        }
        playerDamage = rand.nextInt((int) (playerDamage * 1.2)) + (int) (playerDamage * 0.8);
        if (!avoid(enemy)) {
            if (!crit(player)) {
                enemy.setHealth(enemy.getHealth() - playerDamage);
                printOutSleep(800);
                System.out.println("You deal " + playerDamage + " points damage to the " + enemy.getName() + "  (" + enemy.getHealth() + "/" + enemy.getHealthMax() + ")");
            } else {
                playerDamage = playerDamage * 2;
                enemy.setHealth(enemy.getHealth() - playerDamage);
                printOutSleep(800);
                System.out.println("You deal " + playerDamage + " points damage to the " + enemy.getName() + "  (" + enemy.getHealth() + "/" + enemy.getHealthMax() + ")");
            }

        }
        if (enemy.getHealth() > 0) {
            // enemy turn
            printOutSleep(800);
            if ((enemy.getEnemyProperty() == EnemyProperty.BOSS && rand.nextInt(100) + 1 <= enemy.getSkillChance()) ||(enemy.getEnemyProperty() == EnemyProperty.BOSS && enemy.getHealth() <= enemy.getHealthMax() /3) ) {
                bossSkill(enemy);
            }

            System.out.println(enemy.getName() + " attack you with some weapon");
            int enemyDamage = enemy.getAttack() - player.getDefence();
            if (enemyDamage <= 0) {
                enemyDamage = 1;
            }

            enemyDamage = rand.nextInt((int) (enemyDamage * 1.2)) + (int) (enemyDamage * 0.8);
            if (!avoid(player)) {
                if (!crit(enemy)) {
                    player.setHealth(player.getHealth() - enemyDamage);
                    printOutSleep(800);
                    System.out.println("You get " + enemyDamage + " points damage from the " + enemy.getName() + "  (" + player.getHealth() + "/" + player.getHealthMax() + ")");
                } else {
                    enemyDamage = enemyDamage * 2;
                    player.setHealth(player.getHealth() - enemyDamage);
                    printOutSleep(800);
                    System.out.println("You get " + enemyDamage + " points damage from the 2" + enemy.getName() + "  (" + player.getHealth() + "/" + player.getHealthMax() + ")");
                }
            }

        }
    }

    private int lostHealthBounds(Player player) {
        int lostHealthBounds = 0;
        if (player.getHealth() >= player.getHealthMax() / 2) {
            lostHealthBounds = 0;
        } else if (player.getHealth() >= player.getHealthMax() / 4) {
            lostHealthBounds = player.getEndurance() / 2;
        } else {
            lostHealthBounds = player.getEndurance();
        }
        return lostHealthBounds;
    }

    public void defence(Player player, Enemy enemy) {
        Random random = new Random();
        printOutSleep(800);
        System.out.println("You get ready for the coming attack.");
        int playerDefence = player.getDefence();
        player.setDefence(playerDefence * (100 + player.getEndurance()) / 100);
        printOutSleep(800);
        System.out.println("Your Defence temporarily increase " + (player.getDefence() - playerDefence) + " points!");
        int lostHealth = player.getHealthMax() - player.getHealth();
        int healHealth = lostHealth * (player.getEndurance() + lostHealthBounds(player)) / (100 - random.nextInt(player.getEndurance()));
        if (healHealth <= 0) {
            healHealth = 1;
        }
        player.setHealth(player.getHealth() + healHealth);
        printOutSleep(800);
        System.out.println("You take a breath and recover " + healHealth + " points health.");
        printOutSleep(800);
        System.out.println("You health is current " + player.getHealth() + "/" + player.getHealthMax());

        // enemy turn
        printOutSleep(800);
        System.out.println(enemy.getName() + " attack you with some weapon");
        int enemyDamage = enemy.getAttack() - player.getDefence();
        if (enemyDamage <= 0) {
            enemyDamage = 1;
        }
        enemyDamage = random.nextInt((int) (enemyDamage * 1.2)) + (int) (enemyDamage * 0.8);
        if (!avoid(player)) {
            if (!crit(enemy)) {
                player.setHealth(player.getHealth() - enemyDamage);//sdada
                printOutSleep(800);
                System.out.println("You get " + enemyDamage + " points damage from the " + enemy.getName() + "  (" + player.getHealth() + "/" + player.getHealthMax() + ")");
            } else {
                enemyDamage = enemyDamage * 2;
                player.setHealth(player.getHealth() - enemyDamage);
                printOutSleep(800);
                System.out.println("You get " + enemyDamage + " points damage from the " + enemy.getName() + "  (" + player.getHealth() + "/" + player.getHealthMax() + ")");
            }
        }
        player.setDefence(playerDefence);

    }

    public void bossSkill(Enemy enemy) {
        Random random = new Random();
        printOutSleep(800);
        System.out.println(enemy.getName() + " feels very angry.");
        int bossAttack = enemy.getAttack();
        enemy.setAttack(bossAttack + bossAttack * (random.nextInt(20) + 10) / 100);
        printOutSleep(800);
        System.out.println(enemy.getName() + "'s Attack increase " + (enemy.getAttack() - bossAttack) + " points!");
        int lostHealth = enemy.getHealthMax() - enemy.getHealth();
        int healHealth = lostHealth * 50 / 100;
        if (healHealth <= 0) {
            healHealth = 1;
        }
        enemy.setHealth(enemy.getHealth() + healHealth);
        printOutSleep(800);
        System.out.println(enemy.getName() + " use dark magic to recover " + healHealth + " points health." + "(" + enemy.getHealth() + "/" + enemy.getHealthMax() + ")");

    }

    public boolean avoid(Peon peon) {
        Random random = new Random();
        int peonAvoidChance = peon.getAvoidChance();
        if (random.nextInt(100) <= peonAvoidChance) {
            printOutSleep(800);
            System.out.println("The attack was missed");
            return true;
        }
        return false;
    }

    public boolean crit(Peon peon) {
        Random random = new Random();
        int peonCritChance = peon.getCritChance();
        if (random.nextInt(100) <= peonCritChance) {
            printOutSleep(800);
            System.out.println("This attack took a crit");
            return true;
        }
        return false;
    }

    public boolean poison(Enemy enemy) {
        Random random = new Random();
        int enemyPoisonChance = enemy.getSkillChance();
        if (random.nextInt(100) + 1 <= enemyPoisonChance) {
            printOutSleep(800);
            System.out.println("This attack deal a poison");
            return true;
        }
        return false;
    }
}
