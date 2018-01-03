package com.example.andrew.dungeoneer.Game;

import com.example.andrew.dungeoneer.Rooms.Dungeon1;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    ArrayList<Dungeon1> roomSequence;
    public Dungeon1 room;


    public Game() {
        this.roomSequence = new ArrayList<>(Arrays.asList(new Dungeon1("The Undercroft")));
        this.room = roomSequence.get(0);
    }

    public ArrayList<Dungeon1> getRoomSequence() {
        return this.roomSequence;
    }
}
