package com.app.riddle.scenes.feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.app.riddle.R;
import com.app.riddle.scenes.create.CreatePostActivity;
import com.app.riddle.scenes.feed.interfaces.IFeedActivity;
import com.app.riddle.scenes.feed.interfaces.IFeedActivityPresenter;
import com.app.riddle.scenes.login.LoginActivity;
import com.app.riddle.scenes.main.MainActivity;

public class FeedActivity extends AppCompatActivity implements IFeedActivity {

    private FeedActivityPresenter presenter;
    ImageView add_post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        this.presenter = new FeedActivityPresenter(FeedActivity.this);
        this.presenter.fetchFeed();


        /*ABRIR MODAL MENU*/
       /* final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });*/

        add_post= (ImageView) findViewById(R.id.add_post);
        add_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FeedActivity.this.navigateNewPost();

            }
        });

    }

    @Override
    public void navigateNewPost() {
        Intent intent = new Intent(FeedActivity.this, CreatePostActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        FeedActivity.this.startActivity(intent);
    }

    @Override
    public void showSpinner() {

    }

    @Override
    public void hideSpinner() {

    }

    @Override
    public void presentContent() {
        FeedActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((GridView) FeedActivity.this.findViewById(R.id.list))
                        .setAdapter(FeedActivity.this.presenter);
            }
        });
    }

    @Override
    public void presentError(String error) {

    }

    @Override
    public void navigateToCreate() {

    }
}