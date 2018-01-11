package com.example.andrew.dungeoneer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Timer;
import java.util.TimerTask;

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
        game = (Game) intent.getSerializableExtra("game");


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
        game.room1.fellowship.healer().sufficientManaCheck(game.room1.fellowship.healer().getAction1cost());

        Button onAction2Healer = findViewById(R.id.action2Healer);
        onAction2Healer.setEnabled(game.room1.fellowship.healer().sufficientManaCheck(game.room1.fellowship.healer().getAction2cost()));

        Button onAction3Healer = findViewById(R.id.action3Healer);
        onAction3Healer.setEnabled(game.room1.fellowship.healer().sufficientManaCheck(game.room1.fellowship.healer().getAction3cost()));

        Button onAction4Healer = findViewById(R.id.action4Healer);
        onAction4Healer.setEnabled(game.room1.fellowship.healer().isManaAbove50());

        TextView mana = (TextView) findViewById(R.id.manaView);
        mana.setText(game.room1.fellowship.healer().getManaPool().toString());

        TextView manaview1 = (TextView) findViewById(R.id.action1ManaView);
        manaview1.setText(game.room1.fellowship.healer().getAction1cost().toString());

        TextView manaview2 = (TextView) findViewById(R.id.action2ManaView);
        manaview2.setText(game.room1.fellowship.healer().getAction2cost().toString());

        TextView manaview3 = (TextView) findViewById(R.id.action3ManaView);
        manaview3.setText(game.room1.fellowship.healer().getAction3cost().toString());



    if (game.novice == 1) {
        Toast toast1 = Toast.makeText(this, "This Screen shows your healer, they need to keep their party alive with spells.", Toast.LENGTH_LONG);
        toast1.setGravity(Gravity.CENTER, 0, 0);
        View view1 = toast1.getView();
        view1.setBackgroundColor(Color.parseColor("#ff99cc00"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toast1.show();
            }
        }, 1500);

        Toast toast2 = Toast.makeText(this, "You can select a target or use spells that effect all the heroes!", Toast.LENGTH_LONG);
        toast2.setGravity(Gravity.CENTER, 0, 0);
        View view2 = toast2.getView();
        view2.setBackgroundColor(Color.parseColor("#ff99cc00"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toast2.show();
            }
        }, 7000);

        Toast toast3 = Toast.makeText(this, "Your Spells use up your Mana Pool, it regenerates a little each turn.", Toast.LENGTH_LONG);
        toast3.setGravity(Gravity.CENTER, 0, 0);
        View view3 = toast3.getView();
        view3.setBackgroundColor(Color.parseColor("#ff99cc00"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toast3.show();
            }
        }, 12000);


        Toast toast4 = Toast.makeText(this, "Try the AOEHOT Spell to start us off!", Toast.LENGTH_SHORT);
        toast4.setGravity(Gravity.CENTER, 0, 0);
        View view4 = toast4.getView();
        view4.setBackgroundColor(Color.parseColor("#ff99cc00"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toast4.show();
            }
        }, 17000);
    }



    }


    public void onSkipButtonClick(View view){
        Intent intent = new Intent(this, Main4ActivityTank.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }


    public void onAction1Healer(View view){
    if (target1 != null){

        game.room1.fellowship.healer().heal(target1, game.room1);
        game.room1.endOfCharacterTurnChecks();
        Toast toast1 = Toast.makeText(this, target1.getDesignation() + " was healed!", Toast.LENGTH_SHORT );
        toast1.setGravity(Gravity.CENTER, 0, 0);
        View view1 = toast1.getView();
        view1.setBackgroundColor(Color.parseColor("#ff99cc00"));
        toast1.show();
        Intent intent = new Intent(this, Main4ActivityTank.class);
        intent.putExtra("game", game);
        startActivity(intent);
    } else {
        Toast toast = Toast.makeText(this, "You need to target a Hero in order to cast this spell!", Toast.LENGTH_SHORT);
        View view2 = toast.getView();
        toast.setGravity(Gravity.CENTER, 0, 0);
        view2.setBackgroundColor(Color.parseColor("#ff99cc00"));
        toast.show();

    }


    }

    public void onAction2Healer(View view){

        game.room1.fellowship.healer().aoeHeal(game.room1.fellowship, game.room1);
        game.room1.endOfCharacterTurnChecks();
        Toast toast1 = Toast.makeText(this, "All your Heroes were healed!", Toast.LENGTH_SHORT );
        toast1.setGravity(Gravity.CENTER, 0, 0);
        View view1 = toast1.getView();
        view1.setBackgroundColor(Color.parseColor("#ff99cc00"));
        toast1.show();
        Intent intent = new Intent(this, Main4ActivityTank.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

    public void onAction3Healer(View view){

        game.room1.fellowship.healer().aoeHot(game.room1.fellowship, game.room1);
        game.room1.endOfCharacterTurnChecks();
        Toast toast1 = Toast.makeText(this, "Your Heroes will be healed a small amount at the end of each turn for 3 turns!", Toast.LENGTH_LONG );
        toast1.setGravity(Gravity.CENTER, 0, 0);
        View view1 = toast1.getView();
        view1.setBackgroundColor(Color.parseColor("#ff99cc00"));
        toast1.show();
        Intent intent = new Intent(this, Main4ActivityTank.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

    public void onAction4Healer(View view){

        game.room1.fellowship.healer().manaStorm(game.room1);
        game.room1.endOfCharacterTurnChecks();
        Toast toast1 = Toast.makeText(this, "The Priest uses the Force and replenishes the Mana resources of the Fellowship!", Toast.LENGTH_SHORT );
        toast1.setGravity(Gravity.CENTER, 0, 0);
        View view1 = toast1.getView();
        view1.setBackgroundColor(Color.parseColor("#ff99cc00"));
        toast1.show();
        Intent intent = new Intent(this, Main4ActivityTank.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }



}
