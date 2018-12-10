package com.e.vasialeleka.jsom;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class General  {
    @SerializedName("data")
    List<Socket> list;

    public General(List<Socket> list) {
        this.list = list;
    }
}
