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
import com.example.andrew.dungeoneer.Characters.Archetypes.Fellowship;
import com.example.andrew.dungeoneer.Characters.Archetypes.Goblin;
import com.example.andrew.dungeoneer.Characters.Archetypes.Knight;
import com.example.andrew.dungeoneer.Characters.Archetypes.Priest;
import com.example.andrew.dungeoneer.Characters.Archetypes.Wizard;
import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;
import com.example.andrew.dungeoneer.Game.Game;
import com.example.andrew.dungeoneer.Items.RecordObject;

import java.util.ArrayList;

public class Main6ActivitySummary extends AppCompatActivity {

    Game game;
    String dead;
    boolean noDeadCharacters;
    boolean enemiesAllDead;


//  Activity Set Up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6_summary);
        Intent intent = getIntent();
        game = (Game)intent.getSerializableExtra("game");
        noDeadCharacters = true;
        enemiesAllDead = true;


//  Game Logic
        game.room1.attacksThisTurn.clear();
        game.room1.sortAllThreatTables();
        game.room1.checkWhoIsAlive();
        game.room1.villainsTurnAttacks();
        game.room1.endOfCombatChecks();
        game.room1.checkWhoIsAlive();
        game.room1.removeDead();
        game.turn += 1;


        for (Character hero: game.room1.fellowship.getHeroes()) {
            if (hero.isAlive() == false){
                dead = "GAME OVER! Your " + hero.getDesignation().toString() + " died! Try again!";
                noDeadCharacters = false;}

        }


        if (game.room1.baddies.size() == 0){
                enemiesAllDead = false;
        }









////      Heroes ListView
//        ArrayList<Character> list1 = game.room1.fellowship.getHeroes();
//        HeroesRowAdaptor heroesAdapter = new HeroesRowAdaptor(this, list1);
//        ListView listView1 = findViewById(R.id.healerSummaryList);
//        listView1.setAdapter(heroesAdapter);

//       Record ListView
        ArrayList<RecordObject> list2 = game.room1.getAttacksThisTurn();
        RecordRowAdaptor recordAdapter = new RecordRowAdaptor(this, list2);
        ListView listView2 = findViewById(R.id.enemyAttacksList);
        listView2.setAdapter(recordAdapter);




        TextView deadHeroMessage = findViewById(R.id.gameOverView);
        deadHeroMessage.setText(dead);
        if (noDeadCharacters == false){
//            listView1.setVisibility(View.INVISIBLE);
            deadHeroMessage.setVisibility(View.VISIBLE);
        }


        TextView victoryMessage = findViewById(R.id.gameWonView);
        if (enemiesAllDead == false){
            victoryMessage.setVisibility(View.VISIBLE);
        }



        Button onNextTurn = findViewById(R.id.onNextTurn);
        onNextTurn.setEnabled(noDeadCharacters);
        onNextTurn.setEnabled(enemiesAllDead);

        if (game.turn == 3) {
            game.currentRoom().baddies.add(new Goblin("Sneaky"));
            game.currentRoom().baddies.add(new Goblin("Peaky"));
            game.room1.addThreatObjectsToTables();
            Toast toast1 = Toast.makeText(this, "A door opens and more enemies join the fight!", Toast.LENGTH_SHORT);
            toast1.setGravity(Gravity.CENTER, 0, 0);
            View view1 = toast1.getView();
            view1.setBackgroundColor(Color.parseColor("#ffcc0000"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast1.show();
                }
            }, 1000);


        }


            if (game.novice == 1) {
                Toast toast1 = Toast.makeText(this, "This Screen shows the Attacks made by the Enemy to your party.", Toast.LENGTH_LONG);
                toast1.setGravity(Gravity.CENTER, 0, 0);
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
                view2.setBackgroundColor(Color.parseColor("#ffcc0000"));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast2.show();
                    }
                }, 9000);


                Toast toast3 = Toast.makeText(this, "Now, manage your resources, get the threat on the tank, and kill the enemy!", Toast.LENGTH_LONG);
                toast3.setGravity(Gravity.CENTER, 0, 0);
                View view3 = toast3.getView();
                view3.setBackgroundColor(Color.parseColor("#ffcc0000"));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast3.show();
                    }
                }, 14000);

                Toast toast4 = Toast.makeText(this, "Click next turn!", Toast.LENGTH_SHORT);
                toast4.setGravity(Gravity.CENTER, 0, 0);
                View view4 = toast4.getView();
                view4.setBackgroundColor(Color.parseColor("#ffcc0000"));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast4.show();
                    }
                }, 19000);


            }
        }




    public void onQuit(View view){

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void onNextTurn(View view){

        game.novice = 0;
        Toast toast1 = Toast.makeText(this, "Turn "+ game.turn, Toast.LENGTH_SHORT );
        toast1.setGravity(Gravity.CENTER, 0, 0);
        toast1.show();
        Intent intent = new Intent(this, Main3ActivityHealer.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

}
