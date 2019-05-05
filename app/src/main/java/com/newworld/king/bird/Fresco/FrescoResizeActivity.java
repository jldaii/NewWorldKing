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

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrescoResizeActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.sdv_fresco_resize)
    SimpleDraweeView sdvFrescoResize;
    @BindView(R.id.bt_fresco_resize)
    Button btFrescoResize;
    @BindView(R.id.bt_fresco_rotate)
    Button btFrescoRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_resize);
        ButterKnife.bind(this);


        toolbar.setTitle("图片缩放和旋转");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //展示顶部标题栏 toolBar 左侧默认 HomeAsUp 按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @OnClick({R.id.bt_fresco_resize, R.id.bt_fresco_rotate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 修内存中改图片大小
            case R.id.bt_fresco_resize:
                // 图片地址
                Uri uri = Uri.parse("http://c.hiphotos.baidu.com/image/pic/item/962bd40735fae6cd21a519680db30f2442a70fa1.jpg");
                // 图片的请求
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setResizeOptions(new ResizeOptions(50, 50))
                        .build();
                // 控制图片的加载
                PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                        .setOldController(sdvFrescoResize.getController())
                        .setImageRequest(request)
                        .build();
                // 加载图片
                sdvFrescoResize.setController(controller);
                break;
            case R.id.bt_fresco_rotate:

                Uri mUri = Uri.parse("http://c.hiphotos.baidu.com/image/pic/item/962bd40735fae6cd21a519680db30f2442a70fa1.jpg");
                ImageRequest mImageRequest = ImageRequestBuilder.newBuilderWithSource(mUri)
                        .setAutoRotateEnabled(true)
                        .build();
                // 控制图片的加载
                DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                        .setOldController(sdvFrescoResize.getController())
                        .setImageRequest(mImageRequest)
                        .build();
                // 加载图片
                sdvFrescoResize.setController(mDraweeController);
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
