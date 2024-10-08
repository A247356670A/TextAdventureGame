package game.locations;

import game.GameLauncher;
import game.enemies.Boss;
import game.enemies.Enemy;
import game.enemies.EnemyFactory;
import game.event.Battle;
import game.event.Talk;
import game.items.Item;
import game.items.ItemFactory;
import game.peons.Player;



public class Location {
    public static boolean mainMapFlag;
    public boolean battleMapFlag;
    public boolean bossRoomFlag;
    public boolean cityFlag;
    public boolean killSlime = false;


    public void mainMap(Player player) {
        new Talk(player,"Background");
        mainMapFlag = true;
        mainLoop:
        do {
            System.out.println("---------------Main Map-----------------");
            System.out.println("        1. StormWind City");
            System.out.println("        2. Battle Map");
            System.out.println("        3. Boss Room");
            System.out.println("        4. Quit Game");
            char key = LocationUtility.readMapSelection();
            System.out.println();
            switch (key) {
                case '1':
                    System.out.println("You choose to go to StormWind City");
                    System.out.print("Do you want to enter City? (Y/N):");
                    System.out.println();
                    char confirmCity = LocationUtility.readConfirmSelection();
                    if (confirmCity == 'Y' || confirmCity == '1') {

                        System.out.println("You choose to enter StormWind City");
                        city(player);
                    }else {
                        System.out.println("You choose to leave StormWind City");
                    }
                    continue;
                case '2':
                    System.out.println("You choose to go to Battle Map");
                    System.out.print("Do you want to enter Battle Map? (Y/N):");
                    System.out.println();
                    char confirmBattle = LocationUtility.readConfirmSelection();
                    if (confirmBattle == 'Y' || confirmBattle == '1') {
                        System.out.println("You choose to enter Battle Map");
                        battleMap(player);
                    }else {
                        System.out.println("You choose to leave Battle Map");
                    }
                    continue;

                case '3':
                    if (bossRoomFlag){
                        System.out.println("You choose to go to Boss Room");
                        System.out.println("Do you want to enter Boos Room? (Y/N):");
                        char confirmBoss1 = LocationUtility.readConfirmSelection();
                        if (confirmBoss1 == 'Y') {
                            System.out.println("Warning!: You will not be allowed to leave once you entry this room.");// You will end this game whether you defeat the Boss or not
                            System.out.println("Warning!: Are you sure?(Y/N)");
                            char confirmBoss2 = LocationUtility.readConfirmSelection();
                            if (confirmBoss2 == 'Y') {
                                System.out.println("You choose to enter Boss Room");
                                Enemy boss = new EnemyFactory().loadBoss();
                                new Battle(player,boss);

                            }else {
                                break;
                            }
                        }else {
                            continue;
                        }
                    }else {
                        System.out.println("How about we explore the area ahead of us later!");
                        continue;
                    }

                case '4':
                    mainMapFlag = false;
                    break;

            }
        } while (mainMapFlag);

//        bossRoom(player);

    }


    public void battleMap(Player player) {
        battleMapFlag = true;
        EnemyFactory enemyFactory = new EnemyFactory();
        ItemFactory itemFactory = new ItemFactory();
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
                    //前进是否要加事件？
                    System.out.println("You choose to Go ahead");
//                    LocationUtility.createRandomMap();
                    if (LocationUtility.inToCamp()){
                        System.out.println("---------------You enter the Camp area-----------------");
                        System.out.println("You are safe, for now.");
                        System.out.println("You take a breath and recover some health");
                        player.heal(player.getHealthMax() * 10 /100);
                        System.out.println("(" + player.getHealth() + "/" + player.getHealthMax() + ")");
                    }else {
                        if (LocationUtility.inToBattle()){
                            Enemy enemy = enemyFactory.generateEnemies(player);
                            if (enemy.getName().contains("Goblin")){
                                new Talk(player,"Chapter 2-1");
                            }else if (enemy.getName().contains("Slime")){
                                new Talk(player,"Chapter 2-2");
                                killSlime = true;
                            }
                            new Battle(player, enemy);

                        }else {
                            System.out.println("nothing happens");
                        }

                    }

                    continue;
                case '2':
                    //探索？？
                    System.out.println("You choose to Search");
                    Item item = itemFactory.generateItem(player);
                    if (item != null){
                        System.out.println("You find something, would you like to check it? (Y/N)");
                        char confirmSearch = LocationUtility.readConfirmSelection();
                        if (confirmSearch == 'Y') {
                            item.view();
                            System.out.println("Do you want to equip this? (Y/N)");
                            char confirmSearch2 = LocationUtility.readConfirmSelection();
                            if (confirmSearch2 == 'Y'){
                                player.equip(item);
                            }else {
                                break;
                            }
                        }else {
                            continue;
                        }
                    }


                    continue;
                case '3':
                    //inspect?? 需要确定每个location和location里都有什么东西.
                    System.out.println("You choose to inspect");
                    boolean inspectFlag = true;
                    while (inspectFlag) {
                        System.out.println("what would you like to inspect?");
                        System.out.println("1. Check " + player.getName());
                        System.out.println("2. Check Some NPC name");
                        System.out.println("3. leave");
                        char target = LocationUtility.readMapSelection();
                        switch (target) {
                            case '1':
                                System.out.println("You choose to Check " + player.getName());
                                player.view();
                                continue;
                            case '2':
                                System.out.println("You choose to Check Some NPC name");
                                continue;
                            case '3':
                                System.out.println("You choose to leave");
                                inspectFlag = false;
                                break;
                        }
                    }


                    continue;
                case '4':
                    System.out.print("Do you want to leave Battle Map? (Y/N):");
                    char confirm = LocationUtility.readConfirmSelection();
                    if (confirm == 'Y') {
                        System.out.println("You choose to leave Battle Map");
                        battleMapFlag = false;
                    }else {
                        System.out.println("You choose to stay in Battle Map");
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
            System.out.println("        2. Get some rest");
            System.out.println("        3. Leave");
            char key = LocationUtility.readMapSelection();
            System.out.println();
            switch (key) {
                case '1':
                    //跟NPC谈话的内容
                    if (killSlime) {
                        new Talk(player, "Chapter 2-3");
                        System.out.println("Now you know the way to the boss room!");
                        bossRoomFlag = true;
                    }else {
                        new Talk(player, "Chapter 1");
                    }
                    continue;
                case '2':
                    //休息的功能
                    System.out.println("You find a hotel, the owner offers your a free room and a big dinner. \r\n\"It has been a long time to see such a warrior like you! \" he said, \r\n\"I used to be an adventurer like you, then I took an arrow in the knee.\"");
                    System.out.println("You take a good sleep and feels like reborn");
                    player.healAll();
                    continue;
                case '3':
                    System.out.print("Do you want to leave City? (Y/N):");
                    char confirm = LocationUtility.readConfirmSelection();
                    if (confirm == 'Y') {
                        System.out.println("You choose to leave City");
                        cityFlag = false;
                    }else {
                        System.out.println("You choose to stay in City");
                    }
                    break;
            }
        } while (cityFlag);
    }

}
