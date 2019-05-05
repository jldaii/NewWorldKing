package com.newworld.king.bird.ListViewStudy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.newworld.king.R;
import com.newworld.king.bird.ListViewStudy.bean.ListShowPerson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListViewShowSqliteActivity extends AppCompatActivity {

    private MyListShowSqlOpenHelper openHelper;
    private ArrayList<ListShowPerson> persons = new ArrayList<ListShowPerson>();
    SQLiteDatabase database;
    private ListViewShowSqliteActivity.MyAdapter myAdapter;

    @BindView(R.id.btn_insert)
    Button btnInsert;
    @BindView(R.id.btn_query)
    Button btnQuery;
    @BindView(R.id.lv_list)
    ListView lvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_show_sqlite);
        ButterKnife.bind(this);
        openHelper = new MyListShowSqlOpenHelper(this);
        myAdapter = new MyAdapter();

    }

    @OnClick({R.id.btn_insert, R.id.btn_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:
                database = openHelper.getReadableDatabase();
                database.execSQL("insert into info(name,phone) values('王五','13777777');");
                database.execSQL("insert into info(name,phone) values('赵四','13888888');");
                database.execSQL("insert into info(name,phone) values('张三','139999999');");
                database.close();
//                myAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_query:
                database = openHelper.getReadableDatabase();
                Cursor cursor = database.rawQuery("select * from info", null);
                while(cursor.moveToNext()){
                    ListShowPerson person = new ListShowPerson();
                    person.name = cursor.getString(1);
                    person.phone = cursor.getString(2);
                    //对象存储到list
                    persons.add(person);
                    //通知list数据更新
                    myAdapter.notifyDataSetChanged();
                }
                cursor.close();
                database.close();
                for(ListShowPerson person:persons){
                    System.out.println(person);
                }
                lvList.setAdapter(myAdapter);
                break;
        }
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return persons.size();
        }

        @Override
        public Object getItem(int position) {
            return persons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            //①判断convertview是否为空
            if(convertView == null){
                //说明没有可以复用的旧的view 需要创建新的view对象
                view = View.inflate(ListViewShowSqliteActivity.this, R.layout.list_showsqlite, null);
            }else{
                //说明convertview不为空 可以重新使用converveiw
                view = convertView;
            }
            //②找到要修改的控件对象 需要注意 一定是view.findveiwById
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);

            //③获取要显示的数据 拿着传入的postion到数据集合中获取要展示的数据  position就是当前条目在listview中的索引
            //在展示数据的时候 第0个条目展示的就是集合中第0个元素
            ListShowPerson person = persons.get(position);

            //④拿着数据到对应控件进行展示
            tv_name.setText(person.name);
            tv_phone.setText(person.phone);
            return view;
        }

    }
}
