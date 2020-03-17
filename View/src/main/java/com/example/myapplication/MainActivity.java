package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    int start_time = 9*60;
    int end_time = 17*60+30;
    MyTitles myTitles;
    MyImageView img;
    MyView viewCicle;
    int angle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();

    }


    private void initview(){
        myTitles = findViewById(R.id.myTitle);
        img = findViewById(R.id.img);
        viewCicle = findViewById(R.id.view_cicle);
        myTitles.setActivity(this);
        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        myTitles.setShared("分享","注册账号送积分","https://www.baidu.com",icon);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                startActivity(intent);
            }
        });

        handler.postDelayed(runnable,30);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(runnable,30);
            angle ++;
            angle = angle > 360 ? 1 : angle;
            viewCicle.setAngle(angle);
            viewCicle.invalidate();
        }
    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };
}
