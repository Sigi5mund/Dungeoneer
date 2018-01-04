package com.example.andrew.dungeoneer.Game;

import com.example.andrew.dungeoneer.Rooms.Dungeon1;

public class Game {

//    ArrayList<Room> roomSequence;
    public Dungeon1 room1;
    public Dungeon1 room2;
    public Dungeon1 room3;


    public Game() {
//        this.roomSequence = new ArrayList<>(Arrays.asList(new Dungeon1("The Undercroft")));
        this.room1 = (new Dungeon1("The Undercroft"));
        this.room2 = null;
        this.room3 = null;
    }




//    public ArrayList<Room> getRoomSequence() {
//        return this.roomSequence;
//    }


//    this.roomSequence = new ArrayList<>(Arrays.asList(new Dungeon1("The Undercroft")));
//        this.room1 = roomSequence.get(0);
//}
}
