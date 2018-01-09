package com.example.andrew.dungeoneer.Characters;

import java.io.Serializable;

public enum Armour implements Serializable {


    MAGIC(0.0),
    GOLD(0.25),
    PLATE(0.5),
    LEATHER(0.75),
    CLOTHE(1.0),
    SLAGGED(0.9),
    DEFAULT(0.0);

    Double value;

    Armour(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
