package com.example.andrew.dungeoneer.Characters.Archetypes;

import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.Interfaces.IThreatAttack;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Dragon extends Character implements Serializable, IThreatAttack {

    double maxHealth;

    public Dragon(String name, int gold) {
        super(name, gold, Weapon.CLAWS, Armour.LEATHER, OffHand.BITE);
        this.maxHealth = stamina * 6.00;
        this.healthBar = maxHealth;
        this.strength = 100;
        this.agility = 20;
        this.intellect = 50;
        this.stamina = 100;
        this.damageModifier = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 1.0));
        this.threat = 100;
        this.critChance = agility / 100;
        this.critDamage = agility / 25;
        this.stunned = false;
        this.designation = "Boss";
    }


    public double threatAttack(){
        return weaponAttack(this.topThreat());
    }
}






