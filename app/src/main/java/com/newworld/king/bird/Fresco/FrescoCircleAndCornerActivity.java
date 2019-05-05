package com.newworld.king.bird.Fresco;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrescoCircleAndCornerActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.sdv_fresco_circleandcorner)
    SimpleDraweeView sdvFrescoCircleandcorner;
    @BindView(R.id.bt_fresco_circle)
    Button btFrescoCircle;
    @BindView(R.id.bt_fresco_corner)
    Button btFrescoCorner;
    private Uri uri;
    private GenericDraweeHierarchyBuilder builder;
    private RoundingParams parames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_circle_and_corner);
        ButterKnife.bind(this);


        toolbar.setTitle("圆形和圆角图片");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //展示顶部标题栏 toolBar 左侧默认 HomeAsUp 按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initData();
    }

    private void initData() {

        uri = Uri.parse("http://img4q.duitang.com/uploads/item/201304/27/20130427043538_wAfHC.jpeg");

        builder = new GenericDraweeHierarchyBuilder(getResources());
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

    @OnClick({R.id.bt_fresco_circle, R.id.bt_fresco_corner})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_fresco_circle:
                // 设置圆形图片
                parames = RoundingParams.asCircle();
                GenericDraweeHierarchy hierarchy = builder.setRoundingParams(parames).build();
                sdvFrescoCircleandcorner.setHierarchy(hierarchy);
                sdvFrescoCircleandcorner.setImageURI(uri);
                break;

            case R.id.bt_fresco_corner:
                parames = RoundingParams.fromCornersRadius(50f);
                parames.setOverlayColor(getResources().getColor(android.R.color.holo_red_light));//覆盖层
                parames.setBorder(getResources().getColor(android.R.color.holo_blue_light), 5);//边框
                hierarchy = builder.setRoundingParams(parames).build();
                sdvFrescoCircleandcorner.setHierarchy(hierarchy);
                // 加载图片
                sdvFrescoCircleandcorner.setImageURI(uri);
                break;
        }
    }
}
