package com.newworld.king.bird.chooser;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.newworld.king.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DateTimeChooseActivity extends AppCompatActivity {

    @BindView(R.id.btn_dateChoose)
    Button btnDateChoose;
    @BindView(R.id.btn_timeChoose)
    Button textTimeChoose;
    //定义String类型的月日年时分
    int Cyear,Cmonth,Cdate,Chour,Cminute,Csecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_choose);
        ButterKnife.bind(this);
        //获取当前日期 年 月 日 小时 分
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        Cyear = c.get(Calendar.YEAR);
        Cmonth = c.get(Calendar.MONTH);
        Cdate = c.get(Calendar.DATE);
        Chour = c.get(Calendar.HOUR_OF_DAY);
        Cminute = c.get(Calendar.MINUTE);
        Csecond = c.get(Calendar.SECOND);
//        System.out.println(Cyear + "/" + Cmonth + "/" + Cdate + " " +Chour + ":" +Cminute + ":" + csecond);
    }

    @OnClick({R.id.btn_dateChoose, R.id.btn_timeChoose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_dateChoose:
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        System.out.print(String.format("%d-%d-%d",year,month,dayOfMonth));
                        String date = String.format("%d-%d-%d",year,month+1,dayOfMonth);
                        btnDateChoose.setText(date);
                    }
                },Cyear,Cmonth,Cdate).show(); //这里传入 int类型的 年 月 日 作为起始时间
                break;

            case R.id.btn_timeChoose:
                new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        System.out.print(String.format("%d-%d",hourOfDay,minute));
                        String time = String.format("%d:%d",hourOfDay,minute);
                        textTimeChoose.setText(time);
                    }
                },Chour,Cminute,true).show(); // 这里传入 int类型的 时 分 是否24小时 作为起始时间
                break;
        }
    }
}
