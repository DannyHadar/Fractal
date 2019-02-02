package com.mvp.fractal.hadar.danny.fractalmvp.fractal;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mvp.fractal.hadar.danny.fractalmvp.fractal.painters.FractalPainter;

public class FractalView extends View {
    private FractalPainter mFractal;

    public FractalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setFractal(FractalPainter fractal) {
        mFractal = fractal;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mFractal != null) {
            mFractal.drawFractal(canvas, getWidth(), getHeight());
        }
    }
}
