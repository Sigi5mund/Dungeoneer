package com.example.andrew.dungeoneer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

public class Main3ActivityHealer extends AppCompatActivity {

    Game game;
    Character target1;
    ListView listView;
    int targetInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_healer);


        Intent intent = getIntent();
        game = (Game)intent.getSerializableExtra("game");


        ArrayList<Character> list = game.room1.fellowship.getHeroes();

        HeroesRowAdaptor heroesAdapter = new HeroesRowAdaptor(this, list);

        listView = findViewById(R.id.healerList);
        listView.setAdapter(heroesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                targetInt = position;
                target1 = game.room1.fellowship.getHeroes().get(targetInt);
            }

        });

        Button onAction1Healer = findViewById(R.id.action1Healer);
        onAction1Healer.setEnabled(game.room1.fellowship.healer().sufficientManaCheck(game.room1.fellowship.healer().getAction1cost()));

        Button onAction2Healer = findViewById(R.id.action2Healer);
        onAction2Healer.setEnabled(game.room1.fellowship.healer().sufficientManaCheck(game.room1.fellowship.healer().getAction2cost()));

        Button onAction3Healer = findViewById(R.id.action3Healer);
        onAction3Healer.setEnabled(game.room1.fellowship.healer().sufficientManaCheck(game.room1.fellowship.healer().getAction3cost()));

        Button onAction4Healer = findViewById(R.id.action4Healer);
        onAction4Healer.setEnabled(game.room1.fellowship.healer().sufficientManaCheck(game.room1.fellowship.healer().getAction4cost()));

                TextView mana = (TextView) findViewById(R.id.manaView);
        mana.setText(game.room1.fellowship.healer().getManaPool().toString());
    }



    public void onSkipButtonClick(View view){
        Intent intent = new Intent(this, Main4ActivityTank.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }


    public void onAction1Healer(View view){

        game.room1.fellowship.healer().heal(target1, game.room1);
        game.room1.endOfCharacterTurnChecks();
        Intent intent = new Intent(this, Main4ActivityTank.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

    public void onAction2Healer(View view){

        game.room1.fellowship.healer().aoeHeal(game.room1.fellowship, game.room1);
        game.room1.endOfCharacterTurnChecks();
        Intent intent = new Intent(this, Main4ActivityTank.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

    public void onAction3Healer(View view){

        game.room1.fellowship.healer().aoeHot(game.room1.fellowship, game.room1);
        game.room1.endOfCharacterTurnChecks();
        Intent intent = new Intent(this, Main4ActivityTank.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

    public void onAction4Healer(View view){

        game.room1.fellowship.healer().manaStorm(game.room1);
        game.room1.endOfCharacterTurnChecks();
        Intent intent = new Intent(this, Main4ActivityTank.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }



}
