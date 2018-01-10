package com.example.andrew.dungeoneer.Characters.Archetypes;

import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;
import com.example.andrew.dungeoneer.Magic.HealOverTime;
import com.example.andrew.dungeoneer.Rooms.Room;

import java.io.Serializable;
import java.util.ArrayList;

public class Priest extends Character implements Serializable {




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
        this.action1cost = 20;
        this.action2cost = 40;
        this.action3cost = 20;
        this.action4cost = 0;
        this.action1threat = 25;
        this.action2threat = 100;
        this.action3threat = 30;
        this.action4threat = 100;
        this.action1desc = "";
        this.action2desc = "";
        this.action3desc = "";
        this.action4desc = "";

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
        this.spendManaToCast(this.action1cost);
        target.increaseHealth(500);
        for (Character enemy: room.baddies
                ) {
            increaseSpecificThreat(this.action1threat, enemy);
        }

    }

    @Override
    public void aoeHeal (Fellowship fellowship, Room room){
        this.spendManaToCast(this.action2cost);
        for (Character hero: fellowship.heroes
             ) {hero.increaseHealth(250);
        }
        this.raiseAllThreat(this.action2threat, room);
    }

    @Override
    public void aoeHot (Fellowship fellowship, Room room){
        this.spendManaToCast(this.action3cost);
        for (Character hero: fellowship.heroes
                ) {room.hotsAndDots.add(new HealOverTime( hero,100, 5));
        }
        this.raiseAllThreat(this.action3threat, room);
    }

    @Override
    public void manaStorm(Room room){
        this.spendManaToCast(this.action4cost);
        this.setManaPool(this.getManaMax());
        room.fellowship.dps().setManaPool(room.fellowship.dps().getManaMax());
        this.raiseAllThreat(this.action4threat, room);
    }

}


