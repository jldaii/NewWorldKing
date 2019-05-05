package com.newworld.king.bird.okhttp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.newworld.king.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity {

    private static final String TAG = "OkHttpActivity";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final int GET = 1;  //get
    private static final int POST = 2; // post


    OkHttpClient client = new OkHttpClient();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.btn_get_post)
    Button btnGetPost;
    @BindView(R.id.btn_eventbus_sticky)
    Button btnEventbusSticky;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.btn_okhttpzhy)
    Button btnOkhttpzhy;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET:
                    //获取数据
                    tvResult.setText((String) msg.obj);
                    break;

                case POST:
                    //
                    tvResult.setText((String) msg.obj);

                    break;

            }

        }
    };

    String urr = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);

        toolbar.setTitle("okhttp");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //展示顶部标题栏 toolBar 左侧默认 HomeAsUp 按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @OnClick({R.id.btn_get_post, R.id.btn_eventbus_sticky,R.id.btn_okhttpzhy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //使用原生okttp请求网络数据，get 和 post
            case R.id.btn_get_post:
                getDataFormGet();
                break;
            //post
            case R.id.btn_eventbus_sticky:
                tvResult.setText("");
                getDataFormPost();
                break;
            case R.id.btn_okhttpzhy:
                tvResult.setText("");
//                OkHttpUtils.post().url(urr).tag("post").addParams("","").build().execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        Toast.makeText(OkHttpActivity.this, "网络出错，请检查网络", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//
//                        tvResult.setText(response);
//                    }
//                });

                break;
        }
    }

    //get请求网络数据
    private void getDataFormGet() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String result = get(urr);
                    Log.d(TAG, "onViewClicked: " + result);
                    Message msg = Message.obtain();
                    msg.what = GET;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //post请求网络数据
    private void getDataFormPost() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String result = post(urr, "");
                    Log.d(TAG, "onViewClicked: " + result);
                    Message msg = Message.obtain();
                    msg.what = POST;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * get请求
     *
     * @param url 网络连接
     * @return
     * @throws IOException
     */
    private String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    /**
     * okhttp3 post
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
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
