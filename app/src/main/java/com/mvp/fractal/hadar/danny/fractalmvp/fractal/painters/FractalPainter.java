package com.mvp.fractal.hadar.danny.fractalmvp.fractal.painters;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.mvp.fractal.hadar.danny.fractalmvp.fractal.utils.FractalAppearanceUtils;

public abstract class FractalPainter {
    private static final int DEFAULT_STROKE_WIDTH = 4;

    int mResolution;

    final Paint mPaint;
    /**
     * This stupid int holds the number of shapes drawn so far
     */
    int mShapesDrawnCounter;
    /**
     * This stupid int holds the current limit for shapes to be drawn
     */
    int mShapesDrawnLimit;

    FractalPainter() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(DEFAULT_STROKE_WIDTH);

    }

    public abstract void drawFractal(Canvas c, int width, int height);

    void setResolution(int resolution) {
        mResolution = getValidResolution(resolution);
    }

    void setFill(Paint.Style fill) {
        mPaint.setStyle(fill);
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }

    public int getShapesDrawnCounter() {
        return mShapesDrawnCounter;
    }

    public int getShapesDrawnLimit() {
        return mShapesDrawnLimit;
    }

    public void setShapesDrawnCounter(int counter) {
        mShapesDrawnCounter = counter;
    }

    boolean drawingPermitted(int length) {
        return mShapesDrawnCounter < mShapesDrawnLimit && length > mResolution;
    }

    public void setShapesDrawnLimit(int limit) {
        mShapesDrawnLimit = limit;
    }

    public void incrementShapesDrawnLimit() {
        mShapesDrawnLimit++;
    }

    private int getValidResolution(int resolution) {
        return (resolution < FractalAppearanceUtils.RESOLUTION_MAX) ? FractalAppearanceUtils.RESOLUTION_MAX : resolution;
    }
}
