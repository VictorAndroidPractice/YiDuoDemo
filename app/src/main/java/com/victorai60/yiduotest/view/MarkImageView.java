package com.victorai60.yiduotest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Author: victor
 * Date: 2015-12-25 12:16
 * Email: 497042813@qq.com
 */
public class MarkImageView extends ImageView {
    private float x;
    private float y;
    private float width = 10;
    private float radius = 40;
    private float textSize = 50;

    public MarkImageView(Context context) {
        super(context);
    }

    public MarkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (x > 0 && y > 0) {
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(width);
            paint.setAntiAlias(true);
            canvas.drawCircle(x, y, radius, paint);

            paint.reset();
            paint.setTextSize(textSize);

            canvas.drawText("X=" + x + ", Y=" + (getHeight() - y), 100, 100, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();

        if (x > radius && y > radius && x + radius < getWidth() && y + radius < getHeight()) {
            invalidate();
        }

        return true;
    }

}
