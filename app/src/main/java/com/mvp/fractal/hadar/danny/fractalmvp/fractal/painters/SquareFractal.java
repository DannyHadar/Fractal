package com.mvp.fractal.hadar.danny.fractalmvp.fractal.painters;

import android.graphics.Canvas;

public class SquareFractal extends FractalPainter {

    @Override
    public void drawFractal(Canvas c, int width, int height) {

        int left = width / 2 - width / 6;
        int top = height / 2 - width / 6;
        int right = width / 2 + width / 6;
        int bottom = height / 2 + width / 6;

        drawFractal(c, left, top, right, bottom, right - left);
    }

    private void drawFractal(Canvas c, int left, int top, int right, int bottom, int side) {
        int newRectSide = side / 2;

        if (drawingPermitted(newRectSide)) {
            drawSquare(c, left, top, right, bottom);
            // left square
            drawFractal(c, left - newRectSide, top, left, bottom - newRectSide, newRectSide);
            // top square
            drawFractal(c, left + newRectSide, top - newRectSide, right, top, newRectSide);
            // right square
            drawFractal(c, right, top + newRectSide, right + newRectSide, bottom, newRectSide);
            // bottom square
            drawFractal(c, left, bottom, right - newRectSide, bottom + newRectSide, newRectSide);
        }
    }

    private void drawSquare(Canvas c, int left, int top, int right, int bottom) {
        c.drawRect(left, top, right, bottom, mPaint);
        mShapesDrawnCounter++;
    }
}
