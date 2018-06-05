package com.contentprovide.liuliu.test_one;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liuliu on 2018/6/4.
 */

public class clock extends View {

    Paint circle_paint;
    Paint text_paint;

    int count = 1;


    public clock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        circle_paint = new Paint();
        text_paint = new Paint();

        circle_paint.setColor(Color.BLACK);
        text_paint.setColor(Color.RED);
        text_paint.setTextSize(20);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    postInvalidate();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        设置画笔风格为空心
        circle_paint.setStyle(Paint.Style.STROKE);
//        设置画笔粗细
        circle_paint.setStrokeWidth(4);

//        画一个空心圆
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 300, circle_paint);

//        设置画笔风格为实心
        circle_paint.setStyle(Paint.Style.FILL);
//        画一个实心圆
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 20, circle_paint);

//        旋转canvas绘制刻度和数字
        for (int i = 1; i <= 12; i++) {
//            保存画布
            canvas.save();
//            以一个点坐标为圆点，每次旋转30度的倍数
            canvas.rotate(360 / 12 * i, getWidth() / 2, getHeight() / 2);
//            绘制刻度
            canvas.drawLine(getWidth() / 2, getHeight() / 2 - 300 + 8, getWidth() / 2, getHeight() / 2 - 300 + 25, circle_paint);
//            绘制数字
            canvas.drawText(i + "", getWidth() / 2, getHeight() / 2 - 300 + 50, text_paint);
//            复原画布角度
            canvas.restore();
        }


        canvas.save();
        canvas.rotate(count * 6, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(getWidth() / 2, getHeight() / 2 - 250, getWidth() / 2, getHeight() / 2, circle_paint);
        canvas.restore();
        count++;
    }


}
