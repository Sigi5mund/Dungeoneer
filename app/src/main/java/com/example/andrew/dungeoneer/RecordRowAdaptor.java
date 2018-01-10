package com.example.andrew.dungeoneer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.andrew.dungeoneer.Characters.Archetypes.Character;
import com.example.andrew.dungeoneer.Items.RecordObject;

import java.util.ArrayList;

public class RecordRowAdaptor extends ArrayAdapter<RecordObject> {

    public RecordRowAdaptor(Context context, ArrayList<RecordObject> records) {
        super(context, 0, records);
    }

        @Override
        public View getView(int position, View listItemView, ViewGroup parent) {
            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.record_row, parent, false);
            }

            RecordObject currentRecord = getItem(position);

            TextView origin = (TextView) listItemView.findViewById(R.id.attackerView);
            origin.setText(currentRecord.getOrigin().getName().toString());

            TextView damage = (TextView) listItemView.findViewById(R.id.damageView);
            damage.setText(currentRecord.getDamage().toString());

            TextView target = (TextView) listItemView.findViewById(R.id.targetView1);
            target.setText(currentRecord.getTarget().getDesignation().toString());


            listItemView.setTag(currentRecord);

            return listItemView;






    }



}
