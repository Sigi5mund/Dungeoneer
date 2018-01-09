package com.example.andrew.dungeoneer.Items;

/**
 * Created by Andrew on 04/01/2018.
 */
import com.example.andrew.dungeoneer.Characters.Archetypes.Character;

import java.io.Serializable;

public class ThreatObject implements Serializable{

    Character reference;
    Integer threatLevel;

    public ThreatObject(Character reference, Integer threatLevel) {
        this.reference = reference;
        this.threatLevel = threatLevel;
    }

    public Character getReference() {
        return reference;
    }

    public void setReference(Character reference) {
        this.reference = reference;
    }

    public Integer getThreatLevel() {
        return threatLevel;
    }

    public void setThreatLevel(Integer threatLevel) {
        this.threatLevel = threatLevel;
    }

    public void increaseThreatLevel(Integer threatIncrease){
        this.threatLevel += threatIncrease;
    }

}
