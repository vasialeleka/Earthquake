package com.e.vasialeleka.jsom;

import com.google.gson.annotations.SerializedName;

public class Socket {
    @SerializedName("temp")
     String temp;
    @SerializedName("value")
     String value;
    @SerializedName("type")
     String type;

    public Socket(String temp, String value, String type) {
        this.temp = temp;
        this.value = value;
        this.type = type;
    }

}
