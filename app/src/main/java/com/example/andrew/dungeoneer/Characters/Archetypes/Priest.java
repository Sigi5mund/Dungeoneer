package com.example.andrew.dungeoneer.Characters.Archetypes;

import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;

public class Priest extends Character {


    Integer manaMax;
    Integer manaLevel;
    Integer manaRegen;

    public Priest(String name, int gold, Weapon weapon, Armour armour, OffHand offHand) {
        super(name, gold, weapon, armour, offHand);

        this.strength = 20;
        this.agility = 30;
        this.intellect = 85;
        this.stamina = 25;
        this.threat = 15;
        this.critChance = intellect/100;
        this.critDamage = intellect/25;
        this.stunned = false;
        this.manaLevel = 50;
        this.manaRegen = 5;
        this.manaMax = 100;
        this.maxHealth = stamina * 20;
        this.healthBar = maxHealth;
    }


    @Override
    public String spell(Character target) {
        double heal;
        heal = 300 * randomDamageModifier();
        target.healthBar = target.healthBar + heal;
        return "HealthBomb: did " + heal + " healing";
    }


}

