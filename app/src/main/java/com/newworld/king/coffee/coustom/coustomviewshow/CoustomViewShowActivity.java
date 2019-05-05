package com.newworld.king.coffee.coustom.coustomviewshow;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.newworld.king.R;
import com.newworld.king.coffee.coustom.coustomviewshow.ShowBaseRotateFragment;
import com.newworld.king.coffee.coustom.coustomviewshow.ShowBaseRotateTestFragment;
import com.newworld.king.coffee.coustom.coustomviewshow.ShowBaseScaleFragment;
import com.newworld.king.coffee.coustom.coustomviewshow.ShowBaseScaleTestFragment;
import com.newworld.king.coffee.coustom.coustomviewshow.ShowBaseSkewFragment;
import com.newworld.king.coffee.coustom.coustomviewshow.ShowBaseTraslateFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoustomViewShowActivity extends AppCompatActivity {
    @BindView(R.id.tabLayout)
    SmartTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

//    private TabLayout tabLayout;
//    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coustom_view_show);
        ButterKnife.bind(this);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("位移画布", ShowBaseTraslateFragment.class)
                .add("缩放画布", ShowBaseScaleFragment.class)
                .add("缩放画布test", ShowBaseScaleTestFragment.class)
                .add("旋转画布", ShowBaseRotateFragment.class)
                .add("旋转画布test", ShowBaseRotateTestFragment.class)
                .add("错切", ShowBaseSkewFragment.class)
                

                .create());

        viewPager.setAdapter(adapter);

        tabLayout.setViewPager(viewPager);
    }


}
