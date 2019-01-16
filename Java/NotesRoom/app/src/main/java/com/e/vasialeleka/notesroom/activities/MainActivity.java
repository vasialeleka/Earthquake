package com.e.vasialeleka.notesroom.activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

import com.e.vasialeleka.notesroom.Note;
import com.e.vasialeleka.notesroom.R;
import com.e.vasialeleka.notesroom.db.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView textView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recView);
        textView = findViewById(R.id.count_of_notes);
        List<Note> note = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
            Note n = new Note();
            n.setTitleOfNote("Title" + i);
            n.setTextNote("Text  " + i + "fdfbdbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbadfbabavadvxz zx zx zx zv vz zzxfbzvzx");
            note.add(n);
        }


        textView = findViewById(R.id.count_of_notes);
        textView.setText("You have " + note.size() + " notes.");
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Notes")
                .allowMainThreadQueries().build();
        List<Note> n = db.noteDao().getAllUsers();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        com.e.vasialeleka.notesroom.adapter.Adapter adapter = new com.e.vasialeleka.notesroom.adapter.Adapter(note, this);
        recyclerView.setAdapter(adapter);


        floatingActionButton = findViewById(R.id.add_note);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                startActivity(intent);
            }
        });
    }
}
