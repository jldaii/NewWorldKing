package com.newworld.king.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newworld.king.R;
import com.newworld.king.adpter.BirdAdapter;
import com.newworld.king.bird.showFragment.AllMainFragment;
import com.newworld.king.bird.showFragment.AtGuiGuMainFragment;
import com.newworld.king.coffee.coustom.coustomviewshow.ShowBaseRotateFragment;
import com.newworld.king.coffee.coustom.coustomviewshow.ShowBaseRotateTestFragment;
import com.newworld.king.coffee.coustom.coustomviewshow.ShowBaseScaleFragment;
import com.newworld.king.coffee.coustom.coustomviewshow.ShowBaseTraslateFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BirdFragment extends Fragment {


    @BindView(R.id.smartTabLayout)
    SmartTabLayout smartTabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    private View view;
    private Activity activity;

    private List<String> mDatas; //数据
    private BirdAdapter birdAdapter;//适配器

    private FragmentManager manager;
    private FragmentTransaction ft;

    private boolean isadd = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            activity = getActivity();
            view = activity.getLayoutInflater().inflate(R.layout.fragment_bird, container, false);
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        unbinder = ButterKnife.bind(this, view);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getActivity().getSupportFragmentManager(), FragmentPagerItems.with(getContext())
                .add("尚硅谷", AtGuiGuMainFragment.class)
                .add(R.string.customView, ShowBaseTraslateFragment.class)
                .add("动画", ShowBaseScaleFragment.class)
                .add("框架", ShowBaseRotateFragment.class)
                .add("功能", ShowBaseRotateTestFragment.class)
                .add("All", AllMainFragment.class)
                .create());

        if(isadd){
            viewPager.setAdapter(adapter);
            smartTabLayout.setViewPager(viewPager);
            isadd = false;
        }
        return view;
    }


    public void jump(Class clazz) {
//        Toast.makeText(getActivity()," 测试 jump click",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(getActivity(), clazz);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
