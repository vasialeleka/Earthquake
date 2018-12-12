package com.e.vasialeleka.smarthome.Elements;

public class Socket {
    private String value;
    private  String temp;
    private String name;
    private String id;
    private  String group;
    private String room;

    public Socket(String value, String temp,String name) {
        this.name = name;
        this.value = value;
        this.temp = temp;


    }

    public Socket(String value, String temp, String name, String id) {
        this.value = value;
        this.temp = temp;
        this.name = name;
        this.id = id;
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
