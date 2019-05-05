package com.newworld.king.andfix;

import android.content.Context;
import android.graphics.PathMeasure;


import com.alipay.euler.andfix.AndFixManager;
import com.alipay.euler.andfix.patch.PatchManager;
import com.newworld.king.util.Utils;

/**
 * Created by jldaii on 2017/7/9.
 * 管理andfix的全部api
 */

public class AndFixPatchManager {

    private static AndFixPatchManager mInstance = null;
    private static PatchManager mPatchManager = null;

    public static AndFixPatchManager getInstance(){
        if(mInstance == null){
            synchronized (AndFixManager.class){
                if(mInstance == null){
                    mInstance = new AndFixPatchManager();
                }
            }
        }
        return mInstance;
    }

    //初始化AndFix方法
    public void initPatch(Context context) {
        mPatchManager = new PatchManager(context);
        mPatchManager.init(Utils.getVersionName(context));
        mPatchManager.loadPatch();
    }
    //加载我们的patch文件
    public void addPatch(String path) {
        try {
            if (mPatchManager != null) {
                mPatchManager.addPatch(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
