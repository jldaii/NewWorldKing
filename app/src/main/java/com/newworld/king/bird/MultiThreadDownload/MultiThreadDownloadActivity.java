package com.newworld.king.bird.MultiThreadDownload;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.newworld.king.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MultiThreadDownloadActivity extends AppCompatActivity {

    @BindView(R.id.et_url)
    EditText et_url;
    @BindView(R.id.et_count)
    EditText et_count;
    @BindView(R.id.btn_download)
    Button btn_download;
    @BindView(R.id.ll_progress)
    LinearLayout ll_progress;

    String path = "http://211.137.51.153/cache/gdown.baidu.com/data/wisegame/41a04ccb443cd61a/QQ_692.apk?ich_args2=90-04144203018245_21fb" +
            "07fb317633d49b5cddb8c6785d80_10068001_9c886d25d6c6f1d4923a518939a83798_9f052841a8c75746b301fbcf47c6c8a1";
    private int blockSize;
    private int threadCount = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_thread_download);
        ButterKnife.bind(this);




    }

    @OnClick(R.id.btn_download)
    public void onViewClicked() {
        ll_progress.removeAllViews();


        String temp = et_count.getText().toString().trim();
        int count = Integer.parseInt(temp);

        for (int i = 0; i < count; i++) {
            View.inflate(getApplicationContext(),R.layout.multi_thread_download_item,ll_progress);

        }

        new Thread(){
            public void run() {
                //①联网获取要下载的文件长度
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(10000);
                    int code = connection.getResponseCode();
                    if(code==200){
                        //获取要下载的文件长度
                        int length = connection.getContentLength();
                        //在本地创建一个一样大的文件
                        RandomAccessFile file = new RandomAccessFile(getFileName(path), "rw");
                        file.setLength(length);
                        blockSize = length/threadCount;
                        //计算每一个线程要下载的数据范围
                        for(int i =0;i<threadCount;i++){
                            int startIndex = i*blockSize;
                            int endIndex = (i+1)*blockSize-1;
                            if(i == threadCount-1){
                                //说明是最后一个线程
                                endIndex = length-1;
                            }
                            ProgressBar pb = (ProgressBar) ll_progress.getChildAt(i);
                            //给进度条设置显示的最大进度
                            pb.setMax(endIndex-startIndex);
                            new DownLoadThread(startIndex, endIndex, i).start();
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            };
        }.start();
    }
    private class DownLoadThread extends Thread{
        private int startIndex;
        private int endIndex;
        private int threadID;
        private ProgressBar pb;

        public DownLoadThread(int startIndex, int endIndex, int threadID) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.threadID = threadID;
            this.pb = (ProgressBar) ll_progress.getChildAt(threadID);
        }

        public void run() {
            try {
                //读取出记录下来的位置
                File temp = new File(getFileName(path)+threadID+".log");
                if(temp!=null && temp.length()>0){
                    //说明日志文件有内容
                    FileInputStream fis = new FileInputStream(temp);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                    String result = reader.readLine();
                    //读出记录下来的位置更新下载请求数据的起始位置
                    startIndex = Integer.parseInt(result);
                }
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000);
                //设置Range头 用计算好的开始索引 和结束索引到服务端请求数据
                connection.setRequestProperty("Range", "bytes="+startIndex+"-"+endIndex);
                if(connection.getResponseCode()==206){
                    System.out.println("线程"+threadID+"开始下载"+startIndex);
                    InputStream inputStream = connection.getInputStream();
                    int len = -1;
                    byte[] buffer = new byte[1024*500];
                    RandomAccessFile file = new RandomAccessFile(getFileName(path), "rw");
                    //一定不要忘记  要seek到startIndex位置 写入数据
                    file.seek(startIndex);
                    int count=0;
                    while((len=inputStream.read(buffer))!=-1){
                        file.write(buffer, 0, len);
                        //计算当前线程下载了多少
                        count+=len;
                        //计算当前线程总共下载了多少
                        int position = count+startIndex;
                        //用一个文件记录这个位置
                        pb.setProgress(position-threadID*blockSize);
                        RandomAccessFile tempFile = new RandomAccessFile(getFileName(path)+threadID+".log", "rwd");
                        tempFile.write(String.valueOf(position).getBytes());
                    }
                    file.close();
                    inputStream.close();
                    System.out.println("线程"+threadID+"下载结束");
                    if(temp!=null){
                        //删除对应的日志文件
                        temp.delete();
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    private String getFileName(String path) {
        String[] result = path.split("/");
        return getCacheDir().getAbsolutePath()+"/"+result[result.length-1];
    }

}
