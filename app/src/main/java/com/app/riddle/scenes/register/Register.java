package com.app.riddle.scenes.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.riddle.R;
import com.app.riddle.datasources.SessionDataSource;
import com.app.riddle.helpers.Callback;
import com.app.riddle.scenes.feed.FeedActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {


    private EditText input_email;
    private EditText input_phone;
    private EditText input_password;
    private Button btn_register;

    // variable of user datas

    private String email = "";
    private String phone = "";
    private String password = "";

    FirebaseAuth auth;
    DatabaseReference dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        input_email = (EditText) findViewById(R.id.input_email);
        input_phone = (EditText) findViewById(R.id.input_phone);
        input_password = (EditText) findViewById(R.id.input_password);
        btn_register = (Button) findViewById(R.id.btn_register);

        input_email.setText("mariaeduardacoutt@gmail.com");
        input_password.setText("mariaeduardaom");
        input_phone.setText("556854545");

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = input_email.getText().toString();
                phone = input_phone.getText().toString();
                password = input_password.getText().toString();

                if (!email.isEmpty() && !phone.isEmpty() && !password.isEmpty()){

                    if(password.length() >= 6){
                        registerUser();
                    }else {
                        Toast.makeText(Register.this, "The password must be at least 6 characters long.", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(Register.this, "Complete all fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void registerUser() {


        SessionDataSource.shared.register(email, password, new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
                Toast.makeText(Register.this,
                        "Welcome to Riddle!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Register.this, FeedActivity.class);
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        Register.this.startActivity(intent);

            }

            @Override
            public void onError(String error) {
                Toast.makeText(Register.this,
                        "It´s not possible to connect this user.", Toast.LENGTH_SHORT).show();
            }
        });

       
    }
}