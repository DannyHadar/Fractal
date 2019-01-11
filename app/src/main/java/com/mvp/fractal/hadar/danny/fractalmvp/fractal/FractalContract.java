package com.mvp.fractal.hadar.danny.fractalmvp.fractal;

import com.mvp.fractal.hadar.danny.fractalmvp.BasePresenter;

public interface FractalContract {

    interface View{
        void setFractal(FractalPainter fractalPainter);

        void updateDrawing();
    }

    interface Presenter extends BasePresenter<View> {
        void onDrawButtonClicked(boolean fillType, String shapeType, float rating, String colorString);
    }
}
