package com.mvp.fractal.hadar.danny.fractalmvp.fractal.painters;

import android.graphics.Canvas;

public class TriangleFractal extends FractalPainter {

    @Override
    public void drawFractal(Canvas c, int width, int height) {
        height -= (height / 6);
        int[] x = {0, width / 2, width};
        int[] y = {height, 0, height };

        drawTriangle(c, x, y);

        width /= 2;
        height /= 2;
        
        x[0] = x[0] + width / 2;
        x[1] = x[2] - width / 2;
        x[2] = x[2] - width;

        y[2] = y[0];
        y[0] = y[0] - height;
        y[1] = y[0];

        drawFractal(c, x, y, width, height);
    }

    private void drawTriangle(Canvas c, int[] x, int[] y) {
        c.drawLine(x[0], y[0], x[1], y[1], mPaint);
        c.drawLine(x[2], y[2], x[1], y[1], mPaint);
        c.drawLine(x[0], y[0], x[2], y[2], mPaint);
    }

    private void drawFractal(Canvas c, int[] x, int[] y, int width, int height) {
        int newWidth = width / 2;
        int newHeight = height / 2;

        int[][] dX = new int[3][3];
        int[][] dY = new int[3][3];

        dX[0][0] = x[0] + newWidth / 2;
        dX[0][1] = x[1] - newWidth / 2;
        dX[0][2] = x[0] + newWidth;

        dY[0][0] = y[0] - newHeight;
        dY[0][1] = y[0] - newHeight;
        dY[0][2] = y[0];

        dX[1][0] = dX[0][1];
        dX[1][1] = dX[0][1] + newWidth;
        dX[1][2] = x[1];

        dY[1][0] = y[0] + newHeight;
        dY[1][1] = y[0] + newHeight;
        dY[1][2] = y[0] + height;

        dX[2][0] = x[0] - newWidth / 2;
        dX[2][1] = x[0] + newWidth / 2;
        dX[2][2] = x[0];

        dY[2][0] = dY[1][0];
        dY[2][1] = dY[1][1];
        dY[2][2] = dY[1][2];

        if (mShapesDrawnCounter < mShapesDrawnLimit && (newWidth * 2) / 3 > mResolution) {
            drawTriangle(c, x, y);
            mShapesDrawnCounter++;
            // left triangle
            drawFractal(c, dX[2], dY[2], newWidth, newHeight);
            // top triangle
            drawFractal(c, dX[0], dY[0], newWidth, newHeight);
            // right triangle
            drawFractal(c, dX[1], dY[1], newWidth, newHeight);
        }
    }
}
