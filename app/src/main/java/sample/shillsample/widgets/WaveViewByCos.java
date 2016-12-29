package sample.shillsample.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

import sample.shillsample.R;

/**
 * Created by we on 2016/12/20.
 */

public class WaveViewByCos extends View {

    private float aboveOffset = 2.0f;
    private int aboveWaveColor;
    private Paint aboveWavePaint = new Paint();
    private Path aboveWavePath = new Path();

    private float animOffset = 0.15f;

    private float blowOffset = 4.0f;
    private int belowWaveColor;
    private Paint belowWavePaint = new Paint();
    private Path belowWavePath = new Path();

    private int[] colors;

    private Context context;
    private int default_above_wave_alpha = 50;
    private int default_above_wave_color = Color.RED;
    private int default_below_wave_alpha = 30;
    private int default_below_wave_color = Color.RED;
    private int default_innerCircleColor = Color.WHITE;
    private int default_outerCircleColor = Color.RED;
    private int default_progress = 50;
    private int default_ringColor = Color.RED;

    private int innerCircleColor;
    private int outerCircleColor;
    private float max_right = 75.0f;
    private float offset = 0.5f;
    private int offsetIndex = 0;

    private RefreshProgressRunnable mRefreshProgressRunnable;

    private Paint paint;
    private int progress;
    private int ringColor;
    private int waveToTop;
    private int x_zoom = 150;
    private int y_zoom = 6;

    public WaveViewByCos(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public WaveViewByCos(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.paint = new Paint();
        TypedArray localTypedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WaveViewByCos, defStyleAttr, 0);
        this.aboveWaveColor = localTypedArray.getColor(R.styleable.WaveViewByCos_abovewavecolor
                , default_above_wave_color);
        this.belowWaveColor = localTypedArray.getColor(R.styleable.WaveViewByCos_belowwavecolor
                , default_below_wave_color);
        this.outerCircleColor = localTypedArray.getColor(R.styleable.WaveViewByCos_outercirclecolor
                , default_outerCircleColor);
        this.ringColor = localTypedArray.getColor(R.styleable.WaveViewByCos_ringcolor
                , default_ringColor);
        this.innerCircleColor = localTypedArray.getColor(R.styleable.WaveViewByCos_innercirclecolor
                , default_innerCircleColor);

        this.progress = localTypedArray.getInt(R.styleable.WaveViewByCos_progress
                , default_progress);

        setProgress(this.progress);

        initializePainters();

        localTypedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = getWidth() / 2;
        int j = getHeight() / 2;
        int k = getWidth() / 3;

        int m = k - 26;
        int n = k - 37;

        this.paint.setColor(this.outerCircleColor);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(1.0f);
        this.paint.setAntiAlias(true);
        canvas.drawCircle(i, j, k, this.paint);

        this.paint.setColor(this.ringColor);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(22);
        this.paint.setAntiAlias(true);
        canvas.drawCircle(i, j, m, this.paint);

        this.paint.setColor(this.innerCircleColor);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setAntiAlias(true);
        canvas.drawCircle(i, j, n, this.paint);

        canvas.drawPath(this.belowWavePath, this.belowWavePaint);
        canvas.drawPath(this.aboveWavePath, this.aboveWavePaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int width = widthSize;

        setMeasuredDimension(width, width);
    }


    private void calculatePath() {
        this.aboveWavePath.reset();
        this.belowWavePath.reset();

        getWaveOffset();

        int i = getWidth() / 2;
        int j = getHeight() / 2;
        int k = -26 + getWidth() / 3;
        int m = getHeight() / 2 - k;

        double d1 = Math.asin(Math.abs(this.waveToTop - k - m) / k);
        double d2 = Math.cos(d1) * k;
        double d3;
        double d4;
        float f1;
        float f2;
        if (this.waveToTop <= j) {
            d3 = -(180 * d1 / Math.PI);
            d4 = 180 + 2 * (180 * d1 / Math.PI);
            this.aboveWavePath.moveTo((float) (i - d2), this.waveToTop);
            this.belowWavePath.moveTo((float) (i - d2), this.waveToTop);

            RectF localRectF1 = new RectF(i - k, m, i + k, getHeight() - m);
            this.aboveWavePath.arcTo(localRectF1, (float) d3, (float) d4);
            this.belowWavePath.arcTo(localRectF1, (float) d3, (float) d4);

            f1 = 0.0f;
//            while(true){
            for (int s = 0; s < 50; s+=5) {
                if (x_zoom * f1 + (i - d2) < d2 + i) {
                    f1 += offset;
                } else {
                    f1 = 0.0f;
                }
                this.aboveWavePath.lineTo((float) (x_zoom * f1 + i - d2)
                        , (float) (y_zoom * Math.cos(f1 + this.aboveOffset)) + this.waveToTop);

                this.belowWavePath.lineTo((float) (x_zoom * f1 + i - d2)
                        , (float) (y_zoom * Math.cos(f1 + this.blowOffset)) + this.waveToTop);
            }
//            }



        } else {
            d3 = 180 * d1 / Math.PI;
            d4 = 180 - 2.0 * d3;

            this.aboveWavePath.moveTo((float) (i - d2), this.waveToTop);
            this.belowWavePath.moveTo((float) (i - d2), this.waveToTop);

            RectF localRectF2 = new RectF(i - k, m, i + k, getHeight() - m);
            this.aboveWavePath.arcTo(localRectF2, (float) d3, (float) d4);
            this.belowWavePath.arcTo(localRectF2, (float) d3, (float) d4);

            f2 = 0.0f;
            for (int s = 0; s < 50; s+=5) {
                if (x_zoom * f2 + (i - d2) < d2 + i) {
                    f2 += offset;
                } else {
                    f2 = 0.0f;
                }
                this.aboveWavePath.lineTo((float) (x_zoom * f2 + i - d2)
                        , (float) (y_zoom * Math.cos(f2 + this.aboveOffset)) + this.waveToTop);

                this.belowWavePath.lineTo((float) (x_zoom * f2 + i - d2)
                        , (float) (y_zoom * Math.cos(f2 + this.blowOffset)) + this.waveToTop);
            }

        }

    }

    private void getWaveOffset() {
        if (blowOffset > Float.MAX_VALUE - 100) {
            blowOffset = 0;
        } else {
            blowOffset += animOffset;
        }

        if (aboveOffset > Float.MAX_VALUE - 100) {
            aboveOffset = 0;
        } else {
            aboveOffset += animOffset;
        }
    }

    public int getAboveWaveColor() {
        return aboveWaveColor;
    }

    public void setAboveWaveColor(int aboveWaveColor) {
        this.aboveWaveColor = aboveWaveColor;
    }

    public int getBelowWaveColor() {
        return belowWaveColor;
    }

    public void setBelowWaveColor(int belowWaveColor) {
        this.belowWaveColor = belowWaveColor;
    }

    public int getInnerCircleColor() {
        return innerCircleColor;
    }

    public void setInnerCircleColor(int innerCircleColor) {
        this.innerCircleColor = innerCircleColor;
    }

    public int getOuterCircleColor() {
        return outerCircleColor;
    }

    public void setOuterCircleColor(int outerCircleColor) {
        this.outerCircleColor = outerCircleColor;
    }

    public int getRingColor() {
        return ringColor;
    }

    public void setRingColor(int ringColor) {
        this.ringColor = ringColor;
    }

    public void setProgress(int progress) {
        if (progress > 100) {
            progress = 100;
        }
        this.progress = progress;
    }


    private void initializePainters() {
        this.aboveWavePaint.setColor(this.aboveWaveColor);
        this.aboveWavePaint.setAlpha(default_above_wave_alpha);
        this.aboveWavePaint.setStyle(Paint.Style.FILL);
        this.aboveWavePaint.setAntiAlias(true);

        this.belowWavePaint.setColor(this.belowWaveColor);
        this.belowWavePaint.setAlpha(default_below_wave_alpha);
        this.belowWavePaint.setStyle(Paint.Style.FILL);
        this.belowWavePaint.setAntiAlias(true);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mRefreshProgressRunnable = new RefreshProgressRunnable();
        post(this.mRefreshProgressRunnable);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mRefreshProgressRunnable);
    }


    private class RefreshProgressRunnable implements Runnable {
        private RefreshProgressRunnable() {
        }

        @Override
        public void run() {
            synchronized (WaveViewByCos.this) {
                long start = System.currentTimeMillis();

                int i = -26 + WaveViewByCos.this.getWidth() / 3;
                int j = WaveViewByCos.this.getHeight() / 2 - i;

                WaveViewByCos.this.waveToTop = (int) ((WaveViewByCos.this.getHeight() - j * 2) *
                        (1.0f - WaveViewByCos.this.progress / 100.0f) + j);
                WaveViewByCos.this.calculatePath();
                WaveViewByCos.this.invalidate();

                long gap = 16 - (System.currentTimeMillis() - start);
                WaveViewByCos.this.postDelayed(this, gap < 0 ? 0 : gap);
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState localSavedState = (SavedState) state;
        super.onRestoreInstanceState(localSavedState.getSuperState());
        setProgress(localSavedState.progress);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        SavedState localSavedState = new SavedState(super.onSaveInstanceState());
        localSavedState.progress = this.progress;
        return localSavedState;
    }

    private static class SavedState extends View.BaseSavedState {
        private static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            @Override
            public WaveViewByCos.SavedState createFromParcel(Parcel parcel) {
                return new WaveViewByCos.SavedState(parcel);
            }

            @Override
            public WaveViewByCos.SavedState[] newArray(int i) {
                return new WaveViewByCos.SavedState[i];
            }
        };

        int progress;

        public SavedState(Parcel source) {
            super(source);
            this.progress = source.readInt();
        }

        SavedState(Parcelable source) {
            super(source);
        }

        public void writeToParcel(Parcel parcel, int flags) {
            super.writeToParcel(parcel, flags);
            parcel.writeInt(this.progress);
        }
    }
}
