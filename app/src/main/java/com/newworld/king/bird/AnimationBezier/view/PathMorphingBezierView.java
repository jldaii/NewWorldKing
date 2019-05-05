package com.newworld.king.bird.AnimationBezier.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jldaii on 2017/7/1.
 */

public class PathMorphingBezierView extends View implements View.OnClickListener{
    //起点坐标
    private float mStartPointX;
    private float mStartPointY;

    //终点坐标
    private float mEndPointX;
    private float mEndPointY;

    //控制点 1 one
    private float mFlagPointOneX;
    private float mFlagPointOneY;
    //控制点 2 two
    private float mFlagPointTwoX;
    private float mFlagPointTwoY;

    private Path mPath;  //初始化路径

    private Paint mPaintBezier;// 画笔
    private Paint mPaintFlag; //画笔绘制
    private Paint mPaintFlagText;


    private ValueAnimator mValueAnimator; //属性动画

    // 标示 是否多点触控
    private boolean isSecondPoint = false;

    public PathMorphingBezierView(Context context) {
        super(context);
    }

    public PathMorphingBezierView(Context context, @Nullable AttributeSet attrs) {
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

    public PathMorphingBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        mFlagPointOneX = mStartPointX;
        mFlagPointOneY =mStartPointY;
        mFlagPointTwoX = mEndPointX;
        mFlagPointTwoY = mEndPointY;

        mPath = new Path();

        mValueAnimator = ValueAnimator.ofFloat(mStartPointY,h); // 坐标点的变化 a-->b
//        mValueAnimator.setInterpolator(new BounceInterpolator()); //插值器
        mValueAnimator.setDuration(1000); //动画时间
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mFlagPointOneY = (float) mValueAnimator.getAnimatedValue();
                mFlagPointTwoY = (float) mValueAnimator.getAnimatedValue();

                invalidate();
            }
        });
        setOnClickListener(this);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制贝塞尔曲线
        mPath.reset();
        //移动到坐标点
        mPath.moveTo(mStartPointX,mStartPointY);

//        mPath.quadTo(mFlagPointOneX,mFlagPointOneY,mEndPointX,mEndPointY);  //二阶使用
        mPath.cubicTo(mFlagPointOneX,mFlagPointOneY,mFlagPointTwoX,mFlagPointTwoY,mEndPointX,mEndPointY);//三阶
        //绘制起点和控制点
        canvas.drawPoint(mStartPointX,mStartPointY,mPaintFlag);
        canvas.drawText("起点",mStartPointX,mStartPointY,mPaintFlagText); //坐标x，y 文字绘制对象
        //绘制终点和控制点
        canvas.drawPoint(mEndPointX,mEndPointY,mPaintFlag);
        canvas.drawText("终点",mEndPointX,mEndPointY,mPaintFlagText);
        //绘制控制点1
        canvas.drawPoint(mFlagPointOneX,mFlagPointOneY,mPaintFlag);
        canvas.drawText("控制点1",mFlagPointOneX,mFlagPointOneY,mPaintFlagText);
        //绘制控制点2
        canvas.drawPoint(mFlagPointTwoX,mFlagPointTwoY,mPaintFlag);
        canvas.drawText("控制点2",mFlagPointTwoX,mFlagPointTwoY,mPaintFlagText);
        //辅助线 开始坐标x,y 控制点坐标x，y ，画笔
        canvas.drawLine(mStartPointX,mStartPointY,mFlagPointOneX,mFlagPointOneY,mPaintFlag);
        //辅助线 控制点1 到 控制点2
        canvas.drawLine(mFlagPointOneX,mFlagPointOneY,mFlagPointTwoX,mFlagPointTwoY,mPaintFlag);
        // 辅助线 控制点1到终点 改成从 终点 到孔 控制点2
        canvas.drawLine(mEndPointX,mEndPointY,mFlagPointTwoX,mFlagPointTwoY,mPaintFlag);

        canvas.drawPath(mPath,mPaintBezier); //绘制路径 画笔
    }

    @Override
    public void onClick(View v) {
        mValueAnimator.start();
    }
}
