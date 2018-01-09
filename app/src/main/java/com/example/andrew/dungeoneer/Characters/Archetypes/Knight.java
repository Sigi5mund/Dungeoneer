package com.example.andrew.dungeoneer.Characters.Archetypes;

import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;
import com.example.andrew.dungeoneer.Magic.ThreatOverTime;
import com.example.andrew.dungeoneer.Rooms.Room;

import java.io.Serializable;
import java.util.ArrayList;

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
        this.maxHealth = stamina * 20;
        this.healthBar = maxHealth;
        this.manaMax = 50;
        this.manaPool = 0;
        this.manaRegen = 10;
        this.action1cost = -10;
        this.action2cost = 30;
        this.action3cost = 20;
        this.action4cost = 20;
    }

    @Override
    public void tauntAttack(Character target){
        spendManaToCast(this.action1cost);
        weaponattack1(target);
        increaseSpecificThreat(100, target);
    }

    @Override
    public void shieldWall(Room room){
        spendManaToCast(this.action2cost);
        setBlockAll(true);
        this.raiseAllThreat(10, room);
    }

    @Override
    public void tauntAOE(Room room){
        spendManaToCast(this.action3cost);
        this.raiseAllThreat(80, room);
        for (Character enemy: room.baddies) {
            room.hotsAndDots.add(new ThreatOverTime(enemy, this, 20, 3 ));
        }
    }

    @Override
    public void headBash(Character target){
        spendManaToCast(this.action4cost);
        this.increaseSpecificThreat(100, target);
        target.decreaseIntellect(20);
    }

}
