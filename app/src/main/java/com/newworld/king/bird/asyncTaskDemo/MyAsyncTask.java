package com.newworld.king.bird.asyncTaskDemo;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by jldaii on 2017/7/3.
 */

public class MyAsyncTask extends AsyncTask<Void,Void,Void>{
    private static final String TAG = "MyAsyncTask";
    
    @Override
    protected Void doInBackground(Void... params) {
        Log.d(TAG, "doInBackground: ");
        
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: ");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d(TAG, "onPostExecute: ");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate: ");
    }
}
