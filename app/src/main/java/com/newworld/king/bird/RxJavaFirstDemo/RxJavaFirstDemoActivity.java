package com.newworld.king.bird.RxJavaFirstDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;


public class RxJavaFirstDemoActivity extends AppCompatActivity {

    private final String url = "http://www.iamxiarui.com/wp-content/uploads/2016/06/套路.png";
    @BindView(R.id.iv_main)
    ImageView ivMain;
    @BindView(R.id.pb_main)
    ProgressBar pbMain;
    @BindView(R.id.bt_one)
    Button btOne;

    @BindView(R.id.bt_two)
    Button btTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_first_demo);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_one, R.id.bt_two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_one:
                setBitmap3();
                break;
            case R.id.bt_two:
                break;
        }
    }

    private void setBitmap3() {
        Observable.just(url);



    }


}
