package com.newworld.king.bird.AnimationBezier.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by jldaii on 2017/7/1.
 */

public class WaveBezierView extends View implements View.OnClickListener{
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

    private int mWaveLength;
    //屏幕宽高
    private int mScreenHeight;
    private int mScreenWidth;
    private int mCenterY;

    private int mWaveCount;

    private ValueAnimator mValueAnimator; //属性动画

    private int mOffset;


    // 标示 是否多点触控
    private boolean isSecondPoint = false;

    public WaveBezierView(Context context) {
        super(context);
    }

    public WaveBezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaintBezier = new Paint(Paint.ANTI_ALIAS_FLAG); //初始化 （抗锯齿）
        mPaintBezier.setColor(Color.LTGRAY);
        mPaintBezier.setStrokeWidth(8);//线条大小
        mPaintBezier.setStyle(Paint.Style.FILL_AND_STROKE);//风格 直线

        mWaveLength = 800;

        mPaintFlag = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintFlag.setStrokeWidth(3);//线条大小
        mPaintFlag.setStyle(Paint.Style.STROKE);//风格 直线

        mPaintFlagText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintFlagText.setStyle(Paint.Style.STROKE);//风格 直线
        mPaintFlagText.setTextSize(20);
    }

    public WaveBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //定义坐标点位置
        mPath = new Path();

        setOnClickListener(this);
        mScreenHeight = h;
        mScreenWidth = w;
        mCenterY = h/2;
        mWaveCount = (int) Math.round( mScreenWidth/mWaveLength +1.5);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制贝塞尔曲线
        mPath.reset();
        //起点移动到最左边的起点
        mPath.moveTo(-mWaveLength + mOffset , mCenterY);
        for (int i = 0; i <mWaveCount ; i++) {
            mPath.quadTo(-mWaveLength * 3/4 + i * mWaveLength  + mOffset, mCenterY+60 , -mWaveLength/2 + i * mWaveLength  + mOffset, mCenterY);
            mPath.quadTo(-mWaveLength /4 + i * mWaveLength  + mOffset, mCenterY-60 ,i * mWaveLength  + mOffset, mCenterY);
        }
        mPath.lineTo(mScreenWidth,mScreenHeight);
        mPath.lineTo(0,mScreenHeight);
        mPath.close();
        canvas.drawPath(mPath,mPaintBezier); //绘制路径 画笔
    }

    @Override
    public void onClick(View v) {
        mValueAnimator = ValueAnimator.ofInt(0,mWaveLength);
        mValueAnimator.setDuration(1000);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE); //无限循环
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffset = (int) mValueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator.start();
    }
}
