package com.kob.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2016/6/30.
 */
public class MyView extends View {
    private Canvas canvas;
    private Bitmap bitmap;
    private Paint paint;
    private Path path;
    private float mx,my;
    public MyView(Context context,int screenWidth,int screenHeight) {
        super(context);

        bitmap=Bitmap.createBitmap(screenWidth,screenHeight, Bitmap.Config.ARGB_8888);
        canvas=new Canvas(bitmap);
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       int action= event.getAction();
        float x=event.getX();
        float y=event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                Log.i("aaaa", "onTouchEvent: MotionEvent.ACTION_DOWN");
                path=new Path();
                path.moveTo(x,y);
                mx=x;
                my=y;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("aaaa", "onTouchEvent: MotionEvent.ACTION_MOVE");
                float dx=Math.abs(x-mx);
                float dy=Math.abs(y-my);
                if (dx>4||dy>4){
                    path.lineTo(x,y);
                    canvas.drawPath(path,paint);
                }
                my=y;
                mx=x;
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
         invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,paint);
    }
}
