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

public class Basics extends View {

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
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px


        BluePaint.setColor(Color.BLUE);       //设置画笔颜色
        BluePaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        BluePaint.setStrokeWidth(10f);         //设置画笔宽度为10px


        RedPaint.setColor(Color.RED);       //设置画笔颜色
        RedPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        RedPaint.setStrokeWidth(10f);         //设置画笔宽度为10px

        /*
        STROKE                //描边
        FILL                  //填充
        FILL_AND_STROKE       //描边加填充
         */
    }

    public Basics(Context context) {
        super(context);
    }
    // 3.在构造函数中初始化
    public Basics(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public Basics(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawColor(Color.RED); //绘制颜色是填充整个画布，常用于绘制底色。

        canvas.drawPoint(200, 200, mPaint);     //在坐标(200,200)位置绘制一个点
        canvas.drawPoints(new float[]{          //绘制一组点，坐标位置由float数组指定
                500,500,
                500,600,
                500,700
        },mPaint);


        canvas.drawLine(400,500,500,600,mPaint);    // 在坐标(300,300)(500,600)之间绘制一条直线
        canvas.drawLines(new float[]{               // 绘制一组线 每四数字(两个点的坐标)确定一条线
                100,200,200,200,
                100,300,200,300
        },mPaint);

        //绘制矩形
        // 第一种
//        canvas.drawRect(100,100,800,400,mPaint); //绘制矩形  左上角（100,100） 右下角（800,400）

        // 第二种
//        Rect rect = new Rect(150,150,400,400);
//        canvas.drawRect(rect,BluePaint);

        // 第三种
//        RectF rectF = new RectF(200,200,300,300);
//        canvas.drawRect(rectF,RedPaint);

        //绘制圆角矩形
        // 第一种
//        RectF rectFr = new RectF(100,100,800,400);
//        canvas.drawRoundRect(rectFr,30,30,mPaint);  //30,30 表示的是在角落处 绘制圆的半径rx，ry
        // 第二种
//        canvas.drawRoundRect(100,100,800,400,30,30,mPaint);

        // 绘制矩形
//        RectF rectF = new RectF(100,100,800,400);
//        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF,mPaint);
//        // 绘制圆角矩形
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRoundRect(rectF,700,400,mPaint);


        // 绘制椭圆第一种
//        RectF rectF = new RectF(100,100,800,400);
//        canvas.drawOval(rectF,mPaint);
        // 绘制椭圆第二种
//        canvas.drawOval(100,100,800,400,mPaint);

        //绘制原型
//        canvas.drawCircle(500,500,400,mPaint);  // 绘制一个圆心坐标在(500,500)，半径为400 的圆。

        /*
        绘制圆弧：
        绘制圆弧就比较神奇一点了，为了理解这个比较神奇的东西，我们先看一下它需要的几个参数：
        // 第一种
        public void drawArc(@NonNull RectF oval, float startAngle, float sweepAngle, boolean useCenter, @NonNull Paint paint){}
        // 第二种
        public void drawArc(float left, float top, float right, float bottom, float startAngle,
                    float sweepAngle, boolean useCenter, @NonNull Paint paint) {}
        从上面可以看出，相比于绘制椭圆，绘制圆弧还多了三个参数：
        startAngle  // 开始角度
        sweepAngle  // 扫过角度
        useCenter   // 是否使用中心
         */

//        RectF rectF = new RectF(100,100,600,600);
//        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF,mPaint);
//        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF,0,90,false,mPaint); //false 表示不过中心点  绘制的是圆弧

        //-------------------------------------

//        RectF rectF2 = new RectF(100,700,600,1200);
//        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF2,mPaint);
//        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF2,0,90,true,mPaint); // true 表示过中心点 绘制的是 扇形

        //画笔的三个主题 STROKE 描边    FILL 填充    FILL_AND_STROKE 描边加填充
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(40);     //为了实验效果明显，特地设置描边宽度非常大
        // 描边
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(200,200,100,paint);
        // 填充
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200,500,100,paint);
        // 描边加填充
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(200, 800, 100, paint);


    }
}
