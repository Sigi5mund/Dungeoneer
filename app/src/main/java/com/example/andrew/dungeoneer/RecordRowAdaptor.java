package com.example.andrew.dungeoneer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


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


            ImageView classIcon1 = listItemView.findViewById(R.id.enemyView);
            switch (currentRecord.getOrigin().getDesignation()) {
                case "Goblin":
                    classIcon1.setImageResource(R.drawable.goblinicon);
                    break;
                case "Orc Captain":
                    classIcon1.setImageResource(R.drawable.orcicon);
                    break;
            }

            TextView damage = (TextView) listItemView.findViewById(R.id.damageView);
            damage.setText(currentRecord.getDamage().toString());

            TextView health = (TextView) listItemView.findViewById(R.id.herohealthview);
            health.setText(currentRecord.getTarget().getHealthBar().toString());

            TextView healthMax = (TextView) listItemView.findViewById(R.id.herohealthmaxview);
            healthMax.setText(currentRecord.getTarget().getMaxHealth().toString());

            ImageView classIcon = listItemView.findViewById(R.id.heroView);
            switch (currentRecord.getTarget().getDesignation()) {
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





            listItemView.setTag(currentRecord);

            return listItemView;






    }



}
