package com.example.andrew.dungeoneer.Characters;

public enum Weapon {

    SWORD(100, 10),
    STAFF(10, 5 ),
    BLESSED_SCEPTER(-200, 100),
    CLAWS(2500, 30),
    DEFAULT(0, 0);

    int weaponDamage;
    Integer threatIncrease;


    Weapon(int weaponDamage, Integer threatIncrease) {
        this.weaponDamage = weaponDamage;
        this.threatIncrease = threatIncrease;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public Integer getThreatIncrease() {
        return threatIncrease;
    }


}
