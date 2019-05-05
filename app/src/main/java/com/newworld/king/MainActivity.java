package com.newworld.king;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.newworld.king.ui.fragment.BirdFragment;
import com.newworld.king.ui.fragment.CoffeeFragment;
import com.newworld.king.ui.fragment.FishFragment;
import com.newworld.king.ui.fragment.FlyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
//    @BindView(R.id.swipe_refresh)
//    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private FishFragment fishFragment;
    private FlyFragment flyFragment;
    private BirdFragment birdFragment;
    private CoffeeFragment coffeeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //取消toolbar的标题


        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        } else {
           finish();
        }



//        BadgeItem badgeItem = new BadgeItem();
//        badgeItem.setHideOnSelect(false)
//                .setText("110")
//                .setBackgroundColorResource(R.color.orange)
//                .setBorderWidth(0);
        /*** the setting for BottomNavigationBar ***/
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);//图标不变化
//        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);// 图标会凸出
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);//底部导航不会随着点击改变颜色
//        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);//底部导航随着图标的点击改变颜色
//        mBottomNavigationBar.setBarBackgroundColor(R.color.blue);//set background color for navigation bar 设置为一种背景色 默认白色
        mBottomNavigationBar.setInActiveColor(R.color.colorPrimary);//unSelected icon color 图标为选中状态的颜色 不设置就是灰色
        //设置了item
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.icon_one, R.string.tab_one).setActiveColorResource(R.color.blue))
//                        .setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.drawable.icon_two, R.string.tab_two).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.icon_three, R.string.tab_three).setActiveColorResource(R.color.lime))
                .addItem(new BottomNavigationItem(R.drawable.icon_four, R.string.tab_four))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            /**
             * 设置顶部标题栏左侧是否展示图标 系统默认toolBar最左侧的按钮就叫做 HomeAsUp
             * 默认图标是一个返回箭头，含义是返回上一个活动
             *  HomeAsUp 按钮的id 永远是 android.R.id.home
             */

            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置标题栏左侧的导航图标 三
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

//        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
//        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                refreshFruits();
//            }
//        });
    }


    /**
     * set the default fragment
     */
    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        mFragmentOne = FragmentOne.newInstance("First Fragment");
        fishFragment = new FishFragment();
        transaction.replace(R.id.ll_content, fishFragment).commit();

    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                if (fishFragment == null) {
                    fishFragment = new FishFragment();
                }
                transaction.replace(R.id.ll_content, fishFragment);
                break;
            case 1:
                if (flyFragment == null) {
                    flyFragment = new FlyFragment();
                }
                transaction.replace(R.id.ll_content, flyFragment);
                break;
            case 2:
                if (birdFragment == null) {
                    birdFragment = new BirdFragment();
                }
                transaction.replace(R.id.ll_content, birdFragment);
                break;
            case 3:
                if (coffeeFragment == null) {
                    coffeeFragment = new CoffeeFragment();
                }
                transaction.replace(R.id.ll_content, coffeeFragment);
                break;
            default:
                if (fishFragment == null) {
                }
                transaction.replace(R.id.ll_content, fishFragment);
                break;
        }
        transaction.commit();

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.backup:
                Toast.makeText(this, "点击了 Backup", Toast.LENGTH_SHORT).show();
                break;

            case R.id.delete:
                Toast.makeText(this, "点击了 delete", Toast.LENGTH_SHORT).show();
                break;

            case R.id.setting:
                Toast.makeText(this, "点击了 setting", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
                //点击左侧导航图标时，展示左侧隐藏的滑动弹出页面
                mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }
}

