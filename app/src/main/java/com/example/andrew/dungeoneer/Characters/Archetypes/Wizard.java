package com.example.andrew.dungeoneer.Characters.Archetypes;


import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;

public class Wizard extends Character {




    public Wizard(String name, int gold, Weapon weapon, Armour armour, OffHand offHand) {
        super(name, gold, weapon, armour, offHand);
        this.strength = 20;
        this.agility = 30;
        this.intellect = 80;
        this.stamina = 25;
        this.threat = 25;
        this.critChance = intellect/100;
        this.critDamage = intellect/25;
        this.stunned = false;
        this.manaPool = 100;
        this.manaRegen = 5;
        this.manaMax = 100;
        this.maxHealth = stamina * 20;
        this.healthBar = maxHealth;
    }

//  Attack Mechanics:
    @Override
    public String spell(Character target) {
        double damage;
        damage = 1000.00 * randomDamageModifier();
        target.magicDamage(damage);
        return "Fireball! : Did " + damage + "damage.";
    }

}
