package game;

import game.locations.Location;
import game.peons.Player;

public class GameLauncher {

    public static void main(String[] args) {
        Location location = new Location();
        Player player = new Player();
        location.mainMap(player);

    }
}
