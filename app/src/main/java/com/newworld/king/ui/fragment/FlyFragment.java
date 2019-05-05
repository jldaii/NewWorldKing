package com.newworld.king.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newworld.king.R;


public class FlyFragment extends Fragment {
    private View view;
    private Activity activity;
    private TextView txtFlyhome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view ==null){
            activity = getActivity();
//            view = activity.getLayoutInflater().inflate(R.layout.fragment_fly,null);
            view = activity.getLayoutInflater().inflate(R.layout.fragment_fly,container,false);
        }else{
            ViewGroup parent = (ViewGroup) view.getParent();
            if(parent != null){
                parent.removeView(view);
            }
        }
        txtFlyhome = (TextView)view.findViewById(R.id.txt_flyhome);

        txtFlyhome.setText("Product Model: " + android.os.Build.MODEL + ","
                + android.os.Build.VERSION.SDK + ","
                + android.os.Build.VERSION.RELEASE);


        return view;
    }
}
