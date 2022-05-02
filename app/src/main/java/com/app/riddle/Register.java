package com.app.riddle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.app.riddle.datasources.SessionDataSource;
import com.app.riddle.helpers.Callback;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    void register(){
        EditText email= this.findViewById(R.id.input_email);
        SessionDataSource.shared.signIn("tkmduda@gmail.com", "123456", new Callback() {
            @Override
            public void onSuccess(Object responseObject) {

            }

            @Override
            public void onError(String error) {

            }
        });
    }
}