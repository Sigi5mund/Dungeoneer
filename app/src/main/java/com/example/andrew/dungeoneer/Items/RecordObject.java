package com.example.andrew.dungeoneer.Items;

/**
 * Created by user on 09/01/2018.
 */

public class RecordObject {

    Character origin;
    Character target;
    Double damage;
    String outcome;


    public RecordObject(Character origin, Character target) {
        this.origin = origin;
        this.target = target;
        this.damage = 0.0;
        this.outcome = null;
    }
}
