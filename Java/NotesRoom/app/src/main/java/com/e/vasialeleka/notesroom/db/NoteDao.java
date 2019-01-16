package com.e.vasialeleka.notesroom.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.e.vasialeleka.notesroom.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    List<Note> getAllUsers();

    @Insert
    void insertAll(Note... notes);
}
