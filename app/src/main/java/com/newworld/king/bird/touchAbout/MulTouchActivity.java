package com.newworld.king.bird.touchAbout;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MulTouchActivity extends AppCompatActivity {

    String x;
    String y;

    private static final String TAG = "MulTouchActivity";
    Context context;

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.imgv_mk)
    ImageView imgvMk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mul_touch);
        ButterKnife.bind(this);
        context = MulTouchActivity.this;


        container.setOnTouchListener(new View.OnTouchListener() {
            float currentDistance;
            float lastDistance = -1;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
//                        Log.d(TAG, "onTouch:  actiom down");
                        break;

                    case MotionEvent.ACTION_MOVE:
//                        Log.d(TAG, "onTouch:  actiom move");
//                        String x = String.format("x:f,y%f",event.getX(),event.getY());
                        x = String.valueOf(event.getX());
                        y = String.valueOf(event.getY());
                        if(event.getPointerCount() >=2){
                            float offsetX = event.getX(0)-event.getX(1);
                            float offsetY = event.getY(0)-event.getY(1);
                            currentDistance = (float) Math.sqrt(offsetX*offsetX+offsetY*offsetY);
                            if(lastDistance <0){
                                lastDistance = currentDistance;
                            }else{
                                //如果超过5个像素
                                if(currentDistance- lastDistance>5){
                                    FrameLayout.LayoutParams lp = (android.widget.FrameLayout.LayoutParams) imgvMk.getLayoutParams();
                                    lp.width *= (int)(1.1 * imgvMk.getWidth());
                                    lp.height *= (int) (1.1 * imgvMk.getHeight());
                                    imgvMk.setLayoutParams(lp);
                                    lastDistance  = currentDistance;
                                }else if(lastDistance -currentDistance >5){
                                    Log.d(TAG, "onTouch: 缩小");
                                    FrameLayout.LayoutParams lp = (android.widget.FrameLayout.LayoutParams) imgvMk.getLayoutParams();
                                    lp.width *= (int)(0.9 * imgvMk.getWidth());
                                    lp.height *= (int) (0.9 * imgvMk.getHeight());
                                    imgvMk.setLayoutParams(lp);
                                    lastDistance = currentDistance;
                                }
                            }
                        }

                        Log.d(TAG, "onTouch: touchNum__:"+event.getPointerCount() );

                        FrameLayout.LayoutParams lp = (android.widget.FrameLayout.LayoutParams) imgvMk.getLayoutParams();
                        lp.leftMargin = (int)event.getX();
                        lp.topMargin = (int)event.getY();
                        imgvMk.setLayoutParams(lp);

                        break;

                    case MotionEvent.ACTION_UP:
//                        Log.d(TAG, "onTouch:  actiom up");
//                        Toast.makeText(context, "x__:" + x + "  y__:" + y, Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}
