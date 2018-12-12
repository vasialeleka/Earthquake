package com.e.vasialeleka.roomaplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    List<User> users;

    public UserAdapter(List<User> users) {
        this.users = users;
    }


    @Override
    public UserAdapter.ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.users_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( UserAdapter.ViewHolder viewHolder, int position) {
        viewHolder.firstName.setText(users.get(position).getFirstName());
        viewHolder.secondName.setText(users.get(position).getLastName());
        viewHolder.email.setText(users.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView firstName;
        public TextView secondName;
        public TextView email;

        public ViewHolder( View itemView) {
            super(itemView);
            secondName = itemView.findViewById(R.id.second);
            email= itemView.findViewById(R.id.email);
            firstName = itemView.findViewById(R.id.first);

        }
    }
}
