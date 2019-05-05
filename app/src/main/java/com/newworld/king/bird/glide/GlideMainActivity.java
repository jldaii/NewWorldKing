package com.newworld.king.bird.glide;

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
import com.newworld.king.bird.eventbus.EventBusActivity;
import com.newworld.king.bird.eventbus.EventBusSendActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GlideMainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.btn_glide_base)
    Button btnGlideBase;
    @BindView(R.id.btn_glade_recyvlerview)
    Button btnGladeRecyvlerview;
    @BindView(R.id.btn_glide_tranfromations)
    Button btnGlideTranfromations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_main);
        ButterKnife.bind(this);

        toolbar.setTitle("Glide");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //展示顶部标题栏 toolBar 左侧默认 HomeAsUp 按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @OnClick({R.id.btn_glide_base, R.id.btn_glade_recyvlerview, R.id.btn_glide_tranfromations})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //基本使用
            case R.id.btn_glide_base:
                startActivity(new Intent(this, GlideBaseActivity.class));
                break;
            //recyvler使用
            case R.id.btn_glade_recyvlerview:
                startActivity(new Intent(this, GlideRecyclerViewActivity.class));
                break;
            //图片变换
            case R.id.btn_glide_tranfromations:
                startActivity(new Intent(this, GlideTranfromationsActivity.class));
                break;
        }
    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
