package com.example.andrew.dungeoneer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.andrew.dungeoneer.Characters.Archetypes.Character;

import java.util.ArrayList;

/**
 * Created by user on 08/01/2018.
 */

public class VillainsRowAdaptor extends ArrayAdapter<Character> {

    public VillainsRowAdaptor(Context context, ArrayList<Character> villains) {
        super(context, 0, villains);
    }

        @Override
        public View getView(int position, View listItemView, ViewGroup parent) {
            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.villains_row, parent, false);
            }

            Character currentCharacter = getItem(position);

            TextView name = (TextView) listItemView.findViewById(R.id.nameView);
            name.setText(currentCharacter.getName().toString());

            TextView designation = (TextView) listItemView.findViewById(R.id.designationView);
            designation.setText(currentCharacter.getDesignation().toString());

            TextView mainhand = (TextView) listItemView.findViewById(R.id.mainWeaponView);
            mainhand.setText(currentCharacter.getWeapon().toString());


            TextView intelligence = (TextView) listItemView.findViewById(R.id.intView);
            intelligence.setText(currentCharacter.getIntellect().toString());


            TextView health = (TextView) listItemView.findViewById(R.id.healthView);
            health.setText(currentCharacter.getHealthBar().toString());

            TextView armour = (TextView) listItemView.findViewById(R.id.armourView);
            armour.setText(currentCharacter.getArmour().toString());

            TextView alive = (TextView) listItemView.findViewById(R.id.aliveView);
            alive.setText(currentCharacter.aliveNow().toString());

            TextView threat= (TextView) listItemView.findViewById(R.id.threatView);
            threat.setText(currentCharacter.topThreat().getDesignation().toString());



            listItemView.setTag(currentCharacter);

            return listItemView;






    }



}
