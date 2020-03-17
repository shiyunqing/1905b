package com.example.myapplication.common;

import com.example.myapplication.apps.MyApp;

import java.io.File;

public class Constant {
    public static final String BASE_WAN_URL = "https://www.wanandroid.com/";
    public static final String BASE_SHOP_URL = "https://cdwan.cn/api/";
    public static final String PATH_DATA = MyApp.myApp.getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/shop";
    public static final String PRICE_MODEL = "$元起";
    public static String session_id;
    public static String token;
}
