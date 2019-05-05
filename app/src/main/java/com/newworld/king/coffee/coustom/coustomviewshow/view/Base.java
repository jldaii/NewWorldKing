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
 */

public class Base extends View {

    // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    // 饼状图初始绘制角度
    private float mStartAngle = 0;
    // 数据
//    private ArrayList<PieData> mData;
    // 宽高
    private int mWidth, mHeight;

    // 1.创建一个画笔
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint BluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint RedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
    }

    public Base(Context context) {
        super(context);
    }
    // 3.在构造函数中初始化
    public Base(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public Base(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
