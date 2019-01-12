package com.e.vasialeleka.firebaseblog.Activities;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.vasialeleka.firebaseblog.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText emailLogin, passwordLogin;
    private FirebaseAuth mAuth;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailLogin = findViewById(R.id.login_mail);
        passwordLogin = findViewById(R.id.login_password);
        mAuth = FirebaseAuth.getInstance();
        intent = new Intent(LoginActivity.this, Home.class);
        Button regButton = findViewById(R.id.registration_button);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentReg);
                finish();
            }
        });
        Button logInButton = findViewById(R.id.login_button);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailLogin.getText().toString();
                final String password = passwordLogin.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    showMessage("Please verify all fields!");
                } else {
                    signIn(email, password);

                }
            }
        });
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    updateUi();
                }
            }
        });
    }

    private void updateUi() {
        startActivity(intent);
        finish();
    }

    private void showMessage(String s) {
        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            updateUi();
        }

    }
}
