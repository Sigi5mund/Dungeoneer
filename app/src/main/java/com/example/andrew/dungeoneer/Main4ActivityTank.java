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

        if (game.novice == 1) {
            Toast toast1 = Toast.makeText(this, "This Screen shows your Tank, they need to defend your party and take the damage.", Toast.LENGTH_LONG);
            toast1.setGravity(Gravity.CENTER, 0, 0);
            View view1 = toast1.getView();
            view1.setBackgroundColor(Color.parseColor("#ffff8800"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast1.show();
                }
            }, 5000);

            Toast toast2 = Toast.makeText(this, "You can select a target and use an attack, it will build up your rage.", Toast.LENGTH_LONG);
            toast2.setGravity(Gravity.CENTER, 0, 0);
            View view2 = toast2.getView();
            view2.setBackgroundColor(Color.parseColor("#ffff8800"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast2.show();
                }
            }, 9500);


            Toast toast3 = Toast.makeText(this, "Your attacks will generate Threat and make the enemy attack the Tank!", Toast.LENGTH_LONG);
            toast3.setGravity(Gravity.CENTER, 0, 0);
            View view3 = toast3.getView();
            view3.setBackgroundColor(Color.parseColor("#ffff8800"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast3.show();
                }
            }, 14000);

            Toast toast4 = Toast.makeText(this, "Try Attacking the Orc Captain first.", Toast.LENGTH_SHORT);
            toast4.setGravity(Gravity.CENTER, 0, 0);
            View view4 = toast4.getView();
            view4.setBackgroundColor(Color.parseColor("#ffff8800"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast4.show();
                }
            }, 18000);
        }
    }


    public void onSkipButtonClick(View view) {



        Intent intent = new Intent(this, Main5ActivityDPS.class);
        intent.putExtra("game", game);
        startActivity(intent);

    }


    public void onAction1Tank(View view){
        if (target1 != null){
            damageDone = game.room1.fellowship.tank().tauntAttack(target1, game.room1);
            Toast toast1 = Toast.makeText(this, target1.getDesignation() + " was hit for "+ damageDone + "damage!", Toast.LENGTH_SHORT );
            toast1.setGravity(Gravity.CENTER, 0, 0);
            View view1 = toast1.getView();
            view1.setBackgroundColor(Color.parseColor("#ffff8800"));
            toast1.show();
            game.room1.endOfCharacterTurnChecks();
            game.room1.removeDead();


//            boolean trigger = false;
//            if ( game.room1.checkIfAnyBaddiesDead() == true){trigger = true;}
//            if (trigger ==true)
//            {String message = game.room1.removeDeadMessage();
//                Toast toast2 = Toast.makeText(this, message, Toast.LENGTH_SHORT);
//                toast2.setGravity(Gravity.CENTER, 0, 0);
//                View view2 = toast2.getView();
//                view2.setBackgroundColor(Color.parseColor("#ff0099cc"));
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        toast2.show();
//                    }
//                }, 4000);
//                toast2.show();
//            }

        Intent intent = new Intent(this, Main5ActivityDPS.class);
        intent.putExtra("game", game);
        startActivity(intent);
    } else {
        Toast toast = Toast.makeText(this, "You need to target an Enemy before you can attack with this ability!", Toast.LENGTH_SHORT );
            View view2 = toast.getView();
            toast.setGravity(Gravity.CENTER, 0, 0);
            view2.setBackgroundColor(Color.parseColor("#ffff8800"));
            toast.show();

    }
    }


    public void onAction2Tank(View view){

        Toast toast1 = Toast.makeText(this, "The Tank raises the Shield and becomes impervious to damage this turn!", Toast.LENGTH_LONG );
        toast1.setGravity(Gravity.CENTER, 0, 0);
        View view1 = toast1.getView();
        view1.setBackgroundColor(Color.parseColor("#ffff8800"));
        toast1.show();
        game.room1.fellowship.tank().shieldWall(game.room1);
        game.room1.endOfCharacterTurnChecks();
        game.room1.removeDead();
        Intent intent = new Intent(this, Main5ActivityDPS.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }


    public void onAction3Tank(View view){
        Toast toast1 = Toast.makeText(this, "The Tank taunts all the Enemies and increases Threat!", Toast.LENGTH_LONG );
        toast1.setGravity(Gravity.CENTER, 0, 0);
        View view1 = toast1.getView();
        view1.setBackgroundColor(Color.parseColor("#ffff8800"));
        toast1.show();
        game.room1.fellowship.tank().tauntAOE(game.room1);
        game.room1.endOfCharacterTurnChecks();
        game.room1.removeDead();
        Intent intent = new Intent(this, Main5ActivityDPS.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }


    public void onAction4Tank(View view){
        if (target1 != null){
            Toast toast1 = Toast.makeText(this, "The Tank brains the target with the Shield and makes it vulnerable to magic!", Toast.LENGTH_LONG );
            toast1.setGravity(Gravity.CENTER, 0, 0);
            View view1 = toast1.getView();
            view1.setBackgroundColor(Color.parseColor("#ffff8800"));
            toast1.show();
        game.room1.fellowship.tank().headBash(target1);
        game.room1.endOfCharacterTurnChecks();
        game.room1.removeDead();
        Intent intent = new Intent(this, Main5ActivityDPS.class);
        intent.putExtra("game", game);
        startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, "You need to target an Enemy before you can attack with this ability!", Toast.LENGTH_SHORT );
            View view2 = toast.getView();
            toast.setGravity(Gravity.CENTER, 0, 0);
            view2.setBackgroundColor(Color.parseColor("#ffff8800"));
            toast.show();
        }
    }
}
