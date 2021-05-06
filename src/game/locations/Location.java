package game.locations;

import game.enemies.Enemy;
import game.enemies.EnemyFactory;
import game.event.Battle;
import game.peons.Player;

public class Location {
    private boolean mainMapFlag;
    private boolean battleMapFlag;
    private boolean bossRoomFlag;
    private boolean cityFlag;


    public void mainMap(Player player) {
        mainMapFlag = true;
        do {
            System.out.println("---------------Main Map-----------------");
            System.out.println("        1. StormWind City");
            System.out.println("        2. Battle Map");
            System.out.println("        3. Boss Room");
            char key = LocationUtility.readMapSelection();
            System.out.println();
            switch (key) {
                case '1':
                    System.out.print("Do you want to enter City? (Y/N):");
                    char confirmCity = LocationUtility.readConfirmSelection();
                    if (confirmCity == 'Y') {
                        city(player);
                    }
                    continue;
                case '2':
                    System.out.print("Do you want to enter Battle Map? (Y/N):");
                    char confirmBattle = LocationUtility.readConfirmSelection();
                    if (confirmBattle == 'Y') {
                        battleMap(player);
                    }
                    continue;

                case '3':
                    System.out.println("Do you want to enter Boos Room? (Y/N):");
                    char confirmBoss1 = LocationUtility.readConfirmSelection();
                    if (confirmBoss1 == 'Y') {
                        System.out.println("Warning!: You will not be allowed to leave once you entry this room.");// You will end this game whether you defeat the Boss or not
                        System.out.println("Warning!: Are you sure?(Y/N)");
                        char confirmBoss2 = LocationUtility.readConfirmSelection();
                        if (confirmBoss2 == 'Y') {
                            mainMapFlag = false;
                        }
                    }
            }
        } while (mainMapFlag);

        bossRoom(player);

    }


    public void battleMap(Player player) {
        battleMapFlag = true;
        EnemyFactory enemyFactory = new EnemyFactory();
        do {
            System.out.println("---------------Battle Map-----------------");
            System.out.println("        1. Go ahead");
            System.out.println("        2. Search");
            System.out.println("        3. View");
            System.out.println("        4. Leave");
            char key = LocationUtility.readMapSelection();
            System.out.println();
            switch (key) {
                case '1':
                    LocationUtility.createRandomMap();
                    Enemy enemy = enemyFactory.generateEnemies(player);
                    new Battle(player, enemy);
                    continue;
                case '2':
                    //探索？？
                    continue;
                case '3':
                    //inspect?? 需要确定每个location和location里都有什么东西.
                    boolean inspectFlag = true;
                    while (inspectFlag) {
                        System.out.println("what would you like to inspect?");
                        System.out.println("1. Check " + player.getName());
                        System.out.println("2. Check Some NPC name");
                        System.out.println("3. leave");
                        char target = LocationUtility.readMapSelection();
                        switch (target) {
                            case '1':
                                player.view();
                                continue;
                            case '2':
                                continue;
                            case '3':
                                inspectFlag = false;
                                break;
                        }
                    }


                    continue;
                case '4':
                    System.out.print("Do you want to leave Battle Map? (Y/N):");
                    char confirm = LocationUtility.readConfirmSelection();
                    if (confirm == 'Y') {
                        battleMapFlag = false;
                    }
                    break;
            }
        } while (battleMapFlag);
    }

    public void city(Player player) {
        cityFlag = true;
        do {
            System.out.println("---------------City-----------------");
            System.out.println("        1. NPC");
            System.out.println("        2. Pass this turn");
            System.out.println("        3. Leave");
            char key = LocationUtility.readMapSelection();
            System.out.println();
            switch (key) {
                case '1':
                    //跟NPC谈话的内容
                    continue;
                case '2':
                    //休息的功能
                    continue;
                case '3':
                    System.out.print("Do you want to leave City? (Y/N):");
                    char confirm = LocationUtility.readConfirmSelection();
                    if (confirm == 'Y') {
                        cityFlag = false;
                    }
                    break;
            }
        } while (cityFlag);
    }

    public void bossRoom(Player player) {

    }
}
