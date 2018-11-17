package com.e.vasialeleka.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.e.vasialeleka.myapplication.data.PetContract;
import com.e.vasialeleka.myapplication.data.PetDBhelper;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item1 = menu.findItem(R.id.item1);
        MenuItem save = menu.findItem(R.id.save);
        save.setVisible(false);
        item1.setTitle("New pet");
        MenuItem item2 = menu.findItem(R.id.item2);
        item2.setTitle("Delete all");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item1) {
            //logic
            insertPet();
            return true;
        }
        if (id == R.id.item2) {
            //       PetDBhelper dBhelper = new PetDBhelper(this);
            //       SQLiteDatabase db = dBhelper.getWritableDatabase();
            //     Cursor c =  db.rawQuery("DELETE FROM"+PetContract.PetEntry.TABLE_NAME);
            //  delete();
            //logic
            return true;
        }
        return true;
    }

    private void insertPet() {
        PetDBhelper pets = new PetDBhelper(this);
        SQLiteDatabase database = pets.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PetContract.PetEntry.COLUMN_PET_NAME, "name");
        contentValues.put(PetContract.PetEntry.COLUMN_PET_BREED, "breed");
        contentValues.put(PetContract.PetEntry.COLUMN_PET_GENDER, 2);
        contentValues.put(PetContract.PetEntry.COLUMN_PET_WEIGHT, 14);
        long rowId = database.insert(PetContract.PetEntry.TABLE_NAME, null, contentValues);
        Log.v("MainActivity", "Num of row" + rowId);
        displayDataBaseInfo();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton button = findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });
        displayDataBaseInfo();
    }

    @Override
    public void onStart() {
        super.onStart();
        displayDataBaseInfo();
    }

    private void displayDataBaseInfo() {
        // and pass the context, which is the current activity.
        PetDBhelper mDbHelper = new PetDBhelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        //  Cursor cursor = db.rawQuery("SELECT * FROM " + PetContract.PetEntry.TABLE_NAME, null);
        String[] projection = {PetContract.PetEntry._ID, PetContract.PetEntry.COLUMN_PET_NAME,PetContract.PetEntry.COLUMN_PET_BREED,PetContract.PetEntry.COLUMN_PET_GENDER,PetContract.PetEntry.COLUMN_PET_WEIGHT};
        //  String selection = PetContract.PetEntry._ID
        Cursor cursor = db.query(PetContract.PetEntry.TABLE_NAME, projection, null, null, null, null, null);
/*cursor.moveToPosition(6);
        int nameColonInd = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);*/

        try {
            String newtext = "";
            int idColonInd = cursor.getColumnIndex(PetContract.PetEntry._ID);
            int nameColonInd = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
            int breedColonInd = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);
            int genderColonInd = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_GENDER);
            int massColonInd = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_WEIGHT);


         //   for (int i = 0; i < cursor.getCount(); i++) {
         //       cursor.moveToPosition(i);
            while(cursor.moveToNext())

            {
                int idPet = cursor.getInt(idColonInd);
                int gender = cursor.getInt(genderColonInd);
                int massPet = cursor.getInt(massColonInd);
                String namePet = cursor.getString(nameColonInd);
                String breedPet = cursor.getString(breedColonInd);


                newtext += "Id : "+idPet + "  Name : " + namePet + " Breed : "+breedPet+" Weight : "+massPet+"\n";
                TextView displayView = (TextView) findViewById(R.id.t);
                displayView.setText(newtext);
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
