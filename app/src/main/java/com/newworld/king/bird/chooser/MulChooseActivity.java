package com.newworld.king.bird.chooser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MulChooseActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.text_result)
    TextView textResult;

    CheckBox checkBoxA;

    CheckBox checkBoxB;

    CheckBox checkBoxC;

    CheckBox checkBoxD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mul_choose);
        ButterKnife.bind(this);
        checkBoxA = (CheckBox)findViewById(R.id.checkBoxA);
        checkBoxB = (CheckBox)findViewById(R.id.checkBoxB);
        checkBoxC = (CheckBox)findViewById(R.id.checkBoxC);
        checkBoxD = (CheckBox)findViewById(R.id.checkBoxD);

        checkBoxA.setOnCheckedChangeListener(this);
        checkBoxB.setOnCheckedChangeListener(this);
        checkBoxC.setOnCheckedChangeListener(this);
        checkBoxD.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String str = "喜欢吃";
        if (checkBoxA.isChecked()) {
            str+=checkBoxA.getText()+",";  //等价于 str = str + checkBoxA.getText()+",";
        }
        if(checkBoxB.isChecked()){
            str+=checkBoxB.getText()+",";
        }
        if(checkBoxC.isChecked()){
            str+=checkBoxC.getText()+",";
        }
        if(checkBoxD.isChecked()){
            str+=checkBoxD.getText();
        }

        textResult.setText(str);
    }
}
