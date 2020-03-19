package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.myapplication.myviews.LogUtil;
import com.example.myapplication.myviews.MyView;
import com.example.myapplication.myviews.MyViewGroup;

public class MainActivity extends AppCompatActivity {

    private static String TAG;

    private MyViewGroup myViewGroup;
    private MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TAG = this.getClass().getName();
        initView();
    }
    private void initView(){
        myViewGroup = findViewById(R.id.myViewGroup);
        myView = findViewById(R.id.myView);


        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("view click","click");
            }
        });
        myView.setLongClickable(true);
        myView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.show_events(TAG,ev,"dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
        //return true
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.show_events(TAG,event,"onTouchEvent");
        /*myView.performClick();
        myViewGroup.performClick();*/
        //return super.onTouchEvent(event);
        return true;
    }
}
