package game.locations;

import java.util.Scanner;

public class LocationUtility {
    private static Scanner scan = new Scanner(System.in);
    private static LocationName locationName = new LocationName();

    /**
     * 读取键盘输入的字符串，同时限制字符串的长度
     */

    private static String readKeyBoard(int limit, boolean blankReturn ){
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
            if(line.length() < 1 || line.length() > limit){
                System.out.println("Bad input, your input length should less than " + limit + ", please retype: ");
                continue;
            }
            break;
        }
        return line;
    }

    /**
     * 用于地图界面选择（暂定3个选项），非1,2,3,4,5的选项返回异常
     */

    public static char readMapSelection(){
        char c;
        for(; ;){
            String str = readKeyBoard(1, false);
            c = str.charAt(0);
            if (c != '1' && c != '2' && c != '3' && c != '4' && c != '5') {
                System.out.println("Bad input, please retype: ");
            }else{
                break;
            }
        }
        return c;
    }

    /**
     * 从键盘读取字符，如果用户未输入字符则直接default
     */
    public static char readChar(char defaultValue) {
        String str = readKeyBoard(1, true);
        return (str.length() == 0) ? defaultValue : str.charAt(0);
    }

    /**
     用于确认选择的输入。该方法从键盘读取‘Y’或’N’，并将其作为方法的返回值。
     */
    public static char readConfirmSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("Bad input, please retype: ");
            }
        }
        return c;
    }

    /**
     * 创造随机的地图名字
     */
    public static void createRandomMap(){
        locationName.setRiskLevel();
        locationName.setLandscape();
        String risk = locationName.riskLevel.get((int)(Math.random() * 40));
        String landscape = locationName.landscape.get((int)(Math.random() * 30));
        System.out.println("---------------You enter the " + risk +" " + landscape + " " + "area-----------------");
    }

    /**
     * 根据危险等级调整遇怪的概率
     */
    public static void inToBattle(){
        locationName.setRiskLevel();
        locationName.setLandscape();
        String risk = locationName.riskLevel.get((int)(Math.random() * 40));
        String landscape = locationName.landscape.get((int)(Math.random() * 30));
        System.out.println("---------------You enter the " + risk +" " + landscape + " " + "area-----------------");
        if(risk.equals("Safe")){
            if(((int)(Math.random() * 100)) >= 80 ){
                //遇怪： 百分之20
            }
        }
        if(risk.equals("Neutral")){
            if(((int)(Math.random() * 100)) >= 50 ){
                //遇怪： 百分之50
            }
        }
        if(risk.equals("Disturbed")){
            if(((int)(Math.random() * 100)) >= 30){
                //遇怪：百分之百
            }
        }
        if(risk.equals("Dangerous")){
            //遇怪：百分百
        }
        //peaceful 不遇怪

        if(landscape.equals("Camp")){
            //回复一定量的体力
        }
    }

}
