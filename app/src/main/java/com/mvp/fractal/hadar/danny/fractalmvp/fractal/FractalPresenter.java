package com.mvp.fractal.hadar.danny.fractalmvp.fractal;

import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.Log;

import com.mvp.fractal.hadar.danny.fractalmvp.AbsPresenter;
import com.mvp.fractal.hadar.danny.fractalmvp.fractal.painters.FractalPainter;
import com.mvp.fractal.hadar.danny.fractalmvp.fractal.painters.FractalPainterFactory;
import com.mvp.fractal.hadar.danny.fractalmvp.fractal.utils.FractalAppearanceUtils;

public class FractalPresenter extends AbsPresenter<FractalContract.View> implements FractalContract.Presenter {
    private FractalPainter mFractal;

    private volatile boolean mKeepDrawing;

    private DrawingTask mTask;

    @Override
    public void takeView(FractalContract.View view) {
        attach(view);
    }

    @Override
    public void dropView() {
        if (mTask != null) {
            mTask.cancel(true);
        }
        detach();
    }

    @Override
    public void onDrawButtonClicked(boolean fillType, String shapeType, float resRating, String colorString) {
        mKeepDrawing = false;

        int resolution = FractalAppearanceUtils.getResolution(resRating);
        Paint.Style fill = FractalAppearanceUtils.getFillStyle(fillType);
        int color = FractalAppearanceUtils.getColor(colorString);

        mFractal = FractalPainterFactory.getFractal(shapeType, resolution, fill, color);

        mView.setFractal(mFractal);

        startDrawing();
    }

    private void startDrawing() {
        mTask = new DrawingTask();
        mTask.execute();
    }

    private void addShape() {
        mFractal.setShapesDrawnCounter(0);
        mFractal.incrementShapesDrawnLimit();
        mView.updateDrawing();
    }

    private class DrawingTask extends AsyncTask<Void, Void, Void> {
        private static final int SLEEP_BETWEEN_DRAWING = 66;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mFractal.setShapesDrawnLimit(0);
            mKeepDrawing = true;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int tempCounter = 0; mKeepDrawing && !isCancelled(); tempCounter = mFractal.getShapesDrawnCounter()) {
                    publishProgress();
                    Thread.sleep(SLEEP_BETWEEN_DRAWING);
                    // If those two are the same then there aren't more available shapes to draw
                    mKeepDrawing = tempCounter != mFractal.getShapesDrawnCounter();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

            addShape();
        }
    }
}
