package com.newworld.king.ui.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FishFragment extends Fragment {
    @BindView(R.id.text_fish)
    Button textFish;
    @BindView(R.id.frame_fish)
    LinearLayout frameFish;
    Unbinder unbinder;
    @BindView(R.id.text_time)
    Button textTime;
    private View view;
    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            activity = getActivity();
            view = activity.getLayoutInflater().inflate(R.layout.fragment_fish, container, false);
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        unbinder = ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.text_fish, R.id.text_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_fish:
                new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       System.out.print(String.format("%d-%d-%d",year,month,dayOfMonth));
                       String a = String.format("%d-%d-%d",year,month+1,dayOfMonth);
                       textFish.setText(a);
                   }
               },2017,6,19).show();
                break;
            case R.id.text_time:
                new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        System.out.print(String.format("%d-%d",hourOfDay,minute));
                        String a = String.format("%d-%d",hourOfDay,minute);
                        textTime.setText(a);
                    }
                },0,0,true).show();
                break;
        }
    }
}
