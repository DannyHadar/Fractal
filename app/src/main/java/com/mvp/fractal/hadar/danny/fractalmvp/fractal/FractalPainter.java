package com.mvp.fractal.hadar.danny.fractalmvp.fractal;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class FractalPainter {
    public static final int DEFAULT_STROKE_WIDTH = 4;

    protected final int mResolution;
    protected final Paint mPaint;
    /**
     * This stupid int holds the number of shapes drawn so far
     */
    protected int mShapesDrawnCounter;
    /**
     * This stupid int holds the current limit for shapes to be drawn
     */
    protected int mShapesDrawnLimit;

    public FractalPainter(int resolution, Paint.Style fill, int color) {
        mResolution = getValidResolution(resolution);
        mPaint = new Paint();
        mPaint.setStrokeWidth(DEFAULT_STROKE_WIDTH);
        mPaint.setStyle(fill);
        mPaint.setColor(color);
    }

    private int getValidResolution(int resolution) {
        return (resolution < FractalPresenter.MIN_RES) ? FractalPresenter.MIN_RES : resolution;
    }

    public int getResolution() {
        return mResolution;
    }

    public int getShapesDrawnCounter() {
        return mShapesDrawnCounter;
    }

    public int getShapesDrawnLimit() {
        return mShapesDrawnLimit;
    }

    public void setShapesDrawnCounter(int value) {
        mShapesDrawnCounter = value;
    }

    public void setShapesDrawnLimit(int value) {
        mShapesDrawnLimit = value;
    }

    protected abstract void drawFractal(Canvas c, int width, int height);

}
