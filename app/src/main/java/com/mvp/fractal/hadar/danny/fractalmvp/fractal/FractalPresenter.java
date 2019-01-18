package com.mvp.fractal.hadar.danny.fractalmvp.fractal;

import android.graphics.Paint;
import android.os.AsyncTask;

import com.mvp.fractal.hadar.danny.fractalmvp.AbsPresenter;
import com.mvp.fractal.hadar.danny.fractalmvp.fractal.painters.FractalPainter;
import com.mvp.fractal.hadar.danny.fractalmvp.fractal.painters.FractalPainterFactory;
import com.mvp.fractal.hadar.danny.fractalmvp.fractal.utils.FractalAppearanceUtils;

public class FractalPresenter extends AbsPresenter<FractalContract.View> implements FractalContract.Presenter {
    private FractalPainter mFractal;

    private volatile boolean mKeepDrawing;

    @Override
    public void takeView(FractalContract.View view) {
        attach(view);
    }

    @Override
    public void dropView() {
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
        DrawingTask task = new DrawingTask();
        task.execute();
    }

    private void addShape() {
        mFractal.setShapesDrawnCounter(0);
        mFractal.setShapesDrawnLimit(mFractal.getShapesDrawnLimit() + 1);

        mView.updateDrawing();

        mKeepDrawing = mFractal.getShapesDrawnCounter() <= mFractal.getShapesDrawnLimit();
    }

    private class DrawingTask extends AsyncTask<Void, Void, Void> {
        private static final int SLEEP_BETWEEN_DRAWING = 50;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mFractal.setShapesDrawnLimit(0);
            mKeepDrawing = true;
        }

        @Override
        protected Void doInBackground(Void... params) {
            while (mKeepDrawing) {
                try {
                    Thread.sleep(SLEEP_BETWEEN_DRAWING);
                    publishProgress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
