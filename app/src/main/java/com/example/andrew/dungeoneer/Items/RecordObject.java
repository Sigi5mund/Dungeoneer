package com.example.andrew.dungeoneer.Items;

/**
 * Created by user on 09/01/2018.
 */

import com.example.andrew.dungeoneer.Characters.Archetypes.Character;

import java.io.Serializable;

public class RecordObject implements Serializable{

    Character origin;
    Character target;
    Double damage;
    String outcome;


    public RecordObject(Character origin, Character target, Double damage) {
        this.origin = origin;
        this.target = target;
        this.damage = damage;
        this.outcome = null;
    }

    public Character getOrigin() {
        return origin;
    }

    public Character getTarget() {
        return target;
    }

    public Double getDamage() {
        return damage;
    }
}
