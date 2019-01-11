package com.mvp.fractal.hadar.danny.fractalmvp;

public interface BasePresenter<V>{
    void takeView(V view);

    void dropView();
}
