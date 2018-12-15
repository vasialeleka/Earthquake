package com.e.vasialeleka.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.e.vasialeleka.myapplication.data.PetContract;

public class PetCursorAdapter extends CursorAdapter {
    public PetCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.info_list, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameView = view.findViewById(R.id.name);
        TextView breedView = view.findViewById(R.id.breed);
        TextView weightView = view.findViewById(R.id.weight);
        TextView kgView = view.findViewById(R.id.kg);
int nameColonInd = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
int breedColonInd = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);
        int weightColonInd = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_WEIGHT);
        kgView.setText("kg");
        String name = cursor.getString(nameColonInd);
        String breed = cursor.getString(breedColonInd);
        int weight = cursor.getInt(weightColonInd);
        nameView.setText(name);
        breedView.setText(breed);
        weightView.setText(""+weight);
    }
}