package com.newworld.king.bird.customView.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.newworld.king.R;

/**
 * Created by jldaii on 2017/6/20.
 */

public class MyRect extends View {

    public MyRect(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public MyRect(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        int color = ta.getColor(R.styleable.MyView_rect_color,0xffff0000);
        setBackgroundColor(color);

        ta.recycle();
    }


}
