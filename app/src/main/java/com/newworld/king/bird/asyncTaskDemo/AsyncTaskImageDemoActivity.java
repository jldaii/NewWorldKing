package com.newworld.king.bird.asyncTaskDemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.newworld.king.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsyncTaskImageDemoActivity extends AppCompatActivity {

    @BindView(R.id.img_asynctasktest)
    ImageView imgAsynctasktest;
    @BindView(R.id.progressbar_asynctasktest)
    ProgressBar progressbarAsynctasktest;
    private String url ="http://img.sj33.cn/uploads/allimg/201302/1-130201105055.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_image_demo);
        ButterKnife.bind(this);

        new MyAsycTask().execute(url);
    }


    //<url类型，进度值类型，返回值类型>
    class MyAsycTask extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressbarAsynctasktest.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progressbarAsynctasktest.setVisibility(View.GONE);
            imgAsynctasktest.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //获取传递进来的参数
            String url = params[0];
            Bitmap bitmap = null;
            URLConnection connection;
            InputStream is;
            try {
                connection = new URL(url).openConnection();
                is = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //将输入流 转为bitmip
                bitmap = BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //将bitmap作为返回值
            return bitmap;
        }
    }
}
