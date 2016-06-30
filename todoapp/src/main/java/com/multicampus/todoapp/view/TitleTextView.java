package com.multicampus.todoapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.multicampus.todoapp.R;

/**
 * Created by student on 2016-06-30.
 */
public class TitleTextView extends TextView{

    private boolean isComplete;
    private int completeLineColor;
    private float completeLineWidth;


    public TitleTextView(Context context) {
        super(context);
        init(context, null);
    }

    public TitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        // initialize
        if(attrs != null){
            TypedArray att = context.obtainStyledAttributes(attrs, R.styleable.TitleTextView);
            isComplete = att.getBoolean(R.styleable.TitleTextView_complete, false);
            completeLineColor = att.getInt(R.styleable.TitleTextView_completeLineColor, Color.RED);
            completeLineWidth = att.getDimension(R.styleable.TitleTextView_completeLineWidth, 6);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);// 기본 TextView에서 하는 동작은 구현되도록 하기 위해 항상 호출 해줘야 함.

        if(isComplete){
            Paint paint = new Paint();
            paint.setColor(completeLineColor);
            paint.setAlpha(0x99);
            paint.setStrokeWidth(completeLineWidth);

            float startX = 0;
            float y = getHeight()/2;
            float stopX = getWidth();
            canvas.drawLine(startX, y, stopX, y, paint);
        }
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
