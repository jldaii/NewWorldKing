package com.newworld.king.bird.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.newworld.king.R;
import com.newworld.king.bird.eventbus.event.MessageEvent;
import com.newworld.king.bird.eventbus.event.StickyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusActivity extends AppCompatActivity {

    @BindView(R.id.btn_eventbus_send)
    Button btnEventbusSend;
    @BindView(R.id.btn_eventbus_sticky)
    Button btnEventbusSticky;
    @BindView(R.id.tv_eventbus_result)
    TextView tvEventbusResult;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.appBar)
    AppBarLayout appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        ButterKnife.bind(this);

        toolbar.setTitle("EventBus接受数据页面");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            //展示顶部标题栏 toolBar 左侧默认 HomeAsUp 按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //1.注册广播
        EventBus.getDefault().register(this);


    }

    @OnClick({R.id.btn_eventbus_send, R.id.btn_eventbus_sticky})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //跳转到发送界面
            case R.id.btn_eventbus_send:
                Intent intent = new Intent(this, EventBusSendActivity.class);

                startActivity(intent);
                break;
            //发送粘性事件到发送界面
            case R.id.btn_eventbus_sticky:
                //2发送粘性事件
                EventBus.getDefault().postSticky(new StickyEvent("粘性事件测试哦"));
                startActivity(new Intent(this, EventBusSendActivity.class));

                break;
        }
    }

    //5 接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(MessageEvent event){
        //显示接收的消息
        tvEventbusResult.setText(event.name);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //2 解注册
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
