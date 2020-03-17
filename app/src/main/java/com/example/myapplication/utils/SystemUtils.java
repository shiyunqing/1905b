package com.example.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.DisplayMetrics;

import com.example.myapplication.apps.MyApp;

public class SystemUtils {

    public static boolean checkNetWork(){
        ConnectivityManager manager = (ConnectivityManager) MyApp.myApp.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null;
    }

    public static boolean isWifiConnected(){
        ConnectivityManager manager = (ConnectivityManager) MyApp.myApp.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return info != null;
    }

    public static boolean isMobileNetworkConnected(){
        ConnectivityManager manager = (ConnectivityManager) MyApp.myApp.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return info != null;
    }

    public static long getSystemTime(){
        return System.currentTimeMillis();
    }

    public static int getDpi(Activity at){
        DisplayMetrics dm = new DisplayMetrics();
        at.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.densityDpi;
    }

    public static String getPgName(Context context){
        return context.getPackageName();
    }

    public static Long getVersionCode(Context context, String pg){
        PackageInfo pgInfo = null;
        try {
            pgInfo = context.getPackageManager().getPackageInfo(pg,0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return Long.valueOf(pgInfo.versionCode);
        }else{
            return pgInfo.getLongVersionCode();
        }
    }


}
