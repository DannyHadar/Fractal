package com.mvp.fractal.hadar.danny.fractalmvp.fractal.painters;

import android.graphics.Canvas;

public class CircleFractal extends FractalPainter {

    @Override
    public void drawFractal(Canvas c, int width, int height) {
        drawCircle(c, width / 2, height / 2, width / 6);
    }

    private void drawCircle(Canvas c, int cX, int cY, int radius) {
        int newRadius = radius / 2;
        int radiusAndNewRadius = radius + newRadius;

        if (drawAnotherShape(newRadius)) {
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
