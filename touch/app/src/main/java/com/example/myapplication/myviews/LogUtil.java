package com.example.myapplication.myviews;

import android.util.Log;
import android.view.MotionEvent;

public class LogUtil {
    public static void showLog(String tag,String msg){
        Log.i(tag,msg);
    }

    public static void show_events(String tag, MotionEvent evt, String fname){
        Log.i(tag,evt.getAction()+"----"+fname);
    }
}
