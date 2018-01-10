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
        assertEquals(1740, game1.room1.captain.getHealthBar(), 1);
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
    public void threatTableAttackTestUnsorted(){
        assertEquals(game1.room1.fellowship.healer(), game1.room1.captain.topThreat());
        assertEquals(500 ,game1.room1.fellowship.healer().getHealthBar(), 1);
        game1.room1.captain.threatAttack();
        assertEquals(425 ,game1.room1.fellowship.healer().getHealthBar(), 1);
        game1.room1.fellowship.dps().increaseSpecificThreat(1000, game1.room1.captain);
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
        game1.room1.fellowship.dps().increaseSpecificThreat(100, game1.room1.captain);
        game1.room1.fellowship.healer().increaseSpecificThreat(100, game1.room1.goblin1);
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
    public void knightAttackTest(){
        assertEquals(600 ,game1.room1.goblin1.getHealthBar(), 1);
        game1.room1.fellowship.tank().tauntAttack(game1.room1.goblin1);
        assertEquals(520 ,game1.room1.goblin1.getHealthBar(), 1);
        game1.room1.fellowship.dps().tauntAttack(game1.room1.goblin1);
        assertEquals(520 ,game1.room1.goblin1.getHealthBar(), 1);
    }

    @Test
    public void knightBlockAllTestAttack(){
        game1.room1.changeAllThreatTables(-10, game1.room1.fellowship.dps());
        assertEquals(game1.room1.fellowship.tank(), game1.room1.captain.topThreat());
        assertEquals(1800 ,game1.room1.fellowship.tank().getHealthBar(), 1);
        game1.room1.fellowship.tank().shieldWall(game1.room1);
        game1.room1.sortAllThreatTables();
        game1.room1.captain.weaponAttack(game1.room1.fellowship.tank());
        assertEquals(1800 ,game1.room1.fellowship.tank().getHealthBar(), 1);
        game1.room1.endOfCombatChecks();
        game1.room1.captain.weaponAttack(game1.room1.fellowship.tank());
        assertEquals(1780 ,game1.room1.fellowship.tank().getHealthBar(), 1);
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
        assertEquals(40 ,game1.room1.captain.getIntellect(), 1);
        game1.room1.fellowship.tank().headBash(game1.room1.captain);
        assertEquals(20 ,game1.room1.captain.getIntellect(), 1);

    }

    @Test
    public void priestAOEHeal(){
        game1.room1.fellowship.tank().tauntAOE(game1.room1);
        game1.room1.sortAllThreatTables();
        assertEquals(game1.room1.fellowship.tank(), game1.room1.captain.topThreat());
        assertEquals(game1.room1.fellowship.tank(), game1.room1.goblin1.topThreat());
        assertEquals(game1.room1.fellowship.tank(), game1.room1.goblin2.topThreat());
        assertEquals(game1.room1.fellowship.tank(), game1.room1.goblin3.topThreat());
        assertEquals(1800, game1.room1.fellowship.tank().getHealthBar(), 1);
        assertEquals(500, game1.room1.fellowship.dps().getHealthBar(),1);
        assertEquals(500, game1.room1.fellowship.healer().getHealthBar(),1);
        game1.room1.fellowship.healer().aoeHeal(game1.room1.fellowship, game1.room1);
        assertEquals(2050, game1.room1.fellowship.tank().getHealthBar(), 1);
        assertEquals(750, game1.room1.fellowship.dps().getHealthBar(),1);
        assertEquals(750, game1.room1.fellowship.healer().getHealthBar(),1);
        game1.room1.sortAllThreatTables();
        assertEquals(game1.room1.fellowship.healer(), game1.room1.captain.topThreat());
        assertEquals(game1.room1.fellowship.healer(), game1.room1.goblin1.topThreat());
        assertEquals(game1.room1.fellowship.healer(), game1.room1.goblin2.topThreat());
        assertEquals(game1.room1.fellowship.healer(), game1.room1.goblin3.topThreat());
    }

    @Test
    public void priestHOTHealTest(){
        assertEquals(0, game1.room1.hotsAndDots.size());
        game1.room1.fellowship.healer().aoeHot(game1.room1.fellowship, game1.room1);
        assertEquals(3, game1.room1.hotsAndDots.size());
    }

    @Test
    public void priestHealAndManaPoolReduced(){
        assertEquals(500, game1.room1.fellowship.dps().getHealthBar(), 1);
        assertEquals(100, game1.room1.fellowship.healer().getManaPool(), 1);
        game1.room1.fellowship.healer().heal(game1.room1.fellowship.dps(), game1.room1);
        assertEquals(1000, game1.room1.fellowship.dps().getHealthBar(), 1);
        assertEquals(90, game1.room1.fellowship.healer().getManaPool(), 1);
    }

    @Test
    public void priestManaRechargeAbilityTest(){

        assertEquals(100, game1.room1.fellowship.healer().getManaPool(), 1);
        assertEquals(20, game1.room1.captain.getThreatTable().get(1).getThreatLevel(), 1);
        game1.room1.fellowship.healer().heal(game1.room1.fellowship.dps(), game1.room1);
        game1.room1.fellowship.healer().heal(game1.room1.fellowship.dps(), game1.room1);
        game1.room1.fellowship.healer().heal(game1.room1.fellowship.dps(), game1.room1);
        game1.room1.fellowship.healer().heal(game1.room1.fellowship.dps(), game1.room1);
        game1.room1.fellowship.healer().heal(game1.room1.fellowship.dps(), game1.room1);
        assertEquals(50, game1.room1.fellowship.healer().getManaPool(), 1);
        game1.room1.fellowship.healer().manaStorm(game1.room1);
        assertEquals(100, game1.room1.fellowship.healer().getManaPool(), 1);
        game1.room1.sortAllThreatTables();
        assertEquals(240, game1.room1.captain.getThreatTable().get(0).getThreatLevel(), 1);
    }

    @Test
    public void wizardfireBallAttackTest(){
        assertEquals(1800, game1.room1.captain.getHealthBar(), 1);
        assertEquals(game1.room1.fellowship.healer(), game1.room1.captain.topThreat());
        assertEquals(100, game1.room1.fellowship.dps().getManaPool(), 1);
        game1.room1.fellowship.dps().fireBall(game1.room1.captain, game1.room1);
        game1.room1.sortAllThreatTables();
        assertEquals(1500, game1.room1.captain.getHealthBar(), 1);
        assertEquals(game1.room1.fellowship.dps(), game1.room1.captain.topThreat());
        assertEquals(90, game1.room1.fellowship.dps().getManaPool(), 1);
    }

    @Test
    public void wizardFireStormAttackTest(){
        assertEquals(100, game1.room1.fellowship.dps().getManaPool(), 1);
        assertEquals(1800, game1.room1.captain.getHealthBar(), 1);
        assertEquals(600, game1.room1.goblin1.getHealthBar(), 1);
        assertEquals(600, game1.room1.goblin2.getHealthBar(), 1);
        assertEquals(600, game1.room1.goblin3.getHealthBar(), 1);
        assertEquals(game1.room1.fellowship.healer(), game1.room1.captain.topThreat());
        assertEquals(game1.room1.fellowship.healer(), game1.room1.goblin1.topThreat());
        assertEquals(game1.room1.fellowship.healer(), game1.room1.goblin2.topThreat());
        assertEquals(game1.room1.fellowship.healer(), game1.room1.goblin3.topThreat());
        game1.room1.fellowship.dps().fireStorm(game1.room1);
        assertEquals(50, game1.room1.fellowship.dps().getManaPool(), 1);
        assertEquals(1400, game1.room1.captain.getHealthBar(), 1);
        assertEquals(200, game1.room1.goblin1.getHealthBar(), 1);
        assertEquals(200, game1.room1.goblin2.getHealthBar(), 1);
        assertEquals(200, game1.room1.goblin3.getHealthBar(), 1);
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

