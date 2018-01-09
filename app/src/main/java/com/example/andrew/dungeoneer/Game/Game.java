package com.example.andrew.dungeoneer.Game;

import com.example.andrew.dungeoneer.Characters.Archetypes.Fellowship;
import com.example.andrew.dungeoneer.Rooms.Dungeon1;
import com.example.andrew.dungeoneer.Characters.Archetypes.Character;
import com.example.andrew.dungeoneer.Rooms.Room;

import java.io.Serializable;


public class Game implements Serializable{

//    ArrayList<Room> roomSequence;
    public Dungeon1 room1;
    public Dungeon1 room2;
    public Dungeon1 room3;


    public Game() {
//        this.roomSequence = new ArrayList<>(Arrays.asList(new Dungeon1("The Undercroft")));
        this.room1 = (new Dungeon1("The Undercroft"));
        this.room2 = null;
    }

    public String roomClearCheck(Room room){
        for (Character baddie: room.baddies) {
            if (baddie.checkAlive() == true){
                return "Unfinished!";
            }
        }
        return "Clear";
    }



    public void roomEmptyGenerateRoom2(){
        if (roomClearCheck(room1) ==  "Clear"){
            room2 = new Dungeon1("The DeepDark");
            room2.loadGoodies(this.room1.fellowship);
        }
    }

    public Room currentRoom (){
        if (roomClearCheck(room1) =="Unfinished!"){
            return room1;
        }
        else if (roomClearCheck(room2) =="Unfinished!")
        {return room2;}
        else
            {return room3;}
    }

//    public ArrayList<Room> getRoomSequence() {
//        return this.roomSequence;
//    }


//    this.roomSequence = new ArrayList<>(Arrays.asList(new Dungeon1("The Undercroft")));
//        this.room1 = roomSequence.get(0);
//}
}
