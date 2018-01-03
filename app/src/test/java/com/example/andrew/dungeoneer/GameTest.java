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

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Andrew on 29/12/2017.
 */

public class GameTest {

    private Game game1;

    @Before
    public void before() {

        ArrayList<Character> heroes = new ArrayList<>();
        Character tankload = new Knight("Athina", 0, Weapon.SWORD, Armour.GOLD, OffHand.SHIELD);
        Character dpsload = new Wizard("Gandalf", 5, Weapon.STAFF, Armour.CLOTHE, OffHand.DPSWAND);
        Character healerload = new Priest("Cadfael", 100, Weapon.BLESSED_SCEPTER, Armour.LEATHER, OffHand.HEALWAND);
        heroes.add(healerload);
        heroes.add(tankload);
        heroes.add(dpsload);
        Fellowship fellowship = new Fellowship("The Valiant Few", heroes);
        game1 = new Game();
        game1.room1.loadGoodies(fellowship);
        game1.room1.fillAllThreatTables();

    }

    @Test
    public void gameConstructorTest(){
        assertEquals(4, game1.room1.baddies.size());
        assertEquals(null, game1.room2);
    }

    @Test
    public void checkGameHasFellowship(){
        game1.room1.fellowship.tank().weaponattack1(game1.room1.captain);
        assertEquals(1740, game1.room1.captain.getHealthBar(), 1);
    }

    @Test
    public void checkThreatTableHasHeroes(){
        assertEquals(3, game1.room1.captain.getThreatTable().size());
    }

    @Test
    public void threatTablesTests(){
        assertEquals(game1.room1.fellowship.healer(), game1.room1.captain.getThreatTable().get(0));
        game1.room1.fellowship.dps().increaseThreat(1000);
        game1.room1.fillAllThreatTables();
        game1.room1.captain.sortThreatTable();
        assertEquals(game1.room1.fellowship.dps(), game1.room1.captain.topThreat());

    }

    @Test
    public void threatTableAttackTest(){
        assertEquals(game1.room1.fellowship.healer(), game1.room1.captain.getThreatTable().get(0));
        assertEquals(500 ,game1.room1.fellowship.healer().getHealthBar(), 1);
        game1.room1.captain.threatAttack();
        assertEquals(440 ,game1.room1.fellowship.healer().getHealthBar(), 1);
        game1.room1.fellowship.dps().increaseThreat(1000);
        game1.room1.fillAllThreatTables();
        game1.room1.captain.sortThreatTable();
        assertEquals(game1.room1.fellowship.dps(), game1.room1.captain.topThreat());
        assertEquals(500 ,game1.room1.fellowship.dps().getHealthBar(), 1);
        game1.room1.captain.threatAttack();
        assertEquals(420 ,game1.room1.fellowship.dps().getHealthBar(), 1);
    }


}
