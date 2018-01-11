package com.example.andrew.dungeoneer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.andrew.dungeoneer.Characters.Archetypes.Character;

/**
 * Created by user on 08/01/2018.
 */

public class HeroesRowAdaptor extends ArrayAdapter<Character> {

    public HeroesRowAdaptor(Context context, ArrayList<Character> heroes) {
        super(context, 0, heroes);
    }

        @Override
        public View getView(int position, View listItemView, ViewGroup parent) {
            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.heroes_row, parent, false);
            }

            Character currentCharacter = getItem(position);


            TextView health = (TextView) listItemView.findViewById(R.id.healthView);
            health.setText(currentCharacter.getHealthBar().toString());

            TextView healthMax = (TextView) listItemView.findViewById(R.id.herohealthmaxview);
            healthMax.setText(currentCharacter.getMaxHealth().toString());

            TextView armour = (TextView) listItemView.findViewById(R.id.armourView);
            armour.setText(currentCharacter.getArmour().toString());

            TextView resource = (TextView) listItemView.findViewById(R.id.resourceView);
            resource.setText(currentCharacter.getManaPool().toString());

            TextView resourcemax = (TextView) listItemView.findViewById(R.id.resourceMaxView);
            resourcemax.setText(currentCharacter.getManaMax().toString());

            ImageView classIcon = listItemView.findViewById(R.id.classView);
            switch (currentCharacter.getDesignation()) {
                case "Healer":
                        classIcon.setImageResource(R.drawable.healer10);
                        break;
                case "Tank":
                    classIcon.setImageResource(R.drawable.tankicon10);
                        break;
                case "DPS":
                    classIcon.setImageResource(R.drawable.wizardicon3);
                        break;
            }

            ImageView resourceIcon = listItemView.findViewById(R.id.resourceIconView);
            switch (currentCharacter.getClassResource()) {
                case "Mana":
                    resourceIcon.setImageResource(R.drawable.manaicon1111);
                    break;
                case "Rage":
                    resourceIcon.setImageResource(R.drawable.fist);
                    break;
            }


            listItemView.setTag(currentCharacter);

            return listItemView;






    }



}
