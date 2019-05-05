package com.newworld.king.bird.AnimationAlpha;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlphaAnimationMainActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    Context context;
    @BindView(R.id.btn_Anima)

    Button btnAnima2;
    @BindView(R.id.btn_RotateMe)
    Button btnRotateMe;
    @BindView(R.id.btn_traslateMe)
    Button btnTraslateMe;
    @BindView(R.id.btn_scaleMe)
    Button btnScaleMe;
    @BindView(R.id.btn_AnimaMe)
    Button btnAnimaMe;
    @BindView(R.id.btn_AnimaMeToo)
    Button btnAnimaMeToo;

    //透明动画
    private AlphaAnimation alphaAnimation;
    //旋转动画
    private RotateAnimation rotateAnimation;
    //移动动画
    private TranslateAnimation translateAnimation;
    //缩放动画
    private ScaleAnimation scaleAnimation;
    //组合动画
    private AnimationSet animationSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha_animation_main);
        ButterKnife.bind(this);
        context = this;
    }

    @OnClick({R.id.btn_Anima, R.id.btn_RotateMe,
            R.id.btn_traslateMe, R.id.btn_scaleMe,
            R.id.btn_AnimaMe, R.id.btn_AnimaMeToo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_Anima:
//                AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1); //透明度 0-1
//                alphaAnimation.setDuration(1000); //设定动画时间
//                view.startAnimation(alphaAnimation); //动画效果指定给该视图view
                //xml实现
                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.aa));
                break;

            case R.id.btn_RotateMe:
//                float h = btnRotateMe.getHeight() / 2;
//                float w = btnRotateMe.getWidth() / 2; //获取view的宽高 然后取一半的值 传入 可以围绕中心旋转
//               rotateAnimation = new RotateAnimation(0,360,w,h); //从0开始 旋转360度
//                rotateAnimation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//                rotateAnimation.setDuration(1000);
//                view.startAnimation(rotateAnimation);
                //xml实现
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));
                break;

            case R.id.btn_traslateMe:
//                translateAnimation = new TranslateAnimation(0,200,0,200);
//                translateAnimation.setDuration(1000);
//                view.startAnimation(translateAnimation);
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate));
                break;

            case R.id.btn_scaleMe:
//                scaleAnimation = new ScaleAnimation(0,1,0,1, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//                scaleAnimation.setDuration(1000);
//                view.startAnimation(scaleAnimation);
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale));
//                view.clearAnimation();//清除动画效果
                break;

            case R.id.btn_AnimaMe:
                /*animationSet = new AnimationSet(true);
                animationSet.setDuration(1000);
                //透明动画
                alphaAnimation = new AlphaAnimation(0,1);
                alphaAnimation.setDuration(1000);
                //位移动画
                translateAnimation = new TranslateAnimation(200,0,200,0);
                translateAnimation.setDuration(1000);
                //旋转动画
                rotateAnimation = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                rotateAnimation.setDuration(1000);

                animationSet.addAnimation(alphaAnimation);
                animationSet.addAnimation(translateAnimation);
                animationSet.addAnimation(rotateAnimation);
                view.startAnimation(animationSet);*/
//                view.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anima));
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.anima);
                //动画监听效果
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        //开始的时候
                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //动画结束时
                        Toast.makeText(context, "动画结束", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        //重复时
                    }
                });
                view.startAnimation(animation);
                break;

            case R.id.btn_AnimaMeToo:

                CustomAnim customAnim = new CustomAnim();
                customAnim.setDuration(1000);
                btnAnimaMeToo.startAnimation(customAnim);
                break;

        }
    }

}
