package com.newworld.king.coffee.shangguigu;

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

public class ShangGuiGuMainActivity extends AppCompatActivity {

    @BindView(R.id.tabLayout)
    SmartTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shang_gui_gu_main);
        ButterKnife.bind(this);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("", ShowBaseTraslateFragment.class)



                .create());

        viewPager.setAdapter(adapter);

        tabLayout.setViewPager(viewPager);
    }
}
