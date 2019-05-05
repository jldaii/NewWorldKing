package com.newworld.king.bird.ListViewStudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewComplexEntryActivity extends AppCompatActivity {

    @BindView(R.id.list_ComplexEntry)
    ListView listComplexEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_complex_entry);
        ButterKnife.bind(this);
        listComplexEntry.setAdapter(new ListViewComplexEntryActivity.MyAdapter());
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 25;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //第一个参数 上下文 第二个参数 条目布局的资源id 第三个参数 ViewGroup(比如 LinearLayout RelativeLayout)
            //如果viewgroup传入一个具体的对象 那么这个方法创建出来的view对象就会作为这个viewgroup的一个childView
            //现在只是想把xml文件转换成view对象 不需要放到viewgroup中 最后一个参数可以传入null
            View view = null;
            if(convertView == null){
                //view = View.inflate(MainActivity.this, R.layout.item, null);
//				LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//				inflater = getLayoutInflater();
                //谷歌的源代码中 用getSystemService这个方法获取打气筒
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.listview_complexentry, null);
            }else{
                view = convertView;
            }
            return view;
        }

    }
}
