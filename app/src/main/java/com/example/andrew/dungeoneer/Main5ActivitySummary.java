package com.example.andrew.dungeoneer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andrew.dungeoneer.Characters.Archetypes.Character;
import com.example.andrew.dungeoneer.Characters.Archetypes.Goblin;
import com.example.andrew.dungeoneer.Game.Game;
import com.example.andrew.dungeoneer.Items.RecordObject;

import java.util.ArrayList;

public class Main5ActivitySummary extends AppCompatActivity {

    Game game;
    String dead;
    boolean noDeadCharacters;
    boolean enemiesAllDead;
    Integer fallenEnemiesTest;


//  Activity Set Up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6_summary);
        Intent intent = getIntent();
        game = (Game) intent.getSerializableExtra("game");
        noDeadCharacters = true;
        enemiesAllDead = true;

//  Game Logic
        game.room1.attacksThisTurn.clear();
        game.room1.sortAllThreatTables();
        game.room1.checkWhoIsAlive();
        game.room1.villainsTurnAttacks();
        game.room1.checkWhoIsAlive();

        ArrayList<RecordObject> list = game.room1.getAttacksThisTurn();
        RecordRowAdaptor recordAdapter = new RecordRowAdaptor(this, list);
        ListView listView = findViewById(R.id.enemyAttacksList);
        listView.setAdapter(recordAdapter);

        fallenEnemiesTest = game.room1.getBaddies().size();
        game.room1.endOfCombatChecks();
        game.room1.checkWhoIsAlive();
        game.room1.removeDead();

        if (fallenEnemiesTest > game.room1.getBaddies().size()) {
            Toast toast = Toast.makeText(this, "An enemy has fallen to your end of turn damage spells.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            View view = toast.getView();
            view.setBackgroundColor(Color.parseColor("#ffff4444"));
        }
        else {
            Toast toast = Toast.makeText(this, "An enemy has fallen to your end of turn damage spells.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            View view = toast.getView();
            view.setBackgroundColor(Color.parseColor("#ffff4444"));
        }


        Toast toast6 = Toast.makeText(this, "Your Timed Heal spells trigger.", Toast.LENGTH_SHORT);
        toast6.setGravity(Gravity.BOTTOM, 0, 0);
        View view6 = toast6.getView();
        view6.setBackgroundColor(Color.parseColor("#ff99cc00"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toast6.show();
            }
        }, 2000);

        Toast toast7 = Toast.makeText(this, "Your Smoulder spells trigger", Toast.LENGTH_SHORT);
        toast7.setGravity(Gravity.CENTER, 0, 0);
        View view7 = toast7.getView();
        view7.setBackgroundColor(Color.parseColor("#ffff4444"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toast7.show();
            }
        }, 2000);

        game.turn += 1;

        for (Character hero : game.room1.fellowship.getHeroes()) {
            if (hero.isAlive() == false) {
                dead = "GAME OVER! Your " + hero.getDesignation().toString() + " died! Try again!";
                noDeadCharacters = false;
            }
        }

        if (game.room1.baddies.size() == 0) {
            enemiesAllDead = false;
        }


//      Win and Lose Screens
        TextView deadHeroMessage = findViewById(R.id.gameOverView);
        deadHeroMessage.setText(dead);
        if (noDeadCharacters == false) {
            deadHeroMessage.setVisibility(View.VISIBLE);
        }

        TextView victoryMessage = findViewById(R.id.gameWonView);
        if (enemiesAllDead == false) {
            victoryMessage.setVisibility(View.VISIBLE);
        }

        Button onNextTurn = findViewById(R.id.onNextTurn);
        onNextTurn.setEnabled(noDeadCharacters && enemiesAllDead);


        if (game.novice == 1) {
            Toast toast1 = Toast.makeText(this, "It is the Enemies Turn to Attack!", Toast.LENGTH_LONG);
            toast1.setGravity(Gravity.TOP, 0, 0);
            View view1 = toast1.getView();
            view1.setBackgroundColor(Color.parseColor("#ffcc0000"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                        toast1.show();
                    }
            }, 4000);

            Toast toast2 = Toast.makeText(this, "Hopefully they are all hitting the Tank, they are best suited to mitigating the damage.", Toast.LENGTH_LONG);
            toast2.setGravity(Gravity.CENTER, 0, 0);
            View view2 = toast2.getView();
            view2.setBackgroundColor(Color.parseColor("#ffff8800"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                        toast2.show();
                    }
            }, 7000);

            Toast toast3 = Toast.makeText(this, "Any Heal or Damage over time Spells will be triggered at the end of each turn.", Toast.LENGTH_LONG);
            toast3.setGravity(Gravity.BOTTOM, 0, 0);
            View view3 = toast3.getView();
            view3.setBackgroundColor(Color.parseColor("#ff99cc00"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                        toast3.show();
                    }
            }, 14000);

            Toast toast4 = Toast.makeText(this, "Now, manage your resources, get the threat on the tank, and kill the enemy!", Toast.LENGTH_LONG);
            toast4.setGravity(Gravity.CENTER, 0, 0);
            View view4 = toast4.getView();
            view4.setBackgroundColor(Color.parseColor("#ff99cc00"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                        toast4.show();
                    }
            }, 19000);

            Toast toast5 = Toast.makeText(this, "Click next turn!", Toast.LENGTH_SHORT);
            toast5.setGravity(Gravity.TOP, 0, 0);
            View view5 = toast1.getView();
            view5.setBackgroundColor(Color.parseColor("#ff99cc00"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast5.show();
                }
            }, 23000);
        }
    }


    public void onQuit(View view){
        Intent intent = new Intent(this, Main1Activity.class);
        startActivity(intent);
    }

    public void onNextTurn(View view){

        if (game.turn == 3) {
            game.currentRoom().baddies.add(new Goblin("Sneaky"));
            game.currentRoom().baddies.add(new Goblin("Peaky"));
            game.room1.addThreatObjectsToTables();
            Toast toast1 = Toast.makeText(this, "A door opens and more enemies join the fight!", Toast.LENGTH_SHORT);
            toast1.setGravity(Gravity.TOP, 0, 0);
            View view1 = toast1.getView();
            view1.setBackgroundColor(Color.parseColor("#ffff8800"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast1.show();
                }
            }, 1000);


            Toast toast2 = Toast.makeText(this, "A door opens and more enemies join the fight!", Toast.LENGTH_SHORT);
            toast2.setGravity(Gravity.BOTTOM, 0, 0);
            View view2 = toast2.getView();
            view2.setBackgroundColor(Color.parseColor("#ffff8800"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast2.show();
                }
            }, 1000);



        }

        game.novice = 0;
        Toast toast1 = Toast.makeText(this, "Turn "+ game.turn, Toast.LENGTH_SHORT );
        toast1.setGravity(Gravity.CENTER, 0, 0);
        View view1 = toast1.getView();
        view1.setBackgroundColor(Color.parseColor("#ff99cc00"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toast1.show();;
            }
        }, 500);

        Intent intent = new Intent(this, Main2ActivityHealer.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

}
