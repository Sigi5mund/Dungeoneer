package com.example.andrew.dungeoneer.Characters.Archetypes;

import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.Interfaces.IThreatAttack;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;

import java.io.Serializable;

public class OrcCaptain extends Character implements IThreatAttack, Serializable {



    public OrcCaptain(String name) {
        super(name,  100.00, Weapon.SWORD, Armour.PLATE, OffHand.KNIFE);
        this.strength = 200;
        this.agility = 30;
        this.intellect = 50;
        this.stamina = 90;
        this.threat = 20;
        this.critChance = agility/100;
        this.critDamage = agility/25;
        this.stunned = false;
        this.maxHealth = stamina * 20.00;
        this.healthBar = maxHealth;
        this.designation = "Orc Captain";
    }

    public double threatAttack(){
        return weaponAttack(this.topThreat());
    }
}
