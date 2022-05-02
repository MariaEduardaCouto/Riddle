package com.app.riddle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.riddle.datasources.SessionDataSource;
import com.app.riddle.helpers.Callback;

public class MainActivity extends AppCompatActivity {

    TextView btn_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_Register=(TextView) findViewById(R.id.btn_Register);
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent intent = new Intent(MainActivity.this, Register.class);
                intent.setAction(Intent.ACTION_VIEW);
                MainActivity.this.startActivity(intent);

            }
        });


    }
}