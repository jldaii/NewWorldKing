package com.newworld.king.bird.Fresco;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrescoMainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.bt_fresco_spimg)
    Button btFrescoSpimg;
    @BindView(R.id.bt_fresco_crop)
    Button btFrescoCrop;
    @BindView(R.id.bt_fresco_circleAndCorner)
    Button btFrescoCircleAndCorner;
    @BindView(R.id.bt_fresco_jpeg)
    Button btFrescoJpeg;
    @BindView(R.id.bt_fresco_gif)
    Button btFrescoGif;
    @BindView(R.id.bt_fresco_multi)
    Button btFrescoMulti;
    @BindView(R.id.bt_fresco_listener)
    Button btFrescoListener;
    @BindView(R.id.bt_fresco_resize)
    Button btFrescoResize;
    @BindView(R.id.bt_fresco_modifyImg)
    Button btFrescoModifyImg;
    @BindView(R.id.bt_fresco_autoSizeImg)
    Button btFrescoAutoSizeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_main);
        ButterKnife.bind(this);


        toolbar.setTitle("Fresco");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //展示顶部标题栏 toolBar 左侧默认 HomeAsUp 按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @OnClick({R.id.bt_fresco_spimg, R.id.bt_fresco_crop, R.id.bt_fresco_circleAndCorner,
            R.id.bt_fresco_jpeg, R.id.bt_fresco_gif, R.id.bt_fresco_multi,
            R.id.bt_fresco_listener, R.id.bt_fresco_resize, R.id.bt_fresco_modifyImg,
            R.id.bt_fresco_autoSizeImg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_fresco_spimg:
                // 带进度条的图片
                startActivity(new Intent(this, FrescoSpimgActivity.class));
                break;
            case R.id.bt_fresco_crop:
                // 图片的不同裁剪
                startActivity(new Intent(this, FrescoCropActivity.class));
                break;
            case R.id.bt_fresco_circleAndCorner:
                // 圆形和圆角图片
                startActivity(new Intent(this, FrescoCircleAndCornerActivity.class));
                break;
            case R.id.bt_fresco_jpeg:
                // 渐进式展示图片
                startActivity(new Intent(this, FrescoJpegActivity.class));
                break;
            case R.id.bt_fresco_gif:
                // GIF动画图片
                startActivity(new Intent(this, FrescoGifAcitivity.class));
                break;
            case R.id.bt_fresco_multi:
                // 多图请求及图片复用
                startActivity(new Intent(this, FrescoMultiActivity.class));
                break;
            case R.id.bt_fresco_listener:
                // 图片加载监听
                startActivity(new Intent(this, FrescoListenerActivity.class));
                break;
            case R.id.bt_fresco_resize:
                // 图片修改和旋转
                startActivity(new Intent(this, FrescoResizeActivity.class));
                break;
            case R.id.bt_fresco_modifyImg:
                // 修改图片
                startActivity(new Intent(this, FrescoModifyActivity.class));
                break;
            case R.id.bt_fresco_autoSizeImg:
                // 动态展示图片
                startActivity(new Intent(this, FrescoAutoSizeActivity.class));
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
