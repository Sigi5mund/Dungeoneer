package com.example.andrew.dungeoneer.Characters.Archetypes;

import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.Interfaces.IThreatAttack;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;

import java.util.ArrayList;
import java.util.Arrays;

public class Dragon extends Character implements IThreatAttack {

    double maxHealth;

    public Dragon(String name, int gold) {
        super(name, gold, Weapon.CLAWS, Armour.LEATHER, OffHand.BITE);
        this.maxHealth = stamina * 8;
        this.healthBar = maxHealth;
        this.strength = 100;
        this.agility = 20;
        this.intellect = 50;
        this.stamina = 100;
        this.baseThreat = 100;
        this.damageModifier = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 1.0));
        this.threat = 0;
        this.critChance = agility / 100;
        this.critDamage = agility / 25;
        this.stunned = false;
    }

    @Override
    public String spell(Character target) {
        double damage;
        damage = 1000.00;
        target.healthBar = healthBar - damage;
        return "FireBall : Did " + damage + "damage.";
    }

    public void threatAttack(){
        weaponattack1(this.topThreat());
    }
}

//
//    @Override
//    public String spell(ArrayList<Character> targets){
//        double damage;
//        damage = 200.00;
//        for (Character character:targets) {
//            character.magicDamage(damage);
//        }
//        return "FireWall : Damage to everyone!";}
//
//}





