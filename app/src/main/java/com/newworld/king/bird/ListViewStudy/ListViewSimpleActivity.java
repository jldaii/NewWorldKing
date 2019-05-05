package com.newworld.king.bird.ListViewStudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewSimpleActivity extends AppCompatActivity {

    private static final String TAG = "ListViewSimpleActivity";
    @BindView(R.id.list_simple)
    ListView listSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_simple);
        ButterKnife.bind(this);

        //需要一个适配器给listView提供数据
        listSimple.setAdapter(new ListViewSimpleActivity.MySimpleAdapter());

    }

    public class MySimpleAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            //这个风方法的返回值决定了 listview 要展示多少条数据
            return 200;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //展示一个具体的条目
//            TextView tv_text = new TextView(ListViewSimpleActivity.this);
//            tv_text.setText("这是第" + position + "条数据");
            //优化
            TextView tv_text = null;
            if(convertView==null){
                //说明没有可以重用的View对象 需要创建新的view对象
                Log.d(TAG, "getView: 创建新的view对象" + position);
                tv_text = new TextView(ListViewSimpleActivity.this);
            }else{
                //当前的converView可以被重新使用
                Log.d(TAG, "getView: 重用旧的view对象" + position);
                tv_text = (TextView) convertView;
            }
            //给textView设置数据
            tv_text.setText("这是第"+position+"个条目");
            return tv_text;
        }
    }


}
