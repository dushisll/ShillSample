package sample.shillsample.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


public class CircleProgress extends View {


    private int mPanelWidth;
    private int mPanelRadius;
    private float ratioPieceOfWidth = 1 * 1.0f;


    public CircleProgress(Context context) {
        this(context, null);

    }

    @SuppressLint("NewApi")
    public CircleProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int width = widthSize;

        setMeasuredDimension(width, width);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mPanelWidth = w;

        int Width = (int) (mPanelWidth * ratioPieceOfWidth);

        mPanelRadius = Width / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSmallBg(canvas);

        invalidate();
    }

    private RectF rectBg = null;
    private RectF rectBg2 = null;
    private RectF rectBg3 = null;
    private Paint mPaintCircle1;
    private Paint mPaintCircle2;
    private Paint mPaintCircle3;
    private Paint mPaintCircle4;
    private Paint mPaintCircle5;
    private Paint mPaintCircle6;
    private Paint mPaintCircle7;

    private void drawSmallBg(Canvas canvas) {

        rectBg = new RectF(mPanelWidth / 2 - mPanelRadius + 60,
                mPanelWidth / 2 - mPanelRadius + 60,
                mPanelWidth - ((mPanelWidth - mPanelRadius * 2) / 2 + 60),
                mPanelWidth - ((mPanelWidth - mPanelRadius * 2) / 2 + 60));

        rectBg2 = new RectF(mPanelWidth / 2 - mPanelRadius + 60 + 15,
                mPanelWidth / 2 - mPanelRadius + 60 + 15,
                mPanelWidth - ((mPanelWidth - mPanelRadius * 2) / 2 + 60 + 15),
                mPanelWidth - ((mPanelWidth - mPanelRadius * 2) / 2 + 60 + 15));

        rectBg3 = new RectF(mPanelWidth / 2 - mPanelRadius + 60 + 15 + 55,
                mPanelWidth / 2 - mPanelRadius + 60 + 15 + 55,
                mPanelWidth - ((mPanelWidth - mPanelRadius * 2) / 2 + 60 + 15 + 55),
                mPanelWidth - ((mPanelWidth - mPanelRadius * 2) / 2 + 60 + 15 + 55));

        int radius = ((mPanelWidth - ((mPanelWidth - mPanelRadius * 2) / 2 + 60 + 15 + 55)) - (mPanelWidth / 2 - mPanelRadius + 60 + 15 + 55)) / 2 - 60;

        int cx =  mPanelWidth/2;
        int cy = mPanelWidth/2;
//        int cx = radius + 190;
//        int cy = ((mPanelWidth - ((mPanelWidth - mPanelRadius * 2) / 2 + 60 + 15 + 55)) - (mPanelWidth / 2 - mPanelRadius + 60 + 15 + 55)) / 2 + 130;

        mPaintCircle1 = new Paint();
        mPaintCircle1.setAntiAlias(true);
        mPaintCircle1.setStyle(Style.STROKE);
        mPaintCircle1.setStrokeWidth(60);
        mPaintCircle1.setColor(0x7818ffff);

        mPaintCircle2 = new Paint();
        mPaintCircle2.setAntiAlias(true);
        mPaintCircle2.setStyle(Style.STROKE);
        mPaintCircle2.setStrokeWidth(30);
        mPaintCircle2.setColor(0x00000000);


        mPaintCircle3 = new Paint();
        mPaintCircle3.setAntiAlias(true);
        mPaintCircle3.setStyle(Style.STROKE);
        mPaintCircle3.setStrokeWidth(30);
        mPaintCircle3.setColor(0x7818ffff);

        mPaintCircle4 = new Paint();
        mPaintCircle4.setAntiAlias(true);
        mPaintCircle4.setStyle(Style.STROKE);
        mPaintCircle4.setStrokeWidth(60);
        mPaintCircle4.setColor(0xffffff00);

        mPaintCircle5 = new Paint();
        mPaintCircle5.setAntiAlias(true);
        mPaintCircle5.setStyle(Style.STROKE);
        mPaintCircle5.setStrokeWidth(30);
        mPaintCircle5.setColor(0xffffff00);


        mPaintCircle6 = new Paint();
        mPaintCircle6.setAntiAlias(true);
        mPaintCircle6.setStyle(Style.STROKE);
        mPaintCircle6.setStrokeWidth(20);
        mPaintCircle6.setColor(0xffff0000);

        mPaintCircle7 = new Paint();
        mPaintCircle7.setAntiAlias(true);
        mPaintCircle7.setColor(0xffff0000);

        for (int i = 0; i < 90; i++) {
            canvas.drawArc(rectBg, i * 4 - 90, 1, false, mPaintCircle1);
            canvas.drawArc(rectBg, i * 4 + 1 - 90, 3, false, mPaintCircle2);
        }

        for (int i = 0; i < 90; i++) {
            canvas.drawArc(rectBg2, i * 4 - 88, 1, false, mPaintCircle3);
            canvas.drawArc(rectBg2, i * 4 + 1 - 88, 3, false, mPaintCircle2);
        }

        //绘制画笔 4，5
        if (progress > 0) {

            for (int i = 0; i < progress / 4; i++) {
                canvas.drawArc(rectBg, i * 4 - 90, 1, false, mPaintCircle4);
                canvas.drawArc(rectBg, i * 4 + 1 - 90, 3, false, mPaintCircle2);
            }
            for (int i = 0; i < progress; i++) {
                int j = progress / 4;
                int part = progress % 4;
                if (part != 0) {
                    if (part <= 1) {
                        canvas.drawArc(rectBg, j * 4 - 90, part, false, mPaintCircle4);
                    } else {
                        canvas.drawArc(rectBg, j * 4 - 90, 1, false, mPaintCircle4);
                        canvas.drawArc(rectBg, j * 4 + 1 - 90, part - 1, false, mPaintCircle2);
                    }
                }
            }

            for (int i = 0; i < progress / 4; i++) {
                canvas.drawArc(rectBg2, i * 4 - 88, 1, false, mPaintCircle5);
                canvas.drawArc(rectBg2, i * 4 + 1 - 88, 3, false, mPaintCircle2);
            }
            for (int i = 0; i < progress; i++) {
                int j = progress / 4;
                int part = progress % 4;
                if (part != 0) {
                    if (part <= 1) {
                        canvas.drawArc(rectBg2, j * 4 - 88, part, false, mPaintCircle5);
                    } else {
                        canvas.drawArc(rectBg2, j * 4 - 88, 1, false, mPaintCircle5);
                        canvas.drawArc(rectBg2, j * 4 + 1 - 88, part - 1, false, mPaintCircle2);
                    }
                }
            }

            for (int i = 0; i <= progress / 60; i++) {
                if (i == 6) {
                    canvas.drawCircle(
                            cx + (float) (radius * Math.sin((i - 3) * 60 * Math.PI / 180)),
                            cy + (float) (radius * Math.cos((i - 3) * 60 * Math.PI / 180)),
                            20 * 1.2f, mPaintCircle7);
                } else if (i == 1) {
                    canvas.drawCircle(
                            cx + (float) (radius * Math.sin((i + 1) * 60 * Math.PI / 180)),
                            cy + (float) (radius * Math.cos((i + 1) * 60 * Math.PI / 180)),
                            20 * 1.2f, mPaintCircle7);
                } else if (i == 2) {
                    canvas.drawCircle(
                            cx + (float) (radius * Math.sin((i - 1) * 60 * Math.PI / 180)),
                            cy + (float) (radius * Math.cos((i - 1) * 60 * Math.PI / 180)),
                            20 * 1.2f, mPaintCircle7);
                } else if (i == 3) {
                    canvas.drawCircle(
                            cx + (float) (radius * Math.sin((i - 3) * 60 * Math.PI / 180)),
                            cy + (float) (radius * Math.cos((i - 3) * 60 * Math.PI / 180)),
                            20 * 1.2f, mPaintCircle7);
                } else if (i == 4) {
                    canvas.drawCircle(
                            cx + (float) (radius * Math.sin((i + 1) * 60 * Math.PI / 180)),
                            cy + (float) (radius * Math.cos((i + 1) * 60 * Math.PI / 180)),
                            20 * 1.2f, mPaintCircle7);
                } else if (i == 5) {
                    canvas.drawCircle(
                            cx + (float) (radius * Math.sin((i - 1) * 60 * Math.PI / 180)),
                            cy + (float) (radius * Math.cos((i - 1) * 60 * Math.PI / 180)),
                            20 * 1.2f, mPaintCircle7);
                }
            }
        }
    }

    private int progress = 360;
    private int angleOfMoveCircle = 180;
    private int startAngle = 180;
    private int endAngle = 360;

    public void addProgress(int pro) {
        progress = pro;
        angleOfMoveCircle = 180 + pro;

        if (progress > endAngle) {
            progress = 0;
            angleOfMoveCircle = startAngle;
        }
        invalidate();
    }

    public int getProgress() {
        return progress;
    }
}
