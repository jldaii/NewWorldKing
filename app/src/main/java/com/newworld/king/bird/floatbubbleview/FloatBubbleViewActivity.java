package com.newworld.king.bird.floatbubbleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.newworld.king.R;
import com.newworld.king.bird.floatbubbleview.view.BubbleDrawer;
import com.newworld.king.bird.floatbubbleview.view.FloatBubbleView;

public class FloatBubbleViewActivity extends AppCompatActivity {
    private FloatBubbleView mDWView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_bubble_view);
        //初始化操作
        initView();
        initData();
    }
    /**
     * 初始化View
     */
    private void initView() {
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        mDWView = (FloatBubbleView) findViewById(R.id.fbv_main);
    }

    /**
     * 初始化Data
     */
    private void initData() {
        //设置气泡绘制者
        BubbleDrawer bubbleDrawer = new BubbleDrawer(this);
        //设置渐变背景 如果不需要渐变 设置相同颜色即可
        bubbleDrawer.setBackgroundGradient(new int[]{0xffffffff, 0xffffffff});
        //给SurfaceView设置一个绘制者
        mDWView.setDrawer(bubbleDrawer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDWView.onDrawResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDWView.onDrawPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDWView.onDrawDestroy();
    }

}
