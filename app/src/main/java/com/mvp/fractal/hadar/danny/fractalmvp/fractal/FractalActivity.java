package com.mvp.fractal.hadar.danny.fractalmvp.fractal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;

import com.mvp.fractal.hadar.danny.fractalmvp.R;
import com.mvp.fractal.hadar.danny.fractalmvp.fractal.painters.FractalPainter;

public class FractalActivity extends AppCompatActivity implements FractalContract.View {

    // views
    private FractalView mFractalView;
    private Spinner mSpinnerShapeType;
    private Spinner mSpinnerColor;
    private Switch mSwitch;
    private RatingBar mResolutionBar;
    private FloatingActionButton mButtonDraw;
    // presenter
    private FractalPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fractal);

        setPresenter();
        findAllViewsById();
        setListeners();
        setAdapters();
    }

    private void setPresenter() {
        mPresenter = new FractalPresenter();
        mPresenter.takeView(this);
    }

    private void findAllViewsById() {
        mFractalView = findViewById(R.id.fractal_view);
        mSpinnerShapeType = findViewById(R.id.spinner_shape);
        mSwitch = findViewById(R.id.switch_fill_empty);
        mResolutionBar = findViewById(R.id.rating_bar_resolution);
        mButtonDraw = findViewById(R.id.fab_draw);
        mSpinnerColor = findViewById(R.id.spinner_color);
    }

    private void setListeners() {
        mButtonDraw.setOnClickListener(v -> {
            boolean fillType = mSwitch.isChecked();
            String shapeType = mSpinnerShapeType.getSelectedItem().toString();
            String color = mSpinnerColor.getSelectedItem().toString();
            float ratingResolution = mResolutionBar.getRating();

            mPresenter.onDrawButtonClicked(fillType, shapeType, ratingResolution, color);
        });
    }

    private void setAdapters() {
        mSpinnerShapeType.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.shapes_array)));

        mSpinnerColor.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.colors_array)));
    }

    @Override
    public void setFractal(FractalPainter fractalPainter) {
        mFractalView.setFractal(fractalPainter);
    }

    @Override
    public void updateDrawing() {
        mFractalView.invalidate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }
}
