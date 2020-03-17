package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MyTitles extends ConstraintLayout implements View.OnClickListener {

    TextView txtReturn;
    TextView txtTitle;
    TextView txtShared;
    Context  context;
    AppCompatActivity activity;

    private boolean shared_visible;
    private String title;
    private String shared_title;
    private String shared_content;
    private String shared_url;
    private Bitmap shared_icon;
    private ITitleClick clickFun;

    public MyTitles(Context context) {
        super(context);
    }

    public MyTitles(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public MyTitles(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    private void initView(Context context,AttributeSet attrs){
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.mytitle);
        shared_visible = typedArray.getBoolean(R.styleable.mytitle_title_shared_visible,false);
        title = typedArray.getString(R.styleable.mytitle_title_word);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_titles,null);
        addView(view,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        txtReturn = view.findViewById(R.id.txt_return);
        txtTitle = view.findViewById(R.id.txt_title);
        txtShared = view.findViewById(R.id.txt_shared);
        txtReturn.setOnClickListener(this);
        if(!TextUtils.isEmpty(title)){
            txtTitle.setText(title);
        }
        txtShared.setVisibility(shared_visible ? View.VISIBLE : View.GONE);
        if(shared_visible){
            txtShared.setOnClickListener(this);
        }
    }

    public void setActivity(AppCompatActivity activity){
        this.activity = activity;
    }

    public void setClick(ITitleClick click){
        this.clickFun = click;
    }

    public void setShared(String shared_title, String shared_content, String shared_url, Bitmap shared_icon){
        this.shared_title = shared_title;
        this.shared_content = shared_content;
        this.shared_url = shared_url;
        this.shared_icon = shared_icon;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_return:
                if(activity != null && !activity.isFinishing()){
                    activity.finish();
                    activity = null;
                }
                break;
            case R.id.txt_shared:
                Toast.makeText(context,"点击分享",Toast.LENGTH_SHORT).show();
                if(clickFun != null){
                    clickFun.titleClick(v);
                }
                break;
        }
    }

    interface ITitleClick{
        void titleClick(View view);
    }
}
