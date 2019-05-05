package com.newworld.king.bird.AnimationLayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentChangeAnimActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_rootview)
    LinearLayout layoutRootview;


    private View.OnClickListener button_OnclickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            layoutRootview.removeView(v);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_change_anim);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //取消toolbar的标题

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "点击了 add", Toast.LENGTH_SHORT).show();
                addButton();

                break;
        }
        return true;
    }

    private  void addButton(){
        Button button = new Button(this);
        button.setText("Remove me");
        layoutRootview.addView(button);
        //定义动画效果
        AnimationSet as = new AnimationSet(true);
        as.setDuration(800);
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1);
        sa.setDuration(1000);
        RotateAnimation rotateAnimation = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(1000);
        as.addAnimation(sa);
        as.addAnimation(rotateAnimation);

        LayoutAnimationController loc = new LayoutAnimationController(as, 0.5f); //这里的0.5f 设定的是 布局子空间的动画时间 默认写0 就同时出现
        layoutRootview.setLayoutAnimation(loc);
        button.setOnClickListener(button_OnclickListener);
    }


}
