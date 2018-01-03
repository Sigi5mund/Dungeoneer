package com.example.andrew.dungeoneer.Game;

import com.example.andrew.dungeoneer.Rooms.Dungeon1;
import com.example.andrew.dungeoneer.Rooms.Room;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    ArrayList<Room> roomSequence;


    public Game() {
        this.roomSequence = new ArrayList<>(Arrays.asList(new Dungeon1() ));
    }
}
