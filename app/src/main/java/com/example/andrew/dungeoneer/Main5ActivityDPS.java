package com.example.andrew.dungeoneer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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

        TextView mana = (TextView) findViewById(R.id.manaView);
        mana.setText(game.room1.fellowship.dps().getManaPool().toString());

        TextView manaview1 = (TextView) findViewById(R.id.action1ManaView);
        manaview1.setText(game.room1.fellowship.dps().getAction1cost().toString());

        TextView manaview2 = (TextView) findViewById(R.id.action2ManaView);
        manaview2.setText(game.room1.fellowship.dps().getAction2cost().toString());

        TextView manaview3 = (TextView) findViewById(R.id.action3ManaView);
        manaview3.setText(game.room1.fellowship.dps().getAction3cost().toString());

        TextView manaview4 = (TextView) findViewById(R.id.action4ManaView);
        manaview4.setText(game.room1.fellowship.dps().getAction4cost().toString());

        if (game.novice == 1) {
            Toast toast1 = Toast.makeText(this, "This Screen shows your DPS, they need to damage the enemy.", Toast.LENGTH_LONG);
            toast1.setGravity(Gravity.CENTER, 0, 0);
            View view1 = toast1.getView();
            view1.setBackgroundColor(Color.parseColor("#ffff4444"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast1.show();
                }
            }, 4000);

            Toast toast2 = Toast.makeText(this, "You can select a target and use an attack, it costs mana.", Toast.LENGTH_LONG);
            toast2.setGravity(Gravity.CENTER, 0, 0);
            View view2 = toast2.getView();
            view2.setBackgroundColor(Color.parseColor("#ffff4444"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast2.show();
                }
            }, 9000);


            Toast toast3 = Toast.makeText(this, "Be careful though, try to only damage enemies that are committed to attacking the Tank.", Toast.LENGTH_LONG);
            toast3.setGravity(Gravity.CENTER, 0, 0);
            View view3 = toast3.getView();
            view3.setBackgroundColor(Color.parseColor("#ffff4444"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast3.show();
                }
            }, 14000);

            Toast toast4 = Toast.makeText(this, "Try an DOTAOE to start with!", Toast.LENGTH_SHORT);
            toast4.setGravity(Gravity.CENTER, 0, 0);
            View view4 = toast4.getView();
            view4.setBackgroundColor(Color.parseColor("#ffff4444"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast4.show();
                }
            }, 19000);
        }
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
            View view1 = toast1.getView();
            view1.setBackgroundColor(Color.parseColor("#ffff4444"));
            toast1.show();
            game.room1.endOfCharacterTurnChecks();


        Intent intent = new Intent(this, Main6ActivitySummary.class);
        intent.putExtra("game", game);
        startActivity(intent);
    } else {
            Toast toast = Toast.makeText(this, "You need to target an Enemy before you can attack with this Spell!", Toast.LENGTH_SHORT );
            View view2 = toast.getView();
            toast.setGravity(Gravity.CENTER, 0, 0);
            view2.setBackgroundColor(Color.parseColor("#ffff4444"));
            toast.show();
    }
    }

    public void onAction2DPS(View view){

        damageDone = game.room1.fellowship.dps().fireStorm(game.room1);
        Toast toast1 = Toast.makeText(this, "A wall of fire hits all the enemy for" + damageDone + "!", Toast.LENGTH_SHORT );
        toast1.setGravity(Gravity.CENTER, 0, 0);
        View view1 = toast1.getView();
        view1.setBackgroundColor(Color.parseColor("#ffff4444"));
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
        View view1 = toast1.getView();
        view1.setBackgroundColor(Color.parseColor("#ffff4444"));
        toast1.show();
        game.room1.endOfCharacterTurnChecks();
        Intent intent = new Intent(this, Main6ActivitySummary.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

    public void onAction4DPS(View view){
        if (target1 != null){
            damageDone = game.room1.fellowship.dps().slagArmour(target1);
            Toast toast1 = Toast.makeText(this, target1.getDesignation() + "'s armour is melted and broken and " + target1.getDesignation()+ " takes " + damageDone + " damage!", Toast.LENGTH_SHORT );
            toast1.setGravity(Gravity.CENTER, 0, 0);
            View view1 = toast1.getView();
            view1.setBackgroundColor(Color.parseColor("#ffff4444"));
            toast1.show();
        game.room1.endOfCharacterTurnChecks();
        Intent intent = new Intent(this, Main6ActivitySummary.class);
        intent.putExtra("game", game);
        startActivity(intent);

    } else {
            Toast toast = Toast.makeText(this, "You need to target an Enemy before you can attack with this ability!", Toast.LENGTH_SHORT );
            View view2 = toast.getView();
            toast.setGravity(Gravity.CENTER, 0, 0);
            view2.setBackgroundColor(Color.parseColor("#ffff4444"));
            toast.show();
        }

    }

}
