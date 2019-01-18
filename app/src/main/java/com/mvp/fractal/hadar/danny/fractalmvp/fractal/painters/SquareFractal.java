package com.mvp.fractal.hadar.danny.fractalmvp.fractal.painters;

import android.graphics.Canvas;

public class SquareFractal extends FractalPainter {

    @Override
    public void drawFractal(Canvas c, int width, int height) {

        int left = width / 2 - width / 6;
        int top = height / 2 - width / 6;
        int right = width / 2 + width / 6;
        int bottom = height / 2 + width / 6;

        drawSquare(c, left, top, right, bottom, right - left);
    }

    private void drawSquare(Canvas c, int left, int top, int right, int bottom, int side) {
        int newRectSide = side / 2;

        if (mShapesDrawnCounter < mShapesDrawnLimit && newRectSide > mResolution) {
            c.drawRect(left, top, right, bottom, mPaint);
            mShapesDrawnCounter++;
            // left square
            drawSquare(c, left - newRectSide, top, left, bottom - newRectSide, newRectSide);
            // top square
            drawSquare(c, left + newRectSide, top - newRectSide, right, top, newRectSide);
            // right square
            drawSquare(c, right, top + newRectSide, right + newRectSide, bottom, newRectSide);
            // bottom square
            drawSquare(c, left, bottom, right - newRectSide, bottom + newRectSide, newRectSide);
        }
    }
}
