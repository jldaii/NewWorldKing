package com.newworld.king.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newworld.king.R;
import com.newworld.king.bird.floatbubbleview.view.FloatBubbleView;
import com.newworld.king.coffee.coustom.coustomviewshow.CoustomViewShowActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class CoffeeFragment extends Fragment {
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.cardv_toCoustomViewShow)
    CardView cardvToCoustomViewShow;

    Unbinder unbinder;
    private View view;
    private Activity activity;

    private FloatBubbleView mDWView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            activity = getActivity();
            view = activity.getLayoutInflater().inflate(R.layout.fragment_coffee, container, false);
//            view = view.inflate(getActivity(),R.layout.fragment_coffee,null);
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        //初始化操作
//        initView();
//        initData();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.cardv_toCoustomViewShow)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), CoustomViewShowActivity.class));
    }
}
