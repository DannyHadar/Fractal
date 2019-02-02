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

        int[][] xValues = new int[3][3];
        int[][] yValues = new int[3][3];

        // X values for top shape
        xValues[0][0] = x[0] + newWidth / 2;
        xValues[0][1] = x[1] - newWidth / 2;
        xValues[0][2] = x[0] + newWidth;
        // Y values for top shape
        yValues[0][0] = y[0] - newHeight;
        yValues[0][1] = y[0] - newHeight;
        yValues[0][2] = y[0];
        // X values for right shape
        xValues[1][0] = xValues[0][1];
        xValues[1][1] = xValues[0][1] + newWidth;
        xValues[1][2] = x[1];
        // Y values for right shape
        yValues[1][0] = y[0] + newHeight;
        yValues[1][1] = y[0] + newHeight;
        yValues[1][2] = y[0] + height;
        // X values for left shape
        xValues[2][0] = x[0] - newWidth / 2;
        xValues[2][1] = x[0] + newWidth / 2;
        xValues[2][2] = x[0];
        // Y values for left shape
        yValues[2][0] = yValues[1][0];
        yValues[2][1] = yValues[1][1];
        yValues[2][2] = yValues[1][2];

        if (drawingPermitted((width) / 3)) {
            drawTriangle(c, x, y);
            mShapesDrawnCounter++;
            // left triangle
            drawFractal(c, xValues[2], yValues[2], newWidth, newHeight);
            // top triangle
            drawFractal(c, xValues[0], yValues[0], newWidth, newHeight);
            // right triangle
            drawFractal(c, xValues[1], yValues[1], newWidth, newHeight);
        }
    }

    /**
     * Because of android doesn't supply a method for drawing polygon
     * we have to draw 3 lines in order to show a triangle
     * taking to arrays of points x,y that match in their indices
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
