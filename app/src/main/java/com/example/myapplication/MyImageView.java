package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class MyImageView extends ImageView {

    private static final int  _left_length = 100;
    private static final int  _bar_length = 100;

    private String markBgColor;
    private String markWorkColor;
    private int markWorkSize;

    private Paint txtPaint;
    private Paint bgPaint;
    private Path txtPath;
    private Path bgPath;

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.myimage);
        markBgColor = typedArray.getString(R.styleable.myimage_mark_bg_color);
        markWorkColor = typedArray.getString(R.styleable.myimage_mark_word_color);
        markWorkSize = typedArray.getInteger(R.styleable.myimage_mark_word_size,10);

        txtPaint = new Paint();
        txtPaint.setTextSize(markWorkSize);
        txtPaint.setColor(Color.parseColor(markWorkColor));

        bgPaint = new Paint();
        bgPaint.setColor(Color.parseColor(markBgColor));
        bgPaint.setStyle(Paint.Style.FILL);


        txtPath = new Path();
        bgPath = new Path();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        countWH(getMeasuredWidth(),getMeasuredHeight());
        canvas.drawPath(bgPath,bgPaint);
        canvas.drawTextOnPath("New",txtPath,100,-20,txtPaint);

    }

    private void countWH(int widthSpec,int heightSpec){
        float x1,y1,x2,y2;
        int right = widthSpec;
        int top = 0;
        x1 = right-_left_length-_bar_length;
        x2 = right-_bar_length;
        y1 = top+_bar_length;
        y2 = top+_bar_length+_left_length;

        txtPath.reset();
        txtPath.moveTo(x1,top);
        txtPath.lineTo(right,y2);
        txtPath.close();

        bgPath.reset();
        bgPath.moveTo(x1,top);
        bgPath.lineTo(x2,top);
        bgPath.lineTo(right,y1);
        bgPath.lineTo(right,y2);
        bgPath.close();

    }
}
