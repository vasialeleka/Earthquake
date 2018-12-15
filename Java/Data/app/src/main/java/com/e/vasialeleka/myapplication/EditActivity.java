package com.e.vasialeleka.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.e.vasialeleka.myapplication.data.PetContract;
import com.e.vasialeleka.myapplication.data.PetDBhelper;

import static android.widget.AdapterView.*;

public class EditActivity extends AppCompatActivity {
    private String[] gender = {"unknown", "male", "female"};
    private int mGender = 0;
    private Spinner mGenderSpinner;
    Button add ;
    private EditText name;
    private EditText breed;
    private EditText mass ;
    SQLiteDatabase db;
    PetDBhelper dbhelp ;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    getMenuInflater().inflate(R.menu.main,menu);
        MenuItem item1 = menu.findItem(R.id.item1);
        item1.setTitle("New pet");
    return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id =  item.getItemId();
        if (id == R.id.item1 ){
            //logic
            name.setText("Teddy");
            breed.setText("unknown");
            mass .setText("14");
            ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                    R.array.array_gender_options, android.R.layout.simple_spinner_item);

            // Specify dropdown layout style - simple list view with 1 item per line
            genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

            // Apply the adapter to the spinner
            mGenderSpinner.setAdapter(genderSpinnerAdapter);
            mGenderSpinner.setSelection(0);
            mGender = 0;
         //   mGender.set
          return true;

        }

        if (id == R.id.save){
insertNewData();
finish();
            return true;
        }
        if (id == R.id.item2 ){
            //logic

            return true;
        }
        return true;
    }

    private void insertNewData() {
        String nameText = name.getText().toString().trim();
    String breedText = breed.getText().toString().trim();
    int weight = Integer.parseInt(mass.getText().toString());
        PetDBhelper pets = new PetDBhelper(this);
     //   SQLiteDatabase database = pets.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
        contentValues.put(PetContract.PetEntry.COLUMN_PET_NAME,nameText);
        contentValues.put(PetContract.PetEntry.COLUMN_PET_BREED,breedText);
        contentValues.put(PetContract.PetEntry.COLUMN_PET_GENDER,mGender);
        contentValues.put(PetContract.PetEntry.COLUMN_PET_WEIGHT,weight);
        Uri insertNewData ;
        insertNewData=getContentResolver().insert(PetContract.PetEntry.CONTENT_URI,contentValues);
       // long rowId =   database.insert(PetContract.PetEntry.TABLE_NAME,null,contentValues);
   if (insertNewData == null) {
     Toast.makeText(getApplicationContext(),"Error with saving pet",Toast.LENGTH_SHORT).show();
   }else {Toast.makeText(getApplicationContext(),"Pet saved with row id "+insertNewData.getQuery(),Toast.LENGTH_SHORT).show();}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
                 mGenderSpinner = (Spinner) findViewById(R.id.sp);
                 name = findViewById(R.id.edit_pet_name);
                 breed = findViewById(R.id.edit_pet_breed);
                 mass = findViewById(R.id.mass);


        setupSpinner();
    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = 1;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = 2;
                    } else {
                        mGender = 0;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = 0;
            }
        });
    }
}