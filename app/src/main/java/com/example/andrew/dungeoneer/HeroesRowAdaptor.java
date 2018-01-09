package com.example.andrew.dungeoneer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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

            TextView name = (TextView) listItemView.findViewById(R.id.nameView);
            name.setText(currentCharacter.getName().toString());

            TextView designation = (TextView) listItemView.findViewById(R.id.designationView);
            designation.setText(currentCharacter.getDesignation().toString());

            TextView mainhand = (TextView) listItemView.findViewById(R.id.mainWeaponView);
            mainhand.setText(currentCharacter.getWeapon().toString());

            TextView offhand = (TextView) listItemView.findViewById(R.id.offHandView);
            offhand.setText(currentCharacter.getOffHand().toString());

            TextView stamina = (TextView) listItemView.findViewById(R.id.staminaView);
            stamina.setText(currentCharacter.getStamina().toString());

            TextView intelligence = (TextView) listItemView.findViewById(R.id.intelligenceView);
            intelligence.setText(currentCharacter.getIntellect().toString());

            TextView strength = (TextView) listItemView.findViewById(R.id.strengthView);
            strength.setText(currentCharacter.getStrength().toString());

            TextView agility = (TextView) listItemView.findViewById(R.id.agilityView);
            agility.setText(currentCharacter.getAgility().toString());

            TextView health = (TextView) listItemView.findViewById(R.id.healthView);
            health.setText(currentCharacter.getHealthBar().toString());

            TextView armour = (TextView) listItemView.findViewById(R.id.armourView);
            armour.setText(currentCharacter.getArmour().toString());




            listItemView.setTag(currentCharacter);

            return listItemView;






    }



}
