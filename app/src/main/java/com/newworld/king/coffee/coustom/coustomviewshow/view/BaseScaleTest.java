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
 * 缩放
 */

public class BaseScaleTest extends View {

    /*
    缩放提供了两个方法，如下：
    public void scale (float sx, float sy)
    public final void scale (float sx, float sy, float px, float py)
    这两个方法中前两个参数是相同的分别为x轴和y轴的缩放比例。而第二种方法比前一种多了两个参数，用来控制缩放中心位置的。
    取值范围(n)	说明
    [-∞, -1)	先根据缩放中心放大n倍，再根据中心轴进行翻转
    -1	        根据缩放中心轴进行翻转
    (-1, 0)	    先根据缩放中心缩小到n，再根据中心轴进行翻转
    0	        不会显示，若sx为0，则宽度为0，不会显示，sy同理
    (0, 1)	    根据缩放中心缩小到n
    1	        没有变化
    (1, +∞)	    根据缩放中心放大n倍
     */

    // 宽高
    private int mWidth, mHeight;

    // 1.创建一个画笔
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint BluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint RedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
    }

    public BaseScaleTest(Context context) {
        super(context);
    }
    // 3.在构造函数中初始化
    public BaseScaleTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();

    }

    public BaseScaleTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rect = new RectF(-400,-400,400,400);   // 矩形区域

        for (int i=0; i<=20; i++)
        {
            canvas.scale(0.9f,0.9f);
            canvas.drawRect(rect,mPaint);
        }

    }
}
