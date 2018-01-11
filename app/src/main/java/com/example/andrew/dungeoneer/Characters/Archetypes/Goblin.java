package com.example.andrew.dungeoneer.Characters.Archetypes;

import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.Interfaces.IThreatAttack;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;

import java.io.Serializable;

public class Goblin extends Character implements Serializable, IThreatAttack {


    public Goblin(String name) {
        super(name, 10, Weapon.SWORD, Armour.LEATHER, OffHand.BITE);

        this.strength = 20;
        this.agility = 70;
        this.intellect = 25;
        this.stamina = 30;
        this.threat = 10;
        this.critChance = agility/100;
        this.critDamage = agility/25;
        this.stunned = false;
        this.maxHealth = stamina * 20.00;
        this.healthBar = maxHealth;
        this.designation = "Goblin";
    }


    public double threatAttack(){
        return weaponAttack(this.topThreat());
    }


}
