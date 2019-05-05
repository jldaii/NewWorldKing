package com.newworld.king.bird.MultiThreadDownload;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;

import com.newworld.king.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.versionName;

public class DownloadManagerTestActivity extends AppCompatActivity {
    private static final String TAG = "DownloadManagerTestActi";

    String path1 = "http://211.137.51.153/cache/gdown.baidu.com/data/wisegame/41a04ccb443cd61a/QQ_692.apk?ich_args2=90-04144203018245_21fb" +
            "07fb317633d49b5cddb8c6785d80_10068001_9c886d25d6c6f1d4923a518939a83798_9f052841a8c75746b301fbcf47c6c8a1";
    String path = "http://gdown.baidu.com/data/wisegame/08e2888575555098/kuaituliulan_40722421.apk";
    @BindView(R.id.btn_download_test)
    Button btnDownloadTest;
    @BindView(R.id.btn_download_toBrowser)
    Button btnDownloadToBrowser;

    private Context mContext;
    DownloadManager downloadManager;
    long mTaskId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_manager_test);
        ButterKnife.bind(this);

        mContext = DownloadManagerTestActivity.this;

    }


    @OnClick({R.id.btn_download_test, R.id.btn_download_toBrowser})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_download_test:
                downloadAPK(path,"kt.apk");
                break;
            case R.id.btn_download_toBrowser:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(path);
                intent.setData(content_url);
                startActivity(intent);
                break;
        }
    }

    //使用系统下载器下载
    private void downloadAPK(String versionUrl, String versionName) {
        //创建下载任务
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(versionUrl));
        request.setAllowedOverRoaming(false);//漫游网络是否可以下载

        //设置文件类型，可以在下载结束后自动打开该文件
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(versionUrl));
        request.setMimeType(mimeString);

        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setVisibleInDownloadsUi(true);

        //sdcard的目录下的download文件夹，必须设置
        request.setDestinationInExternalPublicDir("/download/", versionName);
        //request.setDestinationInExternalFilesDir(),也可以自己制定下载路径

        //将下载请求加入下载队列
        downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        //加入下载队列后会给该任务返回一个long型的id，
        //通过该id可以取消任务，重启任务等等，看上面源码中框起来的方法
        mTaskId = downloadManager.enqueue(request);

        //注册广播接收者，监听下载状态
        mContext.registerReceiver(receiver,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    //广播接受者，接收下载状态
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkDownloadStatus();//检查下载状态
        }
    };

    //检查下载状态
    private void checkDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(mTaskId);//筛选下载任务，传入任务ID，可变参数
        Cursor c = downloadManager.query(query);
        if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status) {
                case DownloadManager.STATUS_PAUSED:
//                    MLog.i(">>>下载暂停");
                    Log.d(TAG, ">>>下载暂停: ");
                case DownloadManager.STATUS_PENDING:
//                    MLog.i(">>>下载延迟");
                    Log.d(TAG, ">>>下载延迟: ");
                case DownloadManager.STATUS_RUNNING:
//                    MLog.i(">>>正在下载");
                    Log.d(TAG, ">>>正在下载: ");
                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
//                    MLog.i(">>>下载完成");
                    Log.d(TAG, ">>>下载完成: ");
                    //下载完成安装APK
                    String downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + versionName;
                    installAPK(new File(downloadPath));
                    break;
                case DownloadManager.STATUS_FAILED:
//                    MLog.i(">>>下载失败");
                    Log.d(TAG, ">>>下载失败: ");
                    break;
            }
        }
    }

    //下载到本地后执行安装
    protected void installAPK(File file) {
        Log.d(TAG, "installAPK: running");
        if (!file.exists()) {
            Log.d(TAG, "installAPK: ttttttttttttttttt");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("file://" + file.toString());
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        //在服务中开启activity必须设置flag,后面解释
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }


}
