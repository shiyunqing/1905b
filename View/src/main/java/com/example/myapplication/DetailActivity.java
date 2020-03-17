package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;


public class DetailActivity extends AppCompatActivity {

    MyTitles myTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        myTitles = findViewById(R.id.myTitle);
        myTitles.setActivity(this);

        myTitles.setClick(new MyTitles.ITitleClick() {
            @Override
            public void titleClick(View view) {
                switch (view.getId()){
                    case R.id.txt_shared:

                        break;
                    case R.id.txt_return:

                        break;
                }
            }
        });
    }
}
