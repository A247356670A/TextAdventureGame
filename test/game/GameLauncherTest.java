package game;

import game.locations.Location;
import game.peons.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class GameLauncherTest {
//    private final InputStream systemIn = System.in;
//    private final PrintStream systemOut = System.out;
//
//    private ByteArrayInputStream testIn;
//    private ByteArrayOutputStream testOut;
//
//    @Before
//    public void setUpOutput() {
//        testOut = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(testOut));
//    }
//
//    private void provideInput(String data) {
//        testIn = new ByteArrayInputStream(data.getBytes());
//        System.setIn(testIn);
//    }
//
//    private String getOutput() {
//        return testOut.toString();
//    }

//    @After
//    public void restoreSystemInputOutput() {
//        System.setIn(systemIn);
//        System.setOut(systemOut);
//    }

    public void testUserInput(String in) {
//        Scanner keyboard = new Scanner(in);
//        String input = keyboard.next();
        System.setIn(new ByteArrayInputStream(in.getBytes()));

    }

    @Test
    public void main() {
        Location location = new Location();
        Player player = new Player();


//        ByteArrayInputStream in = new ByteArrayInputStream("2\n1\n".getBytes());
        String input = "2\nY\n1\n1\n1\n1\n1\n";
        testUserInput(input);
//        System.setIn(new ByteArrayInputStream("2\nY\n1\n1\n1\n1\n1\n".getBytes()));
//        provideInput("2\n1\n1");
        location.mainMap(player);

    }



}