package com.newworld.king.bird.eventbus;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.newworld.king.R;
import com.newworld.king.bird.eventbus.event.MessageEvent;
import com.newworld.king.bird.eventbus.event.StickyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusSendActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.btn_eventbus_send_main)
    Button btnEventbusSendMain;
    @BindView(R.id.btn_eventbus_send_sticky)
    Button btnEventbusSendSticky;
    @BindView(R.id.tv_eventbus_send_result)
    TextView tvEventbusSendResult;

    boolean isFirst = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_send);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle("EventBus发送数据页面");
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            //展示顶部标题栏 toolBar 左侧默认 HomeAsUp 按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @OnClick({ R.id.btn_eventbus_send_main, R.id.btn_eventbus_send_sticky})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //主线程发送数据按钮
            case R.id.btn_eventbus_send_main:
                // 4 发送消息
                EventBus.getDefault().post(new MessageEvent("发送的数据  .........."));
                finish();

                break;
            //接收粘性数据
            case R.id.btn_eventbus_send_sticky:


                if (isFirst){
                    isFirst = false;
                    //4注册
                    EventBus.getDefault().register(this);
                }

                break;
        }
    }

    //3 接收粘性事件
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void StickyEventBus(StickyEvent event){
        tvEventbusSendResult.setText(event.msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //5 解注册
        EventBus.getDefault().removeAllStickyEvents();
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
