package game.locations;

import java.util.Scanner;
//Location Utility is the tool class for the map contribution

public class LocationUtility {
    private static Scanner scan = new Scanner(System.in);
    private static LocationName locationName = new LocationName();

    /**
     * Read the string from keyboard, as well as limit the length of input string
     */

    public static String readKeyBoard(int limit, boolean blankReturn ){
        String line = "";

        while(scan.hasNextLine()){
            line = scan.nextLine();
            if(line.length() == 0){
                if(blankReturn){
                    return line;
                }else{
                    continue;
                }
            }
            if(line.length() > limit){
                System.out.println("Bad input, your input length should less than " + limit + ", please retype: ");
                continue;
            }

            break;
        }
        return line;
    }

    /**
     * Select map by use '1' '2' '3' '4''5', other character will throw the "Bad input" message
     */

    public static char readMapSelection(){
        char c;
        for(; ;){
            String str = readKeyBoard(1, false);
            if (str.length() != 0){
                c = str.charAt(0);
                if (c != '1' && c != '2' && c != '3' && c != '4' && c != '5') {
                    System.out.println("Bad input, please retype: ");
                }else{
                    break;
                }
            }

        }
        return c;
    }

    /**
     * Read the character from keyboard. If there is null, default
     */
    public static char readChar(char defaultValue) {
        String str = readKeyBoard(1, true);
        return (str.length() == 0) ? defaultValue : str.charAt(0);
    }

    /**
     To double check whether player want to select this choice, shows "Y/N"
     */
    public static char readConfirmSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false).toUpperCase();
            if (str.length() != 0){
                c = str.charAt(0);
                if (c == 'Y' || c == 'N' || c == '1' || c == '2') {
                    break;
                } else {
                    System.out.print("Bad input, please retype: ");
                }
            }

        }
        return c;
    }

    /**
     * Creat random map name
     */
    public static void createRandomMap(){
        locationName.setRiskLevel();
        locationName.setLandscape();
        String risk = locationName.riskLevel.get((int)(Math.random() * 40));
        String landscape = locationName.landscape.get((int)(Math.random() * 30));
        if(landscape.equals("Camp")){
            System.out.println("---------------You enter the Camp area-----------------");
        }else {
            System.out.println("---------------You enter the " + risk +" " + landscape + " " + "area-----------------");
        }

    }

    public static int mapEvent(){
        if (inToCamp()){
            System.out.println("---------------You enter the Camp area-----------------");
            return 2;
        }else {
            if (inToBattle()){
                return 1;

            }
            return 0;
        }

    }
    /**
     * Control the battle probability in the battle map by different map name`s heading.
     */
    public static boolean inToBattle(){
        locationName.setRiskLevel();
        locationName.setLandscape();
        String risk = locationName.riskLevel.get((int)(Math.random() * 40));
        String landscape = locationName.landscape.get((int)(Math.random() * 30));
        System.out.println("---------------You enter the " + risk +" " + landscape + " " + "area-----------------");
        if(risk.equals("Safe")){
            if(((int)(Math.random() * 100)) >= 80 ){
                //In the map with heading "Safe", battle probability 20%
                return true;
            }
        }
        if(risk.equals("Neutral")){
            if(((int)(Math.random() * 100)) >= 50 ){
                //In the map with heading "Neutral", battle probability 50%
                return true;
            }
        }
        if(risk.equals("Disturbed")){
            if(((int)(Math.random() * 100)) >= 30){
                //In the map with heading "Neutral", battle probability 70%
                return true;
            }
        }
        if(risk.equals("Dangerous")){
            //In the map with heading "Neutral", battle probability 100%
            return true;
        }
        //peaceful area , no battle
        return false;
    }
    public static boolean inToCamp(){
        locationName.setRiskLevel();
        locationName.setLandscape();
        String risk = locationName.riskLevel.get((int)(Math.random() * 40));
        String landscape = locationName.landscape.get((int)(Math.random() * 30));
        if(landscape.equals("Camp")){

            return true;
            //回复一定量的体力
        }
        return false;
    }

}
