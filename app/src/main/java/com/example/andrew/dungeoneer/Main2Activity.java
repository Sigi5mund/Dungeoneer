package com.example.andrew.dungeoneer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.andrew.dungeoneer.Characters.Archetypes.Character;
import com.example.andrew.dungeoneer.Characters.Archetypes.Fellowship;
import com.example.andrew.dungeoneer.Characters.Archetypes.Knight;
import com.example.andrew.dungeoneer.Characters.Archetypes.Priest;
import com.example.andrew.dungeoneer.Characters.Archetypes.Wizard;
import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;
import com.example.andrew.dungeoneer.Game.Game;

import java.io.Serializable;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity{

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        game = new Game();
        ArrayList<Character> heroes = new ArrayList<>();
        Character healer = new Priest("Cadfael", 100, Weapon.SCEPTER, Armour.LEATHER, OffHand.HEALWAND);
        Character tank = new Knight("Athina", 0, Weapon.SWORD, Armour.PLATE, OffHand.SHIELD);
        Character dps = new Wizard("Gandalf", 5, Weapon.STAFF, Armour.CLOTHE, OffHand.DPSWAND);

        heroes.add(healer);
        heroes.add(tank);
        heroes.add(dps);
        Fellowship fellowship = new Fellowship("The Valiant Few", heroes);
        game.room1.loadGoodies(fellowship);
        game.room1.addThreatObjectsToTables();




    }


    public void onEnterButtonClick(View view) {
        Toast toast1 = Toast.makeText(this, "The heroes enter the dungeon! Prepare for battle!", Toast.LENGTH_LONG );
        toast1.setGravity(Gravity.CENTER, 0, 0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toast1.show();
            }
        }, 0000);


        Intent intent = new Intent(this, Main3ActivityHealer.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

    public void onNoviceButtonClick(View view) {

        game.novice = 1;
        Intent intent = new Intent(this, Main3ActivityHealer.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

}