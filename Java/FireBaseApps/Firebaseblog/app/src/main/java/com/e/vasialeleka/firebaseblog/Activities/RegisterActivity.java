package com.e.vasialeleka.firebaseblog.Activities;


import android.content.Intent;

import android.net.Uri;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.e.vasialeleka.firebaseblog.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegisterActivity extends AppCompatActivity {
    ImageView imageView;
    static int REQUSETCODE = 1;
    private FirebaseAuth mAuth;
    Uri pincedUri;
    private EditText userName, userEmail, userPassword, userPassword2;
    private Button registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        imageView = findViewById(R.id.imageView);
        userEmail = findViewById(R.id.regMail);
        userName = findViewById(R.id.regName);
        userPassword = findViewById(R.id.regPassword);
        userPassword2 = findViewById(R.id.regPassword2);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.registration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = userName.getText().toString();
                final String mail = userEmail.getText().toString();
                final String password1 = userPassword.getText().toString();
                final String password2 = userPassword2.getText().toString();
                if (mail.isEmpty() || name.isEmpty() || password1.isEmpty() || password2.isEmpty()) {

                    showMessage("Please enter all fields");
                } else {
                    if (password1.equals(password2)) {

                        createUser(name, mail, password1);

                    } else {
                        showMessage("Incorrect confirm password");

                    }

                }
                //  Toast.makeText(RegisterActivity.this, "Registrarion", Toast.LENGTH_SHORT).show();
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openGallery();

            }
        });
    }

    private void createUser(final String name, String mail, String password) {

        mAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            showMessage("Account created!");
                            updateUserInfo(name, pincedUri, mAuth.getCurrentUser());
                        } else {
                            showMessage("Failed");
                        }
                    }
                });
    }

    private void updateUserInfo(final String name, final Uri pincedUri, final FirebaseUser currentUser) {
        if (pincedUri != null) {
            StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photo");
            final StorageReference imageFile = mStorage.child(pincedUri.getLastPathSegment());
            imageFile.putFile(pincedUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imageFile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .setPhotoUri(pincedUri)
                                    .build();
                            currentUser.updateProfile(changeRequest).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    showMessage("Registration complete!");
                                    updateUi();
                                }
                            });
                        }

                    });

                }
            });
        } else {
            UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build();
            currentUser.updateProfile(changeRequest).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    showMessage("Registration complete!");
                    updateUi();
                }
            });
        }
    }

    private void updateUi() {
        Intent intent = new Intent(RegisterActivity.this , HomeActivity.class);
        startActivity(intent);
    }

    private void showMessage(String s) {
        Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUSETCODE && data != null) {
            pincedUri = data.getData();
            imageView.setImageURI(pincedUri);


        }
    }

    private void openGallery() {
        //TODO open gallery Intent
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        //intent.setAction("image/*");
        startActivityForResult(intent, REQUSETCODE);

    }


}
