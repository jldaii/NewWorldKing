package com.newworld.king.bird.AnimationBezier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BezierStudyActivity extends AppCompatActivity {

    @BindView(R.id.secondBezierTest)
    Button secondBezierTest;
    @BindView(R.id.thirdBezierTest)
    Button thirdBezierTest;
    @BindView(R.id.drawPadBezierTest)
    Button drawPadBezierTest;
    @BindView(R.id.pathMorthingBezierTest)
    Button pathMorthingBezierTest;
    @BindView(R.id.waveBezierTest)
    Button waveBezierTest;
    @BindView(R.id.pathBezierTest)
    Button pathBezierTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_study);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.secondBezierTest, R.id.thirdBezierTest, R.id.drawPadBezierTest,
            R.id.pathMorthingBezierTest, R.id.waveBezierTest, R.id.pathBezierTest})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.secondBezierTest:
                startActivity(new Intent(this,SecondBezierActivity.class));
                break;
            case R.id.thirdBezierTest:
                startActivity(new Intent(this,ThirdBezierActivity.class));
                break;
            case R.id.drawPadBezierTest:
                startActivity(new Intent(this,DrawPadActivity.class));
                break;
            case R.id.pathMorthingBezierTest:
                startActivity(new Intent(this,PathMorphingActivity.class));
                break;
            case R.id.waveBezierTest:
                startActivity(new Intent(this,WaveActivity.class));
                break;
            case R.id.pathBezierTest:
                startActivity(new Intent(this,PathBezierActivity.class));
                break;
        }
    }
}
