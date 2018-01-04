package com.example.andrew.dungeoneer.Characters.Archetypes;

import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;

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


}
