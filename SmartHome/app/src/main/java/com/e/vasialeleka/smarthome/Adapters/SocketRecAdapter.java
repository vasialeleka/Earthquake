package com.e.vasialeleka.smarthome.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.vasialeleka.smarthome.Elements.Socket;
import com.e.vasialeleka.smarthome.R;
import com.e.vasialeleka.smarthome.Variables;

import java.util.List;

public class SocketRecAdapter extends RecyclerView.Adapter<SocketRecAdapter.MyViewHolder> {

    Context context;
    List<Socket> list;

    public SocketRecAdapter(Context context, List<Socket> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.socket_list, viewGroup, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);

        myViewHolder.swich_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, String.valueOf(myViewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        });
        return myViewHolder;

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull SocketRecAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.temp.setText(list.get(i).getTemp());
        int tem = Integer.parseInt(list.get(i).getTemp());
        if (tem > 20) {myViewHolder.temp.setTextColor(Color.RED);} else {
        //    myViewHolder.temp.setLinkTextColor(R.color.goodTemp);
            myViewHolder.temp.setTextColor(ContextCompat.getColor(context,R.color.goodTemp));
        }
        myViewHolder.name.setText(list.get(i).getName());
        String status = list.get(i).getValue();
        if (status.equals(Variables.enable)) {
            myViewHolder.socket.setBackgroundColor(Color.GREEN);
        } else {
            myViewHolder.socket.setBackgroundColor(Color.RED);

        }
    }

    @Override
    public int getItemCount() {
        int f = list.size();
        String sf;
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView temp;
        private ImageView socket;
        private Button swich_button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            swich_button = itemView.findViewById(R.id.swich_button);
            name = itemView.findViewById(R.id.name);
            temp = itemView.findViewById(R.id.TempSocket);
            socket = itemView.findViewById(R.id.socket);


        }
    }

}
