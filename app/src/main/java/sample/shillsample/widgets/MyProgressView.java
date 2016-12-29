package sample.shillsample.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by we on 2016/9/3.
 */
public class MyProgressView extends View {
    private int width = 200;
    private int height = 200;

    private Paint circlePaint;
    private Paint progressPaint;
    private Paint textPaint;

    private Bitmap bitmap;
    private Canvas bitmapCanvas;

    private Path path = new Path();
    private int progress = 50;
    private int maxprogress = 100;
    private int currentprogress = 0;

    private int num = 50;

    private boolean isSingleTap = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    public MyProgressView(Context context) {
        this(context, null);
    }

    public MyProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MyProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.argb(0xff, 0x3a, 0x8c, 0x6c));

        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setColor(Color.argb(0xff, 0x4e, 0xc9, 0x63));
        progressPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(25);

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bitmapCanvas.drawCircle(width / 2, height / 2, width / 2, circlePaint);
        path.reset();
        float y = (1 - (float) currentprogress / maxprogress) * height;
        path.moveTo(width, y);
        path.lineTo(width, height);
        path.lineTo(count, height);
        path.lineTo(count, y);
        if(!isSingleTap){
            float d = (1 - ((float) currentprogress / progress)) * 10;
            for (int i = 0; i < 5; i++) {
                path.rQuadTo(10, -d, 20, 0);
                path.rQuadTo(10, d, 20, 0);
            }
        }else{
            float d = ((float) num / 50) * 10;
            if(num % 2 == 0){
                for (int i = 0; i < 5; i++) {
                    path.rQuadTo(20, -d, 40, 0);
                    path.rQuadTo(20, d, 40, 0);
                }
            }else{
                for (int i = 0; i < 5; i++) {
                    path.rQuadTo(20, d, 40, 0);
                    path.rQuadTo(20, -d, 40, 0);
                }
            }
        }


        path.close();
        bitmapCanvas.drawPath(path, progressPaint);
        String text = (int) (((float) currentprogress / maxprogress) * 100) + "%";

        float textWidth = textPaint.measureText(text);
        Paint.FontMetrics metric = textPaint.getFontMetrics();
        float baseline = height / 2 - (metric.ascent + metric.descent) / 2;
        bitmapCanvas.drawText(text, width / 2 - textWidth / 2, baseline, textPaint);
        canvas.drawBitmap(bitmap, 0, 0, null);
        final GestureDetector detector = new GestureDetector(new MyGestureDetectorListener());
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return detector.onTouchEvent(event);
            }
        });
        setClickable(true);

    }

    class MyGestureDetectorListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Toast.makeText(getContext(), "双击", Toast.LENGTH_SHORT).show();
            isSingleTap = false;
            startDoubleTapAnimation();
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            isSingleTap = true;
            currentprogress = 50;
            Toast.makeText(getContext(), "单击", Toast.LENGTH_SHORT).show();
            startSingleTapAnimation();
            return super.onSingleTapConfirmed(e);
        }
    }

    private void startSingleTapAnimation() {
        handler.postDelayed(singleTapRunnable, 200);
    }

    private SingleTapRunnable singleTapRunnable = new SingleTapRunnable();

    class SingleTapRunnable implements Runnable {
        @Override
        public void run() {
            num--;
            if (num >= 0) {
                invalidate();
                handler.postDelayed(singleTapRunnable,200);
            }else{
                handler.removeCallbacks(singleTapRunnable);
                num = 50;
            }
        }
    }

    private void startDoubleTapAnimation() {
        handler.postDelayed(doubleTapRunnable, 50);
    }


    private DoubleTapRunnable doubleTapRunnable = new DoubleTapRunnable();
    private int count = -50;

    class DoubleTapRunnable implements Runnable {
        @Override
        public void run() {
            currentprogress++;

            if (currentprogress <= progress) {
                count += 10;
                if (count > 0) {
                    count = -50;
                }
                invalidate();
                handler.postDelayed(doubleTapRunnable, 50);
            } else {
                handler.removeCallbacks(doubleTapRunnable);
                currentprogress = 0;
            }
        }
    }
}
