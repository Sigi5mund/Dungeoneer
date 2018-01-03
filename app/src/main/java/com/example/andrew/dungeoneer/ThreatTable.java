package com.example.andrew.dungeoneer;

import com.example.andrew.dungeoneer.Characters.Archetypes.Character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Andrew on 28/12/2017.
 */

public class ThreatTable {

    ArrayList<Character> targets;
    Character topThreat;
    Character middleThreat;
    Character bottomThreat;


    public ThreatTable() {
        this.targets = new ArrayList<>();
        this.topThreat = targets.get(0);
        this.middleThreat = targets.get(1);
        this.bottomThreat = targets.get(2);
    }


    //    Getters:
    public ArrayList<Character> getHeroes() {
        return targets;
    }

    public Character getTopThreat() {
        return topThreat;
    }

    public Character getSecondThreat() {
        return middleThreat;
    }

    public Character getThirdThreat() {
        return bottomThreat;
    }

    //    Setters:
    public void randomiseThreatTable() {
        Collections.shuffle(this.targets);
    }

//    public void fillAllThreatTables(ArrayList<Character> villains, ArrayList<Character> heroes){
//        for (Character villain: villains
//             ) {villain.addTargetsToThreatTable(heroes);
//        }
//    }


//        Utility:
    public void sortThreatTable() {
        Collections.sort(targets, new Comparator<Character>() {
                    public int compare(Character h1, Character h2) {
                        return Integer.valueOf(h1.getThreat()).compareTo(h2.getThreat());
                    }
                }
        );
    }

}

