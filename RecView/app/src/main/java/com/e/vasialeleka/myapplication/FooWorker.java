package com.e.vasialeleka.myapplication;

import android.support.annotation.NonNull;

import androidx.work.Worker;

class FooWorker extends Worker {
    String s;

    public FooWorker(String s) {
        this.s = s;
    }

    @NonNull
    @Override
    public Result doWork() {
        return Result.SUCCESS;
    }
}
