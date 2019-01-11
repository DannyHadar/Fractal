package com.mvp.fractal.hadar.danny.fractalmvp.fractal;

import android.graphics.Canvas;
import android.graphics.Paint;

public class CircleFractal extends FractalPainter {

    public CircleFractal(int resolution, Paint.Style fill, int color) {
        super(resolution, fill, color);
    }

    @Override
    protected void drawFractal(Canvas c, int width, int height) {
        drawCircle(c, width / 2, height / 2, width / 6);
    }

    private void drawCircle(Canvas c, int cX, int cY, int radius) {
        int newRadius = radius / 2;
        int radiusAndNewRadius = radius + newRadius;

        if (mShapesDrawnCounter < mShapesDrawnLimit && newRadius > mResolution) {
            c.drawCircle(cX, cY, radius, mPaint);
            mShapesDrawnCounter++;
            // left circle
            drawCircle(c, cX - radiusAndNewRadius, cY, newRadius);
            // top circle
            drawCircle(c, cX, cY - radiusAndNewRadius, newRadius);
            // right circle
            drawCircle(c, cX + radiusAndNewRadius, cY, newRadius);
            // bottom circle
            drawCircle(c, cX, cY + radiusAndNewRadius, newRadius);
        }
    }
}
