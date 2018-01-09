package com.example.andrew.dungeoneer.Characters;

import java.io.Serializable;

public enum OffHand implements Serializable {

    KNIFE(100, 10, false),
    SHIELD(10, 5, true),
    HEALWAND(-100, 20, false),
    DPSWAND(100, 50, false),
    BITE (500, 1000, false),
    DEFAULT(0, 0, false);

    int weaponDamage;
    Integer threatIncrease;
    boolean canBlock;


    OffHand(int weaponDamage, Integer threatIncrease, boolean canBlock) {
        this.weaponDamage = weaponDamage;

        this.threatIncrease = threatIncrease;
        this.canBlock = canBlock;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public Integer getThreatIncrease() {
        return threatIncrease;
    }

    public boolean CanBlock() {
        return canBlock;
    }
}

