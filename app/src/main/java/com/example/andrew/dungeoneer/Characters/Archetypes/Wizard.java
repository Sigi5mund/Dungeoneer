package com.example.andrew.dungeoneer.Characters.Archetypes;


import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;
import com.example.andrew.dungeoneer.Magic.PhysicalDamageOverTime;
import com.example.andrew.dungeoneer.Magic.ThreatOverTime;
import com.example.andrew.dungeoneer.Rooms.Room;

import java.io.Serializable;
import java.util.ArrayList;

public class Wizard extends Character implements Serializable {


    public Wizard(String name, int gold, Weapon weapon, Armour armour, OffHand offHand) {
        super(name, gold, weapon, armour, offHand);

        this.designation = "DPS";
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
        this.maxHealth = stamina * 12.00;
        this.healthBar = maxHealth;
        this.action1cost = 10;
        this.action2cost = 50;
        this.action3cost = 10;
        this.action4cost = 30;
        this.action1threat = 100;
        this.action2threat = 150;
        this.action3threat = 30;
        this.action4threat = 100;
        this.action1desc = "";
        this.action2desc = "";
        this.action3desc = "";
        this.action4desc = "";
        this.classResource = "Mana";
    }

    //  Spell Mechanics:

    @Override
    public double fireBall(Character target, Room room) {
        this.spendManaToCast(this.action1cost);
        double damage;
        damage = (300.00 * calculateCritChance()) * randomDamageModifier();
        target.magicDamage(damage);
        increaseSpecificThreat(this.action1threat, target);
        raiseAllThreat(action1threat/2, room);
        return damage;
    }

    @Override
    public double fireStorm(Room room) {
        this.spendManaToCast(this.action2cost);
        double damage;
        damage = 400.00 * randomDamageModifier();
        for (Character baddie :room.baddies
             ) {baddie.magicDamage(damage);
        }
        this.raiseAllThreat(this.action2threat, room);
        return damage;
    }

    @Override
    public double slowBurn(Room room) {
        this.spendManaToCast(this.action3cost);
        for (Character baddie: room.baddies
             ) { room.hotsAndDots.add(new PhysicalDamageOverTime(baddie, 100, 3));
                room.hotsAndDots.add(new ThreatOverTime(baddie, this, action3threat, 3));
        }
        raiseAllThreat(this.action3threat, room);
        return 100;
    }

    @Override
    public double slagArmour(Character target) {
        this.spendManaToCast(this.action4cost);
        double damage;
        damage = (800.00 * calculateCritChance()) * randomDamageModifier();
        target.magicDamage(damage);
        increaseSpecificThreat(this.action4threat, target);
        target.setArmour(Armour.SLAGGED);

        return damage;
    }
}






