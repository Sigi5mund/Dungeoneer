package com.example.andrew.dungeoneer.Characters.Archetypes;

import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;
import com.example.andrew.dungeoneer.Magic.HealOverTime;
import com.example.andrew.dungeoneer.Rooms.Room;

import java.util.ArrayList;

public class Priest extends Character {




    public Priest(String name, int gold, Weapon weapon, Armour armour, OffHand offHand) {
        super(name, gold, weapon, armour, offHand);

        this.designation = "Healer";
        this.strength = 20;
        this.agility = 30;
        this.intellect = 85;
        this.stamina = 25;
        this.threat = 15;
        this.critChance = intellect/100;
        this.critDamage = intellect/25;
        this.stunned = false;
        this.manaPool = 100;
        this.manaRegen = 10;
        this.manaMax = 100;
        this.maxHealth = stamina * 20;
        this.healthBar = maxHealth;
    }

    //  Attack Mechanics:
    @Override
    public String spell(Character target) {
        double heal;
        heal = 300 * randomDamageModifier();
        target.healthBar = target.healthBar + heal;
        return "HealthBomb: did " + heal + " healing";
    }

    @Override
    public void heal(Character target, Room room){
        this.spendManaToCast(10);
        target.increaseHealth(500);
        for (Character enemy: room.baddies
                ) {
            increaseSpecificThreat(25, enemy);
        }

    }

    @Override
    public void aoeHeal (Fellowship fellowship, Room room){
        this.spendManaToCast(35);
        for (Character hero: fellowship.heroes
             ) {hero.increaseHealth(250);
        }
        this.raiseAllThreat(100, room);
    }

    @Override
    public void aoeHot (Fellowship fellowship, Room room){
        this.spendManaToCast(15);
        for (Character hero: fellowship.heroes
                ) {room.hotsAndDots.add(new HealOverTime( hero,100, 5));
        }
        this.raiseAllThreat(30, room);
    }

    @Override
    public void manaStorm(Room room){
        this.setManaPool(this.getManaMax());
        room.fellowship.dps().setManaPool(room.fellowship.dps().getManaMax());
        this.raiseAllThreat(100, room);
    }

}


