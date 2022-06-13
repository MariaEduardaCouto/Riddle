package com.app.riddle.scenes.create;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.riddle.R;
import com.app.riddle.datasources.AnonimousPostDataSource;
import com.app.riddle.models.AnonimousPostModel;
import com.app.riddle.scenes.feed.FeedActivity;
import com.app.riddle.scenes.feed.interfaces.IFeedActivity;
import com.app.riddle.scenes.login.LoginActivity;
import com.app.riddle.scenes.main.MainActivity;

public class CreatePostActivity extends AppCompatActivity  {

    ImageView close_post;
    private EditText txt_body;
    private EditText txt_color;
    private Button publish_btn;

    private String body = "";
    private String color = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);




        txt_body = (EditText) findViewById(R.id.txt_body);
        txt_color = (EditText) findViewById(R.id.txt_color);

        txt_color.setText("#c5c6d9");

        publish_btn = (Button) findViewById(R.id.publish_btn);
        close_post = (ImageView) findViewById(R.id.close_post);


        publish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check the user has filled somenthing

               if (!txt_body.getText().toString().equals("")){


                   String[] colors = {"#9EF246", "#FCCB58", "#E66C5D", "#B658FC","#55B3F2"};
                   String[] colorsNames = {"Green", "Orange", "Red", "Purple","Blue"};


                   AlertDialog.Builder builder = new AlertDialog.Builder(CreatePostActivity.this);
                   builder.setTitle("Pick a color");
                   builder.setItems(colorsNames, new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           // the user clicked on colors[which]

                           String hex = colors[which];



                           AnonimousPostModel post = new AnonimousPostModel(
                                   txt_body.getText().toString(),
                                   "title",
                                   hex);


                           AnonimousPostDataSource.shared.create(post);


                           CreatePostActivity.this.finish();


                       }
                   });
                   builder.show();
               }


            }
        });
        close_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatePostActivity.this.finish();
            }
        });



    }

   // close_post= (ImageView) findViewById(R.id.close_post);
     //   close_post.setOnClickListener(new View.OnClickListener() {
       // @Override
        //public void onClick(View view) {

          //  CreatePostActivity.this.navigateFeed();

     //   }
    //});


}


   // @Override
  //  public void navigateFeed() {
    //    Intent intent = new Intent(CreatePostActivity.this, FeedActivity.class);
      //  intent.setAction(Intent.ACTION_VIEW);
        //CreatePostActivity.this.startActivity(intent);
   // }

