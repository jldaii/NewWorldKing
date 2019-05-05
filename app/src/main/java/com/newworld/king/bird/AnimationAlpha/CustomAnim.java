package com.newworld.king.bird.AnimationAlpha;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by jldaii on 2017/6/20.
 */

public class CustomAnim extends Animation {

    //需要知道目标对象的宽高  重写父类方法
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width,height,parentWidth,parentHeight);
    }

    @Override
    public void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime,t);

//        t.setAlpha(interpolatedTime);//设置透明度 interpolatedTime表示从0-1
//        t.getMatrix().setTranslate(200* interpolatedTime,200); // *interpolatedTime 时间

        t.getMatrix().setTranslate((float) (Math.sin(interpolatedTime*20)*50),0);
    }

}
