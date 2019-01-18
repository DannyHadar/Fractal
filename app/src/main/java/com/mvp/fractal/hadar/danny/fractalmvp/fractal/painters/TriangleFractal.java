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

    private void drawFractal(Canvas c, int[] x, int[] y, int width, int height) {
        int newWidth = width / 2;
        int newHeight = height / 2;

        int[][] dX = new int[3][3];
        int[][] dY = new int[3][3];

        // X values for top shape
        dX[0][0] = x[0] + newWidth / 2;
        dX[0][1] = x[1] - newWidth / 2;
        dX[0][2] = x[0] + newWidth;
        // Y values for top shape
        dY[0][0] = y[0] - newHeight;
        dY[0][1] = y[0] - newHeight;
        dY[0][2] = y[0];
        // X values for right shape
        dX[1][0] = dX[0][1];
        dX[1][1] = dX[0][1] + newWidth;
        dX[1][2] = x[1];
        // Y values for right shape
        dY[1][0] = y[0] + newHeight;
        dY[1][1] = y[0] + newHeight;
        dY[1][2] = y[0] + height;
        // X values for left shape
        dX[2][0] = x[0] - newWidth / 2;
        dX[2][1] = x[0] + newWidth / 2;
        dX[2][2] = x[0];
        // Y values for left shape
        dY[2][0] = dY[1][0];
        dY[2][1] = dY[1][1];
        dY[2][2] = dY[1][2];

        if (drawAnotherShape((newWidth * 2) / 3)) {
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

    /**
     * Because of android doesn't supply a method for drawing polygon
     * we have to draw 3 lines in order to show a triangle
     * taking to arrays of points x and y matching in indices
     * to create 3 points and drawing 3 lines between them
     *
     * @param c - For drawing
     * @param x - The x values of the lines.
     * @param y - The y values of the lines.
     */
    private void drawTriangle(Canvas c, int[] x, int[] y) {
        c.drawLine(x[0], y[0], x[1], y[1], mPaint);
        c.drawLine(x[2], y[2], x[1], y[1], mPaint);
        c.drawLine(x[0], y[0], x[2], y[2], mPaint);
    }
}
