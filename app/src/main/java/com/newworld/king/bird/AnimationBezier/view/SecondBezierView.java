package com.newworld.king.bird.AnimationBezier.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by jldaii on 2017/7/1.
 */

public class SecondBezierView  extends View {
    //起点坐标
    private float mStartPointX;
    private float mStartPointY;

    //终点坐标
    private float mEndPointX;
    private float mEndPointY;

    //控制点
    private float mFlagPointX;
    private float mFlagPointY;

    private Path mPath;  //初始化路径

    private Paint mPaintBezier;// 画笔
    private Paint mPaintFlag; //画笔绘制
    private Paint mPaintFlagText;


    public SecondBezierView(Context context) {
        super(context);
    }

    public SecondBezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaintBezier = new Paint(Paint.ANTI_ALIAS_FLAG); //初始化 （抗锯齿）
        mPaintBezier.setStrokeWidth(8);//线条大小
        mPaintBezier.setStyle(Paint.Style.STROKE);//风格 直线

        mPaintFlag = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintFlag.setStrokeWidth(3);//线条大小
        mPaintFlag.setStyle(Paint.Style.STROKE);//风格 直线

        mPaintFlagText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintFlagText.setStyle(Paint.Style.STROKE);//风格 直线
        mPaintFlagText.setTextSize(20);
    }

    public SecondBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //定义坐标点位置
        mStartPointX = w / 4;
        mStartPointY = h / 2 -200;

        mEndPointX = w * 3 / 4;
        mEndPointY = h/ 2 - 200;

        mFlagPointX = w/2;
        mFlagPointY = h/2 -300;

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制贝塞尔曲线
        mPath.reset();
        //移动到坐标点
        mPath.moveTo(mStartPointX,mStartPointY);

        mPath.quadTo(mFlagPointX,mFlagPointY,mEndPointX,mEndPointY);
        //绘制起点和控制点
        canvas.drawPoint(mStartPointX,mStartPointY,mPaintFlag);
        canvas.drawText("起点",mStartPointX,mStartPointY,mPaintFlagText); //坐标x，y 文字绘制对象
        //绘制终点和控制点
        canvas.drawPoint(mEndPointX,mEndPointY,mPaintFlag);
        canvas.drawText("终点",mEndPointX,mEndPointY,mPaintFlagText);
        //绘制控制点
        canvas.drawPoint(mFlagPointX,mFlagPointY,mPaintFlag);
        canvas.drawText("控制点",mFlagPointX,mFlagPointY,mPaintFlagText);

        //辅助线 开始坐标x,y 控制点坐标x，y ，画笔
        canvas.drawLine(mStartPointX,mStartPointY,mFlagPointX,mFlagPointY,mPaintFlag);
        canvas.drawLine(mEndPointX,mEndPointY,mFlagPointX,mFlagPointY,mPaintFlag);

        canvas.drawPath(mPath,mPaintBezier); //绘制路径 画笔
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){

            case MotionEvent.ACTION_MOVE:
                //将屏幕的坐标赋值给控制点
                mFlagPointX = event.getX();
                mFlagPointY = event.getY();
                invalidate(); //重新绘制  控制点移动后 重绘界面
                break;

        }

        return true;
    }
}
