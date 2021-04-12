package game.locations;

import java.util.Scanner;

public class LocationUtility {
    private static Scanner scan = new Scanner(System.in);

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
     * 用于地图界面选择（暂定3个选项），非1,2,3,4的选项返回异常
     */

    public static char readMapSelection(){
        char c;
        for(; ;){
            String str = readKeyBoard(1, false);
            c = str.charAt(0);
            if (c != '1' && c != '2' && c != '3' && c != '4') {
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

}
