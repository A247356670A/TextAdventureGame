package game.peons;

import java.io.File;

import static org.junit.Assert.*;

public class PlayerTest {

    @org.junit.Test
    public void loadCharacterLevels() {
        // Save it to the specified file.
        File file = new File("json/db/characterLevels.json");

        // Ensure that there is nothing there right now:
//		file.delete();

        Player player = new Player();
        player.loadCharacterLevels();
        // Load it from the specified file.


        // Delete file after test
//		file.delete();
    }
}