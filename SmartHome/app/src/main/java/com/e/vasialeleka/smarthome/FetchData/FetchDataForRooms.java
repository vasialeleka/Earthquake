package com.e.vasialeleka.smarthome.FetchData;


import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.e.vasialeleka.smarthome.Adapters.SocketRecAdapter;
import com.e.vasialeleka.smarthome.Elements.Socket;
import com.e.vasialeleka.smarthome.Rooms.BedroomFragment;
import com.e.vasialeleka.smarthome.Rooms.HallFragment;
import com.e.vasialeleka.smarthome.Rooms.KitchenFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FetchDataForRooms extends AsyncTask<Void, Void, Void> {
    List<Fragment> fragments;
    List<Socket> socketList;
    List<Socket> socketHall;
    List<Socket> socketKitchen;
    List<Socket> socketBadroom;
    List<Socket> socketBathroom;
    HallFragment hall;
    BedroomFragment bedroom;
    KitchenFragment kitchen;
    RecyclerView res;
    RecyclerView resBedroom;
    RecyclerView resKitchen;
    String urls;
    String jsondata = null;

    String json = "{\"1\":{\"id\":\"1596347812\",\"room\":\"hall\",\"group\":\"socket\",\"name\":\"\\u041b\\u0430\\u043c\\u043f\\u0430\",\"value\":\"Enable\",\"temp\":\"29\"},\"2\":{\"id\":\"2222\",\"room\":\"bedroom\",\"group\":\"socket\",\"name\":\"\\u0422\\u0435\\u043b\\u0435\\u0444\\u043e\\u043d\",\"value\":\"Disable\",\"temp\":\"25\"},\"3\":{\"id\":\"3333\",\"room\":\"bedroom\",\"group\":\"socket\",\"name\":\"\\u041e\\u0431\\u0456\\u0433\\u0440\\u0456\\u0432\\u0430\\u0447\",\"value\":\"Disable\",\"temp\":\"27\"},\"4\":{\"id\":\"4444\",\"room\":\"bedroom\",\"group\":\"socket\",\"name\":\"\\u0422\\u0435\\u043b\\u0435\\u0432\\u0456\\u0437\\u043e\\u0440\",\"value\":\"Disable\",\"temp\":\"23\"},\"5\":{\"id\":\"5555\",\"room\":\"hall\",\"group\":\"socket\",\"name\":\"\\u041a\\u043e\\u043d\\u0434\\u0438\\u0446\\u0456\\u043e\\u043d\\u0435\\u0440\",\"value\":\"Disable\",\"temp\":\"24\"},\"6\":{\"id\":\"6666\",\"room\":\"bedroom\",\"group\":\"socket\",\"name\":\"\\u0411\\u0443\\u0434\\u0438\\u043b\\u044c\\u043d\\u0438\\u043a\",\"value\":\"Disable\",\"temp\":\"28\"},\"7\":{\"id\":\"666\",\"room\":\"kitchen\",\"group\":\"socket\",\"name\":\"\\u041a\\u043e\\u043c\\u0431\\u0430\\u0439\\u043d\",\"value\":\"Disable\",\"temp\":\"25\"},\"8\":{\"id\":\"2\",\"room\":\"kitchen\",\"group\":\"socket\",\"name\":\"\\u041a\\u0430\\u0432\\u043e\\u0432\\u0430\\u0440\\u043a\\u0430\",\"value\":\"Disable\",\"temp\":\"24\"},\"9\":{\"id\":\"321\",\"room\":\"kitchen\",\"group\":\"socket\",\"name\":\"\\u0422\\u0435\\u043b\\u0435\\u0432\\u0456\\u0437\\u043e\\u0440\",\"value\":\"Disable\",\"temp\":\"22\"},\"10\":{\"id\":\"0\",\"room\":\"kitchen\",\"group\":\"socket\",\"name\":\"\\u0425\\u043e\\u043b\\u043e\\u0434\\u0438\\u043b\\u044c\\u043d\\u0438\\u043a\",\"value\":\"Disable\",\"temp\":\"27\"},\"11\":{\"id\":\"0\",\"room\":\"kitchen\",\"group\":\"socket\",\"name\":\"\\u041c\\u0456\\u043a\\u0440\\u043e\\u0445\\u0432\\u0438\\u043b\\u044c\\u043e\\u0432\\u0430\",\"value\":\"Disable\",\"temp\":\"23\"},\"12\":{\"id\":\"2\",\"room\":\"kitchen\",\"group\":\"socket\",\"name\":\"\\u0427\\u0430\\u0439\\u043d\\u0438\\u043a\",\"value\":\"Disable\",\"temp\":\"28\"}}";


    public FetchDataForRooms(List<Fragment> fragments, String url) {
        this.fragments = fragments;
        this.urls = url;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        hall = (HallFragment) fragments.get(0);
        bedroom = (BedroomFragment) fragments.get(1);
        kitchen = (KitchenFragment) fragments.get(2);
        res = hall.getHallRecyclerView();
        resBedroom = bedroom.getBadroomRecyclerView();
        resKitchen = kitchen.getKitchenRecyclerView();

        SocketRecAdapter adapterKitchen = new SocketRecAdapter(kitchen.getContext(),socketKitchen);
        resKitchen.setLayoutManager(new LinearLayoutManager(kitchen.getActivity()));
        resKitchen.setAdapter(adapterKitchen);

        SocketRecAdapter adapterBedroom = new SocketRecAdapter(bedroom.getContext(), socketBadroom);
        resBedroom.setLayoutManager(new LinearLayoutManager(bedroom.getActivity()));
        resBedroom.setAdapter(adapterBedroom);

        SocketRecAdapter adapter = new SocketRecAdapter(hall.getContext(), socketHall);//socketList);
        res.setLayoutManager(new LinearLayoutManager(hall.getActivity()));
        res.setAdapter(adapter);


        Log.e("Update info", "Success");
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... voids) {
       /* try {
            URL url = new URL(urls);
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    if (line != null) jsondata = jsondata + line;
                }
                httpURLConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // httpURLConnection.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/


        socketHall = new ArrayList<>();
        socketBathroom = new ArrayList<>();
        socketKitchen = new ArrayList<>();
        socketBadroom = new ArrayList<>();


        try {
            //TODO when server will be on
            //JSONObject js = new JSONObject(jsondaata)
            JSONObject js = new JSONObject(json);
            for (int i = 1; i <= 12; i++) {
                JSONObject obj = js.getJSONObject("" + i);
                String id = obj.getString("id");
                String group = obj.getString("group");
                String room = obj.getString("room");
                String value = obj.getString("value");
                String temp = obj.getString("temp");
                String name = obj.getString("name");

                if (room.equals("hall") && group.equals("socket")) {
                    socketHall.add(new Socket(value, temp, name, id));
                } else if (room.equals("bedroom") && group.equals("socket")) {
                    socketBadroom.add(new Socket(value, temp, name, id));
                } else if (room.equals("kitchen") && group.equals("socket")) {
                    socketKitchen.add(new Socket(value, temp, name, id));
                } else if (room.equals("bathroom") && group.equals("socket")) {

                    socketBathroom.add(new Socket(value, temp, name, id));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
