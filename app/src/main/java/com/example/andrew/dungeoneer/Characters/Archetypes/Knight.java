package com.example.andrew.dungeoneer.Characters.Archetypes;

import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;
import com.example.andrew.dungeoneer.Magic.ThreatOverTime;
import com.example.andrew.dungeoneer.Rooms.Room;

import java.io.Serializable;

public class Knight extends Character implements Serializable {


    public Knight(String name, int gold, Weapon weapon, Armour armour, OffHand offHand) {
        super(name, gold, weapon, armour, offHand);

        this.designation = "Tank";
        this.strength = 80;
        this.agility = 30;
        this.intellect = 40;
        this.stamina = 90;
        this.threat = 20;
        this.critChance = agility/100;
        this.critDamage = agility/25;
        this.stunned = false;
        this.maxHealth = stamina * 12.00;
        this.healthBar = maxHealth;
        this.manaMax = 100;
        this.manaPool = 0;
        this.manaRegen = 10;
        this.action1cost = -10;
        this.action2cost = 30;
        this.action3cost = 20;
        this.action4cost = 20;
        this.action1threat = 100;
        this.action2threat = 10;
        this.action3threat = 80;
        this.action4threat = 100;
        this.action1desc = "";
        this.action2desc = "";
        this.action3desc = "";
        this.action4desc = "";
        this.classResource = "Rage";
    }

    @Override
    public double tauntAttack(Character target, Room room){
        spendManaToCast(this.action1cost);
        increaseSpecificThreat(this.action1threat, target);
        raiseAllThreat(action1threat/2, room);
        return weaponAttack(target);
    }

    @Override
    public void shieldWall(Room room){
        spendManaToCast(this.action2cost);
        setBlockAll(true);
        this.raiseAllThreat(this.action2threat, room);
    }

    @Override
    public void tauntAOE(Room room){
        spendManaToCast(this.action3cost);
        this.raiseAllThreat(this.action3threat, room);
        for (Character enemy: room.baddies) {
            room.hotsAndDots.add(new ThreatOverTime(enemy, this, 20, 3 ));
        }
    }

    @Override
    public double headBash(Character target){
        spendManaToCast(this.action4cost);

        this.increaseSpecificThreat(this.action4threat, target);
        target.decreaseIntellect(20);
        return target.physicalDamage(100);
    }

}
