package com.newworld.king.bird.customView.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jldaii on 2017/6/20.
 */

public class RotatingRect extends View {

    public RotatingRect(Context context) {
        super(context);
        initProperties();
    }

    public RotatingRect(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initProperties();
    }

    public RotatingRect(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initProperties();
    }

    private void initProperties() {
        paint = new Paint();
        paint.setColor(Color.RED);

    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.save();
//        canvas.rotate(degrees); //旋转角度 如果执行 将会绕着左上角旋转  旋转角度可以使用多个
//        canvas.translate(200,200); //位移到指定位置
        canvas.rotate(degrees,50,50);//给的view的大小是 100*100 所以旋转是50 50 绕着中心
        canvas.drawRect(0,0,100,100,paint);
        /*left：矩形的左边位置。
        top：矩形的上边位置。
        right：矩形的右边位置。
        bottom：矩形的下边位置。*/
//        System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>");
        canvas.restore();
        degrees ++; //旋转之后增加一个角度
        invalidate();//使之无效，重绘

    }
    private Paint paint;
    private float degrees = 0; //角度


}
