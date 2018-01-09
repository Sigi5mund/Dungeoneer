package com.example.andrew.dungeoneer.Characters.Archetypes;

import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.Interfaces.IThreatAttack;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;

import java.io.Serializable;

public class OrcCaptain extends Character implements IThreatAttack, Serializable {



    public OrcCaptain(String name) {
        super(name,  100.00, Weapon.SWORD, Armour.LEATHER, OffHand.KNIFE);
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
        this.designation = "Captain";
    }

    public void threatAttack(){
        oldAttack(this.topThreat());
    }
}
