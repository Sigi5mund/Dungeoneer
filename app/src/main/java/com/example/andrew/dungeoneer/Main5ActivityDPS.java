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

public class Main5ActivityDPS extends AppCompatActivity {

    Game game;
    Character target1;
    ListView listView;
    int targetInt;
    Double damageDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5_dps);


        Intent intent = getIntent();
        game = (Game)intent.getSerializableExtra("game");




        game.room1.checkWhoIsAlive();
        game.room1.sortAllThreatTables();
        ArrayList<Character> list = game.room1.baddies;

        VillainsRowAdaptor villainsAdapter = new VillainsRowAdaptor(this, list);



        listView = findViewById(R.id.dpsList);
        listView.setAdapter(villainsAdapter);

        TextView mana = (TextView) findViewById(R.id.manaView);
        mana.setText(game.room1.fellowship.dps().getManaPool().toString());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                targetInt = position;
                target1 = game.room1.baddies.get(targetInt);
            }

        });

        Button onAction1DPS = findViewById(R.id.action1DPS);
        onAction1DPS.setEnabled(game.room1.fellowship.dps().sufficientManaCheck(game.room1.fellowship.dps().getAction1cost()));

        Button onAction2DPS = findViewById(R.id.action2DPS);
        onAction2DPS.setEnabled(game.room1.fellowship.dps().sufficientManaCheck(game.room1.fellowship.dps().getAction2cost()));

        Button onAction3DPS = findViewById(R.id.action3DPS);
        onAction3DPS.setEnabled(game.room1.fellowship.dps().sufficientManaCheck(game.room1.fellowship.dps().getAction3cost()));

        Button onAction4DPS = findViewById(R.id.action4DPS);
        onAction4DPS.setEnabled(game.room1.fellowship.dps().sufficientManaCheck(game.room1.fellowship.dps().getAction4cost()));
    }


    public void onSkipButtonClick(View view) {
        Intent intent = new Intent(this, Main6ActivitySummary.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }


    public void onAction1DPS(View view){
        if (target1 != null){

            damageDone = game.room1.fellowship.dps().fireBall(target1, game.room1);
            Toast toast1 = Toast.makeText(this, target1.getDesignation() + " was hit for "+ damageDone + "!", Toast.LENGTH_SHORT );
            toast1.setGravity(Gravity.CENTER, 0, 0);
            toast1.show();
            game.room1.endOfCharacterTurnChecks();

        Intent intent = new Intent(this, Main6ActivitySummary.class);
        intent.putExtra("game", game);
        startActivity(intent);
    } else {
        Toast.makeText(this, "You need to target a character before you can cast it!", Toast.LENGTH_SHORT ).show();

    }
    }

    public void onAction2DPS(View view){

        damageDone = game.room1.fellowship.dps().fireStorm(game.room1);
        Toast toast1 = Toast.makeText(this, "A wall of fire hits all the enemy for" + damageDone + "!", Toast.LENGTH_SHORT );
        toast1.setGravity(Gravity.CENTER, 0, 0);
        toast1.show();
        game.room1.endOfCharacterTurnChecks();
        Intent intent = new Intent(this, Main6ActivitySummary.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

    public void onAction3DPS(View view){
        damageDone = game.room1.fellowship.dps().slowBurn(game.room1);
        Toast toast1 = Toast.makeText(this, "The enemy are burned and will take " + damageDone + " per turn!", Toast.LENGTH_SHORT );
        toast1.setGravity(Gravity.CENTER, 0, 0);
        toast1.show();
        game.room1.endOfCharacterTurnChecks();
        Intent intent = new Intent(this, Main6ActivitySummary.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

    public void onAction4DPS(View view){
        if (target1 != null){
            damageDone = game.room1.fellowship.dps().slagArmour(target1);
            Toast toast1 = Toast.makeText(this, target1 + "'s armour is melted and broken and " + target1+ " takes " + damageDone + " damage!", Toast.LENGTH_SHORT );
            toast1.setGravity(Gravity.CENTER, 0, 0);
            toast1.show();
        game.room1.endOfCharacterTurnChecks();
        Intent intent = new Intent(this, Main6ActivitySummary.class);
        intent.putExtra("game", game);
        startActivity(intent);

    } else {
        Toast.makeText(this, "You need to target a character before you can cast it!", Toast.LENGTH_SHORT ).show();

        }

    }

}
