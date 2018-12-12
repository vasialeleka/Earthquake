package com.e.vasialeleka.smarthome;

import android.support.annotation.NonNull;
import android.widget.TextView;

import androidx.work.Data;
import androidx.work.Worker;

public class getLoginData extends Worker {
    String url;

TextView text;


    @NonNull
    @Override
    public Result doWork() {

url = getInputData().getString("URL","default");
        Data  output = new Data.Builder()
                .putString("vas","sadasd").build();
        setOutputData(output);

        return Result.SUCCESS;
    }


}
