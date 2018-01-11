package com.example.andrew.dungeoneer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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


            ImageView classIcon = listItemView.findViewById(R.id.classView);
            switch (currentCharacter.getDesignation()) {
                case "Goblin":
                    classIcon.setImageResource(R.drawable.goblinicon);
                    break;
                case "Orc Captain":
                    classIcon.setImageResource(R.drawable.orcicon);
                    break;

            }

            TextView intelligence = (TextView) listItemView.findViewById(R.id.intView);
            intelligence.setText(currentCharacter.getIntellect().toString());


            TextView health = (TextView) listItemView.findViewById(R.id.healthView);
            health.setText(currentCharacter.getHealthBar().toString());


            TextView armour = (TextView) listItemView.findViewById(R.id.armourView);
            armour.setText(currentCharacter.getArmour().toString());





            ImageView topthreatIcon = listItemView.findViewById(R.id.topthreatView);
            switch (currentCharacter.topThreat().getDesignation()) {
                case "Healer":
                    topthreatIcon.setImageResource(R.drawable.healer10);
                    break;
                case "Tank":
                    topthreatIcon.setImageResource(R.drawable.tankicon10);
                    break;
                case "DPS":
                    topthreatIcon.setImageResource(R.drawable.wizardicon3);
                    break;
            }

            listItemView.setTag(currentCharacter);

            return listItemView;






    }



}
