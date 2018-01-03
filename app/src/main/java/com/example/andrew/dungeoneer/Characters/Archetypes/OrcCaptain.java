package com.example.andrew.dungeoneer.Characters.Archetypes;

import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.Interfaces.IThreatAttack;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;

public class OrcCaptain extends Character implements IThreatAttack {



    public OrcCaptain() {
        super("Grishnak",  100.00, Weapon.SWORD, Armour.LEATHER, OffHand.KNIFE);
        this.strength = 80;
        this.agility = 30;
        this.intellect = 40;
        this.stamina = 90;
        this.baseThreat = 20;
        this.threat = 0;
        this.critChance = agility/100;
        this.critDamage = agility/25;
        this.stunned = false;
        this.maxHealth = stamina * 20;
        this.healthBar = maxHealth;
    }

    public void threatAttack(){
        weaponattack1(this.threatTable.get(0));
    }
}
