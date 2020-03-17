package com.example.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {

    private int angle = 60;

    private int _w,_h;
    private String color;
    private int radius;
    private float fontSize;

    int cicle_x,cicle_y = 0;
    String loading = "0%";

    Paint paint;
    RectF rectF;
    Context context;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initview(context,attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview(context,attrs);
    }

    private void initview(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.mycicle);
        if(typedArray != null) {
            color = typedArray.getString(R.styleable.mycicle_cicle_color);
            float size = typedArray.getFloat(R.styleable.mycicle_cicle_loading_size, 12f);
            fontSize = Utils.sp2px(context, size);
            Log.i("fontSize:", String.valueOf(fontSize));
            paint = new Paint();
            paint.setColor(Color.parseColor(color));
            typedArray.recycle();
        }
    }

    public void setAngle(int angle){
        this.angle = angle;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        _w = getWidth();
        _h = getHeight();

        cicle_x = _w/2;
        cicle_y = _w/2;
        radius = _w/2;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#000000"));
        canvas.drawCircle(cicle_x,cicle_y,radius,paint);
        rectF = new RectF();
        rectF.set(0,0,_w,_h);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor(color));
        canvas.drawArc(rectF,-90,angle,true,paint);

        loading = (int) (((float)angle)/360f*100)+"%";
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor("#03A9F4"));
        paint.setTextSize(fontSize);
        int _tx=0,_ty = 0;
        Rect rect = getTextWH(loading);
        _tx = _w/2-rect.width()/2;
        _ty = _h/2+rect.height()/2;
        canvas.drawText(loading,_tx,_ty,paint);
    }
    private Rect getTextWH(String string){
        Rect rect = new Rect();
        paint.getTextBounds(string,0,string.length(),rect);
        return rect;
    }


}
