package com.newworld.king.bird.AnimationLayout;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;

public class ListAnimTestActivity extends ListActivity {

    private ArrayAdapter<String> adapter;

    private LayoutAnimationController lac;
    private ScaleAnimation sa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_anim_test); // 继承ListActivity后 不需要指定布局文件

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{"hello word","hello java","hello kotlin","hello python"});
        setListAdapter(adapter);

        sa = new ScaleAnimation(0,1,0,1);
        sa.setDuration(1000);
        lac = new LayoutAnimationController(sa,0.5f);
        getListView().setLayoutAnimation(lac);
    }
}
