package com.example.andrew.dungeoneer.Rooms;

import com.example.andrew.dungeoneer.Characters.Archetypes.Goblin;
import com.example.andrew.dungeoneer.Characters.Archetypes.OrcCaptain;
import com.example.andrew.dungeoneer.Characters.Archetypes.Character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class DungeonOrcHorde extends Room implements Serializable {

    public Character captain;
    public Character goblin1;
    public Character goblin2;
    public Character goblin3;


    public DungeonOrcHorde(String name) {
        super(name);
        this.rewardGold = 10000;
        this.baddies = new ArrayList<>(Arrays.asList(new OrcCaptain("Grishnak"), new Goblin("Snotty"),new Goblin("Fotty"), new Goblin("Botty") ));
        this.captain = baddies.get(0);
        this.goblin1 = baddies.get(1);
        this.goblin2 = baddies.get(2);
        this.goblin3 = baddies.get(3);

    }

}