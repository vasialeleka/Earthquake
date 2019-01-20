package com.e.vasialeleka.heroretofitapp;

import com.google.gson.annotations.SerializedName;

public class Hero {
    @SerializedName("name")
    private String name;

    @SerializedName("realname")
    private String realName;

    @SerializedName("firstappearance")
    private String firstAppearance;

    @SerializedName("createdby")
    private String createdBy;

    @SerializedName("published")
    private String published;

    @SerializedName("imageurl")
    private String imageUrl;

    @SerializedName("bio")
    private String bio;

    public String getName() {
        return name;
    }

    public String getRealName() {
        return realName;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getPublished() {
        return published;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBio() {
        return bio;
    }
}
