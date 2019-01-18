package com.mvp.fractal.hadar.danny.fractalmvp.fractal;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;

import com.mvp.fractal.hadar.danny.fractalmvp.AbsPresenter;
import com.mvp.fractal.hadar.danny.fractalmvp.fractal.painters.FractalPainter;
import com.mvp.fractal.hadar.danny.fractalmvp.fractal.painters.FractalPainterFactory;

import java.util.HashMap;

public class FractalPresenter extends AbsPresenter<FractalContract.View> implements FractalContract.Presenter {
    public static final int MIN_RES = 3;

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

        int resolution = ResolutionCache.getResolution(resRating);
        Paint.Style fill = getFillStyle(fillType);
        int color = getColor(colorString);

        mFractal = FractalPainterFactory.getFractal(shapeType, resolution, fill, color);

        mView.setFractal(mFractal);

        startDrawing();
    }

    private int getColor(String colorString) {
        switch (colorString) {
            case "Blue":
                return Color.BLUE;
            case "Green":
                return Color.GREEN;
            case "Yellow":
                return Color.YELLOW;
            case "Red":
                return Color.RED;
            default:
            case "Black":
                return Color.BLACK;
        }
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

    private Paint.Style getFillStyle(boolean fillType) {
        return fillType ? Paint.Style.FILL : Paint.Style.STROKE;
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

    private static class ResolutionCache {
        /**
         * This stupid cache stores as a Key the resolution and as a value the
         * actual resolution that will be set while painting
         */
        private static HashMap<Float, Integer> CACHE;

        static {
            CACHE = new HashMap<>();

            CACHE.put(0f, 24);
            CACHE.put(1f, 16);
            CACHE.put(2f, 8);
            CACHE.put(3f, FractalPresenter.MIN_RES);

        }

        private static int getResolution(float key) {
            return CACHE.get(key);
        }
    }

}
