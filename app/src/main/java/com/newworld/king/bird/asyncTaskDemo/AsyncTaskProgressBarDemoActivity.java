package com.newworld.king.bird.asyncTaskDemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.newworld.king.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsyncTaskProgressBarDemoActivity extends AppCompatActivity {


    private AsyncTaskProgressBarDemoActivity.MyAsynTask  myAsynTask;

    @BindView(R.id.progressbar_astask)
    ProgressBar progressbarAstask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_progress_bar_demo);
        ButterKnife.bind(this);
        myAsynTask = new MyAsynTask();
        myAsynTask.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (myAsynTask != null && myAsynTask.getStatus() == AsyncTask.Status.RUNNING) {
            //cencel 方法只是将对应的AsynTask标记为 cencel状态 并不是真正的取消线程的执行。
            myAsynTask.cancel(true);
        }

    }

    class MyAsynTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            //模拟进度更新
            for (int i = 0; i < 100; i++) {
                if (isCancelled()) {
                    break;
                }
                publishProgress(i);
                try {
                    //延缓时间
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (isCancelled()) {
                return;
            }
            //获取进度更新值
            progressbarAstask.setProgress(values[0]);
        }
    }
}
