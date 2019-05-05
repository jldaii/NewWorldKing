package com.newworld.king.bird.AnimationBezier.view;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

import com.newworld.king.bird.AnimationBezier.BezierUtil;


/**
 * Created by jldaii on 2017/7/1.
 */

public class BezierEvaluator implements TypeEvaluator<PointF>{

    private PointF mFlagPoint;

    public BezierEvaluator(PointF FlagPoint) {
        mFlagPoint = FlagPoint;
    }

    @Override
    public PointF evaluate(float v, PointF pointF, PointF t1) {



        return BezierUtil.CalculateBezierPointForQuadratic(v,pointF,mFlagPoint,t1);

    }
}
