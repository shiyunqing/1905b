package com.example.myapplication.models;

import android.util.Log;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.common.Constant;
import com.example.myapplication.models.api.ShopApi;
import com.example.myapplication.models.api.WanApi;
import com.example.myapplication.utils.SpUtils;
import com.example.myapplication.utils.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    private WanApi wanApi;

    private ShopApi shopApi;


    private static volatile HttpManager instance;
    public static HttpManager getInstance(){
        if(instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    private static Retrofit getRetrofit(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkhttpclient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    private static OkHttpClient getOkhttpclient(){
        File file = new File(Constant.PATH_CACHE);
        Cache cache = new Cache(file,100*1024*1024);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new LoggingInterceptor())
                .addNetworkInterceptor(new NetworkInterceptor())
                .cache(cache)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .cookieJar(cookieJar)
                .build();
        return client;
    }

    public WanApi getWanApi(){
        if(wanApi == null){
            wanApi = getRetrofit(Constant.BASE_WAN_URL).create(WanApi.class);
        }
        return wanApi;
    }

    public ShopApi getShopApi(){
        if(shopApi == null) shopApi = getRetrofit(Constant.BASE_SHOP_URL).create(ShopApi.class);
        return shopApi;
    }


    private synchronized <T> T getApi(String url,Class<T> cls){
        return getRetrofit(url).create(cls);
    }



    static class HeaderInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("Connection","keep-alive")
                    .addHeader("Client-Type","ANDROID")
                    .addHeader("X-Nideshop-Token", SpUtils.getInstance().getString("token"))
                    .build();
            return chain.proceed(request);
        }
    }

    static class LoggingInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Log.i("interceptor",String.format("Sending request %s on %s%n%s",request.url(),chain.connection(),request.headers()));

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.i("Received:",String.format("Received response for %s in %.1fms%n%s",response.request().url(),(t2-t1)/1e6d,response.headers()));
            if(response.header("session_id") != null){
                Constant.session_id = response.header("session_id");
            }
            return response;
        }
    }

    static class NetworkInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if(!SystemUtils.checkNetWork()){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if(!SystemUtils.checkNetWork()){
                int maxAge = 0;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public ,max-age="+maxAge).build();
            }else{
                int maxStale = 60*60*24*28;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public, onlyif-cached, max-stale="+maxStale).build();
            }
        }
    }

    private static CookieJar cookieJar = new CookieJar() {

        private final Map<String, List<Cookie>> cookieMap = new HashMap<String,List<Cookie>>();

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            String host = url.host();
            List<Cookie> cookieList = cookieMap.get(host);
            if(cookieList != null){
                cookieMap.remove(host);
            }
            cookieMap.put(host,cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookieList = cookieMap.get(url.host());
            return cookieList != null ? cookieList : new ArrayList<Cookie>();
        }
    };
}
