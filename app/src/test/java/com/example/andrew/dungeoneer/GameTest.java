package com.example.andrew.dungeoneer;

import com.example.andrew.dungeoneer.Characters.Archetypes.Character;
import com.example.andrew.dungeoneer.Characters.Archetypes.Fellowship;
import com.example.andrew.dungeoneer.Characters.Archetypes.Knight;
import com.example.andrew.dungeoneer.Characters.Archetypes.Priest;
import com.example.andrew.dungeoneer.Characters.Archetypes.Wizard;
import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;
import com.example.andrew.dungeoneer.Game.Game;
import com.example.andrew.dungeoneer.Rooms.Room;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Andrew on 29/12/2017.
 */

public class GameTest {

    Game game1;
    Room room1;
    Character tank;
    Character dps;
    Character healer;
    Fellowship fellowship;
    ArrayList<Character> heroes;

    @Before
    public void before() {

        heroes = new ArrayList<>();
        tank = new Knight("Athina", 0, Weapon.SWORD, Armour.GOLD, OffHand.SHIELD);
        dps = new Wizard("Gandalf", 5, Weapon.STAFF, Armour.CLOTHE, OffHand.DPSWAND);
        healer = new Priest("Cadfael", 100, Weapon.BLESSED_SCEPTER, Armour.LEATHER, OffHand.HEALWAND);
        heroes.add(healer);
        heroes.add(tank);
        heroes.add(dps);
        fellowship = new Fellowship("The Valiant Few", heroes);
        game1 = new Game();
        game1.getRoomSequence().get(0).loadGoodies(fellowship);
        game1.room.fillAllThreatTables();

    }

    @Test
    public void gameExistsTest(){
        assertEquals(1, game1.getRoomSequence().size());
    }



    @Test
    public void checkGameHasFellowship(){
        game1.room.fellowship.tank().attack(game1.room.captain);
        assertEquals(1725, game1.room.captain.getHealthBar(), 1);
    }


    @Test
    public void checkThreatTableHasHeroes(){
        assertEquals(3, game1.room.captain.getThreatTable().size());

    }

    @Test
    public void checkThreatTableOrder(){
        assertEquals(game1.room.fellowship.healer(), game1.room.captain.getThreatTable().get(0));
    }

}
