package com.app.riddle.scenes.boot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.app.riddle.R;
import com.app.riddle.datasources.SessionDataSource;
import com.app.riddle.scenes.feed.FeedActivity;
import com.app.riddle.scenes.main.MainActivity;
import com.google.firebase.FirebaseApp;

public class BootActivity extends AppCompatActivity implements IBootActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);
        FirebaseApp.initializeApp(this);


        SessionDataSource.shared.signOut();

        if(SessionDataSource.shared.isUserLogedIn()){
            BootActivity.this.navigateToFeed();
        }else{
            BootActivity.this.navigateToMain();
        }

    }

    @Override
    public void navigateToMain() {
        Intent intent = new Intent(BootActivity.this, MainActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        BootActivity.this.startActivity(intent);
    }

    @Override
    public void navigateToFeed() {
        Intent intent = new Intent(BootActivity.this, FeedActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        BootActivity.this.startActivity(intent);
    }
}