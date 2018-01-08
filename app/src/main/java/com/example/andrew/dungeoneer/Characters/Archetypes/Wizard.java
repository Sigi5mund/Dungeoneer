package com.example.andrew.dungeoneer.Characters.Archetypes;


import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;
import com.example.andrew.dungeoneer.Magic.PhysicalDamageOverTime;
import com.example.andrew.dungeoneer.Rooms.Room;

import java.util.ArrayList;

public class Wizard extends Character {


    public Wizard(String name, int gold, Weapon weapon, Armour armour, OffHand offHand) {
        super(name, gold, weapon, armour, offHand);
        this.strength = 20;
        this.agility = 30;
        this.intellect = 80;
        this.stamina = 25;
        this.threat = 25;
        this.critChance = intellect / 100;
        this.critDamage = intellect / 25;
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

    @Override
    public void fireBall(Character target, Room room) {
        double damage;
        damage = 1500.00 * randomDamageModifier();
        target.magicDamage(damage);
        for (Character enemy : room.baddies
                ) {
            increaseSpecificThreat(100, enemy);
        }
    }

    @Override
    public void fireStorm(Room room) {
        double damage;
        damage = 1500.00 * randomDamageModifier();
        for (Character baddie :room.baddies
             ) {baddie.magicDamage(damage);
        }
        this.raiseAllThreat(1000, room);
    }

    @Override
    public void slowBurn(Room room) {
        for (Character baddie: room.baddies
             ) { room.hotsAndDots.add(new PhysicalDamageOverTime(baddie, 100, 5));
        }
        raiseAllThreat(10, room);
    }

    @Override
    public void slagArmour(Character target){
        double damage;
        damage = 1800.00 * randomDamageModifier();
        target.magicDamage(damage);
        increaseSpecificThreat(100, target);
        target.setArmour(Armour.SLAGGED);
    }

}






