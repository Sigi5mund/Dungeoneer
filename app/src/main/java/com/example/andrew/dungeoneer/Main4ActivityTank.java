package com.example.andrew.dungeoneer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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

import java.util.ArrayList;

public class Main4ActivityTank extends AppCompatActivity {

    Game game;
    Character target1;
    ListView listView;
    int targetInt;
    Double damageDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_tank);


        Intent intent = getIntent();
        game = (Game)intent.getSerializableExtra("game");



        game.room1.checkWhoIsAlive();
        game.room1.sortAllThreatTables();
        ArrayList<Character> list = game.room1.baddies;

        VillainsRowAdaptor villainsAdapter = new VillainsRowAdaptor(this, list);

        listView = findViewById(R.id.tankList);
        listView.setAdapter(villainsAdapter);

        TextView mana = (TextView) findViewById(R.id.manaView);
        mana.setText(game.room1.fellowship.tank().getManaPool().toString());


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                targetInt = position;
                target1 = game.room1.baddies.get(targetInt);
            }

        });

        Button onAction1Tank = findViewById(R.id.action1Tank);
        onAction1Tank.setEnabled(game.room1.fellowship.tank().sufficientManaCheck(game.room1.fellowship.tank().getAction1cost()));

        Button onAction2Tank = findViewById(R.id.action2Tank);
        onAction2Tank.setEnabled(game.room1.fellowship.tank().sufficientManaCheck(game.room1.fellowship.tank().getAction2cost()));

        Button onAction3Tank = findViewById(R.id.action3Tank);
        onAction3Tank.setEnabled(game.room1.fellowship.tank().sufficientManaCheck(game.room1.fellowship.tank().getAction3cost()));

        Button onAction4Tank = findViewById(R.id.action4Tank);
        onAction4Tank.setEnabled(game.room1.fellowship.tank().sufficientManaCheck(game.room1.fellowship.tank().getAction4cost()));
    }


    public void onSkipButtonClick(View view) {



        Intent intent = new Intent(this, Main5ActivityDPS.class);
        intent.putExtra("game", game);
        startActivity(intent);

    }


    public void onAction1Tank(View view){
        if (target1 != null){
            damageDone = game.room1.fellowship.tank().tauntAttack(target1, game.room1);
            Toast toast1 = Toast.makeText(this, target1.getDesignation() + " was hit for "+ damageDone + "!", Toast.LENGTH_SHORT );
            toast1.setGravity(Gravity.CENTER, 0, 0);
            toast1.show();
        game.room1.endOfCharacterTurnChecks();
        Intent intent = new Intent(this, Main5ActivityDPS.class);
        intent.putExtra("game", game);
        startActivity(intent);
    } else {
        Toast.makeText(this, "You need to target a character before you can cast it!", Toast.LENGTH_SHORT ).show();

    }
    }


    public void onAction2Tank(View view){


        game.room1.fellowship.tank().shieldWall(game.room1);
        game.room1.endOfCharacterTurnChecks();
        Intent intent = new Intent(this, Main5ActivityDPS.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }


    public void onAction3Tank(View view){

        game.room1.fellowship.tank().tauntAOE(game.room1);
        game.room1.endOfCharacterTurnChecks();
        Intent intent = new Intent(this, Main5ActivityDPS.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }


    public void onAction4Tank(View view){
        if (target1 != null){
        game.room1.fellowship.tank().headBash(target1);
        game.room1.endOfCharacterTurnChecks();
        Intent intent = new Intent(this, Main5ActivityDPS.class);
        intent.putExtra("game", game);
        startActivity(intent);
        } else {
            Toast.makeText(this, "You need to target a character before you can cast it!", Toast.LENGTH_SHORT ).show();

        }
    }
}
