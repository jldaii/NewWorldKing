package com.newworld.king.bird.amap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AMapeSimpleActivity extends AppCompatActivity {


    @BindView(R.id.map)
    MapView mapView;
    @BindView(R.id.radio_ch)
    RadioButton radioCh;
    @BindView(R.id.radio_en)
    RadioButton radioEn;
    @BindView(R.id.check_language)
    RadioGroup checkLanguage;
    @BindView(R.id.basicmap)
    Button basicmap;
    @BindView(R.id.rsmap)
    Button rsmap;

    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amape_simple);
        ButterKnife.bind(this);

        mapView.onCreate(savedInstanceState);
        init();
    }


    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();

        }


        checkLanguage = (RadioGroup) findViewById(R.id.check_language);

        checkLanguage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_en) {
                    aMap.setMapLanguage(AMap.ENGLISH);
                } else {
                    aMap.setMapLanguage(AMap.CHINESE);
                }
            }
        });
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    @OnClick({R.id.basicmap, R.id.rsmap})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.basicmap:
                aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 矢量地图模式
                break;
            case R.id.rsmap:
                aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式
                break;
        }
    }
}
