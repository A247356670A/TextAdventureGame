package game.locations;

public class Location {
    private boolean mainMapFlag;
    private boolean battleMapFlag;
    private boolean bossRoomFlag;
    private boolean cityFlag;




    public void mainMap() {
        mainMapFlag = true;
        do {
            System.out.println("---------------Main Map-----------------");
            System.out.println("        1. Stormwind(x) City");
            System.out.println("        2. Battle Map");
            System.out.println("        3. Boss Room");
            char key = LocationUtility.readMapSelection();
            System.out.println();
            switch (key) {
                case '1':
                    System.out.print("Do you want to enter City? (Y/N):");
                    char confirmCity = LocationUtility.readConfirmSelection();
                    if(confirmCity == 'Y'){
                        city();
                    }
                    continue;
                case '2':
                    System.out.print("Do you want to enter Battle Map? (Y/N):");
                    char confirmBattle = LocationUtility.readConfirmSelection();
                    if(confirmBattle == 'Y') {
                        battleMap();
                    }
                    continue;

                case '3':
                    System.out.println("Do you want to enter Boos Room? (Y/N):");
                    char confirmBoss1 = LocationUtility.readConfirmSelection();
                    if (confirmBoss1 == 'Y') {
                        System.out.println("Warning!: You will not be allowed to leave once you entry this room.");// You will end this game whether you defeat the Boss or not
                        System.out.println("Warning!: Are you sure?(Y/N)");
                        char confirmBoss2 = LocationUtility.readConfirmSelection();
                        if (confirmBoss2 == 'Y'){
                            mainMapFlag = false;
                        }
                    }
            }
        } while (mainMapFlag);

        bossRoom();

    }


    public void battleMap() {
        battleMapFlag = true;
        do {
            System.out.println("---------------Battle Map-----------------");
            System.out.println("        1. Go ahead");
            System.out.println("        2. Search");
            System.out.println("        3. Leave");
            char key = LocationUtility.readMapSelection();
            System.out.println();
            switch (key) {
                case '1':
                    //前进是否要加事件？
                    continue;
                case '2':
                    //探索？？
                    continue;
                case '3':
                    System.out.print("Do you want to leave Battle Map? (Y/N):");
                    char confirm = LocationUtility.readConfirmSelection();
                    if(confirm == 'Y'){
                        battleMapFlag = false;
                    }
                    break;
            }
        } while (battleMapFlag);
    }

    public void city(){
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
                    if(confirm == 'Y'){
                        cityFlag = false;
                    }
                    break;
            }
        } while (cityFlag);
    }

    public void bossRoom(){

    }
}
