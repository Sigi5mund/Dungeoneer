package com.example.andrew.dungeoneer;

import com.example.andrew.dungeoneer.Characters.Archetypes.Character;
import com.example.andrew.dungeoneer.Characters.Archetypes.Dragon;
import com.example.andrew.dungeoneer.Characters.Archetypes.Fellowship;
import com.example.andrew.dungeoneer.Characters.Archetypes.Knight;
import com.example.andrew.dungeoneer.Characters.Archetypes.OrcCaptain;
import com.example.andrew.dungeoneer.Characters.Archetypes.Priest;
import com.example.andrew.dungeoneer.Characters.Archetypes.Wizard;
import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;
import com.example.andrew.dungeoneer.Items.Item;
import com.example.andrew.dungeoneer.Magic.PhysicalDamageOverTime;
import com.example.andrew.dungeoneer.Magic.HealOverTime;
import com.example.andrew.dungeoneer.Rooms.Dungeon1;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DungeonTest {

    private Dungeon1 dungeon;
    private ArrayList<Character> heroes;
    private ArrayList<Character> villains;
    private Item item;
    private HealOverTime renew;
    private PhysicalDamageOverTime scorch;
    private Character healer;
    private Character dps;
    private Character tank;
    private Character dragon1;
    private Character orc;
    private Fellowship fellowship;


    @Before
    public void before() {
        heroes = new ArrayList<>();
        heroes.add(new Priest("Cadfael", 5, Weapon.SCEPTER, Armour.CLOTHE, OffHand.HEALWAND));
        heroes.add(new Knight("Athina", 20, Weapon.SWORD, Armour.PLATE, OffHand.SHIELD));
        heroes.add(new Wizard("Gandalf", 10, Weapon.STAFF, Armour.CLOTHE, OffHand.DPSWAND));
        villains = new ArrayList<>();
        villains.add(new Dragon("Smaug", 1000));
        villains.add(new OrcCaptain("Badrag"));
        fellowship =  new Fellowship("The Valiant Few", heroes);
        dungeon = new Dungeon1("The Undercroft");
        dungeon.loadGoodies(fellowship);
        item = new Item("Suspicious box", 1, new ArrayList<>());
        dungeon.shelves.add(item);
        healer = fellowship.heroes.get(0);
        tank = fellowship.heroes.get(1);
        dps = fellowship.heroes.get(2);
        dragon1 = villains.get(0);
        orc = villains.get(1);



    }

    @Test
    public void heroesArrayListHasClassProperties(){
        assertEquals(80, tank.getStrength(), 1);
        assertEquals(false, healer.getStunned());
        assertEquals(20, dps.getStrength(),1);
    }

    @Test
    public void openBoxWorks() {
        item = dungeon.shelves.get(0);
        assertNotEquals("", item.openBox(healer));
        assertNotEquals(350, healer.getHealthBar());
    }

//    @Test
//    public void checkCorpseCreationAndLootability() {
//        healer.physicalDamage(2000);
//        dungeon.endOfCombatChecks();
//        assertEquals(1, dungeon.floor.size());
//        assertEquals(2, dungeon.fellowship.getHeroes().size());
//        assertEquals("Cadfael's corpse has 5.0 gold, CLOTHE armour and a SCEPTER weapon. What will you take?", tank.examineCorpse(dungeon.floor.get(0)));
//    }

//    @Test
//    public void checkdeletecorpsesafterspawn() {
//        healer.physicalDamage(2000);
//        dungeon.removeDead();
//        assertEquals(2,  dungeon.fellowship.getHeroes().size());
//    }

//    @Test
//    public void checkGoldLootableAndCorpseGoldEmptyAfter() {
//
//        healer.physicalDamage(2000);
//        dungeon.endOfCombatChecks();
//        double gold1 = tank.getGold();
//        tank.takeGold(dungeon.floor.get(0));
//        double gold2 = tank.getGold();
//        assertNotEquals(gold1, gold2);
//    }

    @Test
    public void spellWorksInDungeons() {
        healer.spell(tank);
        assertEquals(2100, tank.getHealthBar(), 1);
        double dragonHP = dragon1.getHealthBar();
        dps.spell(dragon1);
        assertNotEquals(dragonHP, dragon1.getHealthBar());
    }

    @Test
    public void overHealCorrectedByEndTurn() {
        healer.spell(tank);
        assertEquals(2100, tank.getHealthBar(), 1);
        dungeon.endOfCombatChecks();
        assertEquals(1800, tank.getHealthBar(), 1);
    }


    //    HOTs and Dots:
    @Test
    public void hotInteractsWithTarget() {
        renew = new HealOverTime(tank, 50, 3);
        renew.tick();
        assertEquals(1850, tank.getHealthBar(), 10);
    }

    @Test
    public void hotHasDuration() {
        renew = new HealOverTime(tank, 50, 3);
        assertEquals(1800, tank.getHealthBar(), 10);
        assertEquals(3, renew.getDuration(), 0.1);
        renew.tick();
        assertEquals(1850, tank.getHealthBar(), 10);
        assertEquals(2, renew.getDuration(), 0.1);
        renew.tick();
        assertEquals(1900, tank.getHealthBar(), 10);
        assertEquals(1, renew.getDuration(), 0.1);
        renew.tick();
        assertEquals(1950, tank.getHealthBar(), 10);
        assertEquals(0, renew.getDuration(), 0.1);
        renew.tick();
        assertEquals(1950, tank.getHealthBar(), 10);
        assertEquals(0, renew.getDuration(), 0.1);

    }

    @Test
    public void ITickLoopMechanismWorksWithHot() {
        renew = new HealOverTime(tank, 50, 3);
        dungeon.hotsAndDots.add(renew);
        assertEquals(1, dungeon.hotsAndDots.size());
        dungeon.triggerITickMechanism();
        assertEquals(1850, tank.getHealthBar(), 10);
        dungeon.triggerITickMechanism();
        assertEquals(1900, tank.getHealthBar(), 10);
        dungeon.triggerITickMechanism();
        assertEquals(1950, tank.getHealthBar(), 10);
        dungeon.triggerITickMechanism();
        assertEquals(1950, tank.getHealthBar(), 10);
    }

    @Test
    public void endOfTurnTriggersHots() {
        tank.physicalDamage(500);
        renew = new HealOverTime(tank, 50, 3);
        dungeon.hotsAndDots.add(renew);
        assertEquals(1, dungeon.hotsAndDots.size());
        assertEquals(1550, tank.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1600, tank.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1650, tank.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1700, tank.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1700, tank.getHealthBar(), 10);
    }

    @Test
    public void dotInteractsWithTarget() {
        scorch = new PhysicalDamageOverTime(tank, 50, 3);
        scorch.tick();
        assertEquals(1750, tank.getHealthBar(), 10);
    }


    @Test
    public void dotHasDuration() {
        scorch = new PhysicalDamageOverTime(tank, 50, 3);
        assertEquals(1800, tank.getHealthBar(), 10);
        assertEquals(3, scorch.getDuration(), 0.1);
        scorch.tick();
        assertEquals(1750, tank.getHealthBar(), 10);
        assertEquals(2, scorch.getDuration(), 0.1);
        scorch.tick();
        assertEquals(1700, tank.getHealthBar(), 10);
        assertEquals(1, scorch.getDuration(), 0.1);
        scorch.tick();
        assertEquals(1650, tank.getHealthBar(), 10);
        assertEquals(0, scorch.getDuration(), 0.1);
        scorch.tick();
        assertEquals(1650, tank.getHealthBar(), 10);
        assertEquals(0, scorch.getDuration(), 0.1);
    }

    @Test
    public void ITickLoopMechanismWorksWithDot() {
        scorch = new PhysicalDamageOverTime(tank, 50, 3);
        dungeon.hotsAndDots.add(scorch);
        assertEquals(1, dungeon.hotsAndDots.size());
        dungeon.triggerITickMechanism();
        assertEquals(1750, tank.getHealthBar(), 10);
        dungeon.triggerITickMechanism();
        assertEquals(1700, tank.getHealthBar(), 10);
        dungeon.triggerITickMechanism();
        assertEquals(1650, tank.getHealthBar(), 10);
        dungeon.triggerITickMechanism();
        assertEquals(1650, tank.getHealthBar(), 10);
    }

    @Test
    public void endOfTurnTriggersDots() {
        scorch = new PhysicalDamageOverTime(tank, 50, 3);
        dungeon.hotsAndDots.add(scorch);
        assertEquals(1, dungeon.hotsAndDots.size());
        assertEquals(1800, tank.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1750, tank.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1700, tank.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1650, tank.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1650, tank.getHealthBar(), 10);
    }

    @Test
    public void endOfTurnTriggersAllHotsAndDotsInRoomArray(){

        scorch = new PhysicalDamageOverTime(tank, 100, 3);
        dungeon.hotsAndDots.add(scorch);
        renew = new HealOverTime(tank, 50, 2);
        dungeon.hotsAndDots.add(renew);
        assertEquals(1800, tank.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1750, tank.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1700, tank.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1600, tank.getHealthBar(), 10);
        dungeon.endOfCombatChecks();
        assertEquals(1600, tank.getHealthBar(), 10);

    }



}