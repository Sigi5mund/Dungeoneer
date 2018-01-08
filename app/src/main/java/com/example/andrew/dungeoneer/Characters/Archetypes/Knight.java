package com.example.andrew.dungeoneer.Characters.Archetypes;

import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;
import com.example.andrew.dungeoneer.Magic.ThreatOverTime;
import com.example.andrew.dungeoneer.Rooms.Room;

import java.util.ArrayList;

public class Knight extends Character {


    public Knight(String name, int gold, Weapon weapon, Armour armour, OffHand offHand) {
        super(name, gold, weapon, armour, offHand);

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
    }

    @Override
    public void tauntAttack(Character target){
        weaponattack1(target);
        increaseSpecificThreat(100, target);
    }

    @Override
    public void shieldWall(Room room){
        setBlockAll(true);
        this.raiseAllThreat(10, room);
    }

    @Override
    public void tauntAOE(Room room){
        this.raiseAllThreat(80, room);
        for (Character enemy: room.baddies) {
            room.hotsAndDots.add(new ThreatOverTime(enemy, this, 20, 3 ));
        }
    }

    @Override
    public void headBash(Character target){
        this.increaseSpecificThreat(100, target);
        target.decreaseIntellect(20);
    }

}
