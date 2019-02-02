package com.mvp.fractal.hadar.danny.fractalmvp.fractal.painters;

import android.graphics.Canvas;

public class CircleFractal extends FractalPainter {
    @Override
    public void drawFractal(Canvas c, int width, int height) {
        drawFractal(c, width / 2, height / 2, width / 6);
    }

    private void drawFractal(Canvas c, int cX, int cY, int radius) {
        int newRadius = radius / 2;
        int radiusAndNewRadius = radius + newRadius;

        if (drawingPermitted(newRadius)) {
            drawCircle(c, cX, cY, radius);
            // left circle
            drawFractal(c, cX - radiusAndNewRadius, cY, newRadius);
            // top circle
            drawFractal(c, cX, cY - radiusAndNewRadius, newRadius);
            // right circle
            drawFractal(c, cX + radiusAndNewRadius, cY, newRadius);
            // bottom circle
            drawFractal(c, cX, cY + radiusAndNewRadius, newRadius);
        }
    }

    private void drawCircle(Canvas c, int cX, int cY, int radius) {
        c.drawCircle(cX, cY, radius, mPaint);
        mShapesDrawnCounter++;
    }
}
