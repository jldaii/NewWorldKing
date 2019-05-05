package com.newworld.king.coffee.coustom.coustomviewshow.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jldaii on 2017/7/6.
 */

public class BaseSkew extends View {

    /*
    skew这里翻译为错切，错切是特殊类型的线性变换。
    错切只提供了一种方法：
    public void skew (float sx, float sy)
    参数含义：
    float sx:将画布在x方向上倾斜相应的角度，sx倾斜角度的tan值，
    float sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值.
    变换后:
    X = x + sx * y
    Y = sy * x + y
     */


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
        mPaint.setStyle(Paint.Style.STROKE);  //设置画笔模式
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
    }

    public BaseSkew(Context context) {
        super(context);
    }
    // 3.在构造函数中初始化
    public BaseSkew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public BaseSkew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
// 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rect = new RectF(0,0,200,200);   // 矩形区域

        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect,mPaint);

        canvas.skew(1,0);                       // 水平错切
        canvas.skew(0,1);                       // 垂直错切

        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect,mPaint);
    }
}
