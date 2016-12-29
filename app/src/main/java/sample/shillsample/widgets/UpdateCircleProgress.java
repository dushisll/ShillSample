package sample.shillsample.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by we on 2016/5/30.
 */
public class UpdateCircleProgress extends View {

    private int mPanelWidth;
    private int mPanelRadius;
    private float ratioPieceOfWidth = 1 * 1.0f;

    public UpdateCircleProgress(Context context) {
        this(context, null);
    }

    public UpdateCircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public UpdateCircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

    private Paint mPaintCircle1;
    private Paint mPaintCircle2;

    private void drawSmallBg(Canvas canvas) {

        rectBg = new RectF(mPanelWidth / 2 - mPanelRadius + 3,
                mPanelWidth / 2 - mPanelRadius + 3,
                mPanelWidth - ((mPanelWidth - mPanelRadius * 2) / 2 + 3),
                mPanelWidth - ((mPanelWidth - mPanelRadius * 2) / 2 + 3));

        rectBg2 = new RectF(mPanelWidth / 2 - mPanelRadius + 3,
                mPanelWidth / 2 - mPanelRadius + 3,
                mPanelWidth - ((mPanelWidth - mPanelRadius * 2) / 2 + 3),
                mPanelWidth - ((mPanelWidth - mPanelRadius * 2) / 2 + 3));

        int cx = mPanelWidth / 2;
        int cy = mPanelWidth / 2;

        mPaintCircle1 = new Paint();
        mPaintCircle1.setAntiAlias(true);
        mPaintCircle1.setStyle(Paint.Style.STROKE);
        mPaintCircle1.setStrokeWidth(5);
        mPaintCircle1.setColor(getResources().getColor(android.R.color.holo_green_light));

        mPaintCircle2 = new Paint();
        mPaintCircle2.setAntiAlias(true);

        mPaintCircle2.setStyle(Paint.Style.STROKE);

        mPaintCircle2.setStrokeWidth(5);
        mPaintCircle2.setColor(getResources().getColor(android.R.color.holo_red_light));

        canvas.drawArc(rectBg, 0, 360, false, mPaintCircle1);

        if (progress > 0) {

            canvas.drawArc(rectBg2, -90, progress, false, mPaintCircle2);
        }
    }

    private int progress = 0;
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
