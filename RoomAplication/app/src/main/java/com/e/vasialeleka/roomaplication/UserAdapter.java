package com.e.vasialeleka.roomaplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    ArrayList<String> users;

    public UserAdapter(ArrayList<String> users) {
        this.users = users;
    }


    @Override
    public UserAdapter.ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.users_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( UserAdapter.ViewHolder viewHolder, int position) {
       // viewHolder.firstName.setText(users.get(position));
        viewHolder.firstName.setText("fgd");
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView firstName;

        public ViewHolder( View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.first);

        }
    }
}
