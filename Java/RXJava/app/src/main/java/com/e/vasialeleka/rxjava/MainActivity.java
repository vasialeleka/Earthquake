package com.e.vasialeleka.rxjava;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity {
    private EditText login;
    private EditText password;
    private Button start;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        start = findViewById(R.id.button);
        start.setEnabled(false);
        Observable<String> loginObserfvable= RxEditText.getTextWatcherObservable(login);
        Observable<String> passwordObserfvable= RxEditText.getTextWatcherObservable(password);

        Observable.combineLatest(loginObserfvable,
                passwordObserfvable, new BiFunction<String, String, Boolean>() {
                    @Override
                    public Boolean apply(String s, String s2) throws Exception {
                        if (s.isEmpty()|| s2.isEmpty())
                            return false;
                        else return true;
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
start.setEnabled(aBoolean);
            }
        });
    }
}
