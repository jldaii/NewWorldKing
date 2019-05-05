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
 * 旋转
 */

public class BaseRotate extends View {

    /*
    旋转提供了两种方法：
    public void rotate (float degrees)
    public final void rotate (float degrees, float px, float py)
    和缩放一样，第二种方法多出来的两个参数依旧是控制旋转中心点的。
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
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
    }

    public BaseRotate(Context context) {
        super(context);
    }
    // 3.在构造函数中初始化
    public BaseRotate(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public BaseRotate(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        RectF rect = new RectF(0,-400,400,0);   // 矩形区域
        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect,mPaint);
        canvas.rotate(180);                     // 旋转180度 <-- 默认旋转中心为原点
        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect,mPaint);
    }
}
