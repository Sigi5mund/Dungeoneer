package com.example.andrew.dungeoneer.Characters.Archetypes;

import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.Interfaces.IThreatAttack;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;

public class Goblin extends Character implements IThreatAttack {


    public Goblin(String name, double gold, Weapon weapon, Armour armour, OffHand offHand) {
        super(name, gold, weapon, armour, offHand);

        this.strength = 20;
        this.agility = 70;
        this.intellect = 25;
        this.stamina = 30;
        this.baseThreat = 10;
        this.threat = 0;
        this.critChance = agility/100;
        this.critDamage = agility/25;
        this.stunned = false;
        this.maxHealth = stamina * 20;
        this.healthBar = maxHealth;
    }


    public void threatAttack(){
        weaponattack1(this.threatTable.get(1));
    }


}
