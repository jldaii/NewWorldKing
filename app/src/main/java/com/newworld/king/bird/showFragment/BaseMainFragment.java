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
import com.newworld.king.bird.eventbus.EventBusActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class BaseMainFragment extends Fragment {


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
        // Inflate the layout for this fragment
        if (view == null) {
            activity = getActivity();
            view = activity.getLayoutInflater().inflate(R.layout.test_main_fragment, container, false);
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
                    //
                    case 0:
                        jump(EventBusActivity.class);
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
                "EventBus",

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
//        Toast.makeText(getActivity()," 测试 jump click",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(getActivity(),clazz);
        startActivity(intent);
    }
}
