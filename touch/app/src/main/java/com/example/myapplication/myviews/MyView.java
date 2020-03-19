package com.example.myapplication.myviews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;

public class MyView extends View {
    private static String TAG;

    public MyView(Context context) {
        super(context);
        initTag();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTag();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTag();
    }


    private void initTag(){
        TAG = this.getClass().getName();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtil.show_events(TAG,event,"dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.show_events(TAG,event,"onTouchEvent");
        //boolean bool = super.onTouchEvent(event);
        return true;
    }
}
