package com.e.vasialeleka.smarthome.SendData;

import com.e.vasialeleka.smarthome.Elements.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CreateJson {
    public List<Socket> listFirst;
    public List<Socket> listSecond;
    public List<Socket> listGeneral;
    String key;


    public CreateJson(String key, List<Socket> listFirst, List<Socket> listSecond) {
        this.key = key;
        this.listFirst = listFirst;
        this.listSecond = listSecond;
    }

    public JSONObject createJson() {
        //listGeneral.addAll(listKitchen);
        //listGeneral.addAll(listBedRoom);

        listGeneral = checkPosition(listFirst, listSecond);
        JSONObject jsonObject = new JSONObject();
        //checkPosition(listFirst, listSecond);
        //TODO send api key
        try {
            jsonObject.accumulate("user", key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= listGeneral.size(); i++) {
            JSONObject number = new JSONObject();
            try {
                number.accumulate("id", listGeneral.get(i - 1).getId());
                number.accumulate("value", listGeneral.get(i - 1).getValue());
                number.accumulate("room", listGeneral.get(i - 1).getRoom());

                jsonObject.accumulate("" + i, number);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonObject;
    }

    private List<Socket> checkPosition(List<Socket> listFirst, List<Socket> listSecond) {
        List<Socket> listReturn = new ArrayList<>();
        try {


            if (listFirst.get(0).getRoom().equals("kitchen") && listSecond.get(0).getRoom().equals("bedroom")) {
                listReturn.addAll(listSecond);
                listReturn.addAll(listFirst);

            } else if (listFirst.get(0).getRoom().equals("bedroom") && listSecond.get(0).getRoom().equals("kitchen")) {
                listReturn.addAll(listFirst);
                listReturn.addAll(listSecond);

            }
        } catch (NullPointerException e) {

        }
        return listReturn;
    }
}

