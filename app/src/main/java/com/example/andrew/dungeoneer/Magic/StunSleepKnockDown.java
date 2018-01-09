package com.example.andrew.dungeoneer.Magic;

import com.example.andrew.dungeoneer.Characters.Archetypes.Character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class StunSleepKnockDown implements ITick, Serializable{

    Character target;
    Integer duration;
    ArrayList<Double> stunnedModifier;

    public StunSleepKnockDown(Character target, Integer duration) {
        this.target = target;
        this.duration = duration;
        this.stunnedModifier = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.2, 0.4, 0.8));
    }

    public double randomStunnedModifier() {
        Collections.shuffle(this.stunnedModifier);
        return stunnedModifier.get(0);
    }

    public Character getTarget() {
        return this.target;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public boolean stunWearsOffEarly(){
        if (randomStunnedModifier() + target.getStunnedChance() >= 1){
            return true;
        }
        else {
            return false;
        }
    }

    public void tick(){
        if (duration <=0 || stunWearsOffEarly() == true) {
            this.duration = 0;
        }
        else {
            target.setStunned(true);
            setDuration(duration -1);
        }
    }

}
