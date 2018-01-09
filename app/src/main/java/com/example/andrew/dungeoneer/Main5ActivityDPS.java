package com.example.andrew.dungeoneer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Main4ActivityTank extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_tank);

        Game game = new Game();

        ArrayList<Character> heroes = new ArrayList<>();
        Character healer = new Priest("Cadfael", 100, Weapon.SCEPTER, Armour.LEATHER, OffHand.HEALWAND);
        Character tank = new Knight("Athina", 0, Weapon.SWORD, Armour.GOLD, OffHand.SHIELD);
        Character dps = new Wizard("Gandalf", 5, Weapon.STAFF, Armour.CLOTHE, OffHand.DPSWAND);
        heroes.add(healer);
        heroes.add(tank);
        heroes.add(dps);
        Fellowship fellowship = new Fellowship("The Valiant Few", heroes);
        game.room1.loadGoodies(fellowship);
        game.room1.addThreatObjectsToTables();
        game.room1.fellowship.healer().aoeHeal(game.room1.fellowship, game.room1);
        game.room1.endOfCombatChecks();



        ArrayList<Character> list = game.room1.baddies;

        VillainsRowAdaptor villainsAdapter = new VillainsRowAdaptor(this, list);

        ListView listView = findViewById(R.id.tankList);
        listView.setAdapter(villainsAdapter);

        TextView mana = (TextView) findViewById(R.id.manaView);
        mana.setText(game.room1.fellowship.tank().getManaPool().toString());

    }



}
