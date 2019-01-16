package com.e.vasialeleka.notesroom;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import javax.sql.StatementEvent;

@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String titleOfNote;
    @ColumnInfo(name = "noteText")
    private String textNote;
    private String previewNote;
    private int splitSymbol = 20;

    public void setPreviewNote(String previewNote) {
        this.previewNote = previewNote;
    }

    public void setSplitSymbol(int splitSymbol) {
        this.splitSymbol = splitSymbol;
    }

    public int getSplitSymbol() {
        return splitSymbol;
    }
/* public Note(String titleOfNote, String textNote) {

        this.titleOfNote = titleOfNote;
        this.textNote = textNote;
    }*/

    public int getId() {
        return id;
    }

    public String getTitleOfNote() {
        return titleOfNote;
    }

    public String getTextNote() {
        return textNote;
    }

    public String getPreviewNote() {
        return previewNote;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitleOfNote(String titleOfNote) {
        this.titleOfNote = titleOfNote;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
        String s = "";
        if (textNote.length() >= splitSymbol) {
            for (int i = 0; i <= splitSymbol - 3; i++) {
                s += textNote.charAt(i);
            }
            s += "...";

        } else {
            s = textNote;
        }
        this.previewNote = s;
    }
}
