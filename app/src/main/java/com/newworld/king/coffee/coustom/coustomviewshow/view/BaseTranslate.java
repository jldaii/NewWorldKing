package com.newworld.king.coffee.coustom.coustomviewshow.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jldaii on 2017/7/6.
 * 位移画布
 */

public class BaseTranslate extends View {


    // 1.创建一个画笔
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint BluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint RedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
    }

    public BaseTranslate(Context context) {
        super(context);
    }
    // 3.在构造函数中初始化
    public BaseTranslate(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public BaseTranslate(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

// 在坐标原点绘制一个黑色圆形
        mPaint.setColor(Color.BLACK);
        canvas.translate(200,200);
        canvas.drawCircle(0,0,100,mPaint);

        // 在坐标原点绘制一个蓝色圆形
        mPaint.setColor(Color.BLUE);
        canvas.translate(200,200);
        canvas.drawCircle(0,0,100,mPaint);
    }
}
