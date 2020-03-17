package com.example.myapplication.ui;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.interfaces.test.TestConstract;
import com.example.myapplication.models.bean.ChaptersBean;
import com.example.myapplication.persenter.test.TestPersenter;

public class TestActivity extends BaseActivity<TestConstract.Persenter> implements TestConstract.View {
    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        persenter.getChapters();
    }

    @Override
    protected TestConstract.Persenter createPersenter() {
        return new TestPersenter();
    }

    @Override
    public void getChaptersReturn(ChaptersBean result) {
        Log.i("Test",result.getData().toString());

        Message msg = new Message();
        msg.what = 1;
        msg.obj = "error";
        handler.sendMessage(msg);
    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };
}
