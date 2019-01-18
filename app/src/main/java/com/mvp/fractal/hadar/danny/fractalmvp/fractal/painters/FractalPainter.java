package com.mvp.fractal.hadar.danny.fractalmvp.fractal.painters;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.mvp.fractal.hadar.danny.fractalmvp.fractal.utils.FractalAppearanceUtils;

public abstract class FractalPainter {
    private static final int DEFAULT_STROKE_WIDTH = 4;

    private int mResolution;
    final Paint mPaint;
    /**
     * This stupid int holds the number of shapes drawn so far
     */
    int mShapesDrawnCounter;
    /**
     * This stupid int holds the current limit for shapes to be drawn
     */
    private int mShapesDrawnLimit;

    FractalPainter() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(DEFAULT_STROKE_WIDTH);

    }

    void setResolution(int resolution) {
        mResolution = getValidResolution(resolution);
    }

    void setFill(Paint.Style fill) {
        mPaint.setStyle(fill);
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }

    private int getValidResolution(int resolution) {
        return (resolution < FractalAppearanceUtils.RESOLUTION_MAX) ? FractalAppearanceUtils.RESOLUTION_MAX : resolution;
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

    public abstract void drawFractal(Canvas c, int width, int height);

    boolean drawAnotherShape(int length) {
        return mShapesDrawnCounter < mShapesDrawnLimit && length > mResolution;
    }
}
