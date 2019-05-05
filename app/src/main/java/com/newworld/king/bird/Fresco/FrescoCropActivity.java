package com.newworld.king.bird.Fresco;

import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrescoCropActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.sdv_fresco_crop)
    SimpleDraweeView sdvFrescoCrop;
    @BindView(R.id.tv_fresco_explain)
    TextView tvFrescoExplain;
    @BindView(R.id.bt_fresco_center)
    Button btFrescoCenter;
    @BindView(R.id.bt_fresco_centercrop)
    Button btFrescoCentercrop;
    @BindView(R.id.bt_fresco_focuscrop)
    Button btFrescoFocuscrop;
    @BindView(R.id.bt_fresco_centerinside)
    Button btFrescoCenterinside;
    @BindView(R.id.bt_fresco_fitcenter)
    Button btFrescoFitcenter;
    @BindView(R.id.bt_fresco_fitstart)
    Button btFrescoFitstart;
    @BindView(R.id.bt_fresco_fitend)
    Button btFrescoFitend;
    @BindView(R.id.bt_fresco_fitxy)
    Button btFrescoFitxy;
    @BindView(R.id.bt_fresco_none)
    Button btFrescoNone;
    private GenericDraweeHierarchyBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_crop);
        ButterKnife.bind(this);


        toolbar.setTitle("图片的不同裁剪");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //展示顶部标题栏 toolBar 左侧默认 HomeAsUp 按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initData();
    }


    private void initData() {

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

    @OnClick({R.id.bt_fresco_center, R.id.bt_fresco_centercrop, R.id.bt_fresco_focuscrop, R.id.bt_fresco_centerinside, R.id.bt_fresco_fitcenter, R.id.bt_fresco_fitstart, R.id.bt_fresco_fitend, R.id.bt_fresco_fitxy, R.id.bt_fresco_none})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_fresco_center:
                // 居中，无缩放  // 设置描述
                tvFrescoExplain.setText("居中，无缩放");
                // 样式设置
                GenericDraweeHierarchy hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER).build();
                // 图片显示
                imageDisplay(hierarchy);
                break;

            case R.id.bt_fresco_centercrop:
                // 保持宽高比缩小或放大，使得两边都大于或等于显示边界。居中显示
                // 设置描述
                tvFrescoExplain.setText("保持宽高比缩小或放大，使得两边都大于或等于显示边界。居中显示.");
                // 样式设置
                hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP).build();
                // 图片显示
                imageDisplay(hierarchy);
                break;

            case R.id.bt_fresco_focuscrop:
                // 使两边都在显示边界内，居中显示。如果图尺寸大于显示边界，则保持长宽比缩小图片
                // 设置描述
                tvFrescoExplain.setText("同centerCrop, 但居中点不是中点，而是指定的某个点,这里我设置为图片的左上角那点");
                // 样式设置
                PointF point = new PointF(0, 0);
                hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP)
                        .setActualImageFocusPoint(point).build();
                // 图片显示
                imageDisplay(hierarchy);
                break;

            case R.id.bt_fresco_centerinside:
                // 使两边都在显示边界内，居中显示。如果图尺寸大于显示边界，则保持长宽比缩小图片
                // 设置描述
                tvFrescoExplain.setText("使两边都在显示边界内，居中显示。如果图尺寸大于显示边界，则保持长宽比缩小图片");
                // 样式设置
                hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build();
                // 图片显示
                imageDisplay(hierarchy);
                break;

            case R.id.bt_fresco_fitcenter:
                // 保持宽高比，缩小或者放大，使得图片完全显示在显示边界内。居中显示
                // 设置描述
                tvFrescoExplain.setText("保持宽高比，缩小或者放大，使得图片完全显示在显示边界内。居中显示");
                // 样式设置
                hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).build();
                // 图片显示
                imageDisplay(hierarchy);
                break;

            case R.id.bt_fresco_fitstart:
                // 保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，不居中，和显示边界左上对齐
                // 设置描述
                tvFrescoExplain.setText("保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，不居中，和显示边界左上对齐");
                // 样式设置
                hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).build();
                // 图片显示
                imageDisplay(hierarchy);
                break;

            case R.id.bt_fresco_fitend:
                // 保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，不居中，和显示边界右下对齐
                // 设置描述
                tvFrescoExplain.setText("保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，不居中，和显示边界右下对齐");
                // 样式设置
                hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_END).build();
                // 图片显示
                imageDisplay(hierarchy);
                break;

            case R.id.bt_fresco_fitxy:
                // 不保持宽高比，填充满显示边界
                // 设置描述
                tvFrescoExplain.setText("不保持宽高比，填充满显示边界");
                // 样式设置
                hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).build();
                // 图片显示
                imageDisplay(hierarchy);
                break;

            case R.id.bt_fresco_none:
                // 如要使用title mode显示, 需要设置为none
                // 设置描述
                tvFrescoExplain.setText("如要使用title mode显示, 需要设置为none");
                // 样式设置
                hierarchy = builder.setActualImageScaleType(null).build();
                // 图片显示
                imageDisplay(hierarchy);
                break;

        }
    }

    private void imageDisplay(GenericDraweeHierarchy hierarchy) {
        sdvFrescoCrop.setHierarchy(hierarchy);
        // 加载图片
        Uri uri = Uri.parse("http://img4q.duitang.com/uploads/item/201305/20/20130520115416_VrUUR.jpeg");
        sdvFrescoCrop.setImageURI(uri);
    }
}
