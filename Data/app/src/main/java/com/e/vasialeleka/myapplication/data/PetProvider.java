package com.e.vasialeleka.myapplication.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PetProvider extends ContentProvider {
    public static final String LOG_TAG = PetProvider.class.getSimpleName();
    private PetDBhelper dBhelper;







    /** URI matcher code for the content URI for the pets table */
    private static final int PETS = 100;

    /** URI matcher code for the content URI for a single pet in the pets table */
    private static final int PET_ID = 101;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        // TODO: Add
         sUriMatcher.addURI(PetContract.CONTENT_AUTHORITY,PetContract.PATH_PETS,PETS);
        sUriMatcher.addURI(PetContract.CONTENT_AUTHORITY,PetContract.PATH_PETS+"/#",PET_ID);

    }



    @Override
    public boolean onCreate() {
         dBhelper = new PetDBhelper(getContext());
        // TODO: Create and initialize a PetDbHelper object to gain access to the pets database.
        // Make sure the variable is a global variable, so it can be referenced from other
        // ContentProvider methods.
        return true;
    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

Cursor cursor = null;
        SQLiteDatabase database = dBhelper.getReadableDatabase();
         int match = sUriMatcher.match(uri);
         switch (match){
             case PETS: cursor =database.query(PetContract.PetEntry.TABLE_NAME,projection,selection,selectionArgs,null,
                     null,sortOrder);
             break;
             case PET_ID:
                 selection = PetContract.PetEntry._ID+"=?";
                 selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                 cursor = database.query(PetContract.PetEntry.TABLE_NAME,projection,
                         selection,selectionArgs,null,null,sortOrder);
                 break;
         }

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        return 0;
    }
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * Returns the MIME type of data for the content URI.
     */
    @Override
    public String getType(Uri uri) {
        return null;
    }
}
