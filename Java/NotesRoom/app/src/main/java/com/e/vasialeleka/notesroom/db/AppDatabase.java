package com.e.vasialeleka.notesroom.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.e.vasialeleka.notesroom.Note;

@Database(entities = {Note.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
