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
        Character healerload = new Priest("Cadfael", 100, Weapon.SCEPTER, Armour.LEATHER, OffHand.HEALWAND);
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
        game1.room1.fellowship.tank().weaponAttack(game1.room1.captain);
        assertEquals(1620, game1.room1.captain.getHealthBar(), 1);
    }

    @Test
    public void checkThreatTableHasHeroes(){
        assertEquals(3, game1.room1.captain.getThreatTable().size());
    }

    @Test
    public void threatTablesTests(){
        assertEquals(game1.room1.fellowship.healer(), game1.room1.captain.topThreat());
        game1.room1.fellowship.dps().increaseSpecificThreat(100, game1.room1.captain);
        game1.room1.captain.sortThreatTable();
        assertEquals(game1.room1.fellowship.dps(), game1.room1.captain.topThreat());

    }


    @Test
    public void knightTauntAOETestAttack(){
        game1.room1.sortAllThreatTables();
        assertEquals(game1.room1.fellowship.dps(), game1.room1.captain.topThreat());
        assertEquals(game1.room1.fellowship.dps(), game1.room1.goblin1.topThreat());
        assertEquals(game1.room1.fellowship.dps(), game1.room1.goblin2.topThreat());
        assertEquals(game1.room1.fellowship.dps(), game1.room1.goblin3.topThreat());
        game1.room1.fellowship.tank().tauntAOE(game1.room1);
        game1.room1.sortAllThreatTables();
        assertEquals(game1.room1.fellowship.tank(), game1.room1.captain.topThreat());
        assertEquals(game1.room1.fellowship.tank(), game1.room1.goblin1.topThreat());
        assertEquals(game1.room1.fellowship.tank(), game1.room1.goblin2.topThreat());
        assertEquals(game1.room1.fellowship.tank(), game1.room1.goblin3.topThreat());
    }

    @Test
    public void knightHeadBashAttackTest(){
        assertEquals(50 ,game1.room1.captain.getIntellect(), 1);
        game1.room1.fellowship.tank().headBash(game1.room1.captain);
        assertEquals(30 ,game1.room1.captain.getIntellect(), 1);

    }

    @Test
    public void priestAOEHeal(){
        game1.room1.fellowship.tank().tauntAOE(game1.room1);
        game1.room1.sortAllThreatTables();
        assertEquals(game1.room1.fellowship.tank(), game1.room1.captain.topThreat());
        assertEquals(game1.room1.fellowship.tank(), game1.room1.goblin1.topThreat());
        assertEquals(game1.room1.fellowship.tank(), game1.room1.goblin2.topThreat());
        assertEquals(game1.room1.fellowship.tank(), game1.room1.goblin3.topThreat());
        assertEquals(1080, game1.room1.fellowship.tank().getHealthBar(), 1);
        assertEquals(300, game1.room1.fellowship.dps().getHealthBar(),1);
        assertEquals(300, game1.room1.fellowship.healer().getHealthBar(),1);
        game1.room1.fellowship.healer().aoeHeal(game1.room1.fellowship, game1.room1);
        assertEquals(1330, game1.room1.fellowship.tank().getHealthBar(), 1);
        assertEquals(550, game1.room1.fellowship.dps().getHealthBar(),1);
        assertEquals(550, game1.room1.fellowship.healer().getHealthBar(),1);
        game1.room1.sortAllThreatTables();
        assertEquals(game1.room1.fellowship.healer(), game1.room1.captain.topThreat());
        assertEquals(game1.room1.fellowship.healer(), game1.room1.goblin1.topThreat());
        assertEquals(game1.room1.fellowship.healer(), game1.room1.goblin2.topThreat());
        assertEquals(game1.room1.fellowship.healer(), game1.room1.goblin3.topThreat());
    }


    @Test
    public void priestHealAndManaPoolReduced(){
        assertEquals(300, game1.room1.fellowship.dps().getHealthBar(), 1);
        assertEquals(100, game1.room1.fellowship.healer().getManaPool(), 1);
        game1.room1.fellowship.healer().heal(game1.room1.fellowship.dps(), game1.room1);
        assertEquals(800, game1.room1.fellowship.dps().getHealthBar(), 1);
        assertEquals(80, game1.room1.fellowship.healer().getManaPool(), 1);
    }

    @Test
    public void priestManaRechargeAbilityTest(){

        assertEquals(100, game1.room1.fellowship.healer().getManaPool(), 1);
        game1.room1.fellowship.healer().heal(game1.room1.fellowship.dps(), game1.room1);
        game1.room1.fellowship.healer().heal(game1.room1.fellowship.dps(), game1.room1);
        game1.room1.fellowship.healer().heal(game1.room1.fellowship.dps(), game1.room1);
        game1.room1.fellowship.healer().heal(game1.room1.fellowship.dps(), game1.room1);
        game1.room1.fellowship.healer().heal(game1.room1.fellowship.dps(), game1.room1);
        assertEquals(0, game1.room1.fellowship.healer().getManaPool(), 1);
        game1.room1.fellowship.healer().manaStorm(game1.room1);
        assertEquals(100, game1.room1.fellowship.healer().getManaPool(), 1);

    }


    @Test
    public void wizardFireStormAttackTest(){
        assertEquals(100, game1.room1.fellowship.dps().getManaPool(), 1);
        assertEquals(1800, game1.room1.captain.getHealthBar(), 1);
        assertEquals(600, game1.room1.goblin1.getHealthBar(), 1);
        assertEquals(600, game1.room1.goblin2.getHealthBar(), 1);
        assertEquals(600, game1.room1.goblin3.getHealthBar(), 1);
        game1.room1.fellowship.dps().fireStorm(game1.room1);
        assertEquals(50, game1.room1.fellowship.dps().getManaPool(), 1);
        assertEquals(1400, game1.room1.captain.getHealthBar(), 1);
        assertEquals(280, game1.room1.goblin1.getHealthBar(), 1);
        assertEquals(280, game1.room1.goblin2.getHealthBar(), 1);
        assertEquals(280, game1.room1.goblin3.getHealthBar(), 1);
        game1.room1.sortAllThreatTables();
        assertEquals(game1.room1.fellowship.dps(), game1.room1.captain.topThreat());
        assertEquals(game1.room1.fellowship.dps(), game1.room1.goblin1.topThreat());
        assertEquals(game1.room1.fellowship.dps(), game1.room1.goblin2.topThreat());
        assertEquals(game1.room1.fellowship.dps(), game1.room1.goblin3.topThreat());
    }

    @Test
    public void wizardSlowBurnAttackTest(){
        assertEquals(0, game1.room1.hotsAndDots.size());
        game1.room1.fellowship.dps().slowBurn(game1.room1);
        assertEquals(4, game1.room1.hotsAndDots.size());
    }

    @Test
    public void wizardSlagArmourAttackTest() {
        assertEquals(Armour.LEATHER ,game1.room1.captain.getArmour());
        assertEquals(1800, game1.room1.captain.getHealthBar(), 1);
        assertEquals(game1.room1.fellowship.healer(), game1.room1.captain.topThreat());
        game1.room1.fellowship.dps().slagArmour(game1.room1.captain);
        assertEquals(Armour.SLAGGED ,game1.room1.captain.getArmour());
        assertEquals(1000, game1.room1.captain.getHealthBar(), 1);
        game1.room1.sortAllThreatTables();
        assertEquals(game1.room1.fellowship.dps(), game1.room1.captain.topThreat());
    }

}

