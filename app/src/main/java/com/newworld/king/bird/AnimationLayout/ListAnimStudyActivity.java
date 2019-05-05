package com.newworld.king.bird.AnimationLayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAnimStudyActivity extends AppCompatActivity {

    @BindView(android.R.id.list)
    ListView list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_anim_study);
        ButterKnife.bind(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"hello word", "hello java", "hello kotlin", "hello python"});
        list.setAdapter(adapter);

    }
}
