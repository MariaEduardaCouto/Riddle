package com.app.riddle.scenes.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.riddle.R;
import com.app.riddle.datasources.SessionDataSource;
import com.app.riddle.scenes.feed.FeedActivity;
import com.app.riddle.scenes.login.LoginActivity;
import com.app.riddle.scenes.register.Register;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    TextView btn_register_account;
    TextView btn_have_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_register_account=(Button) findViewById(R.id.btn_Sign);
        btn_register_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.this.navigateToRegister();

            }
        });

        btn_have_account= (TextView) findViewById(R.id.btn_have_account);
        btn_have_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               MainActivity.this.navigateToLogin();

            }
        });


    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        MainActivity.this.startActivity(intent);
    }

    @Override
    public void navigateToRegister() {
        Intent intent = new Intent(MainActivity.this, Register.class);
        intent.setAction(Intent.ACTION_VIEW);
        MainActivity.this.startActivity(intent);
    }

}