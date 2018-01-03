package com.example.andrew.dungeoneer.Rooms;

import com.example.andrew.dungeoneer.Characters.Archetypes.Goblin;
import com.example.andrew.dungeoneer.Characters.Archetypes.OrcCaptain;

import java.util.ArrayList;
import java.util.Arrays;

public class Dungeon1 extends Room {



    public Dungeon1(String name) {
        super(name);
        this.rewardGold = 10000;
        this.baddies = new ArrayList<>(Arrays.asList(new OrcCaptain(), new Goblin(),new Goblin(), new Goblin() ));
        this.floor = new ArrayList<>();
        this.shelves = new ArrayList<>();

    }

}