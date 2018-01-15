package com.example.andrew.dungeoneer;

import com.example.andrew.dungeoneer.Characters.Archetypes.Character;
import com.example.andrew.dungeoneer.Characters.Archetypes.Dragon;
import com.example.andrew.dungeoneer.Characters.Archetypes.Knight;
import com.example.andrew.dungeoneer.Characters.Archetypes.OrcCaptain;
import com.example.andrew.dungeoneer.Characters.Archetypes.Priest;
import com.example.andrew.dungeoneer.Characters.Archetypes.Wizard;
import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CharacterTest {

    private Character tank;
    private Character dps;
    private Character dragon;
    private Character healer;
    private Character orcCaptain;

    @Before
    public void before(){

        tank = new Knight("Athina",0, Weapon.SWORD, Armour.GOLD, OffHand.SHIELD);
        dps = new Wizard( "Gandalf",5, Weapon.STAFF, Armour.CLOTHE, OffHand.DPSWAND);
        dragon = new Dragon("Smaug",10000000);
        healer = new Priest("Cadfael", 100,Weapon.SCEPTER, Armour.LEATHER, OffHand.HEALWAND);
        orcCaptain = new OrcCaptain("Grishnak");

    }

    @Test
    public void attackIsModifiedByArmour(){
        tank.changeArmour(Armour.MAGIC);
        tank.physicalDamage(200000000);
        assertEquals(1800, tank.getHealthBar(),1);
        tank.changeArmour(Armour.CLOTHE);
        tank.physicalDamage(500);
        assertEquals(1300, tank.getHealthBar(), 1);
    }

    @Test
    public void knightTakesDamage(){
        tank.physicalDamage(500);
        assertEquals(955, tank.getHealthBar(), 0.1);
    }

    @Test
    public void canDie(){
        dps.physicalDamage(2000);
        assertEquals(false, dps.checkAlive());
    }

    @Test
    public void increaseHealthPossible(){
        healer.oldAttack(tank);
        assertEquals(1180, tank.getHealthBar(),100);
    }

}
