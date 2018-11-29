package com.e.vasialeleka.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.e.vasialeleka.myapplication.data.PetContract;
import com.e.vasialeleka.myapplication.data.PetDBhelper;

import java.net.URI;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor> {
    ListView list;
private static final int PET_LADER = 0;
PetCursorAdapter mCursorAdapter ;
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
            int dafaultUri = getContentResolver().delete(PetContract.PetEntry.CONTENT_URI, null, null);
          //  displayDataBaseInfo();
            return true;
        }
        return true;
    }

    private void insertPet() {
        PetDBhelper pets = new PetDBhelper(this);
        //  SQLiteDatabase database = pets.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PetContract.PetEntry.COLUMN_PET_NAME, "name");
        contentValues.put(PetContract.PetEntry.COLUMN_PET_BREED, "breed");
        contentValues.put(PetContract.PetEntry.COLUMN_PET_GENDER, 2);
        contentValues.put(PetContract.PetEntry.COLUMN_PET_WEIGHT, 14);
        Uri defaultValue = getContentResolver().insert(PetContract.PetEntry.CONTENT_URI, contentValues);
        //long rowId = database.insert(PetContract.PetEntry.TABLE_NAME, null, contentValues);
        //  Log.v("MainActivity", "Num of row" + rowId);
     //   displayDataBaseInfo();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
list = findViewById(R.id.list);
        mCursorAdapter = new PetCursorAdapter(this,null);
        list.setAdapter(mCursorAdapter);
        getSupportLoaderManager().initLoader(0,null,this);
        floatingButton();
      //  displayDataBaseInfo();

    }

    private void floatingButton() {
        FloatingActionButton button = findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        displayDataBaseInfo();
    }

    private void displayDataBaseInfo() {

        String[] projection = {PetContract.PetEntry._ID, PetContract.PetEntry.COLUMN_PET_NAME, PetContract.PetEntry.COLUMN_PET_BREED, PetContract.PetEntry.COLUMN_PET_GENDER, PetContract.PetEntry.COLUMN_PET_WEIGHT};

        Cursor cursor = getContentResolver().query(PetContract.PetEntry.CONTENT_URI, projection, null, null, null);

        ListView list = findViewById(R.id.list);
        PetCursorAdapter adapter = new PetCursorAdapter(this, cursor);
        list.setAdapter(adapter);
        View emptyView = findViewById(R.id.empty_view);
       list.setEmptyView(emptyView);
       // cursor.close();  // don"t work

    }

    @NonNull
    @Override
    public Loader onCreateLoader(int i, @Nullable Bundle bundle) {

        String[] projection = {PetContract.PetEntry._ID,
                PetContract.PetEntry.COLUMN_PET_NAME,
                PetContract.PetEntry.COLUMN_PET_BREED};

        return new CursorLoader(this ,PetContract.PetEntry.CONTENT_URI,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);
    }



    @Override
    public void onLoaderReset(@NonNull Loader loader) {
mCursorAdapter.swapCursor(null);
    }
}

