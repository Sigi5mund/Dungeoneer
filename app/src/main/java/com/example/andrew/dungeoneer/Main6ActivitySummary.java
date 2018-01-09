package com.example.andrew.dungeoneer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andrew.dungeoneer.Characters.Archetypes.Character;
import com.example.andrew.dungeoneer.Characters.Archetypes.Fellowship;
import com.example.andrew.dungeoneer.Characters.Archetypes.Knight;
import com.example.andrew.dungeoneer.Characters.Archetypes.Priest;
import com.example.andrew.dungeoneer.Characters.Archetypes.Wizard;
import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;
import com.example.andrew.dungeoneer.Game.Game;

import java.util.ArrayList;

public class Main6ActivitySummary extends AppCompatActivity {

    Game game;


//  Activity Set Up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6_summary);
        Intent intent = getIntent();
        game = (Game)intent.getSerializableExtra("game");


//  Game Logic
        game.room1.sortAllThreatTables();
        game.room1.massThreatAttack();
        game.room1.endOfCombatChecks();



//  Display End Step
//      Baddies ListView
        ArrayList<Character> list = game.room1.baddies;
        VillainsRowAdaptor villainsAdapter = new VillainsRowAdaptor(this, list);
        ListView listView = findViewById(R.id.dpsSummaryList);
        listView.setAdapter(villainsAdapter);

//      Heroes ListView
        ArrayList<Character> list1 = game.room1.fellowship.getHeroes();
        HeroesRowAdaptor heroesAdapter = new HeroesRowAdaptor(this, list1);
        ListView listView1 = findViewById(R.id.healerSummaryList);
        listView1.setAdapter(heroesAdapter);

    }

    public void onQuit(View view){

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void onNextTurn(View view){

        Intent intent = new Intent(this, Main3ActivityHealer.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

}
