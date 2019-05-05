package com.newworld.king.bird.showFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.newworld.king.R;
import com.newworld.king.adpter.BirdBaseAdapter;
import com.newworld.king.bird.AnimationAlpha.AlphaAnimationMainActivity;
import com.newworld.king.bird.AnimationBezier.BezierStudyActivity;
import com.newworld.king.bird.AnimationLayout.ContentChangeAnimActivity;
import com.newworld.king.bird.AnimationLayout.LayoutAnimActivity;
import com.newworld.king.bird.AnimationLayout.ListAnimStudyActivity;
import com.newworld.king.bird.AnimationLayout.ListAnimTestActivity;
import com.newworld.king.bird.AnimationVector.VectorAminByXmlActivity;
import com.newworld.king.bird.ListViewStudy.ListViewComplexEntryActivity;
import com.newworld.king.bird.ListViewStudy.ListViewShowSqliteActivity;
import com.newworld.king.bird.ListViewStudy.ListViewSimpleActivity;
import com.newworld.king.bird.MaterialDesign.MaterialDesignMainActivity;
import com.newworld.king.bird.MultiThreadDownload.DownloadManagerTestActivity;
import com.newworld.king.bird.QRCode.QrCodeByZxingActivity;
import com.newworld.king.bird.amap.AMapeSimpleActivity;
import com.newworld.king.bird.asyncTaskDemo.AsyncTaskImageDemoActivity;
import com.newworld.king.bird.asyncTaskDemo.AsyncTaskProgressBarDemoActivity;
import com.newworld.king.bird.chooser.DateTimeChooseActivity;
import com.newworld.king.bird.chooser.MulChooseActivity;
import com.newworld.king.bird.chooser.SingleChooseActivity;
import com.newworld.king.bird.customView.BasicsCoustomViewActivity;
import com.newworld.king.bird.customView.RotatingRectMainActivity;
import com.newworld.king.bird.eventbus.EventBusActivity;
import com.newworld.king.bird.floatbubbleview.FloatBubbleViewActivity;
import com.newworld.king.bird.glide.GlideMainActivity;
import com.newworld.king.bird.okhttp.OkHttpActivity;
import com.newworld.king.bird.touchAbout.MulTouchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class AllMainFragment extends Fragment {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;

    private View view;
    private Activity activity;

    private List<String> mDatas; //数据
    private BirdBaseAdapter birdBaseAdapter;//适配器
    private FragmentManager manager;
    private FragmentTransaction ft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            activity = getActivity();
            view = activity.getLayoutInflater().inflate(R.layout.biar_base_show, container, false);
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        unbinder = ButterKnife.bind(this, view);
        manager = getFragmentManager();
        //数据
        initDatas();
        //指定适配器 初始化适配器并传入数据
        birdBaseAdapter = new BirdBaseAdapter( getActivity(), mDatas);
        mRecyclerView.setAdapter(birdBaseAdapter);
        //设置Recyclerview布局样式
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); //列表
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //点击事件
        birdBaseAdapter.setmOnItemClickListener(new BirdBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(),position+"  click",Toast.LENGTH_SHORT).show();
                switch (position){
                    //时间选择器
                    case 0:
                        jump(DateTimeChooseActivity.class);
                        break;
                    case 1:
                        //单选
                        jump(SingleChooseActivity.class);
                        break;
                    case 2:
                        //多选
                        jump(MulChooseActivity.class);
                        break;
                    case 3:
                        //自定义视图方块
                        jump(RotatingRectMainActivity.class);
                        break;
                    case 4:
                        //动画效果
                        jump(AlphaAnimationMainActivity.class);
                        break;
                    case 5:
                        //布局动画
                        jump(LayoutAnimActivity.class);
                        break;
                    case 6:
                        //布局内容改变动画
                        jump(ContentChangeAnimActivity.class);
                        break;
                    case 7:
                        //list动画效果
                        jump(ListAnimTestActivity.class);
                        break;
                    case 8:
                        //list动画效果 xml实现
                        jump(ListAnimStudyActivity.class);
                        break;
                    case 9:
                        //触摸侦听
                        jump(MulTouchActivity.class);
                        break;
                    case 10:
                        //动画
                        jump(VectorAminByXmlActivity.class);
                        break;
                    case 11:
                        //贝塞尔曲线动画
                        jump(BezierStudyActivity.class);
                        break;
                    case 12:
                        //AsyncTask图片加载
                        jump(AsyncTaskImageDemoActivity.class);
                        break;
                    case 13:
                        //AsyncTask进度条
                        jump(AsyncTaskProgressBarDemoActivity.class);
                        break;
                    case 14:
                        //ListViewSimple
                        jump(ListViewSimpleActivity.class);
                        break;
                    case 15:
                        //List新闻数据
                        jump(ListViewComplexEntryActivity.class);
                        break;
                    case 16:
                        //ListshowSQLite
                        jump(ListViewShowSqliteActivity.class);
                        break;
                    case 17:
                        //多线程下载
//                        jump(MultiThreadDownloadActivity.class);
                        jump(DownloadManagerTestActivity.class);
                        break;
                    case 18:
                        //MaterialDesignMainActivity
                        jump(MaterialDesignMainActivity.class);
                        break;
                    case 19:
                        //二维码生成
                        jump(QrCodeByZxingActivity.class);
                        break;
                    case 20:
                        //悬浮气泡
                        jump(FloatBubbleViewActivity.class);
                        break;
                    case 21:
                        //高德地图
                        jump(AMapeSimpleActivity.class);
                        break;
                    case 22:
                        //高德地图
                        jump(BasicsCoustomViewActivity.class);
                        break;
                    case 23:
                        //eventBus
                        jump(EventBusActivity.class);
                        break;
                    case 24:
                        //OkHttpActivity
                        jump(OkHttpActivity.class);
                        break;
                    case 25:
                        //glide
                        jump(GlideMainActivity.class);
                        break;

                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getActivity(),position+"  Longclick",Toast.LENGTH_SHORT).show();
            }
        });
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
        return view;
    }
    private void initDatas() {
        mDatas = new ArrayList<String>();
        String[] str = new String[]{
                "时间、日期选择器",
                "单项选择",
                "多项选择",
                "自定义view 方块",
                "动画效果",
                "布局动画",
                "布局内容改变动画",
                "列表动画效果",
                "列表动画效果 xml实现",
                "触摸侦听",
                "Vector动画xml实现",
                "贝塞尔曲线动画ByImooc",
                "AsyncTask图片加载",
                "AsyncTask进度条",
                "ListViewSimple",
                "list新闻条目",
                "listShowSQLite",
                "下载",
                "Material Design",
                "二维码生成ByZxing",
                "悬浮气泡",
                "高德地图",
                "自定义viewBasice",
                "eventBus",
                "okhttp",
                "Glide"
        };
        for (String s : str) {
            mDatas.add(s);
        }
    }
    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        initFruits();
//                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void jump(Class clazz){
        Intent intent = new Intent();
        intent.setClass(getActivity(),clazz);
        startActivity(intent);
    }
}
