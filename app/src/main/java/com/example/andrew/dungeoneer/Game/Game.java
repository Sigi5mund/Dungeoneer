package com.example.andrew.dungeoneer.Game;

import com.example.andrew.dungeoneer.Rooms.DungeonOrcHorde;
import com.example.andrew.dungeoneer.Characters.Archetypes.Character;
import com.example.andrew.dungeoneer.Rooms.Room;

import java.io.Serializable;


public class Game implements Serializable{

//    ArrayList<Room> roomSequence;
    public DungeonOrcHorde room1;
    public DungeonOrcHorde room2;
    public DungeonOrcHorde room3;
    public int turn;
    public int novice;

    public Game() {
//        this.roomSequence = new ArrayList<>(Arrays.asList(new DungeonOrcHorde("The Undercroft")));
        this.room1 = (new DungeonOrcHorde("The Undercroft"));
        this.room2 = null;
        this.room3 = null;
        this.turn =1;
        this.novice =0;

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
            room2 = new DungeonOrcHorde("The DeepDark");
            room2.loadGoodies(this.room1.fellowship);
            room2.addThreatObjectsToTables();
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


//    this.roomSequence = new ArrayList<>(Arrays.asList(new DungeonOrcHorde("The Undercroft")));
//        this.room1 = roomSequence.get(0);
//}
}
