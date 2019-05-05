package com.newworld.king.bird.Fresco;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrescoGifAcitivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.sdv_fresco_gif)
    SimpleDraweeView sdvFrescoGif;
    @BindView(R.id.bt_fresco_askImg)
    Button btFrescoAskImg;
    @BindView(R.id.bt_fresco_stopAnim)
    Button btFrescoStopAnim;
    @BindView(R.id.bt_fresco_startAnim)
    Button btFrescoStartAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_gif_acitivity);
        ButterKnife.bind(this);


        toolbar.setTitle("Gif动画图片");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //展示顶部标题栏 toolBar 左侧默认 HomeAsUp 按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @OnClick(R.id.sdv_fresco_gif)
    public void onViewClicked() {
    }

    @OnClick({R.id.bt_fresco_askImg, R.id.bt_fresco_stopAnim, R.id.bt_fresco_startAnim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_fresco_askImg:
                // 请求gif图片
                Uri uri = Uri.parse("http://www.sznews.com/humor/attachement/gif/site3/20140902/4487fcd7fc66156f51db5d.gif");

                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(false)
                        .setOldController(sdvFrescoGif.getController())
                        .build();

                sdvFrescoGif.setController(controller);
                break;
            case R.id.bt_fresco_stopAnim:
                //  动画停止
                Animatable animatable = sdvFrescoGif.getController().getAnimatable();

                if (animatable != null && animatable.isRunning()) {
                    animatable.stop();
                }
                break;
            case R.id.bt_fresco_startAnim:
                // 动画开始
                Animatable mAnimatable = sdvFrescoGif.getController().getAnimatable();

                if (mAnimatable != null && !mAnimatable.isRunning()) {
                    mAnimatable.start();
                }
                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
