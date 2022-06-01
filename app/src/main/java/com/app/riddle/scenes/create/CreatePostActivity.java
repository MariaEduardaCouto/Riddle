package com.app.riddle.scenes.create;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.riddle.R;
import com.app.riddle.datasources.AnonimousPostDataSource;
import com.app.riddle.models.AnonimousPostModel;

public class CreatePostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);


        AnonimousPostModel post = new AnonimousPostModel("body gyu uuy u u yu u", "title");

        AnonimousPostDataSource.shared.create(post);
    }
}