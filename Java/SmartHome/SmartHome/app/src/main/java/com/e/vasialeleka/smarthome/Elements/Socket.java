package com.e.vasialeleka.smarthome.Elements;
import com.google.gson.annotations.SerializedName;

public class Socket {
@SerializedName("value")
    private String value;
    private  String temp;
    private String name;
    @SerializedName("id")
    private String id;
    @SerializedName("group")
    private  String group;
    private String room;






    public void setValue(String value) {
        this.value = value;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Socket(String value, String temp, String name, String id, String group, String room) {

        this.value = value;
        this.temp = temp;
        this.name = name;
        this.id = id;
        this.group = group;
        this.room = room;
    }

    public String getGroup() { return group; }

    public String getRoom() { return room; }

    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public  String getTemp() {
        return temp;
    }
}
