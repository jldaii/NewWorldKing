package com.newworld.king.bird.chooser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleChooseActivity extends AppCompatActivity {

    @BindView(R.id.radioButtonA)
    RadioButton radioButtonA;
    @BindView(R.id.radioButtonB)
    RadioButton radioButtonB;
    @BindView(R.id.radioButtonC)
    RadioButton radioButtonC;
    @BindView(R.id.radioButtonD)
    RadioButton radioButtonD;
    @BindView(R.id.btn_btnSubmit)
    Button btnBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_choose);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.radioButtonA, R.id.radioButtonB, R.id.radioButtonC, R.id.radioButtonD, R.id.btn_btnSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radioButtonA:
                break;
            case R.id.radioButtonB:
                break;
            case R.id.radioButtonC:
                break;
            case R.id.radioButtonD:
                break;
            case R.id.btn_btnSubmit:
                if(radioButtonA.isChecked()){
                    Toast.makeText(this,"选择了A",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"选择错误",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
