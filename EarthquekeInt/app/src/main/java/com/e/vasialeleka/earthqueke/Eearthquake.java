package com.e.vasialeleka.earthqueke;

import java.text.DecimalFormat;

public class Eearthquake {
    private double mag;
    private String place;
    long date;
    private String url;

    public Eearthquake(double mag,String place,long date,String url){
        this.mag=mag;
        this.place = place;
        this.date=date;
        this.url= url;
    }


    public String getUrl() {
        return url;
    }

    public String getPlace() {

        return place;
    }


    public long getDate() {



        return date;
    }
public double getmag(){return mag;}


public String getMag() {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");

        return magnitudeFormat.format(mag);
    }
}
