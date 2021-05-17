package game.locations;

import game.peons.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {
    Player hero = new Player("cursedSoul");
    Location map = new Location();

    @Test
    public void mainMap() {
        map.mainMap(hero);
    }

    @Test
    public void battleMap() {
        map.battleMap(hero);
    }

    @Test
    public void city() {
        map.city(hero);
    }

}