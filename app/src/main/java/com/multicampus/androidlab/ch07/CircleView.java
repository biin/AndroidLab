package com.multicampus.androidlab.ch07;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by student on 2016-06-28.
 */
public class CircleView extends View implements View.OnClickListener{

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawCircle(50, 50, 30, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        super.onTouchEvent(event);

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            Log.d(this.getClass().getSimpleName(), "1. Callback Method");
        }

        // true : 이벤트는 여기서 완료 되었다, 다음 이벤트는 무시
        // false : 이 리스너에서 처리는 했다만 더 남았으니 다른 리스너들이 처리하도록 함
        return true;
    }

    @Override
    public void onClick(View v) {
        Log.d(this.getClass().getSimpleName(), "4. View implements Listener");
    }
}

