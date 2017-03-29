package com.example.dell.battlecity_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 700-15isk on 2017/3/27.
 */

public class Bullet extends View {
    public float x;
    public float y;
    Handler handler = new Handler();
    int time;
    double pi = 3.14159;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int bulletColor = Color.BLACK;

    public Bullet(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Bullet(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Bullet(Context context) {
        super(context);
        init();
    }

    public void removeBullet() {
        this.setVisibility(View.GONE);
    }

    public void init() {
        mPaint.setColor(bulletColor);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, 20, mPaint);
    }

    public void fire(float x, float y, final double direction, final int speed) {
        this.x = x;
        this.y = y;
        handler.post(new Runnable() {
            @Override
            public void run() {
                draw(new Canvas());
                move(direction, speed);
            }
        });
    }


    private void move(final double direction, final int speed) {
        final Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (direction < 90) {
                    x = (float) (x + speed * Math.cos(direction * pi / 180));
                    y = (float) (y - speed * Math.sin(direction * pi / 180));
                } else if (direction == 90) {
                    y = y - speed;
                } else if (direction < 180) {
                    x = (float) (x - speed * Math.cos((direction - 90) * pi / 180));
                    y = (float) (y - speed * Math.sin((direction - 90) * pi / 180));
                } else if (direction == 180) {
                    x = x - speed;
                } else if (direction < 270) {
                    x = (float) (x - speed * Math.cos((direction - 180) * pi / 180));
                    y = (float) (y + speed * Math.sin((direction - 180) * pi / 180));
                } else if (direction == 270) {
                    y = y + speed;
                } else if (direction < 360) {
                    x = (float) (x + speed * Math.cos((direction - 270) * pi / 180));
                    y = (float) (y + speed * Math.sin((direction - 270) * pi / 180));
                } else if (direction == 360) {
                    x = x + speed;
                }
                Log.d("inin", String.valueOf(Math.cos(30 * pi / 180)));
                time = time + 10;
                Log.d("inin", String.valueOf(x));
                if (time > 2000) {
                    timer.cancel();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            removeBullet();
                        }
                    });


                }
                postInvalidate();
            }
        }, 0, 10);
    }


}
