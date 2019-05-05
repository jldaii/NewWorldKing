package com.newworld.king.bird.AnimationLayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LayoutAnimActivity extends AppCompatActivity {


    @BindView(R.id.layout_animatest)
    LinearLayout layoutAnimatest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_anim);
        ButterKnife.bind(this);



        AnimationSet as = new AnimationSet(true);
        as.setDuration(800);

        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1);
        sa.setDuration(1000);

        RotateAnimation rotateAnimation = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(1000);

        as.addAnimation(sa);
        as.addAnimation(rotateAnimation);

        LayoutAnimationController loc = new LayoutAnimationController(as, 0.5f); //这里的0.5f 设定的是 布局子空间的动画时间 默认写0 就同时出现
        loc.setOrder(LayoutAnimationController.ORDER_NORMAL);//.ORDER_NORMAL 从上往下  .ORDER_REVERSE从下往上 .ORDER_RANDOM 随机
        layoutAnimatest.setLayoutAnimation(loc);

    }
}
