package com.newworld.king.bird.Fresco;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrescoModifyActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.sdv_fresco_modify)
    SimpleDraweeView sdvFrescoModify;
    @BindView(R.id.bt_fresco_modify)
    Button btFrescoModify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_modify);
        ButterKnife.bind(this);

        toolbar.setTitle("修改图片");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //展示顶部标题栏 toolBar 左侧默认 HomeAsUp 按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @OnClick(R.id.bt_fresco_modify)
    public void onViewClicked() {

        // 图片地址
        Uri uri = Uri.parse("http://c.hiphotos.baidu.com/image/pic/item/962bd40735fae6cd21a519680db30f2442a70fa1.jpg");

        // 修改图片
        Postprocessor postProcessor = new BasePostprocessor() {
            @Override
            public String getName() {
                return "postProcessor";
            }

            @Override
            public void process(Bitmap bitmap) {

                for (int x = 0; x < bitmap.getWidth(); x += 2) {

                    for (int y = 0; y < bitmap.getHeight(); y += 2) {
                        bitmap.setPixel(x, y, Color.RED);
                    }
                }
            }
        };

        // 创建图片请求
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setPostprocessor(postProcessor)
                .build();

        // 控制加载
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setOldController(sdvFrescoModify.getController())
                .setImageRequest(request)
                .build();

        // 加载图片
        sdvFrescoModify.setController(controller);

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
