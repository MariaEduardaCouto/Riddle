package com.app.riddle.scenes.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.riddle.R;
import com.app.riddle.datasources.SessionDataSource;
import com.app.riddle.helpers.Callback;
import com.app.riddle.scenes.feed.FeedActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class LoginActivity extends AppCompatActivity {


    private Button btn_login;
    private EditText input_email;
    private EditText input_password;

    private String email = "";
    private String password = "";

    FirebaseAuth auth;
    DatabaseReference dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn_login = (Button) findViewById(R.id.btn_login);
        input_email = (EditText) findViewById(R.id.input_email);
        input_password = (EditText) findViewById(R.id.input_password);

        input_email.setText("mariaeduardacoutt@gmail.com");
        input_password.setText("mariaeduardaom");

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 email = input_email.getText().toString();
                 password = input_password.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()){
                    loginUser();

                }else {
                    Toast.makeText(LoginActivity.this, "Complete all fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loginUser (){
        SessionDataSource.shared.login(email, password, new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
                Toast.makeText(LoginActivity.this,
                        "Welcome to Riddle!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, FeedActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                LoginActivity.this.startActivity(intent);

            }

            @Override
            public void onError(String error) {
                Toast.makeText(LoginActivity.this,
                        "It´s not possible to connect this user.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}