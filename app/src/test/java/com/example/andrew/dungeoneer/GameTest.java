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


public class GameTest {

    private Game game1;

    @Before
    public void before() {

        ArrayList<Character> heroes = new ArrayList<>();
        Character healerload = new Priest("Cadfael", 100, Weapon.BLESSED_SCEPTER, Armour.LEATHER, OffHand.HEALWAND);
        Character tankload = new Knight("Athina", 0, Weapon.SWORD, Armour.GOLD, OffHand.SHIELD);
        Character dpsload = new Wizard("Gandalf", 5, Weapon.STAFF, Armour.CLOTHE, OffHand.DPSWAND);
        heroes.add(healerload);
        heroes.add(tankload);
        heroes.add(dpsload);
        Fellowship fellowship = new Fellowship("The Valiant Few", heroes);
        game1 = new Game();
        game1.room1.loadGoodies(fellowship);
        game1.room1.addThreatObjectsToTables();

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
        assertEquals(game1.room1.fellowship.healer(), game1.room1.captain.topThreat());
        game1.room1.fellowship.dps().increaseThreat(100, game1.room1.captain);
        game1.room1.captain.sortThreatTable();
        assertEquals(game1.room1.fellowship.dps(), game1.room1.captain.topThreat());

    }


    @Test
    public void threatTableAttackTestUnsorted(){
        assertEquals(game1.room1.fellowship.healer(), game1.room1.captain.topThreat());
        assertEquals(500 ,game1.room1.fellowship.healer().getHealthBar(), 1);
        game1.room1.captain.threatAttack();
        assertEquals(425 ,game1.room1.fellowship.healer().getHealthBar(), 1);
        game1.room1.fellowship.dps().increaseThreat(1000, game1.room1.captain);
        game1.room1.captain.sortThreatTable();
        assertEquals(game1.room1.fellowship.dps(), game1.room1.captain.topThreat());
        assertEquals(500 ,game1.room1.fellowship.dps().getHealthBar(), 1);
        game1.room1.captain.threatAttack();
        assertEquals(400 ,game1.room1.fellowship.dps().getHealthBar(), 1);
    }
    @Test
    public void threatTableSeparationTest(){
        game1.room1.captain.sortThreatTable();
        game1.room1.goblin1.sortThreatTable();
        assertEquals(game1.room1.fellowship.dps(), game1.room1.captain.topThreat());
        assertEquals(500 ,game1.room1.fellowship.dps().getHealthBar(), 1);
        game1.room1.captain.threatAttack();
        game1.room1.goblin1.threatAttack();
        assertEquals(300 ,game1.room1.fellowship.dps().getHealthBar(), 1);
        game1.room1.fellowship.dps().increaseThreat(100, game1.room1.captain);
        game1.room1.fellowship.healer().increaseThreat(100, game1.room1.goblin1);
        game1.room1.captain.sortThreatTable();
        game1.room1.goblin1.sortThreatTable();
        assertEquals(game1.room1.fellowship.dps(), game1.room1.captain.topThreat());
        assertEquals(300 ,game1.room1.fellowship.dps().getHealthBar(), 1);
        assertEquals(500 ,game1.room1.fellowship.healer().getHealthBar(), 1);
        game1.room1.captain.threatAttack();
        game1.room1.goblin1.threatAttack();
        assertEquals(200 ,game1.room1.fellowship.dps().getHealthBar(), 1);
        assertEquals(425 ,game1.room1.fellowship.healer().getHealthBar(), 1);
    }

    @Test
    public void villainGroupThreatAttack(){
        assertEquals(500 ,game1.room1.fellowship.healer().getHealthBar(), 1);
        assertEquals(500 ,game1.room1.fellowship.dps().getHealthBar(), 1);
        game1.room1.villiansTurnAttacks();
        assertEquals(500 ,game1.room1.fellowship.healer().getHealthBar(), 1);
        assertEquals(100 ,game1.room1.fellowship.dps().getHealthBar(), 1);

    }

    @Test
    public void attackTest(){
        assertEquals(600 ,game1.room1.goblin1.getHealthBar(), 1);
        game1.room1.fellowship.tank().tauntAttack(game1.room1.goblin1);
        assertEquals(520 ,game1.room1.goblin1.getHealthBar(), 1);
        game1.room1.fellowship.dps().tauntAttack(game1.room1.goblin1);
        assertEquals(520 ,game1.room1.goblin1.getHealthBar(), 1);

    }

    @Test
    public void blockAllTest(){
        game1.room1.changeAllThreatTables(-10, game1.room1.fellowship.dps());
        assertEquals(game1.room1.fellowship.tank(), game1.room1.captain.topThreat());
        assertEquals(1800 ,game1.room1.fellowship.tank().getHealthBar(), 1);
        game1.room1.fellowship.tank().shieldWall(game1.room1.getBaddies());
        game1.room1.sortAllThreatTables();
        game1.room1.captain.weaponattack1(game1.room1.fellowship.tank());
        assertEquals(1800 ,game1.room1.fellowship.tank().getHealthBar(), 1);
        game1.room1.endOfCombatChecks();
        game1.room1.captain.weaponattack1(game1.room1.fellowship.tank());
        assertEquals(1780 ,game1.room1.fellowship.tank().getHealthBar(), 1);

    }

}
