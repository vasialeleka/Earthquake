package com.e.vasialeleka.notesroom.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.e.vasialeleka.notesroom.Note;
import com.e.vasialeleka.notesroom.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<Note> notesList;
    private Context context;


    public Adapter(List<Note> notesList, Context context) {
        this.notesList = notesList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_note,viewGroup,false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.title.setText(notesList.get(i).getTitleOfNote());
        viewHolder.preview.setText(notesList.get(i).getPreviewNote());

    }



    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title,preview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_of_note);
            preview = itemView.findViewById(R.id.note_preview);
        }
    }
}
