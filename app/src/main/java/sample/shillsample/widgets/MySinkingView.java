
package sample.shillsample.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Region.Op;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import sample.shillsample.R;


public class MySinkingView extends FrameLayout {
    private static final int DEFAULT_TEXTCOLOT = 0xFFFFFFFF;

    private static final int DEFAULT_TEXTSIZE = 90;
    private static final int SMALL_TEXTSIZE = 28;

    private float mPercent;

    private Paint mPaint = new Paint();

    private Bitmap mBitmap;
    private Bitmap mBitmap1;

    private Bitmap mScaledBitmap;
    private Bitmap mScaledBitmap1;

    private float mLeft;

    private int mSpeed = 15;

    private int mRepeatCount = 0;

    private Status mFlag = Status.NONE;

    private int mTextColor = DEFAULT_TEXTCOLOT;

    private int mTextSize = DEFAULT_TEXTSIZE;

    private String mStatus = "正在删除";

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public MySinkingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setTextColor(int color) {
        mTextColor = color;
    }

    public void setTextSize(int size) {
        mTextSize = size;
    }

    public void setPercent(float percent) {
        mFlag = Status.RUNNING;
        mPercent = percent;
        postInvalidate();

    }

    public void setStatus(Status status) {
        mFlag = status;
    }

    public void clear() {
        mFlag = Status.NONE;
        if (mScaledBitmap != null) {
            mScaledBitmap.recycle();
            mScaledBitmap = null;
        }

        if (mScaledBitmap1 != null) {
            mScaledBitmap1.recycle();
            mScaledBitmap1 = null;
        }

        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }

        if (mBitmap1 != null) {
            mBitmap1.recycle();
            mBitmap1 = null;
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.FILTER_BITMAP_FLAG| Paint.ANTI_ALIAS_FLAG));

        int width = getWidth();
        int height = getHeight();
        
        //裁剪成圆区域
        Path path = new Path();
        canvas.save();
        path.reset();
        canvas.clipPath(path);
        path.addCircle(width / 2, height / 2, width / 2, Direction.CCW);
        canvas.clipPath(path, Op.REPLACE);

        if (mFlag == Status.RUNNING) {
            if (mScaledBitmap == null) {
                mBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.wave_above);
                mScaledBitmap = Bitmap.createScaledBitmap(mBitmap, mBitmap.getWidth(), getHeight(), false);
                mBitmap.recycle();
                mBitmap = null;
                mRepeatCount = (int) Math.ceil(getWidth() / mScaledBitmap.getWidth() + 0.5) + 1;
            }

            if (mScaledBitmap1 == null) {
                mBitmap1 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.wave_below);
                mScaledBitmap1 = Bitmap.createScaledBitmap(mBitmap1, mBitmap1.getWidth(), getHeight(), false);
                mBitmap1.recycle();
                mBitmap1 = null;
            }

            mPaint.setColor(0x99000000);

            if(mPercent >= 1){
                mPercent = 1;
            }
            for (int idx = 0; idx < mRepeatCount; idx++) {
                canvas.drawBitmap(mScaledBitmap, mLeft + (idx - 1) * mScaledBitmap.getWidth(), (1-mPercent) *getHeight(), null);
                canvas.drawBitmap(mScaledBitmap1, mLeft + (idx - 1) * mScaledBitmap1.getWidth(), (1-mPercent) *getHeight(), mPaint);
            }

            mLeft += mSpeed;
            if (mLeft >= mScaledBitmap.getWidth())
                mLeft = 0;
            postInvalidateDelayed(20);
        }

        if(mPercent >= 1){
            mFlag = Status.NONE;

            mBitmap = null;
            mBitmap1 = null;
            mScaledBitmap = null;
            mScaledBitmap1 = null;

            mPaint.setStyle(Style.FILL_AND_STROKE);
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.parseColor("#00b0ff"));
            canvas.drawCircle(width / 2, height / 2, width / 2-12 , mPaint);

        }else{
            mFlag = Status.RUNNING;
        }

        String str = (int) (mPercent * 100)+"";
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        mPaint.setStyle(Style.FILL);
        canvas.drawText(str, (getWidth() - mPaint.measureText(str)) / 2, getHeight() / 2 + mTextSize / 2, mPaint);

        mPaint.setTextSize(SMALL_TEXTSIZE);
        String strstatus = mStatus;
        canvas.drawText(strstatus, (getWidth() - mPaint.measureText(strstatus)) / 2, getHeight() / 2 + mTextSize / 2 + 90, mPaint);

        // 绘制外圆环
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeWidth(12);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#00b0ff"));
        canvas.drawCircle(width / 2, height / 2, width / 2 , mPaint);
        canvas.restore();
    }

    public enum Status {
        RUNNING, NONE
    }

}
