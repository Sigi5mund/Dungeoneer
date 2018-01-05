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
        increaseThreat(50, target);
    }

    @Override
    public void shieldWall(ArrayList<Character> enemies){
        setBlockAll(true);
        for (Character enemy: enemies) {
            increaseThreat(10, enemy);
        }
    }

    @Override
    public void tauntAOE(ArrayList<Character> enemies){
        for (Character enemy: enemies) {
            increaseThreat(100, enemy);
        }
    }

    @Override
    public void tauntOverTime(ArrayList<Character> enemies, Room room){
        for (Character enemy: enemies) {
            room.hotsAndDots.add(new ThreatOverTime(enemy, this, 20, 3 ));
        }
    }
    
}
